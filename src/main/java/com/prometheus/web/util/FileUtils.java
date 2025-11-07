package com.prometheus.web.util;

import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * Utilidad para manejar la carga y eliminación de archivos
 * 
 * Esta clase se encarga de:
 * - Validar tipos de archivo permitidos
 * - Validar tamaño máximo
 * - Guardar archivos con nombres únicos
 * - Eliminar archivos del sistema
 */
public class FileUtils {
    // Directorio donde se guardarán los contratos
    private static final String UPLOAD_DIR = "uploads" + File.separator + "contratos";
    
    // Tamaño máximo: 5MB (5 * 1024 * 1024 bytes)
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    
    // Extensiones de archivo permitidas
    private static final String[] ALLOWED_EXTENSIONS = {".pdf", ".docx", ".jpg", ".png", ".jpeg"};

    /**
     * Guarda un archivo en el sistema de archivos
     * 
     * @param filePart - La parte del request que contiene el archivo
     * @param servletContextPath - La ruta del contexto del servlet (donde está la aplicación)
     * @return La ruta relativa del archivo guardado (ej: "uploads/contratos/uuid.pdf")
     * @throws IOException Si hay un error al guardar el archivo
     */
    public static String saveFile(Part filePart, String servletContextPath) throws IOException {
        // Verificar que el archivo existe y tiene contenido
        if (filePart == null || filePart.getSize() == 0) {
            return null;
        }

        // Obtener el nombre original del archivo
        String fileName = filePart.getSubmittedFileName();
        if (fileName == null || fileName.isEmpty()) {
            return null;
        }

        // Validar la extensión del archivo
        String fileExtension = getFileExtension(fileName);
        if (!isAllowedExtension(fileExtension)) {
            throw new IOException("Tipo de archivo no permitido. Solo se aceptan: " + 
                String.join(", ", ALLOWED_EXTENSIONS));
        }

        // Validar el tamaño del archivo
        if (filePart.getSize() > MAX_FILE_SIZE) {
            throw new IOException("El archivo es demasiado grande. Tamaño máximo: 5MB");
        }

        // Generar un nombre único para evitar conflictos
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
        
        // Construir la ruta completa donde se guardará el archivo
        Path uploadPath = Paths.get(servletContextPath, UPLOAD_DIR);
        
        // Crear el directorio si no existe
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Ruta completa del archivo
        Path filePath = uploadPath.resolve(uniqueFileName);
        
        // Copiar el archivo desde el request al sistema de archivos
        try (InputStream inputStream = filePart.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // Devolver la ruta relativa (esto es lo que guardaremos en la BD)
        return UPLOAD_DIR + File.separator + uniqueFileName;
    }

    /**
     * Elimina un archivo del sistema de archivos
     * 
     * @param filePath - La ruta relativa del archivo (ej: "uploads/contratos/uuid.pdf")
     * @param servletContextPath - La ruta del contexto del servlet
     */
    public static void deleteFile(String filePath, String servletContextPath) {
        if (filePath == null || filePath.isEmpty()) {
            return;
        }

        try {
            Path path = Paths.get(servletContextPath, filePath);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            // Solo loguear el error, no lanzar excepción
            System.err.println("Error al eliminar archivo: " + e.getMessage());
        }
    }

    /**
     * Extrae la extensión de un nombre de archivo
     * 
     * @param fileName - Nombre del archivo
     * @return La extensión con el punto (ej: ".pdf")
     */
    private static String getFileExtension(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot > 0 && lastDot < fileName.length() - 1) {
            return fileName.substring(lastDot).toLowerCase();
        }
        return "";
    }

    /**
     * Verifica si una extensión está permitida
     * 
     * @param extension - La extensión a verificar
     * @return true si está permitida, false en caso contrario
     */
    private static boolean isAllowedExtension(String extension) {
        for (String allowed : ALLOWED_EXTENSIONS) {
            if (allowed.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }
}


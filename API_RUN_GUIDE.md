# 🚀 Guía: Cómo Ejecutar la API

## 📋 **Requisitos Previos**
- ✅ JDK 17 instalado
- ✅ Maven 3.8+ instalado
- ✅ IntelliJ IDEA (o cualquier IDE con soporte para Maven)

---

## **Opción 1: Ejecutar con IntelliJ IDEA (MÁS FÁCIL)**

### **Paso 1: Abrir el Proyecto**
1. Abre IntelliJ IDEA
2. `File` → `Open` → Selecciona la carpeta del proyecto
3. Espera a que Maven descargue las dependencias

### **Paso 2: Configurar Tomcat Embebido**
1. `File` → `Project Structure` → `Libraries`
2. Verifica que las dependencias de Maven estén cargadas

### **Paso 3: Agregar Configuración de Ejecución**
1. Click en la flecha desplegable junto al botón de "Run" (arriba a la derecha)
2. `Edit Configurations...`
3. Click en `+` → `Tomcat Server` → `Local`
4. Configura:
   - **Name**: `Prometheus API`
   - **Application server**: (Si no tienes Tomcat, descárgalo y apunta aquí)
   - **Deployment**: Click en `+` → `Artifact` → Selecciona `prometheus-web-1.0.0:war exploded`
   - **Application context**: `/` (o déjalo vacío)

### **Paso 4: Compilar y Ejecutar**
```bash
# En la terminal de IntelliJ o PowerShell:
mvn clean package
```

5. Click en el botón **Run** (▶️) o presiona `Shift + F10`

### **Paso 5: Verificar que Funciona**
Abre tu navegador o Postman y prueba:
```
GET http://localhost:8080/api/propiedades
```

---

## **Opción 2: Ejecutar con Maven + Tomcat Embebido (SIN IntelliJ)**

### **Paso 1: Compilar el Proyecto**
```powershell
cd "D:\Usuario\Escritorio\FULL STACK COURSE\Prometheus_web\EV02_prometheus_web"
mvn clean package
```

### **Paso 2: Descargar y Configurar Tomcat**
1. Descarga Tomcat 10.1 desde: https://tomcat.apache.org/download-10.cgi
2. Extrae el archivo ZIP
3. Copia el archivo `target/prometheus-web-1.0.0.war` a la carpeta `webapps` de Tomcat

### **Paso 3: Ejecutar Tomcat**
```powershell
# Navega a la carpeta de Tomcat
cd C:\apache-tomcat-10.1.x\bin

# En Windows:
startup.bat

# O en PowerShell:
.\startup.bat
```

### **Paso 4: Verificar**
Abre: `http://localhost:8080/api/propiedades`

---

## **Opción 3: Ejecutar con Maven Tomcat Plugin (MÁS RÁPIDO)**

### **Paso 1: Agregar el Plugin a pom.xml**
Ya está configurado, pero verifica que tengas esto en tu `pom.xml`:

```xml
<plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
    <configuration>
        <port>8080</port>
        <path>/</path>
    </configuration>
</plugin>
```

### **Paso 2: Ejecutar**
```powershell
mvn clean package
mvn tomcat7:run
```

O si prefieres usar el plugin de Tomcat 10:
```powershell
mvn clean package cargo:run
```

---

## **🧪 Probar los Endpoints de la API**

Una vez que la API esté corriendo, puedes probar estos endpoints:

### **Propiedades (Properties)**
```bash
# Listar todas
GET http://localhost:8080/api/propiedades

# Listar con paginación
GET http://localhost:8080/api/propiedades?page=1&size=10

# Buscar
GET http://localhost:8080/api/propiedades?q=casa

# Crear nueva
POST http://localhost:8080/api/propiedades
Content-Type: application/json

{
  "direccion": "Calle 123",
  "tipo": "Casa",
  "precio": 1500.00,
  "estado": "Disponible"
}

# Actualizar (envía el ID en el JSON)
POST http://localhost:8080/api/propiedades
Content-Type: application/json

{
  "id": 1,
  "direccion": "Calle 123 Actualizada",
  "tipo": "Casa",
  "precio": 2000.00,
  "estado": "Ocupada"
}

# Eliminar
DELETE http://localhost:8080/api/propiedades?id=1
```

### **Inquilinos (Tenants)**
```bash
GET http://localhost:8080/api/inquilinos
POST http://localhost:8080/api/inquilinos
DELETE http://localhost:8080/api/inquilinos?id=1
```

### **Alquileres (Rentals)**
```bash
GET http://localhost:8080/api/alquileres
POST http://localhost:8080/api/alquileres
DELETE http://localhost:8080/api/alquileres?id=1
```

---

## **🔍 Verificar que la API Está Corriendo**

### **En el Navegador:**
Abre: `http://localhost:8080/api/propiedades`

Deberías ver algo como:
```json
{
  "success": true,
  "message": null,
  "data": {
    "items": [],
    "total": 0,
    "page": 1,
    "size": 10,
    "totalPages": 0
  },
  "error": null
}
```

### **Con Postman:**
1. Abre Postman
2. Crea una nueva petición `GET`
3. URL: `http://localhost:8080/api/propiedades`
4. Click en `Send`

---

## **❌ Solución de Problemas**

### **Error: "Puerto 8080 ya está en uso"**
```powershell
# Windows: Encontrar qué proceso usa el puerto
netstat -ano | findstr :8080

# Matar el proceso (reemplaza PID con el número que aparezca)
taskkill /PID <PID> /F
```

### **Error: "Cannot find JDK 17"**
1. Verifica que tengas JDK 17 instalado: `java -version`
2. En IntelliJ: `File` → `Project Structure` → `Project` → `SDK` → Selecciona JDK 17

### **Error: "Maven dependencies not found"**
```powershell
# Limpia y reinstala dependencias
mvn clean install
```

### **La API no responde**
1. Verifica que Tomcat esté corriendo (deberías ver logs en la consola)
2. Verifica que el WAR se haya desplegado correctamente
3. Revisa los logs de Tomcat en `logs/catalina.out`

---

## **📝 Notas Importantes**

- 🔹 La API usa **puerto 8080** por defecto
- 🔹 Los endpoints empiezan con `/api/`
- 🔹 CORS está habilitado para `*` (permitir todas las peticiones)
- 🔹 Los datos se almacenan en memoria (se pierden al reiniciar)
- 🔹 Para datos persistentes, necesitarías agregar una base de datos

---

## **🎯 Próximos Pasos**

Una vez que la API esté corriendo:
1. ✅ Prueba todos los endpoints con Postman
2. ✅ Verifica que las respuestas JSON sean correctas
3. ✅ Conecta tu frontend React a estos endpoints
4. ✅ Aprende a entender cada línea del código (¡tu objetivo! 🎓)


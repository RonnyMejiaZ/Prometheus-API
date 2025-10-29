# 🌙 Configuración Eclipse - Paso a Paso

## 🎯 **Eclipse es la opción más popular y gratuita para Java**

---

## 📥 **Paso 1: Instalar Eclipse**

### **Descargar Eclipse:**
1. **Ir a**: https://www.eclipse.org/downloads/
2. **Elegir**: "Eclipse IDE for Enterprise Java and Web Developers"
3. **Descargar** (aproximadamente 500MB)
4. **Instalar** - Ejecutar el instalador

### **Configuración Inicial:**
1. **Abrir Eclipse**
2. **Workspace**: Seleccionar carpeta donde quieres trabajar
3. **Welcome Screen** → **Launch** (cerrar)

---

## 🚀 **Paso 2: Importar tu Proyecto**

### **Importar Proyecto Maven:**
1. **File** → **Import**
2. **Maven** → **Existing Maven Projects**
3. **Next**
4. **Root Directory**: Buscar tu carpeta `EV02_prometheus_web`
5. **Seleccionar** el proyecto
6. **Finish**

### **Verificar Importación:**
- ✅ **Project Explorer** debe mostrar tu proyecto
- ✅ **Maven Dependencies** debe estar expandido
- ✅ **src/main/java** debe estar visible

---

## 🐱 **Paso 3: Instalar Tomcat**

### **Descargar Tomcat:**
1. **Ir a**: https://tomcat.apache.org/download-10.cgi
2. **Elegir**: "Binary Distributions" → "Core" → "zip"
3. **Descargar** Tomcat 10.1.x
4. **Extraer** en una carpeta (ej: `C:\apache-tomcat-10.1.x`)

### **Configurar Tomcat en Eclipse:**
1. **Window** → **Preferences**
2. **Server** → **Runtime Environments**
3. **Add...**
4. **Apache** → **Apache Tomcat v10.1**
5. **Next**
6. **Tomcat installation directory**: Buscar carpeta de Tomcat
7. **JRE**: Seleccionar Java 17
8. **Finish**

---

## ⚙️ **Paso 4: Configurar Servidor**

### **Crear Servidor Tomcat:**
1. **Window** → **Show View** → **Servers**
2. **Servers** → **New** → **Server**
3. **Apache** → **Tomcat v10.1 Server**
4. **Next**
5. **Seleccionar tu proyecto** → **Add**
6. **Finish**

### **Configurar Despliegue:**
1. **Doble clic** en el servidor en la vista Servers
2. **Server Locations** → **Use Tomcat installation**
3. **Deploy Path**: `webapps`
4. **Guardar** (Ctrl+S)

---

## 🚀 **Paso 5: Ejecutar Aplicación**

### **Iniciar Servidor:**
1. **Clic derecho** en servidor → **Start**
2. **Esperar** que aparezca "Server startup in XXXX ms"
3. **Abrir navegador**: http://localhost:8080

### **Verificar Funcionamiento:**
- ✅ **Página de login** debe aparecer
- ✅ **Registro** debe funcionar
- ✅ **Dashboard** debe cargar
- ✅ **Todas las funcionalidades** deben trabajar

---

## 🔄 **Paso 6: Desarrollo con Hot Reload**

### **Configurar Auto-deploy:**
1. **Doble clic** en servidor
2. **Publishing** → **Automatically publish when resources change**
3. **Publishing interval**: 1 second
4. **Guardar**

### **Flujo de Trabajo:**
1. **Cambiar código** en archivo .java
2. **Guardar** (Ctrl+S)
3. **Eclipse recompila** automáticamente
4. **Eclipse despliega** cambios
5. **Recargar navegador** → ¡Cambios visibles!

---

## 🐛 **Paso 7: Debugging**

### **Configurar Breakpoints:**
1. **Clic izquierdo** en margen izquierdo del código
2. **Punto rojo** aparece (breakpoint)
3. **Debug** en lugar de Run

### **Ejecutar en Modo Debug:**
1. **Clic derecho** en servidor → **Debug**
2. **Ejecutar** funcionalidad en navegador
3. **Eclipse se detiene** en breakpoint
4. **Inspeccionar variables** en vista Variables
5. **Continuar** con F8

---

## 🛠️ **Configuración Adicional**

### **Plugins Útiles:**
1. **Help** → **Eclipse Marketplace**
2. **Buscar**: "Spring Tools" (opcional)
3. **Buscar**: "Maven Integration" (ya incluido)
4. **Instalar** plugins deseados

### **Configuración de Código:**
1. **Window** → **Preferences**
2. **Java** → **Code Style** → **Formatter**
3. **Import** estilo de código
4. **Java** → **Editor** → **Save Actions**
5. **Habilitar** "Format source code"

---

## 🎯 **Ventajas de Eclipse:**

### ✅ **Gratuito:**
- Sin costo alguno
- Open source
- Actualizaciones gratuitas

### ✅ **Potente:**
- Debugging avanzado
- Refactoring automático
- Análisis de código

### ✅ **Extensible:**
- Miles de plugins
- Personalizable
- Comunidad activa

---

## 🆘 **Solución de Problemas**

### **Error: "Server cannot be started":**
- Verificar que Tomcat esté correctamente configurado
- Verificar que Java 17 esté instalado
- Verificar que no haya conflictos de puerto

### **Error: "Project cannot be deployed":**
- Verificar que el proyecto sea Maven válido
- Verificar que las dependencias estén resueltas
- Limpiar y reconstruir proyecto

### **Cambios no se reflejan:**
- Verificar que auto-deploy esté habilitado
- Verificar que el proyecto esté en el servidor
- Limpiar y reconstruir proyecto

---

## 🎉 **¡Listo!**

**Tu entorno de desarrollo está configurado. Ahora puedes:**
- ✅ **Desarrollar** con hot reload
- ✅ **Debuggear** fácilmente
- ✅ **Desplegar** automáticamente
- ✅ **Trabajar** como un profesional

**¿Necesitas ayuda con algún paso específico? 🛠️**

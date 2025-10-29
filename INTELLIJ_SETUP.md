# 🧠 Configuración IntelliJ IDEA - Guía Completa

## 🎯 **IntelliJ IDEA es el IDE más potente para Java**

---

## 📥 **Paso 1: Instalar IntelliJ IDEA**

### **Descargar IntelliJ:**
1. **Ir a**: https://www.jetbrains.com/idea/download/
2. **Elegir**: "Community" (gratis) o "Ultimate" (pago)
3. **Descargar** (aproximadamente 500MB)
4. **Instalar** - Ejecutar el instalador

### **Configuración Inicial:**
1. **Abrir IntelliJ IDEA**
2. **Welcome Screen** → **New Project**
3. **Maven** → **Next**
4. **GroupId**: `com.prometheus`
5. **ArtifactId**: `prometheus-web`
6. **Version**: `1.0.0`
7. **Finish**

---

## 🚀 **Paso 2: Importar tu Proyecto**

### **Importar Proyecto Existente:**
1. **File** → **Open**
2. **Seleccionar** carpeta `EV02_prometheus_web`
3. **Open as Project**
4. **IntelliJ detectará** que es un proyecto Maven
5. **Import Maven Project** → **OK**

### **Verificar Importación:**
- ✅ **Project Structure** debe mostrar tu proyecto
- ✅ **Maven** debe estar en la barra lateral
- ✅ **src/main/java** debe estar visible
- ✅ **Dependencies** deben estar resueltas

---

## 🐱 **Paso 3: Configurar Tomcat**

### **Instalar Plugin Tomcat:**
1. **File** → **Settings** (Ctrl+Alt+S)
2. **Plugins** → **Marketplace**
3. **Buscar**: "Tomcat"
4. **Instalar** "Tomcat and TomEE Integration"

### **Configurar Tomcat:**
1. **File** → **Settings** → **Build, Execution, Deployment** → **Application Servers**
2. **+** → **Tomcat Server**
3. **Tomcat Home**: Buscar carpeta de Tomcat
4. **OK**

---

## ⚙️ **Paso 4: Configurar Run Configuration**

### **Crear Run Configuration:**
1. **Run** → **Edit Configurations**
2. **+** → **Tomcat Server** → **Local**
3. **Name**: `Prometheus Web`
4. **Application server**: Seleccionar Tomcat configurado
5. **Deployment** → **+** → **Artifact**
6. **Seleccionar**: `prometheus-web:war exploded`
7. **Application context**: `/` (raíz)
8. **OK**

---

## 🚀 **Paso 5: Ejecutar Aplicación**

### **Iniciar Aplicación:**
1. **Run** → **Run 'Prometheus Web'**
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
1. **Run** → **Edit Configurations**
2. **Seleccionar** tu configuración
3. **Deployment** → **On 'Update' action**: `Update classes and resources`
4. **On frame deactivation**: `Update classes and resources`
5. **OK**

### **Flujo de Trabajo:**
1. **Cambiar código** en archivo .java
2. **Guardar** (Ctrl+S)
3. **IntelliJ recompila** automáticamente
4. **IntelliJ despliega** cambios
5. **Recargar navegador** → ¡Cambios visibles!

---

## 🐛 **Paso 7: Debugging Avanzado**

### **Configurar Breakpoints:**
1. **Clic izquierdo** en margen izquierdo del código
2. **Punto rojo** aparece (breakpoint)
3. **Debug** en lugar de Run

### **Ejecutar en Modo Debug:**
1. **Run** → **Debug 'Prometheus Web'**
2. **Ejecutar** funcionalidad en navegador
3. **IntelliJ se detiene** en breakpoint
4. **Inspeccionar variables** en vista Variables
5. **Continuar** con F8

### **Funciones de Debug:**
- **F8**: Step Over (siguiente línea)
- **F7**: Step Into (entrar en método)
- **Shift+F8**: Step Out (salir de método)
- **F9**: Resume (continuar)

---

## 🛠️ **Configuración Adicional**

### **Plugins Útiles:**
1. **File** → **Settings** → **Plugins**
2. **Marketplace** → Buscar:
   - **Lombok** - Reduce código boilerplate
   - **Checkstyle-IDEA** - Estilo de código
   - **SonarLint** - Análisis de calidad
   - **Git Integration** - Control de versiones

### **Configuración de Código:**
1. **File** → **Settings** → **Editor** → **Code Style**
2. **Java** → **Import** estilo de código
3. **File** → **Settings** → **Editor** → **General** → **Auto Import**
4. **Habilitar** "Add unambiguous imports on the fly"

---

## 🎯 **Ventajas de IntelliJ IDEA:**

### ✅ **Potente:**
- **IntelliSense** avanzado
- **Refactoring** automático
- **Análisis de código** en tiempo real

### ✅ **Productivo:**
- **Shortcuts** inteligentes
- **Templates** de código
- **Generación** automática

### ✅ **Profesional:**
- **Usado en empresas** grandes
- **Soporte** excelente
- **Actualizaciones** frecuentes

---

## 🆘 **Solución de Problemas**

### **Error: "Cannot resolve symbol":**
- **File** → **Invalidate Caches and Restart**
- **Maven** → **Reload All Maven Projects**
- **Verificar** que las dependencias estén correctas

### **Error: "Server cannot be started":**
- **Verificar** que Tomcat esté correctamente configurado
- **Verificar** que Java 17 esté instalado
- **Verificar** que no haya conflictos de puerto

### **Cambios no se reflejan:**
- **Verificar** que auto-deploy esté habilitado
- **Verificar** que el proyecto esté en el servidor
- **Build** → **Rebuild Project**

---

## 🎉 **¡Listo!**

**Tu entorno de desarrollo está configurado. Ahora puedes:**
- ✅ **Desarrollar** con hot reload
- ✅ **Debuggear** fácilmente
- ✅ **Desplegar** automáticamente
- ✅ **Trabajar** como un profesional

**¿Necesitas ayuda con algún paso específico? 🛠️**

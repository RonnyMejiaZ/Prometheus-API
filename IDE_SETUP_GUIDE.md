# 🛠️ Configuración IDE - Guía Completa

## 🎯 **Por qué usar un IDE es la mejor opción:**

### ✅ **Ventajas del IDE:**
- **Despliegue automático** - Cambias código → Guardas → Recargas navegador
- **Debugging integrado** - Puntos de quiebre, inspección de variables
- **Autocompletado** - Sugerencias inteligentes de código
- **Gestión de dependencias** - Maven integrado
- **Servidor integrado** - Tomcat se inicia/para automáticamente
- **Hot reload** - Cambios se reflejan sin reiniciar

---

## 🚀 **Opciones de IDE Recomendadas:**

### 1. **IntelliJ IDEA** (Recomendado)
- ✅ **Mejor para Java** - Soporte nativo excelente
- ✅ **Maven integrado** - No necesitas línea de comandos
- ✅ **Tomcat integrado** - Configuración automática
- ✅ **Debugging avanzado** - Muy potente
- ❌ **Pago** - Versión Community (gratis) o Ultimate ($)

### 2. **Eclipse** (Gratuito)
- ✅ **100% Gratuito** - Open source
- ✅ **Muy popular** - Mucha documentación
- ✅ **Plugins** - Muchas extensiones disponibles
- ❌ **Más complejo** - Curva de aprendizaje mayor

### 3. **Visual Studio Code** (Gratuito)
- ✅ **Gratuito** - Microsoft
- ✅ **Liviano** - Rápido de instalar
- ✅ **Extensiones** - Java Extension Pack
- ❌ **Menos integrado** - Requiere más configuración

---

## 🛠️ **Configuración IntelliJ IDEA (Recomendado)**

### **Paso 1: Instalar IntelliJ IDEA**
1. **Descargar**: https://www.jetbrains.com/idea/download/
2. **Elegir**: IntelliJ IDEA Community (gratis)
3. **Instalar** y abrir

### **Paso 2: Importar Proyecto**
1. **Open** → Seleccionar carpeta del proyecto
2. **Import as Maven project** → Next
3. **Maven settings** → Usar configuración por defecto
4. **Finish**

### **Paso 3: Configurar Tomcat**
1. **Run** → **Edit Configurations**
2. **+** → **Tomcat Server** → **Local**
3. **Application server**: Buscar Tomcat instalado
4. **Deployment** → **+** → **Artifact** → **prometheus-web:war exploded**
5. **Application context**: `/` (raíz)
6. **OK**

### **Paso 4: Ejecutar**
1. **Run** → **Run 'Tomcat Server'**
2. **Abrir navegador**: http://localhost:8080
3. **¡Listo!** - Cambios se reflejan automáticamente

---

## 🛠️ **Configuración Eclipse (Gratuito)**

### **Paso 1: Instalar Eclipse**
1. **Descargar**: https://www.eclipse.org/downloads/
2. **Eaven IDE for Java Developers**
3. **Instalar** y abrir

### **Paso 2: Importar Proyecto**
1. **File** → **Import** → **Maven** → **Existing Maven Projects**
2. **Root Directory**: Seleccionar carpeta del proyecto
3. **Finish**

### **Paso 3: Configurar Tomcat**
1. **Window** → **Preferences** → **Server** → **Runtime Environments**
2. **Add** → **Apache Tomcat** → **Next**
3. **Tomcat installation directory**: Buscar Tomcat
4. **Finish**

### **Paso 4: Ejecutar**
1. **Run** → **Run on Server**
2. **Seleccionar Tomcat** → **Next**
3. **Seleccionar proyecto** → **Finish**
4. **Abrir navegador**: http://localhost:8080

---

## 🛠️ **Configuración VS Code (Gratuito)**

### **Paso 1: Instalar Extensiones**
1. **Extensiones** → Buscar "Extension Pack for Java"
2. **Instalar** - Incluye todas las extensiones necesarias

### **Paso 2: Configurar Proyecto**
1. **File** → **Open Folder** → Seleccionar proyecto
2. **VS Code detectará** que es un proyecto Maven
3. **Aceptar** configuración automática

### **Paso 3: Configurar Tomcat**
1. **Ctrl+Shift+P** → "Java: Configure Classpath"
2. **Agregar Tomcat** a classpath
3. **Crear launch.json** para configuración

---

## 🎯 **Flujo de Trabajo con IDE:**

### **Desarrollo Normal:**
1. **Cambias código** en archivo .java
2. **Guardas** (Ctrl+S)
3. **IDE recompila** automáticamente
4. **IDE despliega** cambios a Tomcat
5. **Recargas navegador** → ¡Cambios visibles!

### **Debugging:**
1. **Poner breakpoint** en línea de código
2. **Debug** en lugar de Run
3. **Ejecutar** funcionalidad en navegador
4. **IDE se detiene** en breakpoint
5. **Inspeccionar variables** y continuar

---

## 📋 **Configuración Adicional Recomendada:**

### **Para Mejor Experiencia:**
1. **Auto-save** habilitado
2. **Hot reload** configurado
3. **Browser auto-refresh** (opcional)
4. **Code formatting** automático

### **Plugins Útiles:**
- **Lombok** - Reduce código boilerplate
- **Checkstyle** - Estilo de código consistente
- **SpotBugs** - Detección de bugs
- **SonarLint** - Análisis de calidad

---

## 🚀 **Ventajas vs Línea de Comandos:**

| Aspecto | IDE | Línea de Comandos |
|---------|-----|-------------------|
| **Velocidad** | ⚡ Instantáneo | 🐌 Lento |
| **Debugging** | 🔍 Avanzado | ❌ Básico |
| **Productividad** | 📈 Alta | 📉 Baja |
| **Curva aprendizaje** | 📚 Media | 📚 Alta |
| **Costo** | 💰 Variable | 💰 Gratis |

---

## 🎯 **Mi Recomendación:**

### **Para tu proyecto:**
1. **IntelliJ IDEA Community** (gratis)
2. **Tomcat 10.1** integrado
3. **Maven** integrado
4. **Debugging** completo

### **Resultado:**
- ✅ **Desarrollo rápido** - Cambios instantáneos
- ✅ **Debugging fácil** - Puntos de quiebre
- ✅ **Sin comandos** - Todo visual
- ✅ **Profesional** - Como en empresas reales

**¿Quieres que te ayude a configurar algún IDE específico? 🛠️**

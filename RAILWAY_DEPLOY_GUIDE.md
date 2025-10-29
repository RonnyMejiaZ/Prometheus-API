# 🚂 Deploy to Railway - Complete Guide

## 🎯 **Railway Overview**
- **Free tier**: 500 hours/month (about 20 days running 24/7)
- **After free tier**: $5-20/month
- **Perfect for**: Java servlet applications
- **Deployment**: From GitHub repository

---

## 🚀 **Step 1: Prepare Your Code**

Your code is already ready! We just need to add a few Railway-specific files.

### Create `railway.json`:
```json
{
  "$schema": "https://railway.app/railway.schema.json",
  "build": {
    "builder": "DOCKERFILE",
    "dockerfilePath": "Dockerfile"
  },
  "deploy": {
    "numReplicas": 1,
    "restartPolicyType": "ALWAYS",
    "restartPolicyMaxRetries": 10,
    "startCommand": "",
    "healthcheckPath": "/",
    "healthcheckTimeout": 300,
    "sleepApplication": false
  }
}
```

### Create `Dockerfile`:
```dockerfile
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/prometheus-web-1.0.0.war ./ROOT.war
COPY --from=build /app/target/dependency/webapp-runner.jar webapp-runner.jar

EXPOSE 8080
ENV PORT=8080
CMD ["sh", "-c", "java -jar webapp-runner.jar --port $PORT ./ROOT.war"]
```

---

## 🚀 **Step 2: Update Maven Configuration**

We need to add webapp-runner for Railway:

### Update `pom.xml`:
```xml
<!-- Add this dependency -->
<dependency>
  <groupId>com.github.jsimone</groupId>
  <artifactId>webapp-runner</artifactId>
  <version>9.0.71.0</version>
  <scope>provided</scope>
</dependency>

<!-- Add this plugin -->
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-dependency-plugin</artifactId>
  <version>3.2.0</version>
  <executions>
    <execution>
      <phase>package</phase>
      <goals>
        <goal>copy</goal>
      </goals>
      <configuration>
        <artifactItems>
          <artifactItem>
            <groupId>com.github.jsimone</groupId>
            <artifactId>webapp-runner</artifactId>
            <version>9.0.71.0</version>
            <destFileName>webapp-runner.jar</destFileName>
          </artifactItem>
        </artifactItems>
      </configuration>
    </execution>
  </executions>
</plugin>
```

---

## 🚀 **Step 3: Deploy to Railway**

### 1. **Push to GitHub** (if not already done):
```bash
git add .
git commit -m "Add Railway deployment configuration"
git push origin main
```

### 2. **Go to Railway**:
- Visit: https://railway.app
- Sign up with GitHub
- Click "New Project"
- Select "Deploy from GitHub repo"
- Choose your repository: `EV02_prometheus_web`

### 3. **Configure Deployment**:
- Railway will auto-detect the Dockerfile
- Port will be automatically set to 8080
- Click "Deploy"

### 4. **Wait for Build**:
- Build takes 3-5 minutes
- You'll see logs in real-time
- Wait for "Deployment successful"

### 5. **Get Your URL**:
- Click on your service
- Go to "Settings" tab
- Scroll to "Domains" section
- Click "Generate Domain"
- Copy your URL!

---

## 🎉 **Result**

Your app will be live at: `https://your-app.railway.app`

**Features working:**
- ✅ Login/Register
- ✅ Dashboard
- ✅ All CRUD operations
- ✅ Data persistence (in-memory)

---

## 💰 **Cost Breakdown**

### **Free Tier** (First 500 hours):
- **Hours**: ~20 days running 24/7
- **Cost**: $0
- **Perfect for**: Demos, testing, school projects

### **After Free Tier**:
- **Cost**: $5-20/month
- **Still cheaper than**: Most other hosting options
- **Good for**: Production use

---

## 🔧 **Managing Your App**

### **View Logs**:
- Click on your service in Railway dashboard
- Go to "Logs" tab
- See real-time application logs

### **Restart App**:
- Click on your service
- Click "Restart" button

### **Update App**:
- Push changes to GitHub
- Railway auto-deploys

---

## 🆘 **Troubleshooting**

### **Build Fails**:
- Check logs in Railway dashboard
- Ensure all dependencies are in pom.xml
- Verify Dockerfile is correct

### **App Won't Start**:
- Check that port is set to 8080
- Verify webapp-runner is included
- Check logs for errors

### **Can't Access URL**:
- Wait 1-2 minutes after deployment
- Check that deployment status is "Active"
- Try refreshing the page

---

## 📋 **Pre-Deployment Checklist**

- [ ] Code is on GitHub
- [ ] `railway.json` exists
- [ ] `Dockerfile` exists
- [ ] `pom.xml` has webapp-runner dependency
- [ ] Maven build works locally
- [ ] Railway account created

---

## 🎯 **Why Railway?**

- ✅ **Easy deployment** from GitHub
- ✅ **Java support** out of the box
- ✅ **Free tier** for testing
- ✅ **Automatic HTTPS**
- ✅ **Good performance**
- ✅ **Easy scaling**

**Ready to deploy? Let's get your Java app live on Railway! 🚀**

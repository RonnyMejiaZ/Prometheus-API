# Deployment Guide - Prometheus Web

This Java servlet application can be deployed to various platforms. Choose one:

## 🌐 Option 1: Heroku (Easiest, Free tier available)

### Prerequisites
- Heroku account (free): https://heroku.com
- Heroku CLI installed: https://devcenter.heroku.com/articles/heroku-cli

### Deployment Steps

1. **Install Heroku CLI** (if not already installed)

2. **Login to Heroku**
   ```bash
   heroku login
   ```

3. **Create Heroku App**
   ```bash
   heroku create your-app-name
   ```

4. **Deploy**
   ```bash
   git push heroku main
   ```

5. **Open your app**
   ```bash
   heroku open
   ```

### Important Notes
- **Data is stored in memory** - All data will be lost on server restart
- **No persistence** - This is a demo application without a database
- **Free tier limits**: App sleeps after 30 minutes of inactivity

---

## 🚂 Option 2: Railway (Modern, Easy)

### Prerequisites
- Railway account: https://railway.app
- GitHub repository with your code

### Deployment Steps

1. **Login to Railway**
   - Go to https://railway.app
   - Sign up with GitHub

2. **Create New Project**
   - Click "New Project"
   - Select "Deploy from GitHub repo"
   - Choose your repository

3. **Configure**
   - Railway will auto-detect the Dockerfile
   - Port will be automatically set to 8080

4. **Deploy**
   - Click "Deploy"
   - Wait for build to complete

5. **Get URL**
   - Railway provides a public URL automatically

### Important Notes
- First 500 hours/month are free
- No credit card required for basic tier
- **Data is still in-memory** (no persistence)

---

## 🐳 Option 3: Any Docker Host

### Prerequisites
- Docker installed
- A Docker hosting service (AWS ECS, Google Cloud Run, Azure Container Apps, etc.)

### Build and Run Locally
```bash
# Build
docker build -t prometheus-web .

# Run
docker run -p 8080:8080 prometheus-web
```

Then deploy to any Docker-compatible hosting service.

---

## ⚙️ Environment Variables

Currently, no environment variables are required. All data is stored in memory.

---

## 🔒 Important Security Notes

**This application is NOT production-ready:**
- Passwords are stored in plain text (no hashing)
- No database persistence
- No HTTPS enforcement in this setup
- Session management is basic

**Do NOT use for production data!**

---

## 🐛 Troubleshooting

### Heroku Deployment Issues

If deployment fails:
```bash
heroku logs --tail
```

Common issues:
- Port must be `$PORT` environment variable (already configured)
- Java version must be 17 (already configured)

### Railway Deployment Issues

Check build logs in Railway dashboard.

### Local Testing

Test the WAR file locally before deploying:
```bash
mvn clean package
java -jar target/dependency/webapp-runner.jar target/prometheus-web-1.0.0.war
```

Visit http://localhost:8080

---

## 📋 Pre-Deployment Checklist

- [ ] Code compiles without errors
- [ ] All dependencies are in pom.xml
- [ ] Procfile exists (for Heroku)
- [ ] Dockerfile exists (for Docker/Railway)
- [ ] system.properties specifies Java 17
- [ ] Reviewed security implications

---

## 🎯 Which Platform Should You Choose?

| Feature | Heroku | Railway | Docker Host |
|---------|--------|---------|-------------|
| **Ease of Setup** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ |
| **Free Tier** | ✅ | ✅ | ❌ |
| **Deploy Speed** | Fast | Fast | Medium |
| **Cost (Paid)** | Expensive | Affordable | Variable |
| **Best For** | Quick demos | Modern apps | Enterprise |

**Recommendation**: Start with **Railway** for modern features and ease of use, or **Heroku** if you need the free tier.


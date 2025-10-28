# 💻 Run Locally - Completely FREE

Run your app on your own computer - **100% FREE**, no restrictions!

## 📋 Requirements
- Java JDK 17 (already have it!)
- Maven (already configured!)
- Any modern browser

---

## 🚀 Run in 3 Steps

### Step 1: Build
```bash
mvn clean package
```

### Step 2: Run with embedded server
```bash
java -jar target/dependency/webapp-runner.jar target/prometheus-web-1.0.0.war
```

### Step 3: Open Browser
Visit: **http://localhost:8080**

🎉 **That's it! Your app is running!**

---

## 🌐 Share Your Local App

### Option A: ngrok (FREE)
1. Download: https://ngrok.com
2. Run: `ngrok http 8080`
3. Get public URL like: `https://abc123.ngrok.io`
4. Share with anyone!

### Option B: Cloudflare Tunnel (FREE)
1. Install: `brew install cloudflare/cloudflare/cloudflared` (or download from cloudflare.com)
2. Run: `cloudflared tunnel --url http://localhost:8080`
3. Get public URL

### Option C: LocalTunnel (FREE)
```bash
npm install -g localtunnel
lt --port 8080
```

---

## ⚡ Alternative: Use Tomcat

If you have Tomcat installed:

```bash
# Copy WAR to Tomcat
cp target/prometheus-web-1.0.0.war /path/to/tomcat/webapps/

# Start Tomcat
/path/to/tomcat/bin/startup.sh

# Visit
http://localhost:8080/prometheus-web-1.0.0/
```

---

## 🎯 For Demos

Best option: Run locally + ngrok
1. **Start your app**: `java -jar target/dependency/webapp-runner.jar target/prometheus-web-1.0.0.war`
2. **Tunnel it**: `ngrok http 8080`
3. **Share the ngrok URL** with your teacher/friends
4. **100% FREE and works perfectly!**

---

## 💡 Why This is Best for School Projects?

- ✅ **Completely FREE** - No cost, no limits
- ✅ **No sleep/hibernate** - Always running while your computer is on
- ✅ **Full control** - You own everything
- ✅ **Perfect for demos** - Use ngrok to share with others
- ✅ **No restrictions** - Do whatever you want

---

## 🔄 Quick Commands

```bash
# Stop the app: Press Ctrl+C

# Restart
java -jar target/dependency/webapp-runner.jar target/prometheus-web-1.0.0.war

# Rebuild and run
mvn clean package && java -jar target/dependency/webapp-runner.jar target/prometheus-web-1.0.0.war
```

---

**Recommended for students: Use this method! It's FREE and perfect for assignments! 🎓**


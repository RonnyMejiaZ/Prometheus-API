# 🚀 Quick Deployment Guide

## ⚡ Local Testing (100% FREE)

```bash
mvn clean package
# Copy target/prometheus-web-1.0.0.war to TOMCAT/webapps/
# Start Tomcat
# Visit http://localhost:8080/prometheus-web-1.0.0/
```

**Time needed:** 3 minutes

---

## ⚡ Local + ngrok (FREE with Public URL)

```bash
# 1. Run your app locally (see above)
# 2. Install ngrok: https://ngrok.com
# 3. Run: ngrok http 8080
# 4. Share the ngrok URL!
```

**Time needed:** 5 minutes

---

## ⚠️ Important Notes

- **Data is not persistent** - Everything resets on restart
- **This is a demo** - Not for production use
- **Free tiers have limits** - Apps may sleep after inactivity

---

## 🆘 Need Help?

Read the full guide: [DEPLOY.md](./DEPLOY.md)


# 🚀 Quick Deployment Guide

Choose ONE option and follow these steps:

## ⚡ Option A: Railway (Recommended - Fastest)

1. Go to https://railway.app
2. Click "Start a New Project"
3. Select "Deploy from GitHub repo"
4. Authorize GitHub and select this repository
5. Click "Deploy"
6. Wait 2-3 minutes
7. Click on your service → Settings → Generate Domain
8. **Done!** Your app is live.

**Time needed:** 5 minutes

---

## ⚡ Option B: Heroku (Free Tier Available)

1. Install Heroku CLI: https://devcenter.heroku.com/articles/heroku-cli
2. Run these commands:

```bash
heroku login
heroku create your-app-name
git push heroku main
heroku open
```

**Time needed:** 10 minutes

---

## ⚡ Option C: Local Docker Testing

```bash
docker build -t prometheus-web .
docker run -p 8080:8080 prometheus-web
```

Visit http://localhost:8080

**Time needed:** 3 minutes

---

## ⚠️ Important Notes

- **Data is not persistent** - Everything resets on restart
- **This is a demo** - Not for production use
- **Free tiers have limits** - Apps may sleep after inactivity

---

## 🆘 Need Help?

Read the full guide: [DEPLOY.md](./DEPLOY.md)


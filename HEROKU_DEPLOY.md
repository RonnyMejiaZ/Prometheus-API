# 🆓 Deploy to Heroku (100% FREE)

Heroku offers a **forever free tier** with some limitations, but it works for demos!

## ⚠️ Free Tier Limitations
- ✅ **550-1000 hours/month FREE** (about 23-42 days running 24/7)
- ⚠️ **App sleeps** after 30 minutes of inactivity
- ⚠️ **First wake-up** can take 10-30 seconds
- ✅ **Works great for demos and testing!**

---

## 🚀 Deploy Steps (5 minutes)

### Step 1: Install Heroku CLI
Download from: https://devcenter.heroku.com/articles/heroku-cli

### Step 2: Login
```bash
heroku login
```

### Step 3: Create App
```bash
heroku create your-app-name
```
Replace `your-app-name` with your choice (must be unique globally)

### Step 4: Push to Heroku
```bash
git push heroku main
```

### Step 5: Open Your App
```bash
heroku open
```

🎉 **That's it! Your app is live!**

---

## 📍 Your App URL

After deployment, your URL will be:
```
https://your-app-name.herokuapp.com
```

---

## 🔄 Managing Your App

### View Logs
```bash
heroku logs --tail
```

### Check App Status
```bash
heroku ps
```

### Restart App
```bash
heroku restart
```

---

## ⚡ Pro Tips

1. **Wake up sleeping apps** - Visit the URL, wait 30 seconds for first load
2. **Keep app active** - Use a free service like UptimeRobot to ping it every 5 minutes
3. **View logs** - Use `heroku logs --tail` to debug issues

---

## 💰 Why FREE?

- Heroku Free Tier is actually FREE (no credit card required!)
- Limited hours per month but enough for demos
- App sleeps but wakes up automatically
- Great for portfolio projects and school assignments

---

## 🆘 Troubleshooting

### Build Fails
```bash
heroku logs --tail
```
Check the logs for errors

### App Crashes
```bash
heroku restart
heroku logs --tail
```

### Port Issues
Already configured! The Procfile uses `$PORT` automatically

---

## ✅ Success Checklist

- [ ] Heroku CLI installed
- [ ] Logged in with `heroku login`
- [ ] App created with `heroku create`
- [ ] Code pushed with `git push heroku main`
- [ ] App opened with `heroku open`
- [ ] Login works on live site!

**You're done! Enjoy your FREE hosted Java app! 🎊**


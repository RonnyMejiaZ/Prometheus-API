# 🚂 Deploy to Railway - Step by Step

## Prerequisites
- A GitHub account
- This code pushed to a GitHub repository

---

## Step 1: Push Code to GitHub

If you haven't already, push your code:

```bash
git add .
git commit -m "Add Railway deployment configuration"
git push origin main
```

---

## Step 2: Create Railway Account

1. Go to **https://railway.app**
2. Click **"Start a New Project"**
3. Choose **"Login with GitHub"**
4. Authorize Railway to access your GitHub account

---

## Step 3: Deploy Your Project

1. After login, click **"New Project"**
2. Select **"Deploy from GitHub repo"**
3. You'll see a list of your repositories
4. Find and select: **EV02_prometheus_web** (or your repo name)
5. Click the repository

Railway will automatically:
- Detect the Dockerfile
- Start building your app
- This takes 3-5 minutes

---

## Step 4: Get Your Live URL

Once deployment is complete:

1. Click on your deployed service
2. Go to **"Settings"** tab
3. Scroll to **"Domains"** section
4. Click **"Generate Domain"**
5. Copy the generated URL (e.g., `https://your-app.railway.app`)

**That's it! Your app is live!** 🎉

---

## Step 5: Test Your Application

Visit your Railway URL and test:

1. **Register**: Create a new account
2. **Login**: Use your credentials
3. **Dashboard**: View the dashboard
4. **Create records**: Add inquilinos, propiedades, etc.

---

## Monitoring & Logs

### View Logs
- Click on your service → **"Logs"** tab
- See real-time application logs

### View Metrics
- Click on your service → **"Metrics"** tab
- Monitor CPU, Memory, Network usage

---

## Important Notes

### ⚠️ Data Persistence
- **Data is stored in memory**
- All data will be lost when the app restarts
- This is a demo app without a database

### 💰 Pricing
- **First 500 hours/month are FREE**
- After that: ~$5/month for a hobby project
- No credit card required to start

### 🔒 Security
This app is **NOT production-ready**:
- Passwords stored in plain text
- No HTTPS by default (Railway provides it)
- No database persistence
- Session management is basic

---

## Updating Your App

When you make changes:

```bash
git add .
git commit -m "Your changes"
git push origin main
```

Railway will **automatically redeploy** with your changes!

---

## Troubleshooting

### Build Fails
Check the logs in Railway dashboard:
1. Click on your service
2. Go to "Logs" tab
3. Look for error messages

### App Doesn't Start
- Verify Java 17 is specified (already configured)
- Check that all dependencies are in pom.xml
- Ensure Dockerfile is present in root directory

### Can't Access the URL
- Wait 1-2 minutes after deployment
- Check that deployment status is "Active"
- Verify the port is set to 8080 (already configured)

### Port Issues
Railway automatically sets the `PORT` environment variable. The Dockerfile is configured to use it with:
```dockerfile
ENV PORT=8080
CMD ["sh", "-c", "java -jar webapp-runner.jar --port $PORT ./ROOT.war"]
```

---

## Need Help?

- Railway Docs: https://docs.railway.app
- Railway Discord: https://discord.gg/railway

---

## Success Checklist

✅ Code is on GitHub  
✅ Railway account created  
✅ Project deployed from GitHub  
✅ Domain generated  
✅ App is accessible  
✅ Login works  
✅ Can create/read data  

**You're done! Congratulations! 🎊**


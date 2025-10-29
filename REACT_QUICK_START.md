# ⚛️ React Quick Start - 30 Minutes

## 🎯 **Goal**: Convert your Java app to React with FREE hosting

---

## 🚀 **Step 1: Create React App (5 minutes)**

```bash
# Create new React project
npx create-react-app prometheus-web-react
cd prometheus-web-react

# Install additional packages
npm install react-router-dom axios recharts
```

---

## 🚀 **Step 2: Basic Structure (10 minutes)**

### Create these files:

#### `src/App.js`
```jsx
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import Dashboard from './components/Dashboard';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
```

#### `src/components/Login.js`
```jsx
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    // Simple validation
    if (email && password) {
      navigate('/dashboard');
    }
  };

  return (
    <div className="login-container">
      <h1>Prometheus Web</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default Login;
```

#### `src/components/Dashboard.js`
```jsx
import React from 'react';

function Dashboard() {
  return (
    <div className="dashboard">
      <h1>Dashboard</h1>
      <div className="stats">
        <div className="stat-card">
          <h3>Inquilinos</h3>
          <p>0</p>
        </div>
        <div className="stat-card">
          <h3>Propiedades</h3>
          <p>0</p>
        </div>
        <div className="stat-card">
          <h3>Alquileres</h3>
          <p>0</p>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
```

#### `src/App.css`
```css
.login-container {
  max-width: 400px;
  margin: 100px auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
}

.login-container input {
  width: 100%;
  padding: 10px;
  margin: 10px 0;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.login-container button {
  width: 100%;
  padding: 10px;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.dashboard {
  padding: 20px;
}

.stats {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
  margin-top: 20px;
}

.stat-card {
  background: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
  text-align: center;
}

.stat-card h3 {
  margin: 0 0 10px 0;
  color: #333;
}

.stat-card p {
  font-size: 2em;
  margin: 0;
  color: #007bff;
}
```

---

## 🚀 **Step 3: Test Locally (5 minutes)**

```bash
# Start development server
npm start
```

Visit: http://localhost:3000

---

## 🚀 **Step 4: Deploy to Vercel (10 minutes)**

### Option A: Vercel CLI
```bash
# Install Vercel CLI
npm install -g vercel

# Deploy
vercel

# Follow prompts:
# - Link to existing project? No
# - Project name: prometheus-web-react
# - Directory: ./
# - Override settings? No
```

### Option B: GitHub + Vercel
1. **Push to GitHub**:
   ```bash
   git init
   git add .
   git commit -m "Initial React app"
   git remote add origin https://github.com/yourusername/prometheus-web-react.git
   git push -u origin main
   ```

2. **Connect to Vercel**:
   - Go to https://vercel.com
   - Sign up with GitHub
   - Click "New Project"
   - Select your repository
   - Click "Deploy"

---

## 🎉 **Result**

Your app will be live at: `https://prometheus-web-react.vercel.app`

**100% FREE!** 🎊

---

## 🔄 **Next Steps**

1. **Add more pages**: Inquilinos, Propiedades, etc.
2. **Add data persistence**: Local storage or database
3. **Improve styling**: Add more CSS or use a UI library
4. **Add authentication**: Real login system

---

## 📚 **Resources**

- **React Docs**: https://reactjs.org/docs
- **Vercel Docs**: https://vercel.com/docs
- **React Router**: https://reactrouter.com

**Want me to help you convert specific pages from your Java app to React?** 🚀

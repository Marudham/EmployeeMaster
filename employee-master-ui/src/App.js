import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import AdminHome from './pages/AdminHome';
import SuperAdmin from './pages/SuperAdmin';
import SAHome from './components/SAHome';
import './styles/App.css'
// import './styles/LoginStyle.css'
// import './styles/Register.css'
// import './styles/Home.css'

function App() {

  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/adminHome/*" element={<AdminHome />} />
          <Route path="/superAdmin" element={<SuperAdmin />} />
          <Route path="/SAHome/*" element={<SAHome />} ></Route>
        </Routes>
      </div>
    </Router>
  );
}

export default App;

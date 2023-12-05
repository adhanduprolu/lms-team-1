import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import Home from './Components/Home/Home';
import Login from './Components/Login/Login';
import Forgot from './Components/Forgot';
import LearnerDashboard from './Components/LearnerDashboard/LearnerDashboard';
import Profile from './Components/Profile/Profile';
import CourseDashboard from './Components/CourseDashboard/CourseDashboard';


function App() {
  return (
   
      <div className="App">
        <Router>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/forgotpassword" element={<Forgot />} />
            <Route path="/learnerdashboard" element={<LearnerDashboard/>}/>
            <Route path="/myprofile" element={<Profile/>}/>
            <Route path="/coursedashboard" element={<CourseDashboard/>}/>
          </Routes>
        </Router>
      </div>
  );
}

export default App;

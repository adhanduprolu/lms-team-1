import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

const Home = () => {
  const [message, setMessage] = useState("");

  const handleButtonClick = async () => {
    try {
      const response = await axios.get("http://18.118.13.4:8080/user/login");
      console.log(response);
      setMessage(response.data);
    } catch (error) {
      console.error("Error fetching data: ", error);
      setMessage("Error connecting to backend");
    }
  };

  return (
    <div className="home d-flex ">
      <div className="logo col-6 pt-5">
        <img
          src="https://digital-lync.konalms.com/assets/logo.ab024049.png"
          alt="Logo"
        />
        <h1 className="mt-5">Welcome</h1>
        <p>Please sign in to your account below</p>
        <Link to="/login">
          <button className="btn btn-primary  w-50 mt-5">SignIn</button>
        </Link>
        <div>
        <button
          className="btn btn-primary  w-50 mt-5"
          onClick={handleButtonClick}
        >
          Test
        </button>
        </div>
        <div className="msg">{message}</div>
      </div>
      <div className="image col-6" style={{ height: "100vh" }}>
        <img
          src="https://digital-lync.konalms.com/assets/illustration-login.a3c562cb.jpg"
          alt="dlync-logo"
          width="100%"
          height="100%"
        />
      </div>
    </div>
  );
};

export default Home;

import React from "react";
import "./Home.css";
import logo from "../../assets/logo.png";
import { Link } from "react-router-dom";
const Home = () => {
  return (
    <div className="d-flex home">
      <div className="form-signin-container pt-5 w-50">
        <main className="form-signin-home text-center mr-auto">
          <form>
            <img
              className="mb-5 "
              src={logo}
              alt="Digital Lync Logo"
              width={250}
              height={90}
            />
            <h1 className="h3 mb-3 fw-normal mt-3 text-dark">Welcome</h1>
            <p className="text-muted">Please sign in to your account below</p>
            <Link to="/login">
              <button
                className="btn btn-primary mt-5 py-2 w-50"
                type="submit">
                Sign In
              </button>
            </Link>
          </form>
        </main>
      </div>
      <div className="background-img w-50">
        <img src="https://lms.digital-lync.com/assets/illustration-login.a3c562cb.jpg" width={'100%'} alt="Wallpaper" />
      </div>
    </div>
  );
};
export default Home;
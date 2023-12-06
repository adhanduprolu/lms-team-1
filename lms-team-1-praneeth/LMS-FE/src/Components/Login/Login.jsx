import "./Login.css";
import logo from "../../assets/logo.png";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { initializeLogin, login } from "./Login.actions";
const Login = () => {
  const initialValues = { email: "", password: "" };
  const [formValues, setFormValues] = useState(initialValues);
  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);
  const { responseStatus, errorMessage: loginErrorMessage } = useSelector(
    (state) => state.login
  );
  //DISPATCHING EMAIL AND PASSWORD FROM UI TO STORE
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(initializeLogin());
  }, []);
  const loginUser = () => {
    dispatch(login(formValues.email, formValues.password));
  };
  // ------------------------------------------------------------------------
  // GETTING EMAIL, PASSWORD, JWT FROM STORE
  const email = useSelector((state) => {
    console.log("state:", state);
    return state.login.email;
  });
  const password = useSelector((state) => state.login.password);
  const jwt = useSelector((state) => state.login.jwt);
  const userRole = useSelector((state) => state.login.role);
  const userName = useSelector((state) => state.login.name);
  console.log("role:", userRole);
  console.log("jwt:", jwt);
  console.log("pw :", password);
  console.log("name :", userName);
  //FORM HANDLING AND GETTING INPUT DATA FROM USER
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues({ ...formValues, [name]: value });
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    setFormErrors(validate(formValues));
    setIsSubmit(true);
  };
  //VALIDATING USER CREDENTIALS IN BACKEND AND ROUTING TO DASHBOARD
  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = useState("");
  useEffect(() => {
    if (Object.keys(formErrors).length === 0 && isSubmit && jwt) {
      localStorage.setItem("JWT Token", jwt);
      localStorage.setItem("Email", email);
      const COOKIE_NAME = "testCookie";
      const COOKIE_NAME2 = "emailCookie";
      document.cookie = `${COOKIE_NAME}=${jwt}`;
      document.cookie = `${COOKIE_NAME2}=${email}`;
      ///////////////////////////
      // async function fetchUserId() {
      //   try {
      //     const response = await fetch('/api/userId', {
      //       headers: { Authorization: Bearer ${localStorage.getItem('token')} },
      //     });
      //     if (!response.ok) {
      //       throw new Error('Failed to fetch user ID');
      //     }
      //     const { userId } = await response.json();
      // console.log(userId);
      //   } catch (error) {
      //     console.error(error);
      //   }
      // }
      // fetchUserId();
      ////////////////////////////
      if (userRole === "admin" || userRole === "user") {
        // window.cookies.set("cook", jwt);
        return navigate("/learnerdashboard");
      } else if (userRole === "superadmin") {
        // window.cookies.set("cook", jwt);
        return navigate("/superadmindashboard");
      }
      // // window.cookies.set("cook", jwt);
      // navigate("/superadmindashboard");
    }
    if (responseStatus === 400) {
      console.log("loginErrorMessage: ", loginErrorMessage);
      setErrorMessage(loginErrorMessage);
    }
  }, [formErrors, isSubmit, jwt, responseStatus]);
  //FORM VALIDATION ON FRONTEND
  const validate = (values) => {
    const errors = {};
    const regex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
    if (!values.email) {
      errors.email = "Email is required!!";
    } else if (!regex.test(values.email)) {
      errors.email = "This is not a valid E-mail format";
    }
    if (!values.password) {
      errors.password = "Password is required!!";
    } else if (values.password.length < 4) {
      errors.password = "Password must be more than 4 characters";
    } else if (values.password.length > 10) {
      errors.password = "Password cannot exceed than 10 characters";
    }
    return errors;
  };
  return (
    <div className="background-img login-page">
      <main className="form-signin text-center bg-white p-4">
        <form onSubmit={handleSubmit} className="p-2">
          <img
            className="mb-4 "
            src={logo}
            alt="Digital Lync Logo"
            width={200}
            height={70}
          />
          <h1 className="h3 mb-3 fw-normal text-secondary">Welcome</h1>
          {errorMessage ? (
            <div className="text-danger">{loginErrorMessage}</div>
          ) : (
            <p className="text-muted mt-4">
              Log in to Kona LMS to continue to DigitalLync
            </p>
          )}
          <p className="text-warning">{formErrors.email}</p>
          <div className="form-floating mt-4">
            <input
              type="email"
              className="form-control"
              id="floatingInput"
              placeholder="name@example.com"
              name="email"
              value={formValues.email}
              onChange={handleChange}
            />
            <label htmlFor="floatingInput">Email address</label>
          </div>
          <p className="text-warning">{formErrors.password}</p>
          <div className="form-floating mt-2">
            <input
              type="password"
              className="form-control"
              id="floatingPassword"
              placeholder="Password"
              name="password"
              value={formValues.password}
              onChange={handleChange}
            />
            <label htmlFor="floatingPassword">Password</label>
          </div>
          <div className="mb-3">
            <p className="span-text text-left mt-2">
              <Link to="/forgotpassword" className="text-decoration-none">
                {" "}
                <span className="span-text"> Forgot Password </span>{" "}
              </Link>
            </p>
          </div>
          <button
            className="w-100 btn btn-lg btn-primary"
            type="submit"
            onClick={loginUser}
          >
            Continue
          </button>
          {/* <p className="mt-5 mb-3 text-muted">
            Don't have an account?{" "}
            <Link to="/signup" className="text-decoration-none">
              {" "}
              <span className="span-text ">Sign Up</span>{" "}
            </Link>
          </p> */}
        </form>
      </main>
    </div>
  );
};
export default Login;
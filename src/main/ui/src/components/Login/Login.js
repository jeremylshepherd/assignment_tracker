import React, { useState } from "react";
import "./_components.login.scss";
import "../Assignment/_component.assignment.scss";
import Toggle from "../Toggle/Toggle";
import { useHistory, useLocation } from "react-router";
import useAuth from "../../auth/useAuth";

function Login() {
  const [signup, setSignup] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const history = useHistory();
  const location = useLocation();
  let auth = useAuth();
  let { from } = location.state || { from: { pathname: "/" } };

  const formHeadingText = () => (signup ? "Register" : "Login");

  const submitLogin = (e) => {
    e.preventDefault();
    const user = {
      username,
      password,
    };
    auth.authenticate(user, () => {
      history.replace(from);
    });
  };

  const submitRegistration = (e) => {
    e.preventDefault();
    const user = {
      username,
      password,
      firstName,
      lastName,
    };
    auth.register(user, () => {
      history.replace(from);
    });
  };

  const handleToggle = () => {
    console.log("setSignUp triggered");
    setSignup(!signup);
  };

  const renderToggle = () => (
    <Toggle
      state={signup}
      setter={handleToggle}
      label={`Need to ${!signup ? "register" : "login"}?`}
    />
  );

  return (
    <div className="login-page">
      <div className="login-form-container">
        <form className="assignment-form form-group">
          <div className="form-header">
            <h3 className="text-center mb-4">{formHeadingText()}</h3>
            {renderToggle()}
          </div>
          <input
            name="username"
            placeholder="Username"
            type="text"
            className="form-control mt-4"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <input
            name="password"
            placeholder="Password"
            type="password"
            className="form-control mt-4"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          {signup && (
            <>
              <input
                type="text"
                name="firstName"
                id="firstName"
                placeholder="First Name"
                className="form-control mt-4"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
              />
              <input
                type="text"
                name="lastName"
                id="lastName"
                placeholder="Last Name"
                className="form-control mt-4"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
              />
            </>
          )}
          <div className="assignment-form-btn-group">
            <button
              className="btn btn-info"
              onClick={(e) => {
                signup ? submitRegistration(e) : submitLogin(e);
              }}
              disabled={!username || !password}
            >
              Submit
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Login;

import React, { useState } from "react";
import { authService } from "../../authentication.service";

function Login() {
  const [signup, setSignup] = useState(false);
  const [username, setUsername] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [password, setPassword] = useState("");

  const formHeadingText = () => (signup ? "Register" : "Login");

  const submitLogin = (e) => {
    e.preventDefault();
    authService.authenticate(() => console.log("login"));
  };

  const submitRegistration = (e) => {
    e.preventDefault();
    authService.authenticate(() => console.log("login"));
  };

  const renderToggle = () => (
    <div className="custom-control custom-switch">
      <input
        className="custom-control-input"
        type="checkbox"
        id="flexSwitchCheckChecked"
        checked={signup}
        onChange={(e) => setSignup(e.target.checked)}
      />
      <label className="custom-control-label" htmlFor="flexSwitchCheckChecked">
        {`Need to ${!signup ? "register" : "login"}?`}
      </label>
    </div>
  );

  return (
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
  );
}

export default Login;

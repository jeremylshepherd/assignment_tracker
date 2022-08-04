import React from "react";
import { Link, useHistory } from "react-router-dom";
import useAuth from "../../auth/useAuth";
import "../nav/_component.nav.scss";

function Nav() {
  const auth = useAuth();
  const history = useHistory();
  const signOut = () => auth.signout(() => history.push("/"));

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/">
          <img
            className="navbar-logo"
            src="https://instructure-uploads.s3.amazonaws.com/account_30000000001939/attachments/67480601/Cincy_Nav%20Logo.png"
            alt="UC logo"
          />
          <i className="far fa-life-ring"></i>
        </Link>
        {auth.user && <span>{`Hello, ${auth.user.firstName}`}</span>}
        <button
          className="navbar-toggler collapsed"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarColor01"
          aria-controls="navbarColor01"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="navbar-collapse collapse" id="navbarColor01"></div>
        {auth.user && (
          <a className="nav-link signout-link" onClick={signOut}>
            Logout
          </a>
        )}
      </div>
    </nav>
  );
}

export default Nav;

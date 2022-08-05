import React from "react";
import { Redirect, Route } from "react-router";
import useAuth from "../../auth/useAuth";

function ProtectedRoute({ children, ...rest }) {
  let auth = useAuth();
  return (
    <Route
      {...rest}
      render={({ location }) => {
        return auth.user ? (
          children
        ) : (
          <Redirect
            to={{
              pathname: "/login",
              state: { from: location },
            }}
          />
        );
      }}
    />
  );
}

export default ProtectedRoute;

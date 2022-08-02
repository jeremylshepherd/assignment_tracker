import React from "react";
import { Redirect, Route } from "react-router";
import { authService } from "../../authentication.service";

function ProtectedRoute({ children, ...rest }) {
  return (
    <Route
      {...rest}
      render={() => {
        return authService.isAuthenticated === true ? (
          children
        ) : (
          <Redirect to="/login" />
        );
      }}
    />
  );
}

export default ProtectedRoute;

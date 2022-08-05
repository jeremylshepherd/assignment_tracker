import React, { useContext } from "react";
import { authContext } from "./ProvideAuth";

function useAuth() {
  return useContext(authContext);
}

export default useAuth;

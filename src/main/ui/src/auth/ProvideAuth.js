import React, { createContext } from "react";
import { useProvideAuth } from "./useProvideAuth";

export const authContext = createContext();

function ProvideAuth({ children }) {
  const auth = useProvideAuth();
  return <authContext.Provider value={auth}>{children}</authContext.Provider>;
}
export default ProvideAuth;

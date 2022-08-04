import React, { useState } from "react";
import { fetchUser } from "./authentication.api";
import { authService } from "./auth.service";

export function useProvideAuth() {
  const [user, setUser] = useState(null);

  const getAuthUser = async () => await fetchUser();

  const authenticate = (userDTO, cb) => {
    return authService.authenticate(userDTO, async () => {
      const user = await getAuthUser();
      setUser(user);
      cb();
    });
  };

  const register = (userDTO, cb) => {
    return authService.register(userDTO, async () => {
      authenticate(userDTO, cb);
    });
  };

  const signout = (cb) => {
    return authService.signout(() => {
      setUser(null);
      cb();
    });
  };

  return {
    user,
    authenticate,
    register,
    signout,
  };
}

export default useProvideAuth;

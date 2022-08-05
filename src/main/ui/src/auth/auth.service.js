import { auth, registerUser } from "./authentication.api";

export const authService = {
  isAuthenticated: false,
  async authenticate(user, cb) {
    console.log("authenticate hit.");
    await auth(user);
    this.isAuthenticated = true;
    cb();
  },
  async register(user, cb) {
    console.log("register hit.");
    await registerUser(user);
    this.isAuthenticated = true;
    cb();
  },
  signout(cb) {
    console.log("signout hit.");
    this.isAuthenticated = false;
    localStorage.removeItem("jwt");
    cb();
  },
};

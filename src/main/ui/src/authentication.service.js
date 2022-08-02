const API = `localhost:8080/auth`;
const DUMMY = `#`;

export async function getAuth() {
  return {
    id: 12345,
    name: "Jeremy Shepherd",
    email: "jeremylshepherd@gmail.com",
  };
}

export const authService = {
  isAuthenticated: false,
  user: null,
  authenticate(cb) {
    let authUser = getAuth();
    if (authUser) {
      this.isAuthenticated = true;
      this.user = authUser;
      setTimeout(cb, 200);
      return this.user;
    }
    return null;
  },
  signout(cb) {
    this.user = null;
    this.isAuthenticated = false;
    setTimeout(cb, 200);
  },
};

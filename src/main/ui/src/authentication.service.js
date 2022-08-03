const LOGIN_API = `localhost:8080/authenticate`;
const SIGNUP_API = `localhost:8080/register`;
const USER_API = `localhost:8080/register`;

export async function auth(user) {
  const response = await fetch(LOGIN_API, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  });
  return response;
}

export async function register(user) {
  const response = await fetch(SIGNUP_API, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  });
  return response;
}

export async function fetchUser(user, token) {
  return await fetch(USER_API, {
    method: "POST",
    headers: {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(user),
    },
  });
}

export async function getAuth(user) {
  const tokenResponse = auth(user);
  if (!tokenResponse.ok) {
    return;
  }
  const { token } = await tokenResponse.json();
  localStorage.setItem("jwt", token);
  const userResponse = fetchUser(user, token);
  if (!userResponse.ok) {
    return;
  }
  const data = await userResponse.json();
  return data.user;
}

export async function registerUser(user) {
  const regResponse = await register(user);
  if (!regResponse.ok) {
    return;
  }
  getAuth(user);
}

export class AuthService {
  _isAuthenticated = false;
  user = null;

  authenticate(loginUser, cb) {
    let authUser = getAuth(loginUser);
    if (authUser) {
      this.isAuthenticated = true;
      this.user = authUser;
      cb(this.user);
    }
    return null;
  }

  register(newUser, cb) {
    let authUser = registerUser(newUser);
    if (authUser) {
      this.isAuthenticated = true;
      this.user = authUser;
      cb(this.user);
    }
  }

  signout(cb) {
    this.user = null;
    this.isAuthenticated = false;
    localStorage.removeItem("jwt");
    cb(null);
  }
}

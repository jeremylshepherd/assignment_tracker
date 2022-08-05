const API = "http://localhost:8080";

const LOGIN_API = `${API}/authenticate`;
const SIGNUP_API = `${API}/register`;
const USER_API = `${API}/user`;

export async function auth(user) {
  const response = await fetch(LOGIN_API, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  });
  const json = await response.json();
  localStorage.setItem("jwt", json.token);
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

export async function fetchUser() {
  const token = localStorage.getItem("jwt");
  const response = await fetch(USER_API, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  });
  const user = await response.json();
  return user;
}

export async function registerUser(user) {
  const regResponse = await register(user);
  if (!regResponse.ok) {
    return;
  }
  const { username, password } = user;
  auth({ username, password });
}

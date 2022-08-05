const API = "http://localhost:8080/api/assignments";

export async function getAssignments(setAssignments) {
  const token = localStorage.getItem("jwt");
  const response = await fetch(API, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  const json = await response.json();
  setAssignments(json);
}

export async function postNewAssignmentAPI(assignment) {
  const token = localStorage.getItem("jwt");
  const response = await fetch(API, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify(assignment),
  });
  return response;
}

export async function putUpdateAssignmentAPI(assignment) {
  const token = localStorage.getItem("jwt");
  const response = await fetch(API, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify(assignment),
  });
  return response;
}

export async function deleteAssignmentAPI(id) {
  const token = localStorage.getItem("jwt");
  console.log("deleteAssignmentAPI: ", id);
  const response = await fetch(`${API}/${id}`, {
    method: "DELETE",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response;
}

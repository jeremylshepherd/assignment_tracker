const API = "http://rhinestone-earthy-cathedral.glitch.me/api/assignments"; // Quick interaction server to mimic our Spring version of API
// const API = "http://localhost:8080/api/assignments";

export async function getAssignments(setAssignments) {
  const response = await fetch(API);
  const json = await response.json();
  setAssignments(json);
}

export async function postNewAssignmentAPI(assignment) {
  const response = await fetch(API, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(assignment),
  });
  return response;
}

export async function putUpdateAssignmentAPI(assignment) {
  const response = await fetch(API, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(assignment),
  });
  return response;
}

export async function deleteAssignmentAPI(id) {
  console.log("deleteAssignmentAPI: ", id);
  const response = await fetch(`${API}/${id}`, {
    method: "DELETE",
  });
  return response;
}

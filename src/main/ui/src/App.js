import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import "../node_modules/@fortawesome/fontawesome-free/css/all.min.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../node_modules/bootswatch/dist/darkly/bootstrap.min.css";
import "./App.css";
import Nav from "./components/nav/Nav";
import ProtectedRoute from "./components/ProtectedRoute/ProtectedRoute";
import Login from "./components/Login/Login";
import AssignmentsPage from "./components/Assignment/AssignmentsPage";
import {
  getAssignments,
  postNewAssignmentAPI,
  putUpdateAssignmentAPI,
  deleteAssignmentAPI,
} from "./components/Assignment/assignments.api";
import useAuth from "./auth/useAuth";

function App() {
  const [assignmentList, setAssignmentList] = useState([]);
  const [newAssignment, setNewAssignment] = useState(false);
  const auth = useAuth();
  const [user, setUser] = useState(auth?.user);

  async function createAssignment(assignment) {
    assignment.status = assignment.status.toUpperCase();
    const response = await postNewAssignmentAPI(assignment);
    if (response.status === 200) {
      getAssignments(setAssignmentList);
    }
  }

  async function updateAssignment(assignment) {
    assignment.status = assignment.status.toUpperCase();
    const response = await putUpdateAssignmentAPI(assignment);
    if (response.status === 200) {
      getAssignments(setAssignmentList);
    }
  }

  async function deleteAssignment(id) {
    console.log("deleteAssignment: ", id);
    const response = await deleteAssignmentAPI(id);
    if (response.status === 200) {
      getAssignments(setAssignmentList);
    }
  }

  useEffect(() => {
    getAssignments(setAssignmentList);
  }, [auth?.user?.username]);

  const closeAssignment = () => setNewAssignment(false);

  return (
    <Router>
      <div className="App">
        <Nav user={user} />
        <Switch>
          <Route path="/login">
            <Login setUser={setUser} />
          </Route>
          <ProtectedRoute path="/">
            <AssignmentsPage
              assignmentList={assignmentList}
              updateAssignment={updateAssignment}
              deleteAssignment={deleteAssignment}
              newAssignment={newAssignment}
              closeAssignment={closeAssignment}
              createAssignment={createAssignment}
              setNewAssignment={setNewAssignment}
            />
          </ProtectedRoute>
        </Switch>
      </div>
    </Router>
  );
}

export default App;

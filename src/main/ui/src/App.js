import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import "../node_modules/@fortawesome/fontawesome-free/css/all.min.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../node_modules/bootswatch/dist/darkly/bootstrap.min.css";
import "./App.css";
import AssignmentList from "./components/Assignment/AssignmentList";
import AssignmentForm from "./components/Assignment/AssignmentForm";
import Nav from "./components/nav/Nav";
import {
  getAssignments,
  postNewAssignmentAPI,
  putUpdateAssignmentAPI,
  deleteAssignmentAPI,
} from "./components/Assignment/assignments.api";

function App() {
  const [assignmentList, setAssignmentList] = useState([]);
  const [newAssignment, setNewAssignment] = useState(false);

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
  }, []);

  const closeAssignment = () => setNewAssignment(false);

  return (
    <Router>
      <div className="App">
        <Nav />
        <h1 className="text-center App__title">Asignment Tracker</h1>
        <h3 className="text-center">Assignments</h3>
        <Switch>
          <Route path="/">
            <AssignmentList
              assignments={assignmentList}
              updateAssignment={updateAssignment}
              deleteAssignment={deleteAssignment}
            />
          </Route>
        </Switch>
        {newAssignment && (
          <AssignmentForm
            closeForm={closeAssignment}
            updateAssignment={updateAssignment}
            createAssignment={createAssignment}
          />
        )}
        <div className="form-btn-group">
          <button
            className="btn btn-info btn-circle"
            onClick={() => setNewAssignment(true)}
          >
            <i className="fas fa-plus" />
          </button>
        </div>
      </div>
    </Router>
  );
}

export default App;

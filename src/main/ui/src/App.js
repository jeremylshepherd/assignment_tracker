import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import "../node_modules/@fortawesome/fontawesome-free/css/all.min.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../node_modules/bootswatch/dist/darkly/bootstrap.min.css";
import "./App.css";
import AssignmentList from "./components/Assignment/AssignmentList";
import AssignmentForm from "./components/Assignment/AssignmentForm";
import Nav from "./components/nav/Nav";

const SERVER_PATH = "http://localhost:8080";
const assignmentsAPI = `${SERVER_PATH}/api/assignments`;

async function getItems(api, setItems) {
  const response = await fetch(api);
  const json = await response.json();
  setItems(json);
}

async function updateItemList(api, listToUpdate, setter, item) {
  const updatedList = [...listToUpdate, item];
  let data = JSON.stringify(updatedList, null, 4);
  const response = await fetch(api, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: data,
  });
  const status = response.status;
  if (status === 200) {
    getItems(api, setter);
  }
}

function App() {
  const [assignmentList, setAssignmentList] = useState([]);
  const [newAssignment, setNewAssignment] = useState(false);

  async function updateAssignment(assignment) {
    const idx = assignmentList.findIndex((t) => t.id === assignment.id);
    const assignmentsCopy = [...assignmentList];
    assignmentsCopy.splice(idx, 1);
    updateItemList(
      assignmentsAPI,
      assignmentsCopy,
      setAssignmentList,
      assignment
    );
  }
  useEffect(() => {
    getItems(assignmentsAPI, setAssignmentList);
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
              updateTask={updateAssignment}
            />
          </Route>
        </Switch>
        {newAssignment && (
          <AssignmentForm
            tasks={assignmentList}
            closeForm={closeAssignment}
            setTaskList={setAssignmentList}
            api={assignmentsAPI}
            update={updateItemList}
          />
        )}
        <div className="form-btn-group">
          <button
            className="btn btn-info"
            onClick={() => setNewAssignment(true)}
          >
            <i className="fas fa-tasks" />
          </button>
        </div>
      </div>
    </Router>
  );
}

export default App;

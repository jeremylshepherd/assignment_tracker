import React, { useState } from "react";
import { Status } from "../../utils/constants";

function AssignmentForm(props) {
  const { api, assignments, setAssignmentList, closeForm, update } = props;
  const [id, setId] = useState(Date.now());
  const [assignment, setAssignment] = useState("");
  const [status, setStatus] = useState(Status.PENDING);

  const submitAssignment = () => {
    let newAssignment = { id, assignment, status };
    update(api, assignments, setAssignmentList, newAssignment);
    resetForm();
    closeForm();
  };

  const resetForm = () => {
    setAssignment("");
    setStatus(Status.PENDING);
  };

  const handleStatusUpdate = (e) => {
    let value = e.target.value;
    let stat;
    if (value === "INPROGRESS") {
      stat = Status.INPROGRESS;
    } else if (value === "COMPLETE") {
      stat = Status.COMPLETE;
    } else {
      stat = Status.PENDING;
    }
    setStatus(stat);
  };

  return (
    <div className="assignment-form-container">
      <form className="assignment-form form-group">
        <h3 className="text-center">Add New Assignment</h3>
        <input
          name="assignment"
          placeholder="Assignment"
          type="text"
          className="form-control mt-4"
          value={assignment}
          onChange={(e) => setAssignment(e.target.value)}
        />
        <select
          className="form-control mt-4"
          onChange={(e) => handleStatusUpdate(e)}
        >
          {Object.keys(Status).map((key) => (
            <option key={key} value={key}>
              {key}
            </option>
          ))}
        </select>
        <div className="assignment-form-btn-group">
          <button
            className="btn btn-info"
            onClick={() => {
              setId(Date.now());
              submitAssignment();
            }}
            disabled={!assignment || !status}
          >
            Submit
          </button>
          <button className="btn btn-danger" onClick={() => closeForm()}>
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
}

export default AssignmentForm;

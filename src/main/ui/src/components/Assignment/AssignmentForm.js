import React, { useState } from "react";
import { Status } from "../../utils/constants";

function AssignmentForm(props) {
  const {
    api,
    assignments,
    setAssignmentList,
    closeForm,
    updateList,
    updateAssignment,
    assignmentToUpdate,
  } = props;
  const [title, setTitle] = useState(assignmentToUpdate?.title ?? "");
  const [description, setDescription] = useState(
    assignmentToUpdate?.description ?? ""
  );
  const [status, setStatus] = useState(
    assignmentToUpdate?.status ?? Status.PENDING
  );
  const [dateTime, setDateTime] = useState(assignmentToUpdate?.date ?? "");

  const formHeadingText = () => {
    return assignmentToUpdate ? "Update" : "Add New";
  };

  const submitAssignment = () => {
    if (assignmentToUpdate && updateAssignment) {
      let updatedAssignment = { title, description, status, date: dateTime };
      updateAssignment(updatedAssignment);
    } else {
      const newAssignment = {
        title: title,
        description,
        status,
        date: dateTime,
      };
      updateList(api, assignments, setAssignmentList, newAssignment);
    }
    resetForm();
    closeForm();
  };

  const resetForm = () => {
    setTitle("");
    setDescription("");
    setDateTime("");
    setStatus(Status.PENDING);
  };

  const handleDescriptionChange = (e) => {
    console.log(e.target.value);
    setDescription(e.target.value);
  };

  const handleDateTimeChange = (e) => {
    console.log(e.target.value);
    setDateTime(e.target.value);
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
        <h3 className="text-center">{formHeadingText()}</h3>
        <input
          name="assignment"
          placeholder="Assignment"
          type="text"
          className="form-control mt-4"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <textarea
          name="description"
          placeholder="Description (320 characters max)"
          maxLength={320}
          className="form-control mt-4"
          rows={3}
          value={description}
          onChange={(e) => handleDescriptionChange(e)}
        />
        <div className="input-group mt-2">
          <input
            type="datetime-local"
            name="date"
            id="date"
            className="form-control"
            value={dateTime}
            onChange={(e) => handleDateTimeChange(e)}
          />
        </div>
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
              submitAssignment();
            }}
            disabled={!title || !status}
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

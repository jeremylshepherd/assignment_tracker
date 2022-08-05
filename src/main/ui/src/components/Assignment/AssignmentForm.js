import React, { useEffect, useState } from "react";
import { Status } from "../../utils/constants";
import { truncateDate } from "../../utils/dateUtils";

function AssignmentForm(props) {
  const { closeForm, updateAssignment, createAssignment, assignmentToUpdate } =
    props;
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [status, setStatus] = useState(Status.PENDING);
  const [dateTime, setDateTime] = useState("");

  useEffect(() => {
    if (assignmentToUpdate !== null) {
      setTitle(assignmentToUpdate?.title);
      setDescription(assignmentToUpdate?.description);
      setStatus(assignmentToUpdate?.status);
      setDateTime(truncateDate(assignmentToUpdate?.date));
    }
  }, [assignmentToUpdate]);

  const formHeadingText = () => {
    return assignmentToUpdate ? "Update" : "Add New";
  };

  const submitAssignment = () => {
    if (assignmentToUpdate && updateAssignment) {
      let updatedAssignment = {
        id: assignmentToUpdate.id,
        title,
        description,
        status: status,
        date: dateTime,
      };
      updateAssignment(updatedAssignment);
    } else {
      const newAssignment = {
        title: title,
        description,
        status: status,
        date: dateTime,
      };
      createAssignment(newAssignment);
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
    console.log(status);
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
          value={status}
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

import React, { useState } from "react";
import { Status } from "../../utils/constants";
import "./_component.assignment.scss";

function Assignment(props) {
  const { id, title, status, updateAssignment } = props;
  const [currentStatus, setCurrentStatus] = useState(status);
  const [showUpdate, setShowUpdate] = useState(false);
  const statusIcon = {
    pending: "redo",
    inProgress: "spinner",
    complete: "check-circle",
  };
  const getIcon = (obj, status) => obj[status];
  const dateString = new Date(id).toLocaleDateString("en-us", {
    month: "long",
    year: "numeric",
    day: "2-digit",
  });
  const renderStatusUpdate = () => (
    <div className="assignment-alert">
      <div className="alert alert-info">
        <form className="form-group form-check">
          <strong>Update Assignment Status!</strong>
          <div className="cb-group">
            {Object.keys(Status).map((key) => (
              <div className="cb-row text-left">
                <input
                  className="form-check-input"
                  type="checkbox"
                  name={key}
                  value={key}
                  id={key}
                  onClick={handleStatusUpdate}
                />
                <label htmlFor={key} className="form-check-label">
                  {key}
                </label>
              </div>
            ))}
          </div>
          <div className="button-group">
            <button
              className="btn btn-danger"
              onClick={(e) => {
                e.preventDefault();
                setShowUpdate(false);
              }}
            >
              Close
            </button>
            <button
              className="btn btn-primary"
              onClick={(e) => {
                e.preventDefault();
                updateAssignmentStatus();
              }}
            >
              Update
            </button>
          </div>
        </form>
      </div>
    </div>
  );

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
    setCurrentStatus(stat);
  };

  const updateAssignmentStatus = () => {
    let updatedAssignment = {
      id,
      title,
      status: currentStatus,
    };
    updateAssignment(updatedAssignment);
    setShowUpdate(false);
  };

  return (
    <div className={`assignment assignment-${status.toLowerCase()}`}>
      <h2 className="assignment-heading">{title}</h2>
      <div className="assignment-footer">
        <div className="assignment-footer-date">{dateString}</div>
        <div className="assignment-footer-status">
          <span
            className="assignment-footer-status-text"
            onClick={() => setShowUpdate(true)}
          >
            {status}
          </span>
          <i
            className={`fas fa-${getIcon(statusIcon, status.toLowerCase())}`}
          />
        </div>
      </div>
      {showUpdate && renderStatusUpdate()}
    </div>
  );
}

export default Assignment;

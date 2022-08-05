import React, { useState } from "react";
import "./_component.assignment.scss";
import AssignmentForm from "./AssignmentForm";
import AssignmentDeleteConfirm from "./AssignmentDeleteConfirm";

function Assignment(props) {
  const {
    id,
    title,
    description,
    status,
    date,
    updateAssignment,
    deleteAssignment,
  } = props;
  const [showUpdate, setShowUpdate] = useState(false);
  const [showDelete, setShowDelete] = useState(false);
  const statusIcon = {
    pending: "redo",
    inprogress: "spinner",
    complete: "check-circle",
  };
  const getIcon = (obj, status) => obj[status.toLowerCase()];
  const dateString = new Date(date).toLocaleDateString("en-us", {
    month: "long",
    year: "numeric",
    day: "2-digit",
  });

  const closeForm = () => setShowUpdate(false);
  const closeDialog = () => setShowDelete(false);

  const handleDelete = (id) => {
    deleteAssignment(id);
    closeDialog();
  };

  const renderDescription = () => {
    let descriptionArray = description?.split("\n");
    return (
      <ul>
        {descriptionArray?.map((com, i) => (
          <li key={i} className="text-left">
            {com}
          </li>
        ))}
      </ul>
    );
  };

  const renderDeleteButton = () => {
    return (
      <i
        className="far fa-trash-alt"
        onClick={() => {
          console.log("delete button hit");
          setShowDelete(true);
        }}
      />
    );
  };

  const renderUpdateButton = () => (
    <i className="fas fa-edit" onClick={(e) => setShowUpdate(true)} />
  );

  const renderUpdateForm = () => {
    const assignment = { id, title, description, status, date };
    return (
      <AssignmentForm
        assignmentToUpdate={assignment}
        updateAssignment={updateAssignment}
        closeForm={closeForm}
      />
    );
  };

  const renderDeleteDialog = () => {
    return (
      <AssignmentDeleteConfirm
        id={id}
        handleDelete={handleDelete}
        close={closeDialog}
      />
    );
  };

  return (
    <div className={`assignment assignment-${status.toLowerCase()}`}>
      <div className="assignment-header">
        {Boolean(id) && renderDeleteButton()}
        <h3 className="assignment-heading">{title}</h3>
        {Boolean(id) && renderUpdateButton()}
      </div>
      {description && (
        <div className="assignment-body">{renderDescription()}</div>
      )}
      <div className="assignment-footer">
        <div className="assignment-footer-date">{dateString}</div>
        <div className="assignment-footer-status">
          <span
            className="assignment-footer-status-text"
            onClick={() => setShowUpdate(true)}
          >
            {status}
          </span>
          <i className={`fas fa-${getIcon(statusIcon, status)}`} />
        </div>
      </div>
      {Boolean(id) && showUpdate && renderUpdateForm()}
      {Boolean(id) && showDelete && renderDeleteDialog()}
    </div>
  );
}

export default Assignment;

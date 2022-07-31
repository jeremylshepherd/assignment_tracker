import React from "react";

const AssignmentDeleteConfirm = ({ id, handleDelete, close }) => {
  return (
    <div className="assignment-form-container">
      <div className="card bg-dark">
        <div className="card-header d-flex align-items-center justify-content-between">
          <h4 className="card-title mt-2">Confirm Delete</h4>
          <i className="fas fa-window-close text-danger" onClick={close} />
        </div>
        <div className="card-body">
          <h6>Confirm that you wish to delete this assignment.</h6>
          <p>You cannot undo this action</p>
        </div>
        <div className="card-footer bg-dark d-flex justify-content-center">
          <button
            type="button"
            className="btn btn-danger mr-2"
            onClick={() => handleDelete(id)}
          >
            Delete
          </button>
          <button type="button" className="btn btn-info ml-2" onClick={close}>
            Close
          </button>
        </div>
      </div>
    </div>
  );
};

export default AssignmentDeleteConfirm;

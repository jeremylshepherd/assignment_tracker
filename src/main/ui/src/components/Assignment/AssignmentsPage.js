import React from "react";
import AssignmentList from "./AssignmentList";
import AssignmentForm from "./AssignmentForm";

function AssignmentsPage({
  assignmentList,
  updateAssignment,
  deleteAssignment,
  newAssignment,
  closeAssignment,
  createAssignment,
  setNewAssignment,
}) {
  return (
    <>
      <h1 className="text-center App__title">Assignment Tracker</h1>
      <h3 className="text-center">Assignments</h3>
      <AssignmentList
        assignments={assignmentList}
        updateAssignment={updateAssignment}
        deleteAssignment={deleteAssignment}
      />
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
    </>
  );
}

export default AssignmentsPage;
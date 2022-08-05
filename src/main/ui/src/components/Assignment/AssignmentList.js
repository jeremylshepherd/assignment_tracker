import React, { useEffect, useState } from "react";
import Assignment from "./Assignment";

function AssignmentList(props) {
  const { assignments, updateAssignment, deleteAssignment } = props;
  const [pendingList, setPendingList] = useState([]);
  const [inProgressList, setInProgressList] = useState([]);
  const [completeList, setCompleteList] = useState([]);

  const parseAssignmentList = (assignmentList) => {
    let obj = {
      pending: [],
      inprogress: [],
      complete: [],
    };
    if (assignmentList.length > 0) {
      assignmentList?.forEach((a) => obj[a.status.toLowerCase()]?.push(a));
    }
    setPendingList(obj.pending);
    setInProgressList(obj.inprogress);
    setCompleteList(obj.complete);
  };
  useEffect(() => {
    parseAssignmentList(assignments);
  }, [assignments]);

  const renderStatusList = (list) =>
    list.map((a) => (
      <Assignment
        key={a.id}
        {...a}
        updateAssignment={updateAssignment}
        deleteAssignment={deleteAssignment}
      />
    ));

  return (
    <div className="assignment-list">
      <div className="assignment-list__column">
        <div className="assignment-list__header">
          <h5>Pending</h5>
        </div>
        <div className="assignment-list__column-content">
          {renderStatusList(pendingList)}
        </div>
      </div>
      <div className="assignment-list__column">
        <div className="assignment-list__header">
          <h5>In Progress</h5>
        </div>
        <div className="assignment-list__column-content">
          {renderStatusList(inProgressList)}
        </div>
      </div>
      <div className="assignment-list-column">
        <div className="assignment-list__header">
          <h5>Complete</h5>
        </div>
        <div className="assignment-list-column-content">
          {renderStatusList(completeList)}
        </div>
      </div>
    </div>
  );
}

export default AssignmentList;

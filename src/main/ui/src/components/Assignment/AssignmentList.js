import React, { useEffect, useState } from "react";
import Assignment from "./Assignment";

function AssignmentList(props) {
  const { assignments, updateAssignment } = props;
  const [pendingList, setPendingList] = useState([]);
  const [inProgressList, setInProgressList] = useState([]);
  const [completeList, setCompleteList] = useState([]);

  const parseAssignmentList = (assignmentList) => {
    let obj = {
      pending: [],
      inProgress: [],
      complete: [],
    };
    assignmentList?.forEach((a) => obj[a.status.toLowerCase()].push(a));
    setPendingList(obj.pending);
    setInProgressList(obj.inProgress);
    setCompleteList(obj.complete);
  };
  useEffect(() => {
    parseAssignmentList(assignments);
  }, [assignments]);

  const renderStatusList = (list) =>
    list.map((a) => (
      <Assignment key={a.id} {...a} updateAssignment={updateAssignment} />
    ));

  return (
    <div className="assignment-list">
      <div className="assignment-list-column">
        <div className="assignment-list-column-content">
          {renderStatusList(pendingList)}
        </div>
      </div>
      <div className="assignment-list-column">
        <div className="assignment-list-column-content">
          {renderStatusList(inProgressList)}
        </div>
      </div>
      <div className="assignment-list-column">
        <div className="assignment-list-column-content">
          {renderStatusList(completeList)}
        </div>
      </div>
    </div>
  );
}

export default AssignmentList;

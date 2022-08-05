import React from "react";
import "./_components.toggle.scss";

function Toggle({ state, setter, label }) {
  return (
    <div className="toggle">
      <div className="toggle-switch-container">
        <input
          id={`iOS-${label}`}
          type="checkbox"
          data-style="ios"
          className="check iOS"
          checked={state}
          onChange={setter}
        />
        <label htmlFor={`iOS-${label}`} className="check-label">
          <span />
        </label>
      </div>
      <span className="toggle-label">{label}</span>
    </div>
  );
}

export default Toggle;

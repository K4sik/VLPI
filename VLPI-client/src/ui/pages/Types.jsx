import React from "react";
import { Link } from "react-router-dom";

import type1 from "../../public/images/type_1.jpg";
import type2 from "../../public/images/type_2.jpg";

export default function Types() {
  return (
    <div className="type-page">
      <div className="type-block">
        <Link to="test">
          <div className="type-test">
            <div className="type-name-test">
              <p>Testing Tasks</p>
            </div>
            <img src={type1} alt="Test" />
          </div>
        </Link>

        <Link to="dragdrop">
          <div className="type-drag">
            <div className="type-name-drag">
              <p>Drag&Drop Tasks</p>
            </div>
            <img src={type2} alt="Drag-Drop" />
          </div>
        </Link>
      </div>
    </div>
  );
}

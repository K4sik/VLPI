import React from "react";
import { Link } from "react-router-dom";

import pic1 from "../../public/images/1.jpg";
import pic2 from "../../public/images/2.jpg";
import pic3 from "../../public/images/3.jpg";
import pic4 from "../../public/images/4.jpg";
import pic5 from "../../public/images/5.jpg";

export default function Modules() {
  return (
    <div className="modules-block">
      <div className="modules-line">
        <Link to="requirement">
          <div className="module">
            <div className="module-img">
              <img src={pic1} alt="Requirement Analysis" />
            </div>
            <div className="module-name">
              <p>Requirement Analysis</p>
            </div>
          </div>
        </Link>

        <Link to="design">
          <div className="module">
            <div className="module-img">
              <img src={pic2} alt="Design" />
            </div>
            <div className="module-name">
              <p>Design</p>
            </div>
          </div>
        </Link>

        <Link to="modelling">
          <div className="module">
            <div className="module-img">
              <img src={pic3} alt="Modelling" />
            </div>
            <div className="module-name">
              <p>Modelling</p>
            </div>
          </div>
        </Link>
      </div>

      <div className="modules-line">
        <Link to="coding">
          <div className="module">
            <div className="module-img">
              <img src={pic4} alt="Coding" />
            </div>
            <div className="module-name">
              <p>Coding</p>
            </div>
          </div>
        </Link>

        <Link to="testing">
          <div className="module">
            <div className="module-img">
              <img src={pic5} alt="Testing" />
            </div>
            <div className="module-name">
              <p>Testing</p>
            </div>
          </div>
        </Link>
      </div>
    </div>
  );
}

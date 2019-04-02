import React from 'react';
import {Link} from "react-router-dom";
import 'react-confirm-alert/src/react-confirm-alert.css';
import {confirmAlert} from "react-confirm-alert"; // Import css


const ProjectItem = ({projectName, projectIdentifier, description, deleteProject}) => {
    const options = {
        message: '프로젝트를 삭제 하시겠습니까?',
        buttons: [
            {
                label: 'Yes',
                onClick: () => {
                    deleteProject(projectIdentifier);
                }
            },
            {
                label: 'No',
            }
        ]
    };
    return (
        <div className="card card-body bg-light mb-3">
            <div className="row">
                <div className="col-2">
                    <span className="mx-auto">{projectIdentifier}</span>
                </div>
                <div className="col-lg-6 col-md-4 col-8">
                    <h3>{projectName}</h3>
                    <p>{description}</p>
                </div>
                <div className="col-md-4 d-none d-lg-block">
                    <ul className="list-group">
                        <Link to={`/projectBoard/${projectIdentifier}`}>
                            <li className="list-group-item board">
                                <i className="fa fa-flag-checkered pr-1">Project Board </i>
                            </li>
                        </Link>
                        <Link to={`/project/${projectIdentifier}/create`}>
                            <li className="list-group-item update">
                                <i className="fa fa-edit pr-1">Update Project Info</i>
                            </li>
                        </Link>
                        <a onClick={() => confirmAlert(options)} href={"#"}>
                            <li className="list-group-item delete">
                                <i className="fa fa-minus-circle pr-1">Delete Project</i>
                            </li>
                        </a>
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default ProjectItem;
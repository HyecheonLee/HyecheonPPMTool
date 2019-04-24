import React, {Component} from 'react';
import {Link} from "react-router-dom";

class ProjectTask extends Component {
    render() {
        const {project_task} = this.props;
        return (
            <div className="card mb-1 bg-light">
                <div className={`card-header text-primary 
                ${project_task.priority === 1 && "bg-danger text-light"}
                ${project_task.priority === 2 && "bg-warning text-light"}
                ${project_task.priority === 3 && "bg-info text-light"}`}>
                    ID: {project_task.projectSequence} -- Priority:{" "} {project_task.priority}
                </div>
                <div className="card-body bg-light">
                    <h5 className="card-title">{project_task.summary}</h5>
                    <p className="card-text text-truncate ">
                        {project_task.acceptanceCriteria}
                    </p>
                    <Link className="btn btn-primary" to={`/updateProjectTask/${project_task.projectIdentifier}/${project_task.projectSequence}`}>
                        View / Update
                    </Link>
                    <button className="btn btn-danger ml-4">
                        Delete
                    </button>
                </div>
            </div>
        );
    }
}

export default ProjectTask;
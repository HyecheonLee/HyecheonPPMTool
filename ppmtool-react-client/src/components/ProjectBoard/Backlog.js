import React, {Component} from 'react';
import ProjectTask from "./ProjectTasks/ProjectTask";

class Backlog extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        const {project_tasks_prop} = this.props;
        const groupByProjectTask = project_tasks_prop.reduce((xs, x) => {
            (xs[x.status] = xs[x.status] || []).push(<ProjectTask key={x.id} project_task={x}/>);
            return xs;
        }, {});

        return (
            <div className="container">
                <div className="container">
                    <div className="row">
                        <div className="col-md-4">
                            <div className="card text-center mb-2">
                                <div className="card-header bg-secondary text-white">
                                    <h3>TO DO</h3>
                                </div>
                            </div>
                            {groupByProjectTask['TODO']}
                        </div>
                        <div className="col-md-4">
                            <div className="card text-center mb-2">
                                <div className="card-header bg-primary text-white">
                                    <h3>In Progress</h3>
                                </div>
                            </div>
                            {groupByProjectTask['IN_PROGRESS']}
                        </div>
                        <div className="col-md-4">
                            <div className="card text-center mb-2">
                                <div className="card-header bg-success text-white">
                                    <h3>Done</h3>
                                </div>
                            </div>
                            {groupByProjectTask['DONE']}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Backlog;
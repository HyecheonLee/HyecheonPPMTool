import React, {Component} from 'react';
import {Link} from "react-router-dom";
import {connect} from "react-redux";
import {createProjectTask, getProjectTask} from "../../../actions/backlogActions";
import classnames from 'classnames';

class CreateOrUpdateProjectTask extends Component {
    constructor(props) {
        super(props);
        const {id} = this.props.match.params;
        this.state = {
            id: "",
            summary: "",
            acceptanceCriteria: "",
            status: "",
            priority: 0,
            dueDate: "",
            projectIdentifier: id,
            errors: {},
            isUpdate: false,
        };
    }

    onSubmitHandler = (e) => {
        e.preventDefault();
        const {createProjectTask} = this.props;
        const newTask = {
            id: this.state.id,
            summary: this.state.summary,
            acceptanceCriteria: this.state.acceptanceCriteria,
            status: this.state.status,
            priority: this.state.priority,
            dueDate: this.state.dueDate,
            projectIdentifier: this.state.projectIdentifier,
        };
        const {id} = this.props.match.params;
        createProjectTask(id, newTask, this.props.history);
    };
    onChangeHandler = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    componentWillReceiveProps(nextProps, nextContext) {
        if (!!nextProps.errors) {
            this.setState({
                errors: nextProps.errors
            })
        }

        if (nextProps.projectTask) {
            this.setState({
                id: nextProps.projectTask.id,
                summary: nextProps.projectTask.summary,
                acceptanceCriteria: nextProps.projectTask.acceptanceCriteria,
                status: nextProps.projectTask.status,
                priority: nextProps.projectTask.priority,
                dueDate: nextProps.projectTask.dueDate,
                projectIdentifier: nextProps.projectTask.projectIdentifier,
            });
        }
    }

    componentDidMount() {
        const {id, ptId} = this.props.match.params;
        if (!!ptId) {
            const {getProjectTask} = this.props;
            getProjectTask(id, ptId);
        }
    }

    render() {
        const {id} = this.props.match.params;
        const {errors} = this.state;
        return (
            <div className="add-PBI">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <Link to={`/projectBoard/${id}`} className="btn btn-light">
                                Back to Project Board
                            </Link>
                            <h4 className="display-4 text-center">Add /Update Project Task</h4>
                            <p className="lead text-center">Project Name + Project Code</p>
                            <form onSubmit={this.onSubmitHandler}>
                                <div className="form-group">
                                    <input type="text" className={classnames("form-control form-control-lg", {
                                        "is-invalid": errors.summary
                                    })} name="summary"
                                           value={this.state.summary}
                                           placeholder="Project Task summary"
                                           onChange={this.onChangeHandler}
                                    />
                                    {
                                        errors.summary && (<div className={"invalid-feedback"}>{errors.summary}</div>)
                                    }
                                </div>
                                <div className="form-group">
                                    <textarea className={classnames("form-control form-control-lg", {
                                        "is-invalid": errors.summary
                                    })} placeholder="Acceptance Criteria"
                                              name="acceptanceCriteria"
                                              value={this.state.acceptanceCriteria}
                                              onChange={this.onChangeHandler}
                                    />
                                    {
                                        errors.acceptanceCriteria && (
                                            <div className={"invalid-feedback"}>{errors.acceptanceCriteria}</div>)
                                    }
                                </div>
                                <h6>Due Date</h6>
                                <div className="form-group">
                                    <input type="date" className="form-control form-control-lg"
                                           name="dueDate"
                                           value={this.state.dueDate}
                                           onChange={this.onChangeHandler}
                                    />
                                    {
                                        errors.dueDate && (<div className={"invalid-feedback"}>{errors.dueDate}</div>)
                                    }
                                </div>
                                <div className="form-group">
                                    <select className="form-control form-control-lg" name="priority"
                                            value={this.state.priority}
                                            onChange={this.onChangeHandler}
                                    >
                                        <option value={0}>Select Priority</option>
                                        <option value={1}>High</option>
                                        <option value={2}>Medium</option>
                                        <option value={3}>Low</option>
                                    </select>
                                </div>

                                <div className="form-group">
                                    <select className="form-control form-control-lg"
                                            name="status"
                                            value={this.state.status}
                                            onChange={this.onChangeHandler}
                                    >
                                        <option value="">Select Status</option>
                                        <option value="TO_DO">TO DO</option>
                                        <option value="IN_PROGRESS">IN PROGRESS</option>
                                        <option value="DONE">DONE</option>
                                    </select>
                                </div>

                                <input type="submit" className="btn btn-primary btn-block mt-4"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = state => ({
    errors: state.errors,
    projectTask: state.backlog.project_task,
});
export default connect(mapStateToProps, {getProjectTask, createProjectTask})(CreateOrUpdateProjectTask);
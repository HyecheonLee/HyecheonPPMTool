import React, {Component, Fragment} from 'react';
import {connect} from 'react-redux';
import {createOrUpdateProject, getProject} from "../../actions/projectActions";
import PropTypes from "prop-types";
import classnames from 'classnames';


class CreateOrUpdateProject extends Component {

    constructor(props, context) {
        super(props, context);
        this.state = {
            projectName: "",
            projectIdentifier: "",
            description: "",
            startDate: "",
            endDate: "",
            id: '',
            isUpdate: false,
            errors: {}
        };
    }

    componentDidMount() {
        const {match} = this.props;
        if (match.url.includes("update")) {
            this.setState({
                isUpdate: true
            });
            const findProject = this.props.projectTask.projects.find(project => project.projectIdentifier === match.params.id);
            if (!findProject) {
                this.props.getProject(match.params.id, this.props.history);
            } else {
                this.setState({
                    ...findProject
                });
            }
        }
    }

    onChangeHandle = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    onSubmitHandle = (e) => {
        e.preventDefault();
        const newProject = {
            projectName: this.state.projectName,
            projectIdentifier: this.state.projectIdentifier,
            description: this.state.description,
            startDate: this.state.startDate,
            endDate: this.state.endDate
        };
        this.state.isUpdate && (newProject["id"] = this.state.id);
        const {createOrUpdateProject} = this.props;
        createOrUpdateProject(newProject, this.props.history);
    };

    componentWillReceiveProps(nextProps, nextContext) {
        if (nextProps.errors) {
            this.setState({
                errors: nextProps.errors,
            });
        }
        if (nextProps.projectTask) {
            this.setState({
                ...nextProps.projectTask.projectTask,
            });
        }
    }

    render() {
        const {errors} = this.state;
        return (
            <Fragment>
                <div className="projects">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-8 m-auto">
                                <h5 className="display-4 text-center">{this.state.isUpdate ? 'Edit' : 'Create'} Project
                                    form</h5>
                                <hr/>
                                <form onSubmit={this.onSubmitHandle}>
                                    <div className="form-group">
                                        {this.state.id && <input type={"hidden"} name={"id"} value={this.state.id}/>}
                                        <input type="text"
                                               className={classnames(
                                                   "form-control form-control-lg",
                                                   {
                                                       "is-invalid": errors.projectName
                                                   })}
                                               placeholder="Project Name"
                                               name={"projectName"}
                                               value={this.state.projectName}
                                               onChange={this.onChangeHandle}
                                        />
                                        {errors && errors['projectName'] &&
                                        <div className="invalid-feedback">
                                            {errors['projectName']}
                                        </div>
                                        }
                                    </div>
                                    <div className="form-group">
                                        <input type="text"
                                               className={classnames(
                                                   "form-control form-control-lg",
                                                   {
                                                       "is-invalid": errors.projectIdentifier
                                                   })}
                                               disabled={this.state.isUpdate}
                                               placeholder="Unique Project ID"
                                               value={this.state.projectIdentifier}
                                               name={"projectIdentifier"}
                                               onChange={this.onChangeHandle}
                                        />
                                        {errors && errors['projectIdentifier'] &&
                                        <div className="invalid-feedback">
                                            {errors['projectIdentifier']}
                                        </div>
                                        }

                                    </div>
                                    <div className="form-group">
                                    <textarea className="form-control form-control-lg"
                                              placeholder="Project Description"
                                              value={this.state.description}
                                              name={"description"}
                                              onChange={this.onChangeHandle}
                                    />
                                    </div>
                                    <h6>Start Date</h6>
                                    <div className="form-group">
                                        <input type="date" className="form-control form-control-lg" name="startDate"
                                               value={this.state.startDate}
                                               onChange={this.onChangeHandle}
                                        />
                                    </div>
                                    <h6>Estimated End Date</h6>
                                    <div className="form-group">
                                        <input type="date" className="form-control form-control-lg"
                                               value={this.state.endDate}
                                               name="endDate"
                                               onChange={this.onChangeHandle}
                                        />
                                    </div>
                                    <input type="submit" className="btn btn-primary btn-block mt-4"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </Fragment>
        );
    }
}

CreateOrUpdateProject.propTyes = {
    createOrUpdateProject: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired,
};
const mapStateToProps = state => ({
    errors: state.errors,
    projectTask: state.projectTask,
});

export default connect(mapStateToProps, {
    createOrUpdateProject: createOrUpdateProject,
    getProject
})(CreateOrUpdateProject);
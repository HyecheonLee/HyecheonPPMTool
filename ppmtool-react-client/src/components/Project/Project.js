import React, {Component} from 'react';
import CreateProjectButton from "./CreateProjectButton";
import {connect} from "react-redux";
import {deleteProject, getProjects} from "../../actions/projectActions";
import PropTypes from 'prop-types';
import ProjectItem from "./ProjectItem";

class Project extends Component {

    componentDidMount() {
        this.props.getProjects();
    }

    render() {
        const {projects} = this.props.project;
        return (
            <div className="projects">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <h1 className="display-4 text-center">Projects</h1>
                            <br/>
                            <CreateProjectButton/>
                            <br/>
                            <hr/>
                            <div className="container">
                                {projects && projects.map(project => <ProjectItem
                                    key={`projectItem_${project.id}`} {...project}
                                    deleteProject={this.props.deleteProject}/>)}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

Project.propTypes = {
    project: PropTypes.object.isRequired,
    getProjects: PropTypes.func,
    deleteProject: PropTypes.func,

};
const mapStateToProps = state => ({
    project: state.project,
});
export default connect(mapStateToProps, {getProjects, deleteProject})(Project);
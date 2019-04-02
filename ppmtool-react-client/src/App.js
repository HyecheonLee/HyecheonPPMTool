import React, {Component} from 'react';
import './App.css';
import Header from "./components/Layout/Header/Header";
import Project from "./components/Project";
import {BrowserRouter, Route} from "react-router-dom";
import CreateOrUpdateProject from "./components/Project/CreateOrUpdateProject";
import ProjectBoard from "./components/ProjectBoard/ProjectBoard";
import CreateProjectTask from "./components/ProjectBoard/ProjectTasks/CreateProjectTask";

class App extends Component {
    render() {
        return (
            <div className="App">
                <Header/>
                <BrowserRouter>
                    <Route exact path={"/project"} component={Project}/>
                    <Route exact path={"/project/:id/update"} component={CreateOrUpdateProject}/>
                    <Route exact path={"/project/create"} component={CreateOrUpdateProject}/>
                    <Route exact path={"/projectBoard/:id"} component={ProjectBoard}/>
                    <Route exact path={"/createProjectTask/:id"} component={CreateProjectTask}/>
                </BrowserRouter>
            </div>
        );
    }
}

export default App;
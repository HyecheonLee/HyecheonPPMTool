import React, {Component} from 'react';
import './App.css';
import Header from "./components/Layout/Header/Header";
import Project from "./components/Project";
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import CreateOrUpdateProject from "./components/Project/CreateOrUpdateProject";
import ProjectBoard from "./components/ProjectBoard/ProjectBoard";
import CreateOrUpdateProjectTask from './components/ProjectBoard/ProjectTasks/CreateOrUpadateProjectTask';
import Landing from "./components/Layout/Landing";
import Register from "./components/UserManagement/Register";
import Login from "./components/UserManagement/Login";

class App extends Component {
    render() {
        return (
            <div className="App">
                <BrowserRouter>
                    <Header/>
                    <Switch>
                        {
                            //public routers
                        }
                        <Route exact path={"/"} component={Landing}/>
                        <Route exact path={"/register"} component={Register}/>
                        <Route exact path={"/login"} component={Login}/>

                        {
                            //private routers
                        }

                        <Route exact path={"/projectTask"} component={Project}/>
                        <Route exact path={"/projectTask/:id/update"} component={CreateOrUpdateProject}/>
                        <Route exact path={"/projectTask/create"} component={CreateOrUpdateProject}/>
                        <Route path={"/projectBoard/:id"} component={ProjectBoard}/>
                        <Route exact path={"/createProjectTask/:id"} component={CreateOrUpdateProjectTask}/>
                        <Route exact path={"/updateProjectTask/:id/:ptId"} component={CreateOrUpdateProjectTask}/>
                        <Route render={() => <Redirect to={"/projectTask"}/>}/>
                    </Switch>
                </BrowserRouter>
            </div>
        );
    }
}

export default App;
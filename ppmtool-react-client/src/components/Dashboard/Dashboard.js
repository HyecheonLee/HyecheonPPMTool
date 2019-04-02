import React, {Component, Fragment} from 'react';
import Project from "../Project";

class Dashboard extends Component {
    render() {
        return (
            <Fragment>
                <h1 className={"alert alert-warning"}>Welcome to the Dashboard</h1>
                <Project/>
            </Fragment>
        );
    }
}

export default Dashboard;
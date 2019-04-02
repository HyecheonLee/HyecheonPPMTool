import React, {Fragment} from 'react';
import {Link} from "react-router-dom";

const CreateProjectButton = () => {
    return (
        <Fragment>
            <Link className="btn btn-lg btn-info" to={"project/create"}>
                Create a Project
            </Link>
        </Fragment>
    );
};

export default CreateProjectButton;
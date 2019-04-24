import React, {Component} from 'react';
import {createNewUser} from "../../actions/securityActions";
import {connect} from "react-redux";

class Register extends Component {

    constructor(props) {
        super(props);
        this.state = {
            "username": "",
            "fullName": "",
            "password": "",
            "confirmPassword": "",
            error: {}
        };
    }

    onChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    };
    onSubmit = (e) => {
        e.preventDefault();
        const newUser = {
            username: this.state.username,
            fullName: this.state.fullName,
            password: this.state.password,
            confirmPassword: this.state.confirmPassword,
        };
        createNewUser(newUser, this.props.history);
    };

    render() {
        return (
            <div className="register">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h1 className="display-4 text-center">Sign Up</h1>
                            <p className="lead text-center">Create your Account</p>
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input type="text" className="form-control form-control-lg" placeholder="Name"
                                           name="fullName"
                                           onChange={this.onChange}
                                           required/>
                                </div>
                                <div className="form-group">
                                    <input type="email" className="form-control form-control-lg"
                                           onChange={this.onChange}
                                           placeholder="Email Address" name="username"/>

                                </div>
                                <div className="form-group">
                                    <input type="password" className="form-control form-control-lg"
                                           onChange={this.onChange}
                                           placeholder="Password" name="password"/>
                                </div>
                                <div className="form-group">
                                    <input type="password" className="form-control form-control-lg"
                                           onChange={this.onChange}
                                           placeholder="Confirm Password"
                                           name="confirmPassword"/>
                                </div>
                                <input type="submit" className="btn btn-info btn-block mt-4"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

Register.propTypes = {};
const mapStateToProps = state => ({
    errors: state.errors
});
export default connect(mapStateToProps, {createNewUser})(Register);
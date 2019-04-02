import Axios from 'axios/index';
import {DELETE_PROJECT, GET_ERRORS, GET_PROJECT, GET_PROJECTS} from "./types";


export const createOrUpdateProject = (project, history) => async dispatch => {
    Axios.post("/api/project", project)
        .then(res => {
            history.push("/project")
        }).catch(e => {
        dispatch({
            type: GET_ERRORS,
            payload: e.response.data
        })
    });
};
export const getProject = (id, history) => dispatch => {
    Axios.get(`/api/project/${id}`).then(
        res => {
            return res.data
        }).then(value => {
        dispatch({
            type: GET_PROJECT,
            payload: value
        })
    }).catch(e => {
        history.push("/project")
    });
};
export const getProjects = () => dispatch => {
    Axios.get(`/api/project/all`).then(
        res => {
            return res.data
        }).then(value => {
        dispatch({
            type: GET_PROJECTS,
            payload: value
        })
    }).catch(e => {
        dispatch({
            type: GET_ERRORS,
            payload: e.response.data
        })
    });
};
export const deleteProject = (id) => dispatch => {
    Axios.delete(`/api/project/${id}`).then(
        res => {
            dispatch({
                type: DELETE_PROJECT,
                payload: id,
            });
        }).catch(e => {
        console.log(e);
    });
};
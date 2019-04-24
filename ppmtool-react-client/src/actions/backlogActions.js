import axios from "axios";
import {GET_BACKLOG, GET_ERRORS, GET_PROJECT_TASK} from "./types";

export const createProjectTask = (backlog_id, projectTask, history) => async dispatch => {
    try {
        await axios.post(`/api/backlog`, projectTask)
            .then(value => {
                dispatch({
                    type: GET_ERRORS,
                    payload: {}
                });
            }).finally(() => {
                history.push(`/projectBoard/${backlog_id}`);
            });
    } catch (e) {
        dispatch({
            type: GET_ERRORS,
            payload: e.response.data
        });
    }
};
export const getProjectTask = (id, ptId) => dispatch => {
    try {
        axios.get(`/api/backlog/${id}/${ptId}`)
            .then(res =>
                dispatch({
                    type: GET_PROJECT_TASK,
                    payload: res.data
                }));
    } catch (err) {

    }
};
export const getBacklog = backlogId => dispatch => {
    try {
        axios.get(`/api/backlog/${backlogId}`)
            .then(res =>
                dispatch({
                    type: GET_BACKLOG,
                    payload: res.data
                }));
    } catch (err) {

    }
};
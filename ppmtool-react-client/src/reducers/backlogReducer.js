import {DELETE_PROJECT_TASK, GET_BACKLOG, GET_PROJECT} from "../actions/types";

const initialState = {
    project_tasks: [],
    project_task: {},
};

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_BACKLOG:
            return {
                ...state,
                project_tasks: action.payload
            };
        case GET_PROJECT:
            return {
                ...state,
                project_task: action.payload
            };
        case DELETE_PROJECT_TASK:
            return {


            }
        default:
            return state;
    }
}
import {DELETE_PROJECT, GET_PROJECT, GET_PROJECTS} from "../actions/types";

const initialState = {
    projects: [],
    project: {},
};

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_PROJECT:
            state.projects.push(action.payload);
            state.project = action.payload;
            return {
                ...state
            };
        case GET_PROJECTS:
            state.projects = action.payload;
            return {
                ...state
            };
        case DELETE_PROJECT:
            return {
                projects: state.projects.filter(value => value.projectIdentifier !== action.payload),
            };
        default:
            return state;
    }
}
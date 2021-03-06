import axios from "axios";
import {GET_ERRORS} from "./types";

export const createNewUser = (newUser, history) => async dispatch => {
    try {
        await axios.post("/api/user/register")
        history.push("/login");
        dispatch({
            type: GET_ERRORS,
            payload: {}
        });
    } catch (e) {
        dispatch({
            type: GET_ERRORS,
            payload: e.response.data
        });
    }
};
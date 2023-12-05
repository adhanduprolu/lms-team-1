import actions from "./Login.action.types";
import { url } from "../../utils";
import axios from "axios";
export function login(email, password) {
  return async (dispatch, getState) => {
    const reqBody = {
      email,
      password,
    };
    console.log("reqbody :", reqBody);
    try {
      // Use axios to make the HTTP POST request
      const response = await axios.post(`${url}login`, reqBody);
      console.log(response);
      // Check if the response is successful
      if (response.status === 200) {
        dispatch({
          type: actions.LOGIN,
          payload: response.data,
        });
      } else {
        dispatch({
          type: actions.LOGIN_ERROR,
          payload: {
            status: response.status,
            message: response.data || "",
          },
        });
      }
    } catch (err) {
      // Handle any errors that occur during the request
      dispatch({
        type: actions.LOGIN_ERROR,
        payload: {
          status: err.response ? err.response.status : 500,
          message: err.message || "An unknown error occurred",
        },
      });
    }
  };
}
//hello
export function initializeLogin() {
  return async (dispatch, getState) => {
    dispatch({
      type: actions.LOGIN_INITIALIZE,
    });
  };
}

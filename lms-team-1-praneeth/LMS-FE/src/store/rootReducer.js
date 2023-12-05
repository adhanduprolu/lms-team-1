import { combineReducers } from "redux";
import loginReducer from "../Components/Login/Login.reducer";

export default combineReducers({
    login: loginReducer
});
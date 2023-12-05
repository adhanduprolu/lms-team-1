import actions from "./Login.action.types";
const initialState = {
  name:"",
  email: "",
  password: "",
  responseStatus: "",
  jwt: "",
  errorMessage: "",
  ////
  // userid:"",
};
const loginReducer = (state = initialState, action) => {
  switch (action.type) {
    case actions.LOGIN_INITIALIZE:
      return {
        ...initialState,
      };
    case actions.LOGIN:
      console.log("payload :", action.payload);
      return {
        ...state,
        email: action.payload.email,
        name: action.payload.name,
        password: action.payload.password,
        responseStatus: "",
        errorMessage: "",
        jwt: action.payload.token,
        role: action.payload.roles,
        userCourses: action.payload.userCourses.courseslist,
        /////
        // userid:action.payload._id
      };
    case actions.LOGIN_ERROR:
      return {
        ...state,
        responseStatus: action.payload.status,
        errorMessage: action.payload.message,
        jwt: "",
      };
    default:
      return state;
  }
};
export default loginReducer;
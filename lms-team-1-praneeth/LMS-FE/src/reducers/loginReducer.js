// src/reducers/loginReducer.js
const initialState = {
    isLoggedIn: false,
    userData: null,
    loginError: false
  };
  
  const loginReducer = (state = initialState, action) => {
    switch (action.type) {
      case 'LOGIN_SUCCESS':
        return {
          ...state,
          isLoggedIn: true,
          userData: action.payload,
          loginError: false
        };
      case 'LOGIN_FAILURE':
        return {
          ...state,
          isLoggedIn: false,
          userData: null,
          loginError: true
        };
      default:
        return state;
    }
  };
  
  export default loginReducer;
  
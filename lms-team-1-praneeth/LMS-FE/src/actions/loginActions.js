export const loginSuccess = (userData) => {
    return {
      type: 'LOGIN_SUCCESS',
      payload: userData
    };
  };
  
  export const loginFailure = () => {
    return {
      type: 'LOGIN_FAILURE'
    };
  };
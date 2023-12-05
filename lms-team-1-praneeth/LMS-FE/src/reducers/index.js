// src/reducers/index.js
import { combineReducers } from 'redux';
import loginReducer from './loginReducer';

const rootReducer = combineReducers({
  login: loginReducer
  // other reducers can be added here
});

export default rootReducer;
import { createSlice } from '@reduxjs/toolkit';

const initialState = localStorage.getItem('reduxState')
  ? JSON.parse(localStorage.getItem('reduxState'))
  : { user: null, role: null };

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    login: (state, action) => {
      state.user = action.payload.user;
      state.role = action.payload.role;
      localStorage.setItem('reduxState', JSON.stringify(state));
    },
    logout: (state) => {
      state.user = null;
      state.role = null;
      localStorage.setItem('reduxState', JSON.stringify(state));
    },
  },
});

export const { actions } = authSlice; 
export const { login, logout } = actions;

export const selectUser = (state) => state.auth.user;
export const selectRole = (state) => state.auth.role;
export default authSlice.reducer;

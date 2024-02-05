import { createSlice } from '@reduxjs/toolkit';

const authSlice = createSlice({
  name: 'auth',
  initialState: { user: null, role: null },
  reducers: {
    login: (state, action) => {
      state.user = action.payload.user;
      state.role = action.payload.role;
    },
    logout: (state) => {
      state.user = null;
      state.role = null;
    },
  },
});

export const { actions } = authSlice; 
export const { login, logout } = actions;

export const selectUser = (state) => state.auth.user;
export const selectRole = (state) => state.auth.role;
export default authSlice.reducer;
import axios from "axios";

const AUTHORIZATION_REST_API_URL = "http://localhost:8080/api/auth";

export const registerUser = (userToRegister) => axios.post(AUTHORIZATION_REST_API_URL + '/register', userToRegister); 

export const login = (usernameOrEmail, password) => axios.post(AUTHORIZATION_REST_API_URL + '/login', { usernameOrEmail, password });

export const storeToken = (token) => localStorage.setItem("token", token);

export const getToken = () => localStorage.getItem("token");
import axios from "axios";

const AUTHORIZATION_REST_API_URL = "http://localhost:8080/api/auth";

export const registerUser = (userToRegister) => axios.post(AUTHORIZATION_REST_API_URL + '/register', userToRegister); 

export const login = (usernameOrEmail, password) => axios.post(AUTHORIZATION_REST_API_URL + '/login', { usernameOrEmail, password });

export const storeToken = (token) => localStorage.setItem("token", token);

export const getToken = () => localStorage.getItem("token");

/* 
    The difference between session storage and local storage is t
    hat local storage does not have an expiration date 
*/
export const saveLoggedInUser = (username) => sessionStorage.setItem("authenticatedUser", username);

export const isUserLoggedIn = () => {
    if (sessionStorage.getItem("authenticatedUser") == null) {
        console.log("User is not logged in");
        return false;
    } else {
        console.log("User is logged in");
        return true;
    }
}

export const getLoggedInUser = () => sessionStorage.getItem("authenticatedUser");

export const logout = () => {
    localStorage.clear();
    sessionStorage.clear();
}
    

    

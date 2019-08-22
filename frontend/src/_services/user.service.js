import config from 'config';
import axios from 'axios';

export const userService = {
    login,
    logout,
    register
};

function login(email, password) {

    const user = {
        'email': email,
        'password': password
    };

    return axios.post(`${config.BACKEND_BASE_URL}/v1/users/login`, user)
        .then(response => {
            if (response.data.token) {
                localStorage.setItem('user', JSON.stringify(response.data));
            }
            return response.data;
        });
}

function logout() {
    localStorage.removeItem('user');
}

function register(user) {
    return axios.post(`${config.BACKEND_BASE_URL}/v1/users/register`, user)
        .then(response => {
            if (response.data.token) {
                localStorage.setItem('user', JSON.stringify(response.data));
            }
            return response.data;
        });
}
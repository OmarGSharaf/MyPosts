import axios from 'axios';
import {router} from '../router';

const user = JSON.parse(localStorage.getItem('user'));
const state = user
    ? {status: {loggedIn: true}, user}
    : {status: {}, user: null};

const actions = {
    login({commit}, {email, password}) {
        commit('loginRequest', email);

        const user = {
            email: email,
            password: password
        };

        axios.post(process.env.VUE_APP_BACKEND_BASE_URL + "/v1/users/login", user)
            .then(response => {
                if (response.data.token) {
                    localStorage.setItem('user', JSON.stringify(response.data));

                    commit('loginSuccess', user);
                    router.push('/');
                }
            }).catch(error => {
            commit('loginFailure', error);
        });
    },
    register({commit}, {user}) {
        commit('registerRequest', user);

        axios.post(process.env.VUE_APP_BACKEND_BASE_URL + "/v1/users/register", user)
            .then(response => {
                if (response.data.token) {
                    localStorage.setItem('user', JSON.stringify(response.data));

                    commit('registerSuccess', user);
                    router.push('/home');
                }
            }).catch(error => {
            commit('registerFailure', error);
        });
    },
    logout({commit}) {
        localStorage.removeItem('user');
        commit('logout');
    }
};

const mutations = {
    loginRequest(state, user) {
        state.status = {loggingIn: true};
        state.user = user;
    },
    loginSuccess(state, user) {
        state.status = {loggedIn: true};
        state.user = user;
    },
    loginFailure(state) {
        state.status = {};
        state.user = null;
    },
    logout(state) {
        state.status = {};
        state.user = null;
    },
    registerRequest(state, user) {
        state.status = {registering: true};
        state.user = user;
    },
    registerSuccess(state) {
        state.status = {};
    },
    registerFailure(state) {
        state.status = {};
        state.user = null;
    }
};

export const account = {
    namespaced: true,
    state,
    actions,
    mutations
};
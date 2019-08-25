import axios from 'axios';
import {authHeader} from './auth-header';

const state = {
    all: {}
};

const actions = {
    getAll({commit}) {
        commit('getAllRequest');

        axios.get(process.env.VUE_APP_BACKEND_BASE_URL + "/v1/posts", {headers: authHeader()})
            .then(response => {
                if (response.data.hasOwnProperty('content'))
                    commit('getAllSuccess', response.data.content);
            }).catch(error => {
            commit('getAllFailure', error);
        });
    },
    createPost({commit}, {post}) {

        axios.post(process.env.VUE_APP_BACKEND_BASE_URL + "/v1/posts", post, {headers: authHeader()})
            .then(response => {
                commit('publishSuccess', response.data);
            });
    },
    search({commit}) {
        commit('getAllRequest');

        return axios.get(process.env.VUE_APP_BACKEND_BASE_URL + "/v1/posts", {headers: authHeader()})
            .then(response => {
                if (response.data.hasOwnProperty('content'))
                    return response.data.content;
                else
                    return [];
            });
    },
    delete({commit}, id) {
        commit('deleteRequest', id);

        axios.delete(process.env.VUE_APP_BACKEND_BASE_URL + "/v1/posts/" + id, {headers: authHeader()})
            .then(function () {
                commit('deleteSuccess', id)
            })
            .catch(error => {
                commit('deleteFailure', {id, error: error.toString()});
            });
    }
};

const mutations = {
    getAllRequest(state) {
        state.all = {loading: true};
    },
    getAllSuccess(state, posts) {
        state.all = {items: posts};
    },
    getAllFailure(state, error) {
        state.all = {error};
    },
    publishSuccess(state, posts) {
        state.all = {items: posts};
    },
    deleteRequest(state, id) {
        state.all.items = state.all.items.map(post =>
            post.id === id
                ? {...post, deleting: true}
                : post
        )
    },
    deleteSuccess(state, id) {
        state.all.items = state.all.items.filter(post => post.id !== id)
    },
    deleteFailure(state, {id, error}) {
        state.all.items = state.items.map(post => {
            if (post.id === id) {
                const {deleting, ...postCopy} = post;
                return {...postCopy, deleteError: error};
            }

            return post;
        })
    }
};

export const posts = {
    namespaced: true,
    state,
    actions,
    mutations
};
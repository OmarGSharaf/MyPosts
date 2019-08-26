import axios from 'axios';
import {authHeader} from './auth-header';

const state = {
    all: {
        items: [],
        loading: false
    },
    search: {
        items: []
    }
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
    search({commit}, searchText) {
        commit('searchRequest');

        axios.get(process.env.VUE_APP_BACKEND_BASE_URL + "/v1/posts/search", {
            headers: authHeader(),
            params: {
                text: searchText
            }
        })
            .then(response => {
                if (response.data.hasOwnProperty('content'))
                    commit('searchSuccess', response.data.content);
            })
            .catch(error => {
                commit('searchFailure', error);
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
        state.all.loading = true;
    },
    getAllSuccess(state, posts) {
        state.all.items = posts;
        state.all.loading = false;
    },
    getAllFailure(state, error) {
        state.all.error = error;
        state.all.loading = false;
    },
    searchRequest(state) {
        state.search.loading = true;
    },
    searchSuccess(state, posts) {
        state.search.items = posts;
        state.search.loading = false;
    },
    searchFailure(state, error) {
        state.search.error = error;
        state.search.loading = false;
    },
    publishSuccess(state, post) {
        state.all.items.push(post);
        state.all.loading = false;
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
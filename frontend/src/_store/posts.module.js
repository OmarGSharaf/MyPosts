import {postService, userService} from '../_services';
import {router} from "../_helpers";

const state = {
    all: {}
};

const actions = {
    getAll({commit}) {
        commit('getAllRequest');

        postService.getAll()
            .then(
                posts => commit('getAllSuccess', posts),
                error => commit('getAllFailure', error)
            );
    },

    post({dispatch, commit}, post) {

        postService.post(post)
            .then(post => {
                postService.getAll()
                    .then(
                        posts => commit('getAllSuccess', posts),
                        error => commit('getAllFailure', error)
                    );
                }
            );
    },

    delete({commit}, id) {
        commit('deleteRequest', id);

        postService.delete(id)
            .then(
                post => commit('deleteSuccess', id),
                error => commit('deleteFailure', {id, error: error.toString()})
            );
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
    deleteRequest(state, id) {
        // add 'deleting:true' property to post being deleted
        state.all.items = state.all.items.map(post =>
            post.id === id
                ? {...post, deleting: true}
                : post
        )
    },
    deleteSuccess(state, id) {
        // remove deleted post from state
        state.all.items = state.all.items.filter(post => post.id !== id)
    },
    deleteFailure(state, {id, error}) {
        // remove 'deleting:true' property and add 'deleteError:[error]' property to post 
        state.all.items = state.items.map(post => {
            if (post.id === id) {
                // make copy of post without 'deleting:true' property
                const {deleting, ...postCopy} = post;
                // return copy of post with 'deleteError:[error]' property
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

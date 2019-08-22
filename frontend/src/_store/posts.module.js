import {postService} from '../_services';

const state = {
    all: [
        {
            id: "1",
            content: "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.",
            user: {
                name: "banana"
            },
            createdAt: "asd"
        }, {
            id: "2",
            content: "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.",
            user: {
                name: "banana"
            },
            createdAt: "asd"
        }, {
            id: "3",
            content: "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.",
            user: {
                name: "banana"
            },
            createdAt: "asd"
        }
    ]
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

import config from 'config';
import {authHeader} from '../_helpers';
import axios from 'axios';

export const postService = {
    getAll,
    post,
    search,
    delete: _delete
};

function getAll() {
    return axios.get(`${config.BACKEND_BASE_URL}/v1/posts`, {headers: authHeader()})
        .then(response => {
            if (response.data.hasOwnProperty('content'))
                return response.data.content;
            else
                return [];
        });
}

function post(post) {
    return axios.post(`${config.BACKEND_BASE_URL}/v1/posts`, post, {headers: authHeader()})
        .then(response => {
            return response.data;
        });
}

function search() {
    return axios.get(`${config.BACKEND_BASE_URL}/v1/posts/search`, {headers: authHeader()})
        .then(response => {
            return response.data;
        });
}

function _delete(id) {
    return axios.delete(`${config.BACKEND_BASE_URL}/v1/posts/${id}`, {headers: authHeader()});
}
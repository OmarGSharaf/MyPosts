import config from 'config';
import {authHeader} from '../_helpers';
import axios from 'axios';

export const postService = {
    getAll,
    search,
    delete: _delete
};

function getAll() {
    return axios.get(`${config.BACKEND_BASE_URL}/v1/posts`, {headers: authHeader()})
        .then(response => {
            return response;
        });
}

function search() {
    return axios.get(`${config.BACKEND_BASE_URL}/v1/search`, {headers: authHeader()})
        .then(response => {
            return response;
        });
}

function _delete(id) {
    return axios.delete(`${config.BACKEND_BASE_URL}/v1/posts/${id}`, {headers: authHeader()});
}
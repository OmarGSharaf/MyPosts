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
            if (response.data.hasOwnProperty('content'))
                return response.data.content;
            else
                return [];
        });
}

function search() {
    return axios.get(`${config.BACKEND_BASE_URL}/v1/search`, {headers: authHeader()})
        .then(response => {
            return response.data;
        });
}

function _delete(id) {
    return axios.delete(`${config.BACKEND_BASE_URL}/v1/posts/${id}`, {headers: authHeader()});
}
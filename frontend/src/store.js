import Vue from 'vue'
import Vuex from 'vuex'
import {account} from './store/account';
import {posts} from './store/post';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        account,
        posts
    }
})
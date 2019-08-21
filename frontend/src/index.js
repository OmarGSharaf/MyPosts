import Vue from 'vue';
import VeeValidate from 'vee-validate';

import { store } from './_store';
import { router } from './_helpers';
import App from './app/App';
import Vuetify from './_helpers/vuetify'

Vue.use(VeeValidate);

VeeValidate.Validator.extend('verify_password', {
    getMessage: field => `The password must contain at least: 1 uppercase letter, 1 lowercase letter, 1 number, and one special character (E.g. , . _ & ? etc)`,
    validate: value => {
        var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
        return strongRegex.test(value);
    }
});

new Vue({
    el: '#app',
    router,
    store,
    Vuetify,
    render: h => h(App)
});
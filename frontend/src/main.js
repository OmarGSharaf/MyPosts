import Vue from 'vue'
import VeeValidate from 'vee-validate';
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import './assets/style/main.styl'

Vue.config.productionTip = false;
Vue.use(VeeValidate);

VeeValidate.Validator.extend('verify_password', {
    getMessage: field => `The password must contain at least: 1 uppercase letter, 1 lowercase letter, 1 number, and one special character (E.g. , . _ & ? etc)`,
    validate: value => {
        var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
        return strongRegex.test(value);
    }
});

new Vue({
    router,
    store,
    vuetify,
    render: h => h(App)
}).$mount('#app');

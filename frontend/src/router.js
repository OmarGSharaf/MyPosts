import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Entry from './views/Entry.vue'

Vue.use(Router);

export const router = new Router({
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/entry',
            component: Entry
        },
        {
            path: '*',
            redirect: '/'
        }
    ]
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/entry'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    if (authRequired && !loggedIn) {
        return next('/entry');
    }

    next();
});
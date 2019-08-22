<template>

    <router-view></router-view>
</template>

<script>
    import {mapState, mapActions} from 'vuex'

    export default {
        name: 'app',
        computed: {
            ...mapState({
                alert: state => state.alert
            })
        },
        methods: {
            ...mapActions({
                clearAlert: 'alert/clear'
            })
        },
        watch: {
            $route(to, from) {
                // clear alert on location change
                this.clearAlert();
            }
        }
    };
</script>
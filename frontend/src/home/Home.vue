<template>
    <div>
        <h1>Hi {{account.user.firstName}}!</h1>
        <em v-if="users.loading">Loading posts...</em>



        <span v-if="users.error" class="text-danger">ERROR: {{users.error}}</span>

            <div v-for="post in posts" :key="post.content">
                {{post.content}}
            </div>

            <router-link to="/login">Logout</router-link>

    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
    data () {
        return {
            posts: [
                {
                    content: "123"
                },{
                    content: "456"
                },{
                    content: "789"
                }
            ]
        }
    },
    computed: {
        ...mapState({
            account: state => state.account,
            users: state => state.users.all
        })
    },
    methods: {

    }
};
</script>
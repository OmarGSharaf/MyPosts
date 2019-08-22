<template>
    <div>
        <div class="topnav">

            <a href="#home" class="active">Home</a>

            <div class="topnav-right">
                <a href="#search">Search</a>
                <router-link to="/login">Logout</router-link>
            </div>

        </div>
        <div v-for="post in all.items" :key="post.id">
            <post :body="post"></post>
        </div>
    </div>
</template>

<script>
    import {mapState, mapActions} from 'vuex'
    import Post from '../post/Post'

    export default {
        data() {
            return {}
        },
        components: {
            "post": Post,
        },

        computed: {
            ...mapState('posts', ['all'])
        },
        methods: {
            ...mapActions('posts', {
                getAllPosts: 'getAll',
                deletePosts: 'delete'
            }),
        },
        mounted() {
            this.getAllPosts();
        }
    };
</script>

<style>

    @import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');

    .topnav {
        margin: 0;
        padding: 0;
        position: relative;
        background-color: #333;
        box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25),
        0 10px 10px rgba(0, 0, 0, 0.22);
        overflow: hidden;
        font-family: 'Montserrat', sans-serif;
    }

    .topnav a {
        float: left;
        color: #f2f2f2;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        font-size: 17px;
    }

    .topnav a:hover {
        background-color: #ddd;
        color: black;
    }

    .topnav a.active {
        border: 1px solid #FF4B2B;
        background-color: #FF4B2B;
        color: #FFFFFF;
    }

    .topnav-right {
        float: right;
    }

    @media screen and (max-width: 600px) {
        .topnav a, .topnav-right {
            float: none;
            display: block;
        }
    }
</style>
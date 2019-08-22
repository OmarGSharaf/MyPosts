<template>
    <div>
        <div class="topnav">

            <a href="#home" class="active">Home</a>

            <div class="topnav-right">
                <a href="#search">Search</a>
                <router-link to="/login">Logout</router-link>
            </div>
        </div>

        <div v-if="all.items === undefined || all.items.length === 0">
            <img class="warning" src="https://png.pngtree.com/svg/20170331/dfbea55b9c.svg" width="100" alt="Opps">
            <p class="warning">You do not have any posts yet!</p>
        </div>
        <div v-else class="cards">
            <div v-for="post in all.items" :key="post.id">
                <post :body="post"></post>
            </div>
        </div>
        <div class="container">
            <form @submit.prevent="publish">
                <textarea v-model="newPost.content" placeholder="Post Content" rows="4" cols="30"/>
                <button>Post Now</button>
            </form>
        </div>

    </div>
</template>

<script>
    import {mapState, mapActions} from 'vuex'
    import Post from '../post/Post'

    export default {
        data() {
            return {
                newPost: {
                    content: '',
                    status: "PUBLIC"
                }
            }
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
                createPost: 'post',
                deletePosts: 'delete'
            }),
            publish(e) {
                this.createPost(this.newPost);
            }
        },
        created() {
            this.getAllPosts();
        }
    };
</script>

<style>

    @import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');

    .topnav {
        position: fixed !important;
        top: 0;
        width: 100%;
        margin: 0;
        padding: 0;
        background-color: #333;
        box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25),
        0 10px 10px rgba(0, 0, 0, 0.22);
        overflow: hidden;
        font-family: 'Montserrat', sans-serif;
        z-index: 10000 !important;
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

    .cards {
        margin-top: 10%;
        margin-bottom: 20%;
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

    .warning {
        font-family: 'Montserrat', sans-serif;
        font-size: 30px;
        color: #cccccc;
        float: none;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -150%);
    }

    .container {
        position: fixed !important;
        overflow: scroll;
        background: #FF416C;
        background: -webkit-linear-gradient(to right, #FF4B2B, #FF416C);
        background: linear-gradient(to right, #FF4B2B, #FF416C);
        background-repeat: no-repeat;
        background-size: cover;
        background-position: 0 0;
        box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
        width: 800px;
        max-width: 100%;
        min-height: 130px;
        bottom: 0%;
        left: 50%;
        transform: translate(-50%, -50%);
        padding: -30px 0px 0px 0;
        z-index: 10000 !important;
    }

    button {
        background-color: transparent;
        border-color: #FFFFFF;
        border-radius: 20px;
        color: #FFFFFF;
        font-size: 12px;
        font-weight: bold;
        padding: 12px 45px;
        letter-spacing: 1px;
        text-transform: uppercase;
        transition: transform 80ms ease-in;
        margin: 10px 18px 12px 0;
        float: right;
    }

    textarea {
        background-color: #eee;
        border: none;
        padding: 10px 20px;
        margin: 10px 0px;
        width: 90%;
        position: relative;
        top: 50%;
        left: 50%;
        transform: translate(-50%, 10px);
    }
</style>
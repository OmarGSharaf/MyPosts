<template>
    <div class="card">
        <div class="post-module">
            <div class="post-content">
                <h2 class="sub_title">{{this.body.user.name}}</h2>
                <p class="description">{{this.body.content}}</p>
                <div class="post-meta"><span class="timestamp">{{this.body.createdAt}}</span></div>
                <button @click="deletePosts(body.id)">delete</button>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapState, mapActions} from 'vuex'

    export default {
        name: "Post",
        props: {
            body: Object
        },
        methods: {
            ...mapActions('posts', {
                getAllPosts: 'getAll',
                deletePosts: 'delete'
            }),
        },
    }

</script>

<style>

    @import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');

    body {
        background: #f2f2f2;
        font-family: 'Montserrat', sans-serif;
        font-size: 14px;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
    }

    .post-module {
        position: relative !important;
        z-index: 1;
        display: block;
        background: #FFFFFF;
        min-width: 270px;
        min-height: 226px;
        -webkit-box-shadow: 0px 1px 2px 0px rgba(0, 0, 0, 0.15);
        -moz-box-shadow: 0px 1px 2px 0px rgba(0, 0, 0, 0.15);
        box-shadow: 0px 1px 2px 0px rgba(0, 0, 0, 0.15);
        -webkit-transition: all 0.3s linear 0s;
        -moz-transition: all 0.3s linear 0s;
        -ms-transition: all 0.3s linear 0s;
        -o-transition: all 0.3s linear 0s;
        transition: all 0.3s linear 0s;
        overflow: visible;
        margin-top: 25px;
    }

    .post-module:hover,
    .hover {
        -webkit-box-shadow: 0px 1px 35px 0px rgba(0, 0, 0, 0.3);
        -moz-box-shadow: 0px 1px 35px 0px rgba(0, 0, 0, 0.3);
        box-shadow: 0px 1px 35px 0px rgba(0, 0, 0, 0.3);
    }

    .post-module .post-content {
        bottom: 0;
        background: #FFFFFF;
        width: 100%;
        padding: 30px;
        -webkti-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        -webkit-transition: all 0.3s cubic-bezier(0.37, 0.75, 0.61, 1.05) 0s;
        -moz-transition: all 0.3s cubic-bezier(0.37, 0.75, 0.61, 1.05) 0s;
        -ms-transition: all 0.3s cubic-bezier(0.37, 0.75, 0.61, 1.05) 0s;
        -o-transition: all 0.3s cubic-bezier(0.37, 0.75, 0.61, 1.05) 0s;
        transition: all 0.3s cubic-bezier(0.37, 0.75, 0.61, 1.05) 0s;
    }

    .post-module .post-content .category {
        position: absolute;
        top: -34px;
        left: 0;
        background: #e74c3c;
        padding: 10px 15px;
        color: #FFFFFF;
        font-size: 14px;
        font-weight: 600;
        text-transform: uppercase;
    }

    .post-module .post-content .title {
        margin: 0;
        padding: 0 0 10px;
        color: #333333;
        font-size: 26px;
        font-weight: 700;
    }

    .post-module .post-content .sub_title {
        margin: 0;
        padding: 0 0 20px;
        color: #e74c3c;
        font-size: 20px;
        font-weight: 400;
    }

    .post-module .post-content .description {
        color: #666666;
        font-size: 14px;
        line-height: 1.8em;
    }

    .post-module .post-content .post-meta {
        margin: 30px 0 0;
        color: #999999;
    }

    .post-module .post-content .post-meta .timestamp {
        margin: 0 16px 0 0;
    }

    .post-module .post-content .post-meta a {
        color: #999999;
        text-decoration: none;
    }

    .hover .post-content .description {
        display: block !important;
        height: auto !important;
        opacity: 1 !important;
    }

    .card {
        max-width: 800px;
        min-width: 640px;
        margin: 0 auto;
    }

    .card:before,
    .card:after {
        content: '';
        display: block;
        clear: both;
    }

    button {
        float: right;
        position: relative;
        border-radius: 20px;
        border: 1px solid #FF4B2B;
        background-color: #FF4B2B;
        color: #FFFFFF;
        font-size: 12px;
        font-weight: bold;
        padding: 10px 30px;
        letter-spacing: 1px;
        text-transform: uppercase;
        transition: transform 80ms ease-in;
        margin: -30px 0px 0px 0;
    }

    button:active {
        transform: scale(0.95);
    }

    button:focus {
        outline: none;
    }
</style>
<template>
    <v-app>
        <v-app-bar fixed dark>

            <v-toolbar-title>MyPosts</v-toolbar-title>

            <div class="flex-grow-1"></div>

            <v-btn icon>
                <v-icon>mdi-magnify</v-icon>
            </v-btn>

            <v-btn icon>
                <v-icon @click="sheet=!sheet">mdi-pencil</v-icon>
            </v-btn>

            <v-menu left bottom>
                <template v-slot:activator="{ on }">
                    <v-btn icon v-on="on">
                        <v-icon>mdi-dots-vertical</v-icon>
                    </v-btn>
                </template>
                <v-list class="text-center">
                    <v-list-item v-for="option in options" :key="option" @click="handleMenu(option)">
                        <v-list-item-title>{{option}}</v-list-item-title>
                    </v-list-item>
                </v-list>
            </v-menu>
            <v-progress-linear v-if="all.loading" bottom absolute :indeterminate="true"
                               class="ma-0"></v-progress-linear>
        </v-app-bar>

        <v-content class="mt-12">
            <v-container fluid>
                <div v-for="post in all.items" :key="post.id">
                    <v-card class="mt-11 mx-auto" max-width="800" outlined>
                        <v-list-item>
                            <v-list-item-content>
                                <v-btn @click="deletePosts(post.id)" absolute top right icon>
                                    <v-icon>mdi-delete</v-icon>
                                </v-btn>
                                <v-list-item-title class="headline mb-1">{{post.user.name}}</v-list-item-title>
                                <v-list-item-subtitle>@ {{post.createdAt}}</v-list-item-subtitle>
                                <v-list-item-content>{{post.content}}</v-list-item-content>
                            </v-list-item-content>
                        </v-list-item>
                    </v-card>
                </div>
            </v-container>
        </v-content>

        <v-footer class="pa-0" dark app>
            <v-bottom-sheet v-model="sheet">
                <template v-slot:activator="{ on }">
                    <v-btn width="100%" color="deep-orange " dark v-on="on">create post</v-btn>
                </template>
                <v-sheet class="text-center" height="100px">
                    <v-textarea
                            v-model="newPost.data.content"
                            clear-icon="mdi-close-circle"
                            label="What's on your mind?"
                            type="text"
                            v-validate="'required'"
                            :append-icon="marker ? 'mdi-lock-open' : 'mdi-lock'"
                            :error-messages="errors.collect('post content')"
                            data-vv-name="post content"
                            filled
                            solo-inverted
                            clearabl
                            no-resize
                            rows="4"
                            row-height="12"
                            @click:append="toggleMarker"
                            @click:clear="clearPost"
                    ></v-textarea>
                </v-sheet>
                <v-btn @click="sendPost" color="green" dark>Publish</v-btn>
            </v-bottom-sheet>
        </v-footer>
    </v-app>
</template>

<script>
    import {mapState, mapActions} from 'vuex'
    import {router} from '../router';

    export default {
        data() {
            return {
                newPost: {
                    data: {
                        content: '',
                        status: "PUBLIC"
                    },
                    submitted: false
                },
                options: ['New Post', 'Logout'],
                marker: true,
                sheet: false
            }
        },
        computed: {
            ...mapState('posts', ['all'])
        },
        methods: {
            ...mapActions('posts', {
                getAllPosts: 'getAll',
                createPost: 'createPost',
                deletePosts: 'delete'
            }),

            toggleMarker() {
                this.marker = !this.marker;
            },
            sendPost() {
                this.newPost.submitted = true;
                const post = this.newPost.data;
                this.$validator.validate()
                    .then(valid => {
                        if (valid) {
                            this.marker ? post.status = 'PUBLIC' : post.status = 'PRIVATE';
                            this.createPost({post});
                            this.clearPost();
                        }
                    });
            },
            clearPost() {
                this.sheet = false;
                this.newPost = {
                    data: {
                        content: '',
                        status: "PUBLIC"
                    },
                    submitted: false
                };
                this.$nextTick(() => {
                    this.errors.clear();
                    this.$nextTick(() => {
                        this.$validator.reset();
                    });
                });
            },
            handleMenu(key) {
                switch (key) {
                    case 'New Post':
                        this.sheet = !this.sheet;
                        break;
                    case 'Logout':
                        router.push('/entry');
                        break;
                    default:
                }
            }
        },
        created() {
            this.getAllPosts();
        }
    };
</script>
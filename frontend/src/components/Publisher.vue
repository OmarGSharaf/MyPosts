<template>
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
</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        name: "Publisher",
        data() {
            return {
                newPost: {
                    data: {
                        content: '',
                        status: "PUBLIC"
                    },
                    submitted: false
                },
                marker: true,
                sheet: false,
            }
        },
        methods: {
            ...mapActions('posts', {
                createPost: 'createPost',
            }),
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
            toggleMarker() {
                this.marker = !this.marker;
            },
            togglePublisher() {
                this.sheet = !this.sheet;
            }
        }
    }
</script>
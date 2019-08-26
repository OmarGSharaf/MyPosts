<template>
    <v-card class="mt-11 mx-auto" max-width="800" outlined>
        <v-list-item>
            <v-list-item-content>
                <v-dialog v-if="enableDelete" v-model="dialog" persistent max-width="350">
                    <template v-slot:activator="{ on }">
                        <v-btn v-on="on" absolute top right icon>
                            <v-icon>mdi-delete</v-icon>
                        </v-btn>
                    </template>
                    <v-card>
                        <v-card-title class="headline">Warning</v-card-title>
                        <v-card-text>Are you sure you want to delete this post ?</v-card-text>
                        <v-card-actions>
                            <div class="flex-grow-1"></div>
                            <v-btn color="green darken-4" text @click="dialog = false">Disagree</v-btn>
                            <v-btn color="green darken-4" text @click="deletePost(post.id)">Agree</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
                <v-list-item-title class="headline mb-1">{{post.user.name}}</v-list-item-title>
                <v-list-item-subtitle>@ {{getTime(post.createdAt)}}</v-list-item-subtitle>
                <v-list-item-content>{{post.content}}</v-list-item-content>
            </v-list-item-content>
        </v-list-item>
    </v-card>
</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        name: "Post",
        props: {
            post: Object,
            enableDelete: String
        },
        data() {
            return {
                dialog: false
            }
        },
        methods: {
            ...mapActions('posts', {
                deletePost: 'delete'
            }),
            getTime(date) {
                return new Date(date).toLocaleString();
            }
        },
    }
</script>
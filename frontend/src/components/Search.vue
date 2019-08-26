<template>
    <v-row justify="center">
        <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
            <v-card>
                <v-toolbar dark color="light-blue darken-4">

                    <v-text-field
                            v-model="searchText"
                            width="100%"
                            hide-details
                            prepend-icon="search"
                            label="Search..."
                            single-line
                            @input="onSearch"
                    ></v-text-field>
                    <div class="flex-grow-0"></div>
                    <v-btn icon dark @click="clear">
                        <v-icon>close</v-icon>
                    </v-btn>
                </v-toolbar>
                <v-content>
                    <v-container fluid>
                        <div v-for="post in search.items" :key="post.id">
                            <post-card :post="post"></post-card>
                        </div>
                    </v-container>
                </v-content>
            </v-card>
        </v-dialog>
    </v-row>
</template>

<script>
    import {mapState, mapActions} from 'vuex'
    import Post from '../components/Post';

    export default {
        components: {
            "post-card": Post,
        },
        data() {
            return {
                dialog: false,
                searchText: ''
            }
        },
        computed: {
            ...mapState('posts', ['search'])
        },
        methods: {
            ...mapActions('posts', {
                searchPosts: 'search',
                clearSearch: 'clearSearch'
            }),
            onSearch() {
                this.searchPosts(this.searchText);
            },
            clear() {
                this.searchText = '';
                this.clearSearch();
                this.dialog = false;
            },
            toggle() {
                this.dialog = !this.dialog;
            }
        }
    }
</script>
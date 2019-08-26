<template>
    <v-app>
        <v-app-bar fixed dark>

            <v-toolbar-title>MyPosts</v-toolbar-title>

            <div class="flex-grow-1"></div>

            <v-btn icon>
                <v-icon @click="$refs.searcher.toggle()">mdi-magnify</v-icon>
            </v-btn>

            <v-btn icon>
                <v-icon @click="$refs.publisher.togglePublisher()">mdi-pencil</v-icon>
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
            <search-dialog ref="searcher"></search-dialog>
            <v-container fluid>
                <div v-for="post in all.items" :key="post.id">
                    <post-card :post="post" enable-delete=true></post-card>
                </div>
            </v-container>
        </v-content>

        <v-footer class="pa-0" dark app>
            <publisher ref="publisher"></publisher>
        </v-footer>
    </v-app>
</template>

<script>
    import {mapState, mapActions} from 'vuex'
    import {router} from '../router';
    import Post from '../components/Post';
    import Publisher from '../components/Publisher'
    import Search from "../components/Search";

    export default {
        components: {
            "post-card": Post,
            "publisher": Publisher,
            "search-dialog": Search
        },
        data() {
            return {
                options: ['New Post', 'Logout'],
                searchText: ''
            }
        },
        computed: {
            ...mapState('posts', ['all']),
            ...mapState('posts', ['search'])
        },
        methods: {
            ...mapActions('posts', {
                getAllPosts: 'getAll',
                searchPosts: 'search',
            }),
            onSearch() {
                this.searchPosts(this.searchText);
            },
            clearSearch() {
                this.searchText = '';
            },
            handleMenu(key) {
                switch (key) {
                    case 'New Post':
                        this.$refs.publisher.togglePublisher();
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
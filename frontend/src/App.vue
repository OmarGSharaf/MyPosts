<template>
    <v-app>
        <router-view></router-view>
    </v-app>
</template>

<script>
    export default {
        name: 'App',
        data: () => ({
            //
        }),
    };
</script>

<style>
    @import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');

    #app {
        font-family: "Montserrat", Helvetica, Arial, sans-serif;
    }
</style>

<template>
    <v-container>
        <v-row no-gutters>
            <v-col>
                <Login/>
            </v-col>
            <v-col>
                <Register/>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import Login from './components/Login';
    import Register from './components/Register';

    export default {
        name: 'App',
        components: {
            Login,
            Register
        },
        data: () => ({
            //
        }),
    };
</script>

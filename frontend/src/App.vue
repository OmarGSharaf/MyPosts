<template>
    <v-card outlined>
        <v-row no-gutters>
            <v-col>
                <v-container>
                    <Login/>
                </v-container>
            </v-col>
            <v-col>
                <Register/>
            </v-col>
        </v-row>
    </v-card>
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

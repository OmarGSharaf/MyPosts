<template>
    <v-container>
        <v-row no-gutters>
            <v-col>
                <v-container class="px-12">
                    <Login ref="login" @toggle="onLoginToggle"></Login>
                </v-container>
            </v-col>
            <v-col>
                <v-container class="px-12">
                    <Register ref="register" @toggle="onRegisterToggle"></Register>
                </v-container>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import Login from '../components/Login';
    import Register from '../components/Register';

    export default {
        name: 'App',
        components: {
            Login,
            Register
        },
        data: () => ({
            //
        }),
        methods: {
            onLoginToggle() {
                this.$refs.register.toggle();
            },
            onRegisterToggle() {
                this.$refs.login.toggle();
            }
        }
    };
</script>

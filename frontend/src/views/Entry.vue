<template>
    <v-content>
        <v-layout align-center justify-center column fill-height>
            <v-card width="770px" max-width="100%" min-height="480px">
                <v-row no-gutters>
                    <v-col>
                        <v-card class="px-12">
                            <Login ref="login" @toggle="onLoginToggle"></Login>
                        </v-card>
                    </v-col>
                    <v-col>
                        <v-flex class="px-12">
                            <Register ref="register" @toggle="onRegisterToggle"></Register>
                        </v-flex>
                    </v-col>
                </v-row>
            </v-card>
        </v-layout>
    </v-content>
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
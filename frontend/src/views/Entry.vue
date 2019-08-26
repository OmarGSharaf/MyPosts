<template>
    <v-container relative fill-height class="container justify-center">
        <v-card>
            <v-layout row wrap>
                <v-flex md6 fill-height>
                    <v-container relative hidden class="px-11 ma-0" height="100%">
                        <login-form ref="login" @toggle="onLoginToggle"></login-form>
                    </v-container>
                </v-flex>
                <v-flex md6 fill-height>
                    <v-container relative hidden class="px-11 ma-0" height="100%">
                        <registration-form ref="registration" @toggle="onRegisterToggle"></registration-form>
                    </v-container>
                </v-flex>
            </v-layout>
        </v-card>
    </v-container>
</template>

<script>
    import Login from '../components/Login';
    import Registration from '../components/Registration';

    export default {
        components: {
            "login-form": Login,
            "registration-form": Registration
        },
        methods: {
            onLoginToggle() {
                this.$refs.registration.toggle();
            },
            onRegisterToggle() {
                this.$refs.login.toggle();
            }
        }
    };
</script>

<style scoped>
    .container {
        width: 768px;
        max-width: 100%;
        min-height: 480px;
    }
</style>
<template>
    <v-form ref="form" @submit.prevent="submit" lazy-validation>
        <h1>Sign In</h1>
        <v-text-field
                v-model="form.data.email"
                v-validate="'required'"
                :error-messages="errors.collect('email')"
                label="Email"
                data-vv-name="email"
                outlined
        ></v-text-field>
        <v-text-field
                v-model="form.data.password"
                v-validate="'required'"
                :error-messages="errors.collect('password')"
                :append-icon="form.show.password ? 'visibility' : 'visibility_off'"
                :type="form.show.password ? 'text' : 'password'"
                label="Password"
                data-vv-name="password"
                outlined
                @click:append="form.show.password = !form.show.password"
        ></v-text-field>
        <v-btn
                :disabled="status.loggingIn"
                :loading="status.loggingIn"
                type="submit"
        >Sign In
        </v-btn>
    </v-form>
</template>

<script>
    import {mapState, mapActions} from 'vuex'

    export default {
        data() {
            return {
                form: {
                    data: {
                        email: '',
                        password: '',
                    },
                    show: {
                        password: false,
                    },
                    submitted: false
                },
            }
        },
        computed: {
            ...mapState('account', ['status'])
        },
        created() {
            this.logout();
        },
        methods: {
            ...mapActions('account', ['login', 'logout']),
            submit() {
                this.form.submitted = true;
                const {email, password} = this.form.data;
                this.$validator.validate()
                    .then(valid => {
                        if (valid) {
                            this.login({email, password});
                            this.clear();
                        }
                    });
            },
            clear() {
                this.$refs.form.reset();
            }
        }
    };
</script>
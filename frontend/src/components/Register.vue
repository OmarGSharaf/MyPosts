<template>
    <v-form ref="form" @submit.prevent="submit" lazy-validation>
        <h1>Create Account</h1>
        <v-text-field
                v-model="form.data.name"
                v-validate="'required'"
                :error-messages="errors.collect('name')"
                label="Name"
                data-vv-name="name"
                outlined
        ></v-text-field>
        <v-text-field
                v-model="form.data.email"
                v-validate="'required|email'"
                :error-messages="errors.collect('email')"
                label="Email"
                data-vv-name="email"
                outlined
        ></v-text-field>
        <v-text-field
                v-model="form.data.password"
                v-validate="'required|min:8|max:30'"
                :error-messages="errors.collect('password')"
                :type="'password'"
                label="Password"
                data-vv-name="password"
                outlined
                counter
        ></v-text-field>
        <v-text-field
                v-model="form.data.confirm_password"
                v-validate="'required|min:8|max:30|validate_password'"
                :error-messages="errors.collect('confirm password')"
                :type="'password'"
                label="Password"
                data-vv-name="confirm password"
                outlined
                counter
        ></v-text-field>
        <v-btn
                :disabled="status.registering"
                :loading="status.registering"
                type="submit"
        >Sign Up
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
                        name: '',
                        email: '',
                        password: '',
                        confirm_password: ''
                    },
                    show: {
                        password: false,
                        confirm_password: false,
                    },
                    submitted: false
                }
            }
        },
        computed: {
            ...mapState('account', ['status'])
        },
        created() {
            this.logout();
        },
        methods: {
            ...mapActions('account', ['register', 'logout']),
            submit() {
                this.form.submitted = true;
                this.$validator.validate()
                    .then(valid => {
                        if (valid) {
                            this.register(this.form.data);
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
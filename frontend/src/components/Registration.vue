<template>
    <v-container relative fill-height>
        <v-form class="text-center" v-if="form.enabled" @submit.prevent="submit" lazy-validation>
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
                    v-model="form.data.confirmPassword"
                    v-validate="'required'"
                    :error-messages="errors.collect('confirm password')"
                    :type="'password'"
                    label="Password"
                    data-vv-name="confirm password"
                    outlined
                    counter
            ></v-text-field>
            <div class="text-center">
                <v-btn
                        :disabled="status.registering"
                        :loading="status.registering"
                        type="submit"
                        rounded
                >Sign Up
                </v-btn>
            </div>
        </v-form>
        <v-container class="text-center" v-else>
            <h1>Hello, Friend!</h1>
            <p>Enter your personal details and start journey with us</p>
            <v-btn @click="toggle" rounded>Sign Up</v-btn>
        </v-container>
    </v-container>
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
                        confirmPassword: ''
                    },
                    show: {
                        password: false,
                        confirmPassword: false,
                    },
                    submitted: false,
                    enabled: false
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
            ...mapActions('account', ['register', 'logout']),
            submit() {
                this.form.submitted = true;
                const user = this.form.data;
                this.$validator.validate()
                    .then(valid => {
                        if (valid) {
                            this.register({user});
                            this.toggle();
                        }
                    });
            },
            toggle(emit) {
                this.form.enabled = !this.form.enabled;
                if (emit) this.$emit('toggle');
            }
        }
    };
</script>
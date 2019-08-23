<template>
    <v-form ref="form" @submit.prevent="submit">
        <h1>Create Account</h1>
        <v-text-field
                v-model="data.name"
                v-validate="'required'"
                :error-messages="errors.collect('name')"
                label="Name"
                data-vv-name="name"
                outlined
        ></v-text-field>
        <v-text-field
                v-model="data.email"
                v-validate="'required|email'"
                :error-messages="errors.collect('email')"
                label="Email"
                data-vv-name="email"
                outlined
        ></v-text-field>
        <v-text-field
                v-model="data.password"
                v-validate="'required|min:8|max:30'"
                :error-messages="errors.collect('password')"
                :type="'password'"
                label="Password"
                data-vv-name="password"
                outlined
                counter
        ></v-text-field>
        <v-text-field
                v-model="data.confirm_password"
                v-validate="'required|min:8|max:30|validate_password'"
                :error-messages="errors.collect('password')"
                :type="'password'"
                label="Password"
                data-vv-name="password"
                outlined
                counter
        ></v-text-field>
        <v-btn type="submit">Sign Up</v-btn>
    </v-form>
</template>

<script>
    export default {
        data() {
            return {
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
            }
        },
        methods: {
            submit() {
                console.log(this.data);
            },
            clear() {
                this.$refs.form.reset();
            }
        }
    };
</script>
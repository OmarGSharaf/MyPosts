<template>
    <v-form ref="form" @submit.prevent="submit">
        <h1>Sign In</h1>
        <v-text-field
                v-model="data.email"
                label="Email"
                outlined
        ></v-text-field>
        <v-text-field
                v-model="data.password"
                :type="'password'"
                label="Password"
                outlined
        ></v-text-field>
        <v-btn type="submit">Sign In</v-btn>
    </v-form>
</template>

<script>
    export default {
        data() {
            return {
                data: {
                    email: '',
                    password: '',
                },
                show: {
                    password: false,
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

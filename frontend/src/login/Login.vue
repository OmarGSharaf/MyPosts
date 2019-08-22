<template>
    <div class="container" ref="container" id="container">
        <div class="form-container sign-up-container">
            <form @submit.prevent="handleRegister">
                <h1>Create Account</h1>

                <input type="text"
                       v-model="register_data.name"
                       v-validate="'required'"
                       name="name"
                       placeholder="Name"
                       :class="{ 'is-invalid': register_data.submitted && errors.has('name') }"/>
                <div v-if="register_data.submitted && errors.has('name')" class="invalid-feedback">{{
                    errors.first('name') }}
                </div>
                <input type="text" v-model="register_data.email"
                       v-validate="'required|email'"
                       data-vv-as="email"
                       name="email"
                       placeholder="Email"
                       :class="{ 'is-invalid': register_data.submitted && errors.has('email') }"/>
                <div v-if="register_data.submitted && errors.has('email')" class="invalid-feedback">{{
                    errors.first('email') }}
                </div>
                <input type="password" v-model="register_data.password"
                       v-validate="'required|min:8|max:30|verify_password'"
                       name="newPassword"
                       ref="newPassword"
                       placeholder="Password"
                       :class="{ 'is-invalid': register_data.submitted && errors.has('newPassword') }"/>
                <div v-if="register_data.submitted && errors.has('newPassword')" class="invalid-feedback">{{
                    errors.first('newPassword')
                    }}
                </div>
                <input type="password" v-model="register_data.confirmPassword"
                       v-validate="'required|confirmed:newPassword'"
                       name="confirmPassword"
                       placeholder="Confirm Password"
                       :class="{ 'is-invalid': register_data.submitted && errors.has('confirmPassword') }"/>
                <div v-if="register_data.submitted && errors.has('confirmPassword')" class="invalid-feedback">{{
                    errors.first('confirmPassword') }}
                </div>

                <div class="form-group">
                    <button :disabled="status.registering">Sign Up</button>
                    <img v-show="status.registering"
                         src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA=="/>
                </div>


            </form>
        </div>
        <div class="form-container sign-in-container">
            <form @submit.prevent="handleLogin">
                <h1>Sign in</h1>
                <input type="email" v-model="login_data.email" name="email"
                       :class="{ 'is-invalid': login_data.submitted && !login_data.email }"
                       placeholder="Email"/>
                <input type="password" @ v-model="login_data.password" name="password"
                       :class="{ 'is-invalid': login_data.submitted && !login_data.password }" placeholder="Password"/>
                <button :disabled="status.loggingIn">Sign In</button>
            </form>
        </div>
        <div class="overlay-container">
            <div class="overlay">
                <div class="overlay-panel overlay-left">
                    <h1>Welcome Back!</h1>
                    <p>To keep connected with us please login with your personal info</p>
                    <button class="ghost" @click="onLoginClick" id="signIn">Sign In</button>
                </div>
                <div class="overlay-panel overlay-right">
                    <h1>Hello, Friend!</h1>
                    <p>Enter your personal details and start journey with us</p>
                    <button class="ghost" @click="onRegisterClick" id="signUp">Sign Up</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapState, mapActions} from 'vuex'

    export default {
        data() {
            return {
                login_data: {
                    email: '',
                    password: '',
                    submitted: false
                },
                register_data: {
                    name: '',
                    email: '',
                    password: '',
                    confirm_password: '',
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
            ...mapActions('account', ['login', 'register', 'logout']),
            handleLogin(e) {
                this.login_data.submitted = true;
                const {email, password} = this.login_data;
                if (email && password) {
                    this.login({email, password})
                }
            },
            handleRegister(e) {
                this.register_data.submitted = true;
                this.$validator.validate().then(valid => {
                    if (valid) {
                        this.register(this.register_data);
                    }
                });
            },
            onLoginClick() {
                this.$refs.container.classList.remove("right-panel-active");
            },
            onRegisterClick() {
                this.$refs.container.classList.add("right-panel-active");
            }
        }
    };
</script>

<style>

    @import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');

    * {
        box-sizing: border-box;
    }

    body {
        background: #f6f5f7;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        font-family: 'Montserrat', sans-serif;
        height: 100vh;
        margin: -20px 0 50px;
    }

    h1 {
        font-weight: bold;
        margin: 0;
    }

    h2 {
        text-align: center;
    }

    p {
        font-size: 14px;
        font-weight: 100;
        line-height: 20px;
        letter-spacing: 0.5px;
        margin: 20px 0 30px;
    }

    span {
        font-size: 12px;
    }

    a {
        color: #333;
        font-size: 14px;
        text-decoration: none;
        margin: 15px 0;
    }

    button {
        border-radius: 20px;
        border: 1px solid #FF4B2B;
        background-color: #FF4B2B;
        color: #FFFFFF;
        font-size: 12px;
        font-weight: bold;
        padding: 12px 45px;
        letter-spacing: 1px;
        text-transform: uppercase;
        transition: transform 80ms ease-in;
        margin-top: 10px;
    }

    .invalid-feedback{
        font-size: 12px;
        color: #FF416C;
    }

    button:active {
        transform: scale(0.95);
    }

    button:focus {
        outline: none;
    }

    button.ghost {
        background-color: transparent;
        border-color: #FFFFFF;
    }

    form {
        background-color: #FFFFFF;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
        padding: 0 50px;
        height: 100%;
        text-align: center;
    }

    input {
        background-color: #eee;
        border: none;
        padding: 12px 15px;
        margin: 8px 0;
        width: 100%;
    }

    .container {
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25),
        0 10px 10px rgba(0, 0, 0, 0.22);
        position: relative;
        overflow: hidden;
        width: 768px;
        max-width: 100%;
        min-height: 480px;
    }

    .form-container {
        position: absolute;
        top: 0;
        height: 100%;
        transition: all 0.6s ease-in-out;
    }

    .sign-in-container {
        left: 0;
        width: 50%;
        z-index: 2;
    }

    .container.right-panel-active .sign-in-container {
        transform: translateX(100%);
    }

    .sign-up-container {
        left: 0;
        width: 50%;
        opacity: 0;
        z-index: 1;
    }

    .container.right-panel-active .sign-up-container {
        transform: translateX(100%);
        opacity: 1;
        z-index: 5;
        animation: show 0.6s;
    }

    @keyframes show {
        0%, 49.99% {
            opacity: 0;
            z-index: 1;
        }

        50%, 100% {
            opacity: 1;
            z-index: 5;
        }
    }

    .overlay-container {
        position: absolute;
        top: 0;
        left: 50%;
        width: 50%;
        height: 100%;
        overflow: hidden;
        transition: transform 0.6s ease-in-out;
        z-index: 100;
    }

    .container.right-panel-active .overlay-container {
        transform: translateX(-100%);
    }

    .overlay {
        background: #FF416C;
        background: -webkit-linear-gradient(to right, #FF4B2B, #FF416C);
        background: linear-gradient(to right, #FF4B2B, #FF416C);
        background-repeat: no-repeat;
        background-size: cover;
        background-position: 0 0;
        color: #FFFFFF;
        position: relative;
        left: -100%;
        height: 100%;
        width: 200%;
        transform: translateX(0);
        transition: transform 0.6s ease-in-out;
    }

    .container.right-panel-active .overlay {
        transform: translateX(50%);
    }

    .overlay-panel {
        position: absolute;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
        padding: 0 40px;
        text-align: center;
        top: 0;
        height: 100%;
        width: 50%;
        transform: translateX(0);
        transition: transform 0.6s ease-in-out;
    }

    .overlay-left {
        transform: translateX(-20%);
    }

    .container.right-panel-active .overlay-left {
        transform: translateX(0);
    }

    .overlay-right {
        right: 0;
        transform: translateX(0);
    }

    .container.right-panel-active .overlay-right {
        transform: translateX(20%);
    }

    .social-container {
        margin: 20px 0;
    }

    .social-container a {
        border: 1px solid #DDDDDD;
        border-radius: 50%;
        display: inline-flex;
        justify-content: center;
        align-items: center;
        margin: 0 5px;
        height: 40px;
        width: 40px;
    }
</style>
<template>
    <div id="app">
        <Header :user="user"/>
        <Middle :posts="posts" :users="users"/>
        <Footer/>
    </div>
</template>

<script>
import Header from "./components/Header";
import Middle from "./components/Middle";
import Footer from "./components/Footer";
import axios from "axios"

function updateData(data) {
    axios.get("/api/1/posts").then(response => {
        data.posts = response.data;
    });

    axios.get("/api/1/users").then(response => {
        data.users = response.data;
    });
}

export default {
    name: 'App',
    components: {
        Footer,
        Middle,
        Header
    },
    data: function () {
        return {
            user: null,
            posts: [],
            users: [],
        }
    },
    beforeMount() {
        if (localStorage.getItem("jwt") && !this.user) {
            this.$root.$emit("onJwt", localStorage.getItem("jwt"));
        }

        updateData(this);
    },
    beforeCreate() {
        this.$root.$on("onEnter", (login, password) => {
            axios.post("/api/1/jwt", {
                    login, password
            }).then(response => {
                localStorage.setItem("jwt", response.data);
                this.$root.$emit("onJwt", response.data);
            }).catch(error => {
                this.$root.$emit("onEnterValidationError", error.response.data);
            });
        });

        this.$root.$on("onRegister", (login, name, password) => {
            axios.post("/api/1/users", {
                login, name, password,
            }).then(() => {
                updateData(this);
                this.$root.$emit("onEnter", login, password);
            }).catch(error => {
                this.$root.$emit("onRegisterValidationError", error.response.data);
            });
        });

        this.$root.$on("onJwt", (jwt) => {
            localStorage.setItem("jwt", jwt);

            axios.get("/api/1/users/auth", {
                params: {
                    jwt,
                }
            }).then(response => {
                this.user = response.data;
                this.$root.$emit("onChangePage", "Index");
            }).catch(() => this.$root.$emit("onLogout"))
        });

        this.$root.$on("onLogout", () => {
            localStorage.removeItem("jwt");
            this.user = null;
        });

        this.$root.$on("onWritePost", (title, text) => {
            if (this.user) {
                axios.post("/api/1/posts", {
                    title, text, userId: this.user.id,
                }).then(() => {
                    updateData(this);
                }).catch(error => {
                    this.$root.$emit("onWritePostValidationError", error.response.data);
                });
            } else {
                this.$root.$emit("onWritePostValidationError", "No access");
            }
        })
    }
}
</script>

<style>
#app {

}
</style>

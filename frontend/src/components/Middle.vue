<template>
    <div class="middle">
        <Sidebar :posts="viewPosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="posts"/>
            <Users v-if="page === 'Users'" :users="users"/>
            <WritePost v-if="page === 'WritePost'"/>
            <Post v-if="page === 'Post' && post !== null" :post="post" :show-comments="true"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./main/Index";
import Enter from "./main/Enter";
import Register from "./main/Register";
import Users from "@/components/main/Users";
import WritePost from "@/components/main/WritePost";
import Post from "@/components/main/Post";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index",
            post: null,
        };
    },
    components: {
        WritePost,
        Users,
        Register,
        Enter,
        Index,
        Sidebar,
        Post,
    },
    props: ["posts", "users"],
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        }
    },
    beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.page = page);
        this.$root.$on("onShowPost", (post) => {
            this.page = "Post";
            this.$root.$set(this, "post", post);
        });
    }
}
</script>

<style scoped>

</style>

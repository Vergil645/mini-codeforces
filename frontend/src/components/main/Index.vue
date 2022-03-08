<template>
    <div>
        <Post v-for="post in posts" :key="post.id" :post="post" :show-comments="false"/>
    </div>
</template>

<script>
import Post from "@/components/main/Post";

export default {
    name: "Index",
    components: {Post},
    props: ["posts"],
}
</script>

<style scoped>

</style>
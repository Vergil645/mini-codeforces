<template>
    <article>
        <div class="title" @click.prevent="showPost(post)">{{ post.title }}</div>
        <div class="information">By {{ post.user.login }}</div>
        <div class="body">
            <p>{{ post.text }}</p>
            <p></p>
        </div>
        <ul class="attachment"></ul>
        <div class="footer">
            <div class="left">
                <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">???</span>
                <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                ???
                <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#">{{ post.comments.length }}</a>
            </div>
        </div>
        <template v-if="showComments">
            <div class="comments-header">Comments</div>
            <template v-if="post.comments.length > 0">
                <div class="comment" v-for="comment in post.comments" :key="comment.id">
                    <div class="comment-information">By {{ comment.user.login }}</div>
                    <div class="comment-body">{{ comment.text }}</div>
                </div>
            </template>
            <template v-else>
                No comments
            </template>
        </template>
    </article>
</template>

<script>
// import axios from "axios"

// function updateData(data) {
//     axios.get("/api/1/comments", {
//         params: {
//             postId: data.post.id,
//         },
//     }).then(response => {
//         data.comments = response.data;
//     });
// }

export default {
    name: "Post",
    props: ["post", "showComments"],
    // data: function () {
    //     return {
    //         comments: [],
    //     };
    // },
    // beforeMount() {
    //     updateData(this);
    // },
    // beforeUpdate() {
    //     updateData(this);
    // },
    methods: {
        showPost: function (post) {
            this.$root.$emit("onShowPost", post);
        },
    }
}
</script>

<style scoped>
article .title {
    cursor: pointer;
}

article .comments-header {
    color: var(--caption-color);
    font-weight: bold;
    font-size: 1.25rem;

    margin-top: 1rem;
    margin-bottom: 0.5rem;
}

article .comment {
    margin-top: 0.75rem;
}

article .comment > .comment-information {
    font-size: 0.85rem;
    color: #888;
}

article .comment > .comment-body {
    margin-top: 0.2rem;
}
</style>
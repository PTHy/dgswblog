window.onload = getPosts();

async function getPosts() {
    try {
        const response = await $.ajax({
            url: '/post',
            method: 'get'
        });

        console.log(response);

        if (response.code == 0)
            throw error;

        drawPosts(response.data);
    } catch (error) {
        console.log(`ERROR : ${error.message}`);
    }
}

function drawPosts(posts) {
    posts.map(p => {
        drawPost(p);
    });
}

function drawPost(post) {
    $('.content').append(`
        <div>${post.created}</div>
        <div>${post.content}</div>
    `);
}
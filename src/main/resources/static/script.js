let currentUser;
let selectedPost;
let posts;
const ADD_POST = 1;
const MODIFY_POST = 2;

window.onload = getPosts();

async function getPosts() {
    try {
        $('ul#post-list').empty();
        const response = await $.ajax({
            url: '/post',
            method: 'get'
        });

        console.log(response);

        if (response.code == 0)
            throw error;

        posts = response.data;
        selectPost(posts[0].id);
        drawPostsList(posts);
    } catch (error) {
        console.log(`ERROR : ${error.message}`);
    }
}

function drawPostsList(posts) {
    posts.map(p => {
        drawPostList(p);
    });
}

function drawPostList(post) {
    console.log(post);
    $('ul#post-list').append(`
        <li id="post${post.id}" onclick="selectPost(${post.id})"><i class="fas fa-angle-right"></i> ${post.title}</li>
    `)
}

async function selectPost(id) {
    try {
        const response = await $.ajax({
            url: `/post/${id}`,
            method: 'get'
        });

        console.log(response);

        if (response.code == 0)
            throw error;

        $(`li#post${selectedPost}`).removeClass('selected');
        $(`li#post${id}`).addClass('selected');
        drawPostContent(response.data);
        selectedPost = id;

        $('div#post-button').children(":first").removeAttr("onclick");
        $('div#post-button').children(":first").attr("onclick", `openPostDialog(${MODIFY_POST}, ${id})`);

        $('div#post-button').children(":last").removeAttr("onclick");
        $('div#post-button').children(":last").attr("onclick", `deletePost(${id})`);
    } catch (error) {
        console.log(`ERROR : ${error.message}`);
    }
}

function drawPostContent(post) {
    $('div#post-title').text(post.title);
    $('div#post-content').text(post.content);
    $('div#post-created-date').text(post.created);
}

function openLoginDialog() {
    if (currentUser) {
        alert("이미 로그인이 되어 있습니다");
        return;
    }
    $('#login-popup').show(400);
}

function closeLoginDialog() {
    $('#input-user-id').val('');
    $('#input-user-password').val('');
    $('#login-popup').hide(1000);
}

async function login() {
    try {
        const data = {
            account: $('#input-user-id').val(),
            password: $('#input-user-password').val()
        };
        await $.ajax({
            url: '/user/login',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: (result) => {
                if(result.code == 0) {
                    alert("로그인 실패");
                } else {
                    alert("로그인 성공");
                    getPosts();
                    const { data } = result;
                    currentUser = data.id;
                    const imagePath = (data.storedPath === null) ? "/public/default.jpg" :  `/attachment/user/${data.id}`;
                    $('#profile-image').children('img').attr("src", imagePath);
                    $('#user-account').text(data.account);
                    $('#user-name').text(data.username);
                    $('#user-email').text(data.email);
                    $('#user-joined').text(data.joined);
                    $('#user-posts').text(posts.length);

                    changeLoginMode();
                }
            }
        })
    } catch (error) {
        console.log(error);
        alert("로그인 실패");
    } finally {
        closeLoginDialog();
    }
}

function chkLogin() {
    if (!currentUser) {
        alert("로그인이 필요합니다!");
        return false;
    }
    return true;
}

function changeLoginMode() {
    if (currentUser) {
        $('#user-info').show();
        $('#login-button').hide();
    } else {
        $('#user-info').show();
        $('#login-button').hide();
    }
}

function openPostDialog(type, id) {
    if (!chkLogin())
        return;

    switch(type) {
        case ADD_POST:
            $('#post-popup').find($('#add-button')).show();
            $('#post-popup').find($('#modify-button')).hide();
            break;
        case MODIFY_POST:
            $('#new-post-title').val($('div#post-title').text());
            $('#new-post-content').val($('div#post-content').text());

            $('#post-popup').find($('#modify-button')).children(":first").removeAttr("onclick");
            $('#post-popup').find($('#modify-button')).children(":first").attr("onclick", `modifyPost(${id})`);

            $('#post-popup').find($('#add-button')).hide();
            $('#post-popup').find($('#modify-button')).show();
            break;
        default:
            alert('알수없는 에러!');
            return;
    }

    $('#post-popup').show(400);
}

function closePostDialog() {
    $('#new-post-title').val('');
    $('#new-post-content').val('');
    $('#post-popup').hide(1000);
}

async function deletePost(id) {
    try {
        await $.ajax({
            url: `/post/${id}`,
            type: 'delete',
            success: (result) => {
                if (result.code == 0)
                    throw new Error;

                alert("글이 삭제되었습니다");
                getPosts();
            }
        });
    } catch (error) {
        console.log(`[ERROR] 글 삭제 실패 ${JSON.stringify(error)}`);
        alert("글 삭제에 실패하였습니다");
    }
}

async function addPost() {
    const newPost = {
        userId: currentUser,
        title: $('#new-post-title').val(),
        content: $('#new-post-content').val(),
    };

    try {
        await $.ajax({
            url: '/post',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(newPost),
            success: (result) => {
                alert("글 작성에 성공하였습니다");
                drawPostList(result.data);
            }
        });
    } catch (error) {
        console.log( `[ERROR] 글 작성 실패 ${JSON.stringify(error)}`);
        alert("글 작성에 실패하였습니다");
    } finally {
        closePostDialog();
    }
}

async function modifyPost(id) {
    const newPost = {
        userId: currentUser,
        title: $('#new-post-title').val(),
        content: $('#new-post-content').val(),
    };

    try {
        await $.ajax({
            url: `/post/${id}`,
            type: 'put',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(newPost),
            success: (result) => {
                alert("글 수정에 성공하였습니다");
                getPosts();
            }
        });
    } catch (error) {
        console.log( `[ERROR] 글 수정 실패 ${JSON.stringify(error)}`);
        alert("글 수정에 실패하였습니다");
    } finally {
        closePostDialog();
    }
}
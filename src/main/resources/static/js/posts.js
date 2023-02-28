'use strict';

$(document).ready(() => {

    console.log("ran the thing");
    getPosts();

    function getPosts() {
        fetch(`/posts.json`)
            .then(response => response.json()).then((data) => {
            console.log(data);
            console.log("user id: ", $('#userId').val());
            data.forEach(function (post) {
                console.log("poster id: ", post.poster.id);
                if ($('#userId').val() == post.poster.id) {
                    $('#postContainer').append(`
            <div class = "col-2 m-1 d-flex flex-column border border-3 rounded-1">
                <h3 class ="text-center fw-bold">${post.title}</h3>
                <p>${post.body}</p>
                <div class="d-flex justify-content-end">
                    <a class="btn btn-sm btn-primary m-1" href="/posts/${post.id}">View</a>
                    <a class="btn btn-sm btn-primary m-1" href="/posts/${post.id}/edit">Edit</a>
                    <a class="btn btn-sm btn-danger m-1" href="/posts/${post.id}/delete">Delete</a>
                </div>
            </div>
                `)
                } else {
                    $('#postContainer').append(`
            <div class = "col-2 m-1 d-flex flex-column border border-3 rounded-1">
                <h3 class ="text-center fw-bold">${post.title}</h3>
                <p>${post.body}</p>
                <div class="d-flex justify-content-end">
                    <a class="btn btn-sm btn-primary m-1" href="/posts/${post.id}">View</a>
                </div>
            </div>
                `)
                }

            })
        })
    }
})



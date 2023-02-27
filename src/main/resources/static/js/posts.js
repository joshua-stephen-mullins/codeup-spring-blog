'use strict';

$(document).ready(() => {

    console.log("ran the thing");
    getPosts();

    function getPosts(){
        fetch(`/posts.json`)
            .then(response => response.json()).then((data) => {
            console.log(data);
            data.forEach(function(post){
                $('#postContainer').append(`
            <div class = "col-2 m-1 d-flex flex-column border border-3 rounded-1">
                <h3 class ="text-center fw-bold">${post.title}</h3>
                <p>${post.body}</p>
                <div class="d-flex justify-content-end">
                    <a class="btn btn-sm btn-primary m-1" href="/posts/${post.id}">View</a>

                </div>
            </div>
                `)
            })
        })
    }
})

// <div th:if="${#authentication.principal.id} != null">
//     <a th:if="${post.poster.getId()} == ${#authentication.principal.id}"
//     className="btn btn-sm btn-primary m-1"
// th:href="@{'/posts/' + ${post.getId()} + '/edit'}">Edit</a>
// <a th:if="${post.poster.getId()} == ${#authentication.principal.id}"
//    className="btn btn-sm btn-danger m-1"
//    th:href="@{'/posts/' + ${post.getId()} + '/delete'}">Delete</a>
// </div>

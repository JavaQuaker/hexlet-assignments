<!-- BEGIN -->
@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>Hello Hexlet!</title>
</head>
<h1>Список постов</h1>

    @for(var post : page.getPosts())
       <div>

            <h2><a href="/posts/${post.getId()}">${post.getName()}</a></h2>
       </div>
    @endfor
    <nav>
            <ul>
                <li>

                    <a href="${NamedRoutes.postsPath(page.getList() - 1)}">Prev</a>
                </li>
                <li>
                 <a href="${NamedRoutes.postsPath(page.getList() + 1)}">Next</a>
                </li>
            </ul>
        </nav>
</html>

<!-- END -->

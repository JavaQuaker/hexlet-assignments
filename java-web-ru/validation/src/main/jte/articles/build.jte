<!-- BEGIN -->
@import exercise.dto.articles.NewArticlePage
  @param NewArticlePage page

  @if(page.getErrors() != null)
      <ul>
          @for(var validator : page.getErrors().values())
              @for(var error : validator)
                  <li>${error.getMessage()}</li>
              @endfor
          @endfor
      </ul>
  @endif

  <form action="/articles" method="post">
    <div>
      <label>
        Title
        <input type="text" name="title" value="${page.getTitle()}" />
      </label>
    </div>
    <div>
      <label>
        Content
        <input type="text" name="content" value="${page.getContent()}" />
      </label>
    </div>


    <input type="submit" value="create" />
  </form>
<!-- END -->

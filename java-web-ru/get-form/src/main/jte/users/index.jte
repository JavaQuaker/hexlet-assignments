<!-- BEGIN -->
@import exercise.dto.users.UsersPage
@param UsersPage page

 <h1>Search User</h1>
 <form action="/users" method="get">
     <input type="search" name="term" value="${page.getTerm()}" />
     <input type="submit" value="Search" />
   </form>
    @if (page.getTerm() != null)
        <table class="table table-striped">
            @for(var user : page.getUsers())
                <tr>
                 <td>
                   ${user.getId()}
                   </td>
                    <td>
                       <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
                    </td>
                </tr>
            @endfor
          </table>
            @endif

<!-- END -->

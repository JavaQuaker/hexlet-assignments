<!-- BEGIN -->
@import exercise.dto.users.UsersPage
@param UsersPage page
 <form action="/users" method="get">
          <input type="search" name="term" value="${page.getTerm()}" />
          <input type="submit" value="Search" />
        </form>
        @if (page.getUsers() != null)
            <h1>Пользователи</h1>
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

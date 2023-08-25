<!-- BEGIN -->

@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        <a href="/users">New User</a>
        @for(var user : page.getUsers())
            <div>${user}</div>
        @endfor
    `
)
<!-- END -->

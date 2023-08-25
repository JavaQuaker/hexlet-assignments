<!-- BEGIN -->
@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(

content = @`
<h2>${page.getUser().getFirstName().getLastName}<h/2>
`,
)
<!-- END -->

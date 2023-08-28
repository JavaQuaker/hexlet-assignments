<!-- BEGIN -->

<form action="/users" method="post">
  <div>
    <label>
      First Name
      <input type="text" name="FirstName" />
    </label>
  </div>
  <div>
  <label>
  Last Name
  <input type="text" name="LastName" />
  </label>
  </div>
  <div>
    <label>
      Email
      <input type="email" required name="email" />
    </label>
  </div>
  <div>
    <label>
     Password
      <input type="password" required name="password" />
    </label>
  </div>
  <div>
    <label>
      password confirmation
      <input type="password" required name="passwordConfirmation" />
    </label>
  </div>
  <input type="submit" value="Register on" />
</form>

<!-- END -->

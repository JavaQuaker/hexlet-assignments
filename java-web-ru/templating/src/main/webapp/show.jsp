<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Company</title>
        // Подключаем стили Bootstrap
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
    <table>
    <thead>

   <tr>
     <th scope="col">id</th>
      <th scope="col">FirstName</th>
      <th scope="col">LastName</th>
       <th scope="col">email</th>
       </tr>
       </thead>
       <tbody>

             <tr>
             <td>${user.get("id")}</td>
             <td><a href='/users/show?id=${user.get("id")}'>${user.get("id")}</a></td>



             <td>${user.get("firstName")}</td>
             <td><a href='/users/show?id=${user.get("firstName")}'>${user.get("firstName")}</a>




                          <td>${user.get("lastName")}</td>
                          <td><a href='/users/show?id=${user.get("lastName")}'>${user.get("lastName")}</a>



                                       <td>${user.get("email")}</td>
                                       <td><a href='/users/show?id=${user.get("email")}'>${user.get("email")}</a>
                                       </tr>

         </tbody>
         <table>
         </body>

<!-- END -->


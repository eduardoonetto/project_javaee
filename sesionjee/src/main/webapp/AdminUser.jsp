<%-- 
    Document   : AdminUser
    Created on : 21 jun. 2022, 22:03:55
    Author     : Eduardo.Onetto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%
    if (session.getAttribute("session_email") == null) {
        response.sendRedirect("index.jsp");
    }
    System.out.println(session.getAttribute("session_email"));
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administraci&oacute;n de Usuarios</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
        <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet" >
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link rel="stylesheet" href="Assets/css/style.css">
  
    </head>
    <body >
        <jsp:include page="nav.jsp" />
        
        <section class="vh-100 gradient-custom">
           
        <form class="p-3 container needs-validation" action="Admin" method="POST" novalidate>
             
            <h3 class="text-white"> <i class="fas fa-users"></i> <u>Administraci&oacute;n de Usuarios. </u></h3><br/>
         <!-- 2 column grid layout with text inputs for the first and last names -->
            <div class="row mb-4">
              <div class="col">
                <div class="form-outline">
                    <input type="text" name="fname" required id="form3Example1" class="form-control" />
                    <label class="form-label text-white" for="form3Example1">Nombre</label>
                    <div class="invalid-feedback">
                        Por favor ingresa tu Nombre.
                    </div>
                </div>
              </div>
              <div class="col">
                <div class="form-outline">
                  <input type="text" name="lname" required id="form3Example2" class="form-control" />
                  <label class="form-label text-white" for="form3Example2">Apellido</label>
                  <div class="invalid-feedback">
                        Por favor ingresa tu Apellido.
                    </div>
                </div>
              </div>
            </div>
         
            <div class="row mb-4">
              <div class="col">
                <div class="form-outline">
              <input type="email" name="email" required   id="form3Example3" class="form-control" />
              <label class="form-label text-white" for="form3Example3">Email</label>
              <div class="invalid-feedback">
                        Por favor ingresa tu Email.
                    </div>
                </div>
              </div>
              <div class="col">
                <div class="form-outline">
                     <input type="password" name="pass" required id="form3Example4" class="form-control" />
              <label class="form-label text-white" for="form3Example4">Password</label>
              <div class="invalid-feedback">
                        Por favor ingresa tu Password.
                    </div>
                </div>
              </div>
            </div>
            <input type="text" name="action" value="addUser" class="d-none" />  
            <!-- Submit button -->
            <button class="btn btn-outline-light btn-lg px-5" type="submit">Grabar</button>


        </form>    
            
        <div class="container">
       <jsp:useBean class="model.UserDao" id="userDao"></jsp:useBean>     
                        
            <hr/>
        <table id="example" class="table table-striped table-light" style="width:100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Email</th>
                <th>Password</th>                
                <th>Acciones</th>

            </tr>
        </thead>
        <tbody> 
             <c:forEach items="${userDao.list()}" var="users">    
            <tr>
                 <th>${users.id}</th>
                <td>${users.nombre} ${users.apellido}</td>
                <td>${users.email}</td>                            
                <td>${users.password}</td>
            
                <td>
                    <button type="button" class="btn btn-outline-warning"  onclick="edit(${users.id})">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                        </svg>
                    </button>
                    <button type="button" class="btn btn-outline-danger" onclick="del(${users.id})" >
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
                            <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/>
                        </svg>
                    </button>
                </td>
            </tr>
            </c:forEach> 
        </tbody>
        <tfoot>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Email</th>
                <th>Password</th>           
                <th>Acciones</th>
            </tr>
        </tfoot>
    </table>
            </div>
        </section>
    </body>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
    </script>
         <script src="https://code.jquery.com/jquery-3.5.1.js"></script> 
         
    <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>    
    <script src="https://cdn.datatables.net/buttons/2.2.3/js/dataTables.buttons.min.js"></script>    
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.html5.min.js   "></script>





    <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
    
        
    <script>
        $(document).ready(function () {
            $('#example').DataTable({
            "language": {
                    "url": "//cdn.datatables.net/plug-ins/1.10.11/i18n/Spanish.json"
                },
            dom: 'Bfrtip',
            buttons: [
                'copyHtml5',
                'excelHtml5',
                'csvHtml5',
                'pdfHtml5'
            ]          
            });
        });
        function exit() {
    Swal.fire({
        title: 'Deseas salir?',
        text: "Estas a punto de salir!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: 'Cancelar',
        confirmButtonText: 'Cerrar Session'
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = "Admin?action=logout";
        }
    });
}
    </script>    
  <script>
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict';

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation');

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault();
          event.stopPropagation();
        }

        form.classList.add('was-validated');
      }, false);
    });
})();
    </script>
    <script src="./Assets/js/main.js" type="text/javascript"></script>
</html>

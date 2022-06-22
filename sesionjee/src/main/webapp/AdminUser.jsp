<%-- 
    Document   : AdminUser
    Created on : 21 jun. 2022, 22:03:55
    Author     : Eduardo.Onetto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
           
        <form class="p-3 container" action="AdminUser" method="POST">
            <h3 class="text-white"> <i class="fas fa-users"></i> <u>Administraci&oacute;n de Usuarios. </u></h3><br/>
         <!-- 2 column grid layout with text inputs for the first and last names -->
            <div class="row mb-4">
              <div class="col">
                <div class="form-outline">
                  <input type="text" name="fname" id="form3Example1" class="form-control" />
                  <label class="form-label text-white" for="form3Example1">Nombre</label>
                </div>
              </div>
              <div class="col">
                <div class="form-outline">
                  <input type="text" name="lname" id="form3Example2" class="form-control" />
                  <label class="form-label text-white" for="form3Example2">Apellido</label>
                </div>
              </div>
            </div>
         
            <div class="row mb-4">
              <div class="col">
                <div class="form-outline">
              <input type="email" name="email" id="form3Example3" class="form-control" />
              <label class="form-label text-white" for="form3Example3">Email</label>
                </div>
              </div>
              <div class="col">
                <div class="form-outline">
                     <input type="password" name="pass" id="form3Example4" class="form-control" />
              <label class="form-label text-white" for="form3Example4">Password</label>
                </div>
              </div>
            </div>
            
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
            </c:forEach> 
                <td><button class="btn btn-warning">Editar</button> <button class="btn btn-danger ml-3">Eliminar</button></td>
            </tr>
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
        confirmButtonText: 'Yes, exit it!'
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = "../../mavenproject13/Cerrar";
        }
    });
}
    </script>    
</html>

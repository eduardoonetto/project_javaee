<%-- 
    Document   : index
    Created on : 21 jun. 2022, 21:59:46
    Author     : Eduardo.Onetto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

    if (session.getAttribute("session_email") != null) {
        response.sendRedirect("AdminUser.jsp");
    }
%>
<html>
    <head>
        <title>Start Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="stylesheet" href="Assets/css/style.css">
    </head>
    <body>
        <section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card bg-dark text-white" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">

            <div class="mb-md-5 mt-md-4 pb-5">

              <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
              <p class="text-white-50 mb-5">EPE 3</p>
              <%
                if (request.getAttribute("res") != null) {
                    out.print("<h3> <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"20\" height=\"20\" fill=\"currentColor\" class=\"bi bi-exclamation-diamond-fill\" viewBox=\"0 0 16 16\">\n"
                            + "  <path d=\"M9.05.435c-.58-.58-1.52-.58-2.1 0L.436 6.95c-.58.58-.58 1.519 0 2.098l6.516 6.516c.58.58 1.519.58 2.098 0l6.516-6.516c.58-.58.58-1.519 0-2.098L9.05.435zM8 4c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 4.995A.905.905 0 0 1 8 4zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z\"/>\n"
                            + "</svg> " + request.getAttribute("res") + "</h3></br>");
                }
            %>
              <form action="Admin" method="POST" class="needs-validation" novalidate>
              <div class="form-outline form-white mb-4">
                <input type="email" name="email" required id="typeEmailX" class="form-control " />
                <label class="form-label" for="typeEmailX">Email</label>
                <div class="invalid-feedback">
                    Por favor ingresa tu email.
                </div>
              </div>

              <div class="form-outline form-white mb-4">
                <input type="password" name="password" required id="typePasswordX" class="form-control form-control-lg" />
                <label class="form-label" for="typePasswordX">Password</label>
                <div class="invalid-feedback">
                    Por favor ingresa tu password.
                </div>
              </div>
                  <input type="text" name="action" value="login" class="d-none" />
              <p class="small mb-5 pb-lg-2"> </p>
            
              <button class="btn btn-outline-light btn-lg px-5" type="submit">Entrar</button>
              </form>
              <div class="d-flex justify-content-center text-center mt-4 pt-1">
                  <img src="https://www.ipchile.cl/wp-content/uploads/2020/09/Logo-IPCHILE-590x590-300dpi.png" width="150" />
              </div>
                  <p>EDUARDO ONETTO CORREA</p>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</section>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
  <script>
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()
    </script> 
    </body>
</html>


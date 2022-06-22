/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



function sleep (time) {
  return new Promise((resolve) => setTimeout(resolve, time));
}

function get_info_by_id(id){
    var response = $.ajax({ type: "POST",   
                        url: "/sesionjee/Admin",   
                        async: false,
                        data: { 
                            id_user : id,
                            action : "get_info_by_id"
                        }
                      }).responseText;
    myjson = JSON.parse(response);
    
    return myjson;
}

function edit(id) {
    
    const info = get_info_by_id(id);
    Swal.fire({
    title: '-Editar Usuario-',
    icon: 'warning',
    confirmButtonColor: '#FACEA8',
    html: '<input style="display:none" id="ed_id" type="text" value="'+info.id+'"><input id="ed_email" type="email" value="'+info.email+'" placeholder="'+ info.email +'"  class="swal2-input"><br><input  class="swal2-input" value="'+info.password+'" id="ed_password" type="password" placeholder="'+ info.password +'"><br><input  class="swal2-input" value="'+info.nombre+'" id="ed_nombre" type="text" placeholder="'+ info.nombre +'"><br><input  class="swal2-input" value="'+info.apellido+'" id="ed_apellido" type="text" placeholder="'+ info.apellido +'">',
    inputAttributes: {
      autocapitalize: 'off'
    },
    showCancelButton: true,
    confirmButtonText: 'Editar',
    showLoaderOnConfirm: true,
    preConfirm: (users) => {
        let formData = new FormData();
        formData.append('ed_email', $('#ed_email').val());
        formData.append('ed_password', $('#ed_password').val());
        formData.append('ed_nombre', $('#ed_nombre').val());        
        formData.append('ed_apellido', $('#ed_apellido').val());
        formData.append('ed_id', $('#ed_id').val());
        formData.append('action', 'edit');
        return fetch("/sesionjee/Admin", {
      method: "POST",
      body: formData
      }).then(response => {
          if (!response.ok) {
            throw new Error(response.statusText);
          }
          return response.json();
        })
        .catch(error => {
          Swal.showValidationMessage(
            `Request failed: ${error}`
          );
        });
    },
    allowOutsideClick: () => !Swal.isLoading()
    }).then((result) => {
        if (result.isConfirmed) {
            myjson = JSON.parse(JSON.stringify(result.value.data));
            if (myjson[0]['status'] === '500'){
            icono="error";
            }else{
                icono = "success";
            }
            Swal.fire({
                icon: icono,
                title: myjson[0]['msg']
            });
        }
        sleep(1200).then(() => {
            console.log('Despues del sleep');
            location.reload();
        });
    });
}

function del(id) {
    Swal.fire({
    title: 'Deseas eliminar el registro ' + id + '?',
    icon: 'error',
    html: '<input style="display:none" id="del_id" type="text" value="'+id+'">',
    inputAttributes: {
      autocapitalize: 'off'
    },
    showCancelButton: true,
    confirmButtonColor: '#F27474',
    confirmButtonText: 'Eliminar',
    showLoaderOnConfirm: true,
    preConfirm: (users) => {
        let formData = new FormData();
        formData.append('del_id', $('#del_id').val());        
        formData.append('action', 'delete');

        //Llamamos por Fetch al AdminUser
        return fetch("/sesionjee/Admin", {
        method: "POST",
        body: formData
        }).then(response => {
            if (!response.ok) {
              throw new Error(response.statusText);
            }
            return response.json();
        })
        .catch(error => {
          Swal.showValidationMessage(
            `Request failed: ${error}`
          );
        });
    },
    allowOutsideClick: () => !Swal.isLoading()
    }).then((result) => {
        if (result.isConfirmed) {
            myjson = JSON.parse(JSON.stringify(result.value.data));
            if (myjson[0]['status'] === '500'){
            icono="error";
            }else{
                icono = "success";
            }
            Swal.fire({
              icon: icono,
              title: myjson[0]['msg']
            });
        }
        sleep(1200).then(() => {
            console.log('Despues del sleep');
            location.reload();
        });
    });
    
}
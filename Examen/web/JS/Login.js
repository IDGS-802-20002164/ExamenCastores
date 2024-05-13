
function validarLogin() {
    var usuario = document.getElementById('txtUser').value;
    var password = document.getElementById('txtPass').value;

    var data = {"nU": usuario, "c": password};
    //alert(JSON.stringify(data));
    $.ajax(
            {
                "url": "../api/log/in",
                "type": "POST",
                "data": data,
                "async": true
            }).done(
            function (data) {
                if (data == null) {
                    swal('Acceso denegado', 'Datos incorrectos', "error");
                    $("#txtUser").val('');
                    $("#txtPass").val('');
                } else if (data.error != null) {
                    swal('Error', data.error, "error");
                } else if (data.idUsuario != 0) {+''
                    var nombre = data.nombre;
                    var usuario = data.idUsuario;
                    var rol = data.idRol;
                    console.log(usuario);
                    console.log(rol);
                    swal('Acceso', "error");
                    sessionStorage.setItem("nombre", nombre);
                    sessionStorage.setItem("usuario", usuario);
                    sessionStorage.setItem("rol", rol);
                    window.location = "Main.html";
                }
                swal('Acceso', "error");
            }
    );
}
function validarLoginC() {
    var usuario = document.getElementById('txtUserC').value;
    var password = document.getElementById('txtPassC').value;

    var data = {"nU": usuario, "c": password};
    //alert(JSON.stringify(data));
    $.ajax(
            {
                "url": "../api/log/inC",
                "type": "POST",
                "data": data,
                "async": true
            }).done(
            function (data) {
                if (data == null) {
                    swal('Acceso denegado', 'Datos incorrectos', "error");
                    $("#txtUser").val('');
                    $("#txtPass").val('');
                } else if (data.error != null) {
                    swal('Error', data.error, "error");
                } else if (data.id != 0) {+''
                    var nombre = data.persona.nombre + " " + data.persona.apellidoP + " " + data.persona.apellidoM;
                    sessionStorage.setItem("nombre", nombre);
                    window.location = "Main.html";
                }
            }
    );
}

function out(){
    sessionStorage.clear();
    window.location="../index.html";
}
var productos;
var rol = sessionStorage.getItem("rol");
var cantidadProdusctos;
function loadProductoModule()
{
    $.ajax(
            {
                "url": "../modulos/EntradaProducto.html",
                "type": "GET",
                "async": true
            }
    ).done(
            function (data)
            {
                $("#mainContainer").html(data);



                if (rol == 1) {
                    // Mostrar el botón si el rol es 1
                    document.getElementById("botonGuardar").style.display = "block";
                    document.getElementById("Inventario").style.display = "block";
                    document.getElementById("Eliminar").style.display = "block";

                } else {
                    // Ocultar el botón si el rol no es 1
                    document.getElementById("botonGuardar").style.display = "none";
                    document.getElementById("Inventario").style.display = "none";
                    document.getElementById("Eliminar").style.display = "none";



                }
                loadProducto();

            }
    );

}

function loadProductoExitModule()
{
    $.ajax(
            {
                "url": "../modulos/SalidaProducto.html",
                "type": "GET",
                "async": true
            }
    ).done(
            function (data)
            {
                $("#mainContainer").html(data);
                if (rol === 1) {
                    // Mostrar el botón si el rol es 1

                    document.getElementById("Salida").style.display = "none";
                } else {

                    document.getElementById("Salida").style.display = "block";


                }

                loadProducto();

            }
    );



}


function loadProducto()
{
    var data = {"estatus": 1};
    $.ajax(
            {
                "url": "../api/producto/getAll",
                "type": "GET",
                "async": true,
                "data": data
            }
    ).done(
            function (data)
            {
                productos = data;
                var datosTablas = "";
                for (var i = 0; i < productos.length; i++)
                {
                    datosTablas += "<tr>";
                    datosTablas += "<td>" + productos[i].id + "</td>";
                    datosTablas += "<td>" + productos[i].nombre + "</td>";
                    datosTablas += "<td>" + productos[i].cantidad + "</td>";
                    datosTablas += "<td>" + productos[i].estatus + "</td>";
                    if (rol == 1) {

                        datosTablas += "<td> <button id='Activar' class='btn btn-outline-danger' onclick='deleteProducto(" + i + ");'><i class='far fa-times-circle'></i></button></td>";
                    }

                    datosTablas += "<td> <button class='btn btn-outline-primary' onclick='loadProductoData(" + i + ")'><i class='far fa-edit'></i></button></td>";
                    datosTablas += "</tr>";


                }
                $("#tbSucursal").html(datosTablas);

            }
    );
}
function loadProductoInactivo()
{
    var data = {"estatus": 0};
    $.ajax(
            {
                "url": "../api/producto/getAll",
                "type": "GET",
                "async": true,
                "data": data
            }
    ).done(
            function (data)
            {
                productos = data;
                var datosTablas = "";
                for (var i = 0; i < productos.length; i++)
                {
                    datosTablas += "<tr>";
                    datosTablas += "<td>" + productos[i].id + "</td>";
                    datosTablas += "<td>" + productos[i].nombre + "</td>";
                    datosTablas += "<td>" + productos[i].cantidad + "</td>";
                    datosTablas += "<td>" + productos[i].estatus + "</td>";
                    datosTablas += "<td> <button id='Activar' class='btn btn-outline-danger' onclick='deleteProducto(" + i + ");'><i class='far fa-times-circle'></i></button></td>";
                    datosTablas += "<td> <button class='btn btn-outline-primary' onclick='loadProductoData(" + i + ")'><i class='far fa-edit'></i></button></td>";
                    datosTablas += "</tr>";
                }
                $("#tbSucursal").html(datosTablas);
                //document.getElementById('tbSucursal').innerHTML = datosTablas;
            }
    );
}
function deleteProducto(i)
{
    if (productos[i].estatus == 1) {
        var data = {"id": productos[i].id, "estatus": 0};
        $.ajax(
                {
                    "url": "../api/producto/delete",
                    "type": "GET",
                    "async": true,
                    "data": data
                }).done(
                function (data)
                {
                    if (data.result != null)
                    {
                        //Alerta de exito
                        swal("Eliminación exitosa", data.result, 'success');
                    } else if (data.error != null)
                    {
                        swal("Error", data.error, 'error');
                    }

                    loadProductoModule();
                }
        );
    }

    var data = {"id": productos[i].id, "estatus": 1};
    $.ajax(
            {
                "url": "../api/producto/activate",
                "type": "GET",
                "async": true,
                "data": data
            }).done(
            function (data)
            {
                if (data.result != null)
                {
                    //Alerta de exito
                    swal("Activacion exitosa", data.result, 'success');
                } else if (data.error != null)
                {
                    swal("Error", data.error, 'error');
                }

                loadProductoModule();
            }
    );

}


function loadProductoData(i)
{
    $("#txtIdPr").val(productos[i].id);
    $("#txtNombrePr").val(productos[i].nombre);
    $("#txtMarcaPr").val(productos[i].cantidad);
    $("#txtEstatusPr").val(productos[i].estatus);
    cantidadProdusctos = productos[i].cantidad;
}
function clearProductoData()
{
    $("#txtIdPr").val('');
    $("#txtNombrePr").val('');
    $("#txtMarcaPr").val('');
    $("#txtEstatusPr").val('');
}
function searchBranch() {
    var busqueda = document.getElementById("txtBuscarSucursal").value;
    var data = {"buscar": busqueda};

    $.ajax(
            {
                "url": "../api/producto/search",
                "type": "GET",
                "async": true,
                "data": data
            }
    ).done(
            function (data)
            {
                productos = data;
                var datosTablas = "";
                for (var i = 0; i < productos.length; i++)
                {
                    datosTablas += "<tr>";
                    datosTablas += "<td>" + productos[i].id + "</td>";
                    datosTablas += "<td>" + productos[i].nombre + "</td>";
                    datosTablas += "<td>" + productos[i].cantidad + "</td>";
                    datosTablas += "<td>" + productos[i].estatus + "</td>";
                    datosTablas += "<td> <button id='Activar' class='btn btn-outline-danger' onclick='deleteProducto(" + i + ");'><i class='far fa-times-circle'></i></button></td>"
                    datosTablas += "<td> <button class='btn btn-outline-primary' onclick='loadProductoData(" + i + ")'><i class='far fa-edit'></i></button></td>";
                    datosTablas += "</tr>";
                }
                $("#tbSucursal").html(datosTablas);
                //document.getElementById('tbSucursal').innerHTML = datosTablas;
            }
    );
}
function saveProducto()
{
    var idP = parseInt(document.getElementById("txtIdPr").value);
    var nameP = document.getElementById("txtNombrePr").value;
    var statusP = parseInt(document.getElementById("txtEstatusPr").value);


    var pro = {"id": idP, "nombre": nameP, "cantidad": 0,
        "estatus": statusP};

    var data = {"p": JSON.stringify(pro)};

    if (idP > 0)
    {
        var cantidadP = document.getElementById("txtMarcaPr").value;
        pro = {"id": idP, "nombre": nameP, "tipo": 1, "cantidad": cantidadP,
            "estatus": statusP, "idUsuario": sessionStorage.getItem("usuario")};

        console.log(pro);

        data = {"p": JSON.stringify(pro)};

        if (cantidadP < cantidadProdusctos) {
            swal("No se puede disminuir la cantidad", "error");
            return;
        }
        //Update
        $.ajax(
                {
                    "url": "../api/producto/update",
                    "type": "GET",
                    "async": true,
                    "data": data
                }
        ).done(
                function (data)
                {
                    if (data.resultado != null)
                    {
                        swal("Ok", "Actualizacion realizada", "success");
                    } else if (data.error != null)
                    {
                        swal("No se pudo actualizar", data.error, "error");
                    }

                }
        );
        loadProductoModule();
        clearProductoData();
    } else
    {
        $.ajax(
                {
                    "url": "../api/producto/insert",
                    "type": "GET",
                    "async": true,
                    "data": data
                }
        ).done(
                function (data)
                {
                    if (data.idGenerado != null)
                    {
                        swal("Ok", "Insercion realizada", "success");
                    } else if (data.error != null)
                    {
                        swal("Producto no insertado", data.error, 'error');
                    }
                    loadProductoModule();
                }
        );
    }
    loadProductoModule();
    clearProductoData();
}

function updateProductoExit()
{
    var idP = parseInt(document.getElementById("txtIdPr").value);
    var nameP = document.getElementById("txtNombrePr").value;
    var statusP = parseInt(document.getElementById("txtEstatusPr").value);

    if (idP > 0)
    {
        var cantidadP = document.getElementById("txtMarcaPr").value;
        pro = {"id": idP, "nombre": nameP, "tipo": 0, "cantidad": cantidadP,
            "estatus": statusP, "idUsuario": sessionStorage.getItem("usuario")};


        var data = {"p": JSON.stringify(pro)};

        if (cantidadP > cantidadProdusctos) {
            swal("No se puede aumentar la cantidad aqui", "error");
            return;
        }


        if (cantidadP < 0) {
            swal("No cuenta con existencia suficiente", "error");
            return;
        }
        //Update
        $.ajax(
                {
                    "url": "../api/producto/update",
                    "type": "GET",
                    "async": true,
                    "data": data
                }
        ).done(
                function (data)
                {
                    if (data.resultado != null)
                    {
                        swal("Ok", "Actualizacion realizada", "success");
                    } else if (data.error != null)
                    {
                        swal("No se pudo actualizar", data.error, "error");
                    }

                }
        );
        loadProductoExitModule();
        clearProductoData();
    } else {
        swal("No se puede crear producto en esta seccion", "error");
    }
}
var productos;

function loadMovimientoModule()
{
    $.ajax(
            {
                "url": "../modulos/Movimiento.html",
                "type": "GET",
                "async": true
            }
    ).done(
            function (data)
            {
                $("#mainContainer").html(data);
                loadMovimiento();
            }
    );
}




function loadMovimiento()
{
    var data = {"tipo": 1};
    $.ajax(
            {
                "url": "../api/movimiento/getAll",
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
                    datosTablas += "<td>" + productos[i].id_producto + "</td>";
                    datosTablas += "<td>" + productos[i].tipo + "</td>";
                    datosTablas += "<td>" + productos[i].cantidad + "</td>";
                    datosTablas += "<td>" + productos[i].id_usuario + "</td>";
                    datosTablas += "<td>" + productos[i].fecha_hora + "</td>";
                    datosTablas += "</tr>";
                }
                $("#tbSucursal").html(datosTablas);
                //document.getElementById('tbSucursal').innerHTML = datosTablas;
            }
    );
}
function loadMovimientoInactivo()
{
    var data = {"tipo": 0};
    $.ajax(
            {
                "url": "../api/movimiento/getAll",
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
                    datosTablas += "<td>" + productos[i].id_producto + "</td>";
                    datosTablas += "<td>" + productos[i].tipo + "</td>";
                    datosTablas += "<td>" + productos[i].cantidad + "</td>";
                    datosTablas += "<td>" + productos[i].id_usuario + "</td>";
                    datosTablas += "<td>" + productos[i].fecha_hora + "</td>";
                    datosTablas += "</tr>";
                }
                $("#tbSucursal").html(datosTablas);
                //document.getElementById('tbSucursal').innerHTML = datosTablas;
            }
    );
}

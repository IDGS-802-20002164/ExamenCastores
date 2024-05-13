create database examen;

use examen;

CREATE TABLE usuarios (
    idUsuario INT PRIMARY KEY auto_increment,
    nombre VARCHAR(255),
    correo VARCHAR(255),
    contrasena VARCHAR(255),
    idRol INT,
    estatus INT
);

CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    cantidad INT DEFAULT 0,
    estatus int  DEFAULT 1
);

CREATE TABLE movimientos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL,
    tipo int NOT NULL,
    cantidad INT NOT NULL,
    id_usuario INT NOT NULL,
    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_producto) REFERENCES productos(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

DELIMITER //
CREATE PROCEDURE ActualizarProductoConMovimiento(
    IN Idproduct INT,
    IN Nombre VARCHAR(255),
    IN Tipe int,
    IN Cantidad INT,
    IN Estatus int,
    IN IdUsuario int
)
BEGIN
    DECLARE antiguaCantidad INT;
   
SELECT cantidad INTO antiguaCantidad FROM productos WHERE id = Idproduct;

    INSERT INTO movimientos (id_producto, tipo, cantidad, id_usuario)
    VALUES (Idproduct, Tipe , Cantidad, IdUsuario); 
    
    UPDATE productos
    SET nombre = Nombre,
        cantidad = Cantidad,
        estatus = Estatus
    WHERE id = Idproduct;

END //

DELIMITER ;

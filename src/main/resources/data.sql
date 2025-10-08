-- Tabla: clientes
INSERT IGNORE INTO clientes (nombre, correo, telefono, edad, genero) VALUES
('Juan Pérez', 'juan.perez@example.com', '25631425', 30, 'Masculino'),
('Ana Gómez', 'ana.gomez@example.com', '87895645', 25, 'Femenino');

-- Tabla: conductores
INSERT IGNORE INTO conductores (nombre, licencia, fecha_nacimiento, direccion, telefono) VALUES
('Carlos Martínez', '1234567891011', '1985-06-15', 'Calle 123 #45-67', '45218723'),
('Laura Torres', '1110987654321', '1990-09-20', 'Carrera 10 #20-30', '58741452');

-- Tabla: unidades
INSERT IGNORE INTO Unidades_Transporte (placa, modelo, capacidad, tipo_unidad) VALUES
('P607GCL', 'Toyota Hiace', 30, 'BUS'),
('P108MTM', 'Chevrolet N300', 20, 'VAN');

-- Tabla: tarifas
INSERT IGNORE INTO tarifas (tipo_unidad, tarifa_base, recargo, hora_inicio_pico) VALUES
('BUS', 2.50, 0.5, '2025-10-08T07:00:00'),
('VAN', 2.00, 0.5, '2025-10-08T06:30:00');

-- Tabla: rutas
INSERT IGNORE INTO rutas (id_conductor, id_unidad, direccion_destino) VALUES
(1, 1, 'Terminal Norte'),
(2, 2, 'Aeropuerto Internacional');

-- Tabla: usuarios
INSERT IGNORE INTO usuarios (id_conductor, correo, contrasena) VALUES
(1, 'carlos.martinez@example.com', 'Hola123'),
(2, 'laura.torres@example.com', 'micontrasena12');
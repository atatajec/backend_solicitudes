-- Habilitar extensión UUID
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Eliminar tablas si existen (orden correcto por claves foráneas)
DROP TABLE IF EXISTS contactos_solicitud CASCADE;
DROP TABLE IF EXISTS solicitudes CASCADE;
DROP TABLE IF EXISTS tipo_solicitud CASCADE;
DROP TABLE IF EXISTS marcas CASCADE;
DROP TABLE IF EXISTS contactos CASCADE;

-- Crear tabla Contactos
CREATE TABLE contactos (
    contacto_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    identificador VARCHAR(50) NOT NULL,
    nombre_contacto VARCHAR(255) NOT NULL,
    numero_contacto VARCHAR(20) NOT NULL,
    estado VARCHAR(50) DEFAULT 'ACTIVO',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_creacion VARCHAR(255) DEFAULT 'SYSTEM',
    fecha_modificacion TIMESTAMP,
    usuario_modificacion VARCHAR(255)
);

-- Insertar contactos
INSERT INTO contactos(identificador, nombre_contacto, numero_contacto)
VALUES ('DOC-001', 'Alexis Tataje', '939733228');

-- Crear tabla Marcas
CREATE TABLE marcas (
    marca_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    descripcion VARCHAR(255) NOT NULL,
    estado VARCHAR(50) DEFAULT 'ACTIVO',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_creacion VARCHAR(255) DEFAULT 'SYSTEM',
    fecha_modificacion TIMESTAMP,
    usuario_modificacion VARCHAR(255)
);

-- Crear tabla Tipo Solicitud
CREATE TABLE tipo_solicitud (
    tipo_solicitud_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    descripcion VARCHAR(255) NOT NULL,
    estado VARCHAR(50) DEFAULT 'ACTIVO',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_creacion VARCHAR(255) DEFAULT 'SYSTEM',
    fecha_modificacion TIMESTAMP,
    usuario_modificacion VARCHAR(255)
);

-- Crear tabla Solicitudes
CREATE TABLE solicitudes (
    solicitud_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    codigo_solicitud VARCHAR(50) UNIQUE NOT NULL,
    marca_id UUID NOT NULL,
    tipo_solicitud_id UUID NOT NULL,
    fecha_envio DATE NOT NULL,
    estado VARCHAR(50) DEFAULT 'ACTIVO',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_creacion VARCHAR(255) DEFAULT 'SYSTEM',
    fecha_modificacion TIMESTAMP,
    usuario_modificacion VARCHAR(255),
    FOREIGN KEY (marca_id) REFERENCES marcas(marca_id) ON DELETE CASCADE,
    FOREIGN KEY (tipo_solicitud_id) REFERENCES tipo_solicitud(tipo_solicitud_id) ON DELETE CASCADE
);

-- Crear tabla Contactos por Solicitud
CREATE TABLE contactos_solicitud (
    contacto_solicitud_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    tipo INT DEFAULT 2,
    solicitud_id UUID NOT NULL,
    contacto_id UUID NOT NULL,
    estado VARCHAR(50) DEFAULT 'ACTIVO',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_creacion VARCHAR(255) DEFAULT 'SYSTEM',
    fecha_modificacion TIMESTAMP,
    usuario_modificacion VARCHAR(255),
    FOREIGN KEY (solicitud_id) REFERENCES solicitudes(solicitud_id) ON DELETE CASCADE,
    FOREIGN KEY (contacto_id) REFERENCES contactos(contacto_id) ON DELETE CASCADE
);

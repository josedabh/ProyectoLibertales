CREATE DATABASE libertales;
USE libertales;
-- Tabla Libro
CREATE TABLE Libro (
    id_libro INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255),
    editorial VARCHAR(255),
    anio_publicacion INT,
    precioCompra DECIMAL(10, 2),
    precioAlquiler DECIMAL(10, 2),
    sinopsis VARCHAR(255),
    rutaImagen VARCHAR(255)
);

-- Tabla Usuario
CREATE TABLE Usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    contrasenia VARCHAR(255) NOT NULL,
    tipo ENUM('lector', 'administrador') NOT NULL
);

-- Tabla Lector
CREATE TABLE Lector (
    id_lector INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    email VARCHAR(255) NOT NULL,
    contrasenia VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

-- Tabla Administrador
CREATE TABLE Administrador (
    id_admin INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    contrasenia VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Compra (
    id_compra INT PRIMARY KEY AUTO_INCREMENT,
    id_lector INT NOT NULL,
    id_libro INT NOT NULL,
    fecha_compra DATE NOT NULL,
    FOREIGN KEY (id_lector) REFERENCES Lector(id_lector),
    FOREIGN KEY (id_libro) REFERENCES Libro(id_libro)
);
CREATE TABLE Alquiler (
    id_alquiler INT PRIMARY KEY AUTO_INCREMENT,
    id_lector INT NOT NULL,
    id_libro INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_limite DATE NOT NULL,
    FOREIGN KEY (id_lector) REFERENCES Lector(id_lector),
    FOREIGN KEY (id_libro) REFERENCES Libro(id_libro)
);

-- Tabla Favoritos
CREATE TABLE Favoritos (
    id_lector INT,
    id_libro INT,
    PRIMARY KEY (id_lector, id_libro),
    FOREIGN KEY (id_lector) REFERENCES Lector(id_lector),
    FOREIGN KEY (id_libro) REFERENCES Libro(id_libro)
);

-- Tabla Cesta
CREATE TABLE Cesta (
    id_lector INT,
    id_libro INT,
    PRIMARY KEY (id_lector, id_libro),
    FOREIGN KEY (id_lector) REFERENCES Lector(id_lector),
    FOREIGN KEY (id_libro) REFERENCES Libro(id_libro)
);

DELIMITER //

-- Trigger para insertar un Usuario antes de insertar un Administrador
CREATE TRIGGER before_insert_administrador
BEFORE INSERT ON Administrador
FOR EACH ROW
BEGIN
    -- Inserta el nuevo usuario en la tabla Usuario
    INSERT INTO Usuario (email, contrasenia, tipo)
    VALUES (NEW.email, NEW.contrasenia, 'administrador');
    
    -- Asigna el id_usuario generado en Usuario al nuevo registro en Administrador
    SET NEW.id_usuario = LAST_INSERT_ID();
END;
//

DELIMITER //
-- Trigger para insertar un Usuario antes de insertar un Lector
CREATE TRIGGER before_insert_lector
BEFORE INSERT ON Lector
FOR EACH ROW
BEGIN
    -- Inserta el nuevo usuario en la tabla Usuario
    INSERT INTO Usuario (email, contrasenia, tipo)
    VALUES (NEW.email, NEW.contrasenia, 'lector');
    
    -- Asigna el id_usuario generado en Usuario al nuevo registro en Lector
    SET NEW.id_usuario = LAST_INSERT_ID();
END;
//
DELIMITER //

CREATE TRIGGER before_update_lector
BEFORE UPDATE ON Lector
FOR EACH ROW
BEGIN
    -- Actualiza la contraseña en la tabla Usuario si la contraseña cambia en Lector
    IF NEW.contrasenia <> OLD.contrasenia THEN
        UPDATE Usuario
        SET contrasenia = NEW.contrasenia
        WHERE id_usuario = NEW.id_usuario;
    END IF;

    -- Actualiza el correo en la tabla Usuario si el correo cambia en Lector
    IF NEW.email<> OLD.email THEN
        UPDATE Usuario
        SET email = NEW.email
        WHERE id_usuario = NEW.id_usuario;
    END IF;
END;
//

DELIMITER ;
-- Tabla: lector
INSERT INTO lector (id_lector, id_usuario, nombre, direccion, telefono, email, contrasenia) 
VALUES (1, 4, 'María López', 'Calle Falsa 123, Ciudad', '555-1234', 'maria.lopez@example.com', 'mraLOP123');

INSERT INTO lector (id_lector, id_usuario, nombre, direccion, telefono, email, contrasenia) 
VALUES (2, 5, 'Juan Pérez', 'Av. Principal 456, Ciudad', '555-5678', 'juan.perez@example.com', 'juanREZ456');

INSERT INTO lector (id_lector, id_usuario, nombre, direccion, telefono, email, contrasenia) 
VALUES (3, 6, 'Ana García', 'Calle Secundaria 789, Ciudad', '555-9876', 'ana.garcia@example.com', 'AnGar789');

-- Tabla: administrador
INSERT INTO administrador (id_admin, id_usuario, nombre, email, contrasenia) 
VALUES (1, 1, 'José Daniel', 'josedabh@gmail.com', 'adminJoseDaniel');

INSERT INTO administrador (id_admin, id_usuario, nombre, email, contrasenia) 
VALUES (2, 2, 'Francisco Jesús', 'francisco.jesus@example.com', 'adminFranciscoJesus');

INSERT INTO administrador (id_admin, id_usuario, nombre, email, contrasenia) 
VALUES (3, 3, 'Alejandro', 'alejandro@example.com', 'adminAlejandro');

-- Tabla: libro
INSERT INTO libro (id_libro, titulo, autor, editorial, anio_publicacion, precioCompra, precioAlquiler, sinopsis, rutaImagen) 
VALUES (1, 'Harry Potter y la piedra filosofal', 'J.K. Rowling', 'Salamandra', 1997, 20.99, 3.50, 'El joven Harry descubre que es un mago y asiste a Hogwarts.', '@../../libros/harrypotter1.png');

INSERT INTO libro (id_libro, titulo, autor, editorial, anio_publicacion, precioCompra, precioAlquiler, sinopsis, rutaImagen) 
VALUES (2, 'El señor de los anillos: La comunidad del anillo', 'J.R.R. Tolkien', 'Minotauro', 1954, 24.99, 4.00, 'Un grupo de héroes debe destruir el Anillo Único.', '@../../libros/lacomunidadanillo.png');

INSERT INTO libro (id_libro, titulo, autor, editorial, anio_publicacion, precioCompra, precioAlquiler, sinopsis, rutaImagen) 
VALUES (3, 'Cien años de soledad', 'Gabriel García Márquez', 'Sudamericana', 1967, 18.50, 3.00, 'La historia de la familia Buendía en Macondo.', '@../../libros/Cienañosdesoledad.png');

INSERT INTO libro (id_libro, titulo, autor, editorial, anio_publicacion, precioCompra, precioAlquiler, sinopsis, rutaImagen) 
VALUES (4, '1984', 'George Orwell', 'Secker & Warburg', 1949, 15.99, 2.80, 'Una distopía sobre la vigilancia y el control gubernamental.', '@../../libros/1984.png');

INSERT INTO libro (id_libro, titulo, autor, editorial, anio_publicacion, precioCompra, precioAlquiler, sinopsis, rutaImagen) 
VALUES (5, 'Don Quijote de la Mancha', 'Miguel de Cervantes', 'Francisco de Robles', 1605, 25.00, 3.50, 'Las aventuras de un caballero loco y su escudero.', '@../../libros/DonQuijotedelaMancha.png');

INSERT INTO libro (id_libro, titulo, autor, editorial, anio_publicacion, precioCompra, precioAlquiler, sinopsis, rutaImagen) 
VALUES (6, 'Orgullo y prejuicio', 'Jane Austen', 'T. Egerton', 1813, 12.99, 2.50, 'Una historia de amor y malentendidos en la Inglaterra rural.', '@../../libros/IN_entrega1_NovelasEternas.png');

INSERT INTO libro (id_libro, titulo, autor, editorial, anio_publicacion, precioCompra, precioAlquiler, sinopsis, rutaImagen) 
VALUES (7, 'El código Da Vinci', 'Dan Brown', 'Doubleday', 2003, 19.99, 3.80, 'Un misterio relacionado con el Santo Grial y sociedades secretas.', '@../../libros/codigodavinci.png');

INSERT INTO libro (id_libro, titulo, autor, editorial, anio_publicacion, precioCompra, precioAlquiler, sinopsis, rutaImagen) 
VALUES (8, 'Crimen y castigo', 'Fiódor Dostoyevski', 'The Russian Messenger', 1866, 17.50, 3.20, 'Un hombre lucha con su conciencia tras cometer un asesinato.', '@../../libros/Crimen_y_castigo-Dostoyevski_Fiodor-md.png');

INSERT INTO libro (id_libro, titulo, autor, editorial, anio_publicacion, precioCompra, precioAlquiler, sinopsis, rutaImagen) 
VALUES (9, 'El Principito', 'Antoine de Saint-Exupéry', 'Reynal & Hitchcock', 1943, 10.99, 2.00, 'La historia de un pequeño príncipe que explora el universo.', '@../../libros/principito.jpg');

INSERT INTO libro (id_libro, titulo, autor, editorial, anio_publicacion, precioCompra, precioAlquiler, sinopsis, rutaImagen) 
VALUES (10, 'Los juegos del hambre', 'Suzanne Collins', 'Scholastic Press', 2008, 16.99, 3.50, 'Katniss Everdeen lucha por sobrevivir en un torneo mortal.', '@../../libros/juegosdelhambre.jpg');

INSERT INTO libro (id_libro, titulo, autor, editorial, anio_publicacion, precioCompra, precioAlquiler, sinopsis, rutaImagen) 
VALUES (11, 'Dune', 'Frank Herbert', 'Chilton Books', 1965, 22.50, 3.80, 'Una épica de poder, política y religión en un planeta desértico.', '@../../libros/Dune-1-web.png');

INSERT INTO libro (id_libro, titulo, autor, editorial, anio_publicacion, precioCompra, precioAlquiler, sinopsis, rutaImagen) 
VALUES (12, 'La sombra del viento', 'Carlos Ruiz Zafón', 'Planeta', 2001, 18.99, 3.60, 'Un joven descubre un misterioso libro que cambiará su vida.', '@../../libros/sombraViento_tapadura.png');

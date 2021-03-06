CREATE TABLE contacto (uid int4 NOT NULL, nombre varchar(100), apellido varchar(100), mail varchar(50), telefono varchar(20), id_ciudad int4 NOT NULL, id_empresa int4 NOT NULL, PRIMARY KEY (uid));
CREATE TABLE ciudad (uid int4 NOT NULL, nombre varchar(255), PRIMARY KEY (uid));
CREATE TABLE empresa (uid int4 NOT NULL, nombre varchar(255), PRIMARY KEY (uid));
CREATE TABLE membresia (uid int4 NOT NULL, id_contactos int4 NOT NULL, id_grupos int4 NOT NULL, PRIMARY KEY (uid));
CREATE TABLE grupos (uid int4 NOT NULL, nombre varchar(20), PRIMARY KEY (uid));
CREATE TABLE notificaciones (uid int4 NOT NULL, fecha date, mensaje varchar(512), grupos_uid int4 NOT NULL, PRIMARY KEY (uid));
CREATE TABLE actividad (uid int4 NOT NULL, fecha date, actividad varchar(128), contenido varchar(512), grupos_uid int4 NOT NULL, PRIMARY KEY (uid));
ALTER TABLE contacto ADD CONSTRAINT FKcontacto66686 FOREIGN KEY (id_ciudad) REFERENCES ciudad (uid);
ALTER TABLE contacto ADD CONSTRAINT FKcontacto813631 FOREIGN KEY (id_empresa) REFERENCES empresa (uid);
ALTER TABLE notificaciones ADD CONSTRAINT FKnotificaci501387 FOREIGN KEY (grupos_uid) REFERENCES grupos (uid);
ALTER TABLE actividad ADD CONSTRAINT FKactividad543837 FOREIGN KEY (grupos_uid) REFERENCES grupos (uid);
ALTER TABLE membresia ADD CONSTRAINT FKmembresia912401 FOREIGN KEY (id_grupos) REFERENCES grupos (uid);
ALTER TABLE membresia ADD CONSTRAINT FKmembresia753698 FOREIGN KEY (id_contactos) REFERENCES contacto (uid);

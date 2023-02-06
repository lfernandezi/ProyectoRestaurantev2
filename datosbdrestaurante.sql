use bdrestaurantev2;
#--USUARIO---
SELECT *FROM USUARIO;
Insert Into USUARIO Values(null,'luis@ramirez','$2y$10$CSRPikgvoBcaHbZkqwBTE.Du8GICZVdtKBQCK/AziHG3Kj471JlsG','ROLE_ADMIN');
Insert Into USUARIO Values(null,'saul@peralta','$2y$10$CSRPikgvoBcaHbZkqwBTE.Du8GICZVdtKBQCK/AziHG3Kj471JlsG','ROLE_USER');
Insert Into USUARIO Values(null,'luis@fernandez','$2y$10$CSRPikgvoBcaHbZkqwBTE.Du8GICZVdtKBQCK/AziHG3Kj471JlsG','ROLE_ADMIN');
Insert Into USUARIO Values(3,'luis@fernandez','123456','ROLE_ADMIN');
Insert Into USUARIO Values(4,'saul@peralta','123456','ROLE_USER');

#--CLIENTE-----
SELECT *FROM CLIENTE;
Insert Into Cliente Values(null,'47859632','Luis','Ramirez','luis@ramirez','123456','987466987','Eucalipto 564','Activo');
Insert Into Cliente Values(null,'45987458','Saúl','Peralta','saul@peralta','123456','963258741', 'Alisos mz b lt 14','Activo');

#---EMPLEADOS----

SELECT * FROM area_empleado;
Insert Into area_empleado Values(null,'Cajas');
Insert Into area_empleado Values(null,'Cocina');
Insert Into area_empleado Values(null,'Delivery');
Insert Into area_empleado Values(null,'Atención al Cliente');

SELECT * FROM cargo_empleado;
Insert Into cargo_empleado Values(null,'Jefe');
Insert Into cargo_empleado Values(null,'Asistente');
Insert Into cargo_empleado Values(null,'Auxiliar');

SELECT * FROM empleado;
Insert Into empleado Values(null,'Norma','Tribejo','41808750','123456','21-05-20',2,1);
Insert Into empleado Values(null,'Jose','Arista','42525564','1234','21-05-21',1,2);
Insert Into empleado Values(null,'Jorge','Ramos','42525458','1234','21-05-22',2,3);
Insert Into empleado Values(null,'Luis','Jimenez','40621347','1234','21-05-21',3,4);
Insert Into empleado Values(null,'Ana','Morales','41424647','1234','21-05-22',1,3);

#----PRODUCTO---------

delete from producto where codcategoria=0;
SELECT * FROM categoria;
Insert Into categoria Values(null,'COMBO');
Insert Into categoria Values(null,'BRASA');
Insert Into categoria Values(null,'BROASTER');
Insert Into categoria Values(null,'CRIOLLO');

SELECT * from producto;
Insert Into Producto Values(null,'COMBO PERSONAL 1','1/4 DE POLLO + PAPAS + ENSALADA + CHAUFA DE POLLO + GASEOSA PERSONAL',22.90,1);
Insert Into Producto Values(null,'COMBO BROASTER','6 PIEZAS DE POLLO + PAPAS + ENSALADA + GASEOSA 1.5 LT',39.90,1);
Insert Into Producto Values(null,'COMBO PERSONAL CHELERO 1','1/4 DE POLLO + PAPAS + CHAUFA DE POLLO + 1 CERVEZA 330ML',22.90,1);
Insert Into Producto Values(null,'COMBO FAMILIAR 1','1 POLLO + PAPAS + ENSALADA + GASEOSA 1.5LT',54.90,1);
Insert Into Producto Values(null,'COMBO DUO 1','1/2 POLLO + PAPAS + ENSALADA + 2 GASEOSAS PERSONALES',30.90,1);
Insert Into Producto Values(null,'COMBO PERSONAL CHELERO 2','1/4 POLLO + PAPAS + 1 CERVEZA 330ML',18.90,1);
Insert Into Producto Values(null,'COMBO FAMILIAR 2','1 POLLO + PAPAS + ENSALADA + GASEOSA 1.5 LT',72.50,1);
Insert Into Producto Values(null,'COMBO DUO 2','1/2 POLLO + PAPAS + CHAUFA DE POLLO + 2 GASEOSAS PERSONALES',35.90,1);
Insert Into Producto Values(null,'COMBO DUO CHELERO 1','1/2 POLLO + PAPAS + 2 CERVEZAS 330ML',32.90,1);
Insert Into Producto Values(null,'COMBO FAMILIAR CHELERO 1','1 POLLO + PAPAS + CHAUFA DE POLLO + 4 CERVEZAS 330ML',65.00,1);

Insert Into Producto Values(null,'BRASA PERSONAL 1','1/4 DE POLLO + PAPAS',12.90,2);
Insert Into Producto Values(null,'BRASA PERSONAL 2','1/4 DE POLLO + PAPAS + ENSALADA',14.90,2);
Insert Into Producto Values(null,'BRASA PERSONAL 3','1/4 DE POLLO + PAPAS + CHAUFA DE POLLO',19.90,2);
Insert Into Producto Values(null,'BRASA DUO 1','1/2 POLLO + PAPAS',26.90,2);
Insert Into Producto Values(null,'BRASA DUO 2','1/2 POLLO + PAPAS + ENSALADA',29.90,2);
Insert Into Producto Values(null,'BRASA DUO 3','1/2 POLLO + PAPAS + CHAUFA DE POLLO',29.90,2);
Insert Into Producto Values(null,'BRASA FAMILIAR 1','1 POLLO + PAPAS',44.90,2);
Insert Into Producto Values(null,'BRASA FAMILIAR 1','1 POLLO + PAPAS + CHAUFA DE POLLO',59.90,2);
Insert Into Producto Values(null,'BRASA FAMILIAR 2','1 POLLO + PAPAS + ENSALADA',52.90,2);

Insert Into Producto Values(null,'BROASTER 1','2 PIEZAS DE POLLO + PAPAS',7.90,3);
Insert Into Producto Values(null,'BROASTER 3','2 PIEZAS DE POLLO + PAPAS + CHAUFA',14.90,3);
Insert Into Producto Values(null,'BROASTER 4','1 FILETE DE POLLO + CHAUFA + ENSALADA',14.90,3);
Insert Into Producto Values(null,'BROASTER 2','2 PIEZAS DE POLLO + PAPAS + ENSALADA',12.90,3);
Insert Into Producto Values(null,'BROASTER 5','1 FILETE DE POLLO + PAPAS + ENSALADA',14.90,3);

Insert Into Producto Values(null,'CRIOLLO 1','LOMO SALTADO DE CARNE + PAPAS + ARROZ',22.90,4);
Insert Into Producto Values(null,'CRIOLLO 3','LOMO SALTADO DE CARNE + PAPAS + HUEVO FRITO + PLATANO FRITO + ARROZ',24.90,4);
Insert Into Producto Values(null,'CRIOLLO 5','TALLARIN SALTADO DE CARNE',22.90,4);
Insert Into Producto Values(null,'CRIOLLO 2','LOMO SALTADO DE POLLO + PAPAS + ARROZ',20.90,4);
Insert Into Producto Values(null,'CRIOLLO 4','LOMO SALTADO DE POLLO + PAPAS + HUEVO FRITO + PLATANO FRITO + ARROZ',21.90,4);
Insert Into Producto Values(null,'CRIOLLO 6','TALLARIN SALTADO DE POLLO',20.90,4);
Insert Into Producto Values(null,'CRIOLLO 7','CHAUFA DE CARNE',22.90,4);
Insert Into Producto Values(null,'CRIOLLO 8','CHAUFA DE POLLO',20.90,4);

#----PEDIDO---------

SELECT *FROM PEDIDO;

select * from detallepedido;
INSERT INTO PEDIDO VALUES (null,now(),'Jr Progreso 270','-12.15545,-77.15678',43.80,'RECIBIDO',null,0,1);



INSERT INTO DETALLEpedido (codpedido,codproducto,cantidad,precio)
VALUES ((select(select max(codpedido)  from pedido)) ,31,1,22.90);

INSERT INTO DETALLEpedido (codpedido,codproducto,cantidad,precio)
VALUES ((select(select max(codpedido)  from pedido)) ,32,1,20.90);


#-------------------------adaptados-----------

DELIMITER $$
create procedure sp_MostrarUsuario(in xemail varchar(45))
            begin
				select codcliente, email,contrasenia from cliente where email = xemail;
			END$$    
 DELIMITER ;
select * from cliente;
select * from pedido;
select * from detallepedido;



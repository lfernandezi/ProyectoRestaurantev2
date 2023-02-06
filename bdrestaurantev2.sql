-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bdrestaurantev2
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bdrestaurantev2` ;

-- -----------------------------------------------------
-- Schema bdrestaurantev2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdrestaurantev2` ;
USE `bdrestaurantev2` ;

-- -----------------------------------------------------
-- Table `area_empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `area_empleado` ;

CREATE TABLE IF NOT EXISTS `area_empleado` (
  `codarea` INT NOT NULL AUTO_INCREMENT,
  `area` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codarea`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `codarea_UNIQUE` ON `area_empleado` (`codarea` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `cargo_empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cargo_empleado` ;

CREATE TABLE IF NOT EXISTS `cargo_empleado` (
  `codcargo` INT NOT NULL AUTO_INCREMENT,
  `cargo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codcargo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `codcargo_UNIQUE` ON `cargo_empleado` (`codcargo` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `categoria` ;

CREATE TABLE IF NOT EXISTS `categoria` (
  `codcategoria` INT NOT NULL AUTO_INCREMENT,
  `categoria` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`codcategoria`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cliente` ;

CREATE TABLE IF NOT EXISTS `cliente` (
  `codcliente` INT NOT NULL AUTO_INCREMENT,
  `dni` VARCHAR(10) NULL DEFAULT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `apellido` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `contrasenia` VARCHAR(45) NULL DEFAULT NULL,
  `telefono` VARCHAR(45) NULL DEFAULT NULL,
  `direccion` VARCHAR(100) NULL DEFAULT NULL,
  `estado` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`codcliente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `codcliente_UNIQUE` ON `cliente` (`codcliente` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `empleado` ;

CREATE TABLE IF NOT EXISTS `empleado` (
  `codempleado` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `apellido` VARCHAR(45) NULL DEFAULT NULL,
  `dni` VARCHAR(8) NULL DEFAULT NULL,
  `contrasenia` VARCHAR(45) NULL DEFAULT NULL,
  `fecha_ingreso` DATE NULL DEFAULT NULL,
  `codcargo` INT NOT NULL,
  `codarea` INT NOT NULL,
  PRIMARY KEY (`codempleado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_empleado_cargo_empleado1_idx` ON `empleado` (`codcargo` ASC) VISIBLE;

CREATE INDEX `fk_empleado_area_empleado1_idx` ON `empleado` (`codarea` ASC) VISIBLE;

CREATE UNIQUE INDEX `codempleado_UNIQUE` ON `empleado` (`codempleado` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `envios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `envios` ;

CREATE TABLE IF NOT EXISTS `envios` (
  `codenvio` INT NOT NULL AUTO_INCREMENT,
  `fecha_envio` DATETIME NULL DEFAULT NULL,
  `estadoenvio` VARCHAR(45) NULL DEFAULT NULL,
  `codempleado` INT NULL DEFAULT 0,
  PRIMARY KEY (`codenvio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_envios_empleado1_idx` ON `envios` (`codempleado` ASC) VISIBLE;

CREATE UNIQUE INDEX `codenvio_UNIQUE` ON `envios` (`codenvio` ASC) VISIBLE;



-- -----------------------------------------------------
-- Table `detallenvio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `detalleenvio` ;
CREATE TABLE IF NOT EXISTS `bdrestaurantev2`.`detalleenvio` (
  `codpedido` INT NOT NULL,
  `codenvio` INT NOT NULL,
  `lat` VARCHAR(45) NULL DEFAULT NULL,
  `long` VARCHAR(45) NULL DEFAULT NULL,
  `horaentrega` DATETIME NULL DEFAULT NULL,
  `motivonoentrega` VARCHAR(100) NULL DEFAULT NULL,
  `imagen` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`codpedido`, `codenvio`),
  INDEX `fk_pedido_has_envios_envios1_idx` (`codenvio` ASC) VISIBLE,
  INDEX `fk_pedido_has_envios_pedido1_idx` (`codpedido` ASC) VISIBLE,
  CONSTRAINT `fk_pedido_has_envios_pedido1`
    FOREIGN KEY (`codpedido`)
    REFERENCES `bdrestaurantev2`.`pedido` (`codpedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_has_envios_envios1`
    FOREIGN KEY (`codenvio`)
    REFERENCES `bdrestaurantev2`.`envios` (`codenvio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pedido` ;

CREATE TABLE IF NOT EXISTS `pedido` (
  `codpedido` INT NOT NULL AUTO_INCREMENT,
  `fechacreacion` DATETIME NULL DEFAULT NULL,
  `direccion` VARCHAR(100) NULL DEFAULT NULL,
  `ubicación` VARCHAR(100) NULL DEFAULT NULL,
  `monto` DECIMAL(10,2) NULL DEFAULT NULL,
  `estadopedido` VARCHAR(45) NULL DEFAULT NULL,
	`motivo` VARCHAR(45) NULL DEFAULT NULL,
  `codenvio` INT NULL DEFAULT 0,
  `codcliente` INT NOT NULL,
  PRIMARY KEY (`codpedido`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_pedido_envios1_idx` ON `pedido` (`codenvio` ASC) VISIBLE;

CREATE INDEX `fk_pedido_cliente1_idx` ON `pedido` (`codcliente` ASC) VISIBLE;

CREATE UNIQUE INDEX `codpedido_UNIQUE` ON `pedido` (`codpedido` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `producto` ;

CREATE TABLE IF NOT EXISTS `producto` (
  `codproducto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion` VARCHAR(100) NULL DEFAULT NULL,
  `precio` DECIMAL(8,2) NULL DEFAULT NULL,
  `codcategoria` INT NOT NULL,
  PRIMARY KEY (`codproducto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_producto_categoria1_idx` ON `producto` (`codcategoria` ASC) VISIBLE;

CREATE UNIQUE INDEX `codproducto_UNIQUE` ON `producto` (`codproducto` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `detallepedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `detallepedido` ;

CREATE TABLE IF NOT EXISTS `detallepedido` (
  `codpedido` INT NOT NULL,
  `codproducto` INT NOT NULL,
  `CANTIDAD` INT NOT NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`codpedido`, `codproducto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `R_2` ON `detallepedido` (`codproducto` ASC) VISIBLE;
CREATE INDEX `R_3` ON `detallepedido` (`codpedido` ASC) VISIBLE;

-- -----------------------------------------------------
-- Table `tipodoc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipodoc` ;

CREATE TABLE IF NOT EXISTS `tipodoc` (
  `codtipodoc` INT NOT NULL AUTO_INCREMENT,
  `nombredoc` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codtipodoc`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `codtipodoc_UNIQUE` ON `tipodoc` (`codtipodoc` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `Foma_pago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Foma_pago` ;

CREATE TABLE IF NOT EXISTS `Foma_pago` (
  `codformapago` INT NOT NULL AUTO_INCREMENT,
  `nombreformapago` VARCHAR(45) NULL,
  PRIMARY KEY (`codformapago`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `codformapago_UNIQUE` ON `Foma_pago` (`codformapago` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `documento_venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `documento_venta` ;

CREATE TABLE IF NOT EXISTS `documento_venta` (
  `coddocumento` INT NOT NULL AUTO_INCREMENT,
  `codtipodocumento` INT NOT NULL,
  `codformapago` INT NOT NULL,
  `codpedido` INT NOT NULL,
  `ruc` VARCHAR(45) NULL DEFAULT NULL,
  `subtotal` DECIMAL(10,2) NULL,
  `igv` DECIMAL(10,2) NULL,
  `total` DECIMAL(10,2) NULL,
  PRIMARY KEY (`coddocumento`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `coddocu_UNIQUE` ON `documento_venta` (`coddocumento` ASC) VISIBLE;

CREATE INDEX `fk_documento_venta_tipodoc1_idx` ON `documento_venta` (`codtipodocumento` ASC) VISIBLE;

CREATE INDEX `fk_documento_venta_Foma_pago1_idx` ON `documento_venta` (`codformapago` ASC) VISIBLE;

CREATE INDEX `fk_documento_venta_pedido1_idx` ON `documento_venta` (`codpedido` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `detalledocumentoventa`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `detalledocumentoventa` ;
CREATE TABLE IF NOT EXISTS `bdrestaurantev2`.`detalledocumentoventa` (
  `coddocumento` INT NOT NULL,
  `codproducto` INT NOT NULL,
  `precio` DECIMAL NULL,
  `cantidad` INT NULL,
  PRIMARY KEY (`coddocumento`, `codproducto`),
  INDEX `fk_detalledocumentoventa_producto1_idx` (`codproducto` ASC) VISIBLE,
  CONSTRAINT `fk_detalledocumentoventa_documento_venta1`
    FOREIGN KEY (`coddocumento`)
    REFERENCES `bdrestaurantev2`.`documento_venta` (`coddocumento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalledocumentoventa_producto1`
    FOREIGN KEY (`codproducto`)
    REFERENCES `bdrestaurantev2`.`producto` (`codproducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `usuario`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `usuario` ;
CREATE TABLE IF NOT EXISTS `bdrestaurantev2`.`usuario` (
  `codusuario` INT NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(500) NOT NULL,
  `rol` varchar(45),
  PRIMARY KEY (`codusuario`)
  
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `codusuario_UNIQUE` ON `usuario` (`codusuario` ASC) VISIBLE;



-- -----------------------------------------------------
-- procedure sp_IngresarDetallePedido
-- -----------------------------------------------------

USE `bdrestaurantev2`;
DROP procedure IF EXISTS `sp_IngresarDetallePedido`;

DELIMITER $$
USE `bdrestaurantev2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_IngresarDetallePedido`( IN _codproducto int, 
		IN _cantidad int,IN _precio decimal(10,2), IN _subtotal decimal(10,2))
BEGIN
INSERT INTO DETALLEpedido (codpedido,codproducto,cantidad,precio,subtotal)
VALUES ((select(select max(codpedido)  from pedido)) ,_codproducto,_cantidad,_precio,_subtotal);
END$$

DELIMITER ;















-- -----------------------------------------------------
-- procedure sp_IngresarPedido---ADICIONADO
-- -----------------------------------------------------

USE `bdrestaurantev2`;
DROP procedure IF EXISTS `sp_IngresarPedido`;

DELIMITER $$
USE `bdrestaurantev2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_IngresarPedido`( IN _codigocliente int, IN _direccion varchar(50),
IN _ubicacion varchar(100),IN _monto decimal(10,2), in _estado varchar(50))
BEGIN
INSERT INTO pedido (codpedido, fechacreacion,direccion,ubicacion, monto,estadopedido, codenvio,codcliente)
VALUES (null,now(),  _direccion,_ubicacion, _monto, _estado,0,_codigocliente);
END$$

DELIMITER ;









-- -----------------------------------------------------
-- procedure sp_ListarPedidoxCliente
-- -----------------------------------------------------

USE `bdrestaurantev2`;
DROP procedure IF EXISTS `sp_ListarPedidoxCliente`;
SHOW WARNINGS;

DELIMITER $$
USE `bdrestaurantev2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarPedidoxCliente`(IN _codcliente int)
select * from pedido
	where codcliente=_codcliente$$
DELIMITER ;







-- -----------------------------------------------------
-- procedure sp_ListarUltimoPedidoxCliente---ADICIONADO
-- -----------------------------------------------------

USE `bdrestaurantev2`;
DROP procedure IF EXISTS `sp_ListarUltimoPedidoxCliente`;
SHOW WARNINGS;

DELIMITER $$
USE `bdrestaurantev2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarUltimoPedidoxCliente`(IN _codcliente int)
select * from pedido
	where codcliente=_codcliente
	order by codpedido desc
	limit 1 
    $$
DELIMITER ;









-- -----------------------------------------------------
-- procedure sp_ListarPedidoxEnvio
-- -----------------------------------------------------

USE `bdrestaurantev2`;
DROP procedure IF EXISTS `sp_ListarPedidoxEnvio`;
SHOW WARNINGS;

DELIMITER $$
USE `bdrestaurantev2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarPedidoxEnvio`(IN _codenvio int)
select * from pedido
	where codenvio=_codenvio$$
DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_ListarPedidoxEstado
-- -----------------------------------------------------

USE `bdrestaurantev2`;
DROP procedure IF EXISTS `sp_ListarPedidoxEstado`;
SHOW WARNINGS;

DELIMITER $$
USE `bdrestaurantev2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarPedidoxEstado`(IN _estado varchar(45))
select * from pedido
	where estadopedido=_estado$$
DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_ListarProductoxCategorias
-- -----------------------------------------------------

USE `bdrestaurantev2`;
DROP procedure IF EXISTS `sp_ListarProductoxCategorias`;
SHOW WARNINGS;

DELIMITER $$
USE `bdrestaurantev2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarProductoxCategorias`(IN _codcategoria int)
select * from producto
	where codcategoria = _codcategoria$$

DELIMITER ;


-- -----------------------------------------------------
-- procedure sp_ListarProductoxPalabra
-- -----------------------------------------------------

USE `bdrestaurantev2`;
DROP procedure IF EXISTS `sp_ListarProductoxPalabra`;
SHOW WARNINGS;

DELIMITER $$
USE `bdrestaurantev2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarProductoxPalabra`(IN _palabra varchar(45))
select * from producto
	where nombre like concat("%",_palabra,"%")$$

DELIMITER ;


-- -----------------------------------------------------
-- procedure sp_buscarClienteEmail
-- -----------------------------------------------------

USE `bdrestaurantev2`;
DROP procedure IF EXISTS `sp_buscarClienteEmail`;

DELIMITER $$
USE `bdrestaurantev2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscarClienteEmail`(IN _email varchar(45))
BEGIN
 select * from cliente where email=_email;
END$$

DELIMITER ;


-- -----------------------------------------------------
-- procedure sp_buscarEmpleadoxdni
-- -----------------------------------------------------

USE `bdrestaurantev2`;
DROP procedure IF EXISTS `sp_buscarEmpleadoxdni`;

DELIMITER $$
USE `bdrestaurantev2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscarEmpleadoxdni`(in _dni varchar(8))
BEGIN
select * from empleado where dni=_dni;
END$$

DELIMITER ;


-- -----------------------------------------------------
-- procedure sp_listarEmpleadoxArea
-- -----------------------------------------------------

USE `bdrestaurantev2`;
DROP procedure IF EXISTS `sp_listarEmpleadoxArea`;
SHOW WARNINGS;

DELIMITER $$
USE `bdrestaurantev2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarEmpleadoxArea`(IN _area int)
BEGIN
 select * from empleado where codarea=_area;
END$$

DELIMITER ;


-- -----------------------------------------------------
-- procedure sp_listarEmpleadoxCargo
-- -----------------------------------------------------

USE `bdrestaurantev2`;
DROP procedure IF EXISTS `sp_listarEmpleadoxCargo`;
SHOW WARNINGS;

DELIMITER $$
USE `bdrestaurantev2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarEmpleadoxCargo`(IN _cargo int)
BEGIN
 select * from empleado where codcargo=_cargo;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_listarClientexEstado
-- -----------------------------------------------------

USE `bdrestaurantev2`;
DROP procedure IF EXISTS `sp_listarClientexEstado`;
SHOW WARNINGS;

DELIMITER $$
USE `bdrestaurantev2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarClientexEstado`(IN _estado varchar(45))
BEGIN
select * from cliente
	where estado = _estado;
END$$

DELIMITER ;


-- -----------------------------------------------------
-- procedure sp_VerDetallePedido
-- -----------------------------------------------------
USE `bdrestaurantev2`;
DROP procedure IF EXISTS `sp_VerDetallePedido`;
SHOW WARNINGS;


DELIMITER $$
USE `bdrestaurantev2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_VerDetallePedido`( IN _codpedido int)
BEGIN
	select * from detallepedido where codpedido =_codpedido;
END$$
DELIMITER ;



SHOW WARNINGS;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


DELIMITER $$
create procedure sp_MantRegistrarCliente(in xdni varchar(8) ,in xnombre varchar(45) ,in xapellido varchar(45),
			in xemail varchar(45), in xcontraseña varchar(45), in xdireccion varchar(45), in xtelefono varchar(45),in xestado varchar(45))
            begin
				insert into cliente values(null,xdni, xnombre, xapellido,xemail,xcontraseña,xtelefono,xdireccion,xestado);
                Insert into usuario values ((select max(codcliente) from cliente),xemail,xcontraseña,'ROLE_CLIENTE');
			END$$    
 DELIMITER ;


DROP procedure IF EXISTS `sp_BuscarUsuario`;
SHOW WARNINGS;
DELIMITER $$
create procedure sp_BuscarUsuario(in xemail varchar(45))
	begin
			select * from usuario where email=xemail;
	END$$    
 DELIMITER ;
 
 call sp_BuscarUsuario('jorge@ramos');
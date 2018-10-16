-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-08-2017 a las 12:44:33
-- Versión del servidor: 10.1.24-MariaDB
-- Versión de PHP: 7.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cuponera`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarAdministradores` ()  SELECT administrador.idAdmin,
       administrador.nombre,
       administrador.apellido,
       administrador.usuario,
       administrador.correo
FROM cuponera.administrador administrador$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarAprobada` ()  SELECT promocion.idPromocion, promocion.titulo, promocion.precioRegular, promocion.fechaInicio, estadopromocion.estado, empresa.nombre FROM (cuponera.promocion promocion INNER JOIN cuponera.empresa empresa ON (promocion.codigoEmpresa = empresa.codigoEmpresa)) INNER JOIN cuponera.estadopromocion estadopromocion ON (promocion.idEstado = estadopromocion.idEstado) WHERE promocion.idEstado=2$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarDependientes` ()  SELECT dependiente.idDependiente,
       dependiente.nombreDep,
       dependiente.apellido,
       dependiente.correo,
       empresa.nombre
FROM cuponera.dependiente dependiente
     INNER JOIN cuponera.empresa empresa
        ON (dependiente.codigoEmpresa = empresa.codigoEmpresa)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarEmpresas` ()  SELECT empresa.codigoEmpresa,
       empresa.nombre,
       empresa.direccion,
       empresa.contactoNom,
       empresa.telefono,
       empresa.correo,
       empresa.comision,
       rubro.nombreRubro
FROM cuponera.empresa empresa
     INNER JOIN cuponera.rubro rubro ON (empresa.idRubro = rubro.idRubro)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarEspera` ()  SELECT promocion.idPromocion, promocion.titulo, promocion.precioRegular, promocion.fechaInicio, estadopromocion.estado, empresa.nombre FROM (cuponera.promocion promocion INNER JOIN cuponera.empresa empresa ON (promocion.codigoEmpresa = empresa.codigoEmpresa)) INNER JOIN cuponera.estadopromocion estadopromocion ON (promocion.idEstado = estadopromocion.idEstado) WHERE promocion.idEstado=1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarPromociones` ()  SELECT promocion.idPromocion,
       promocion.titulo,
       promocion.precioRegular,
       promocion.precioOferta,
       promocion.fechaInicio,
       promocion.fechaFin,
       promocion.fechaLimite,
       promocion.descripcion,
       promocion.justificacion,
       promocion.img,
       estadopromocion.estado,
       empresa.nombre
FROM (cuponera.promocion promocion
      INNER JOIN cuponera.empresa empresa
         ON (promocion.codigoEmpresa = empresa.codigoEmpresa))
     INNER JOIN cuponera.estadopromocion estadopromocion
        ON (promocion.idEstado = estadopromocion.idEstado)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarRechazada` ()  SELECT promocion.idPromocion, promocion.titulo, promocion.precioRegular, promocion.fechaInicio, estadopromocion.estado, empresa.nombre FROM (cuponera.promocion promocion INNER JOIN cuponera.empresa empresa ON (promocion.codigoEmpresa = empresa.codigoEmpresa)) INNER JOIN cuponera.estadopromocion estadopromocion ON (promocion.idEstado = estadopromocion.idEstado) WHERE promocion.idEstado=3$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarRubro1` ()  select titulo, precioRegular, precioOferta, descripcion from promocion
inner join empresa
on promocion.codigoEmpresa = empresa.codigoEmpresa WHERE idRubro='RU0001'$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarRubro1Apro` ()  select idPromocion, titulo, precioRegular, precioOferta, fechaInicio, fechaFin, fechaLimite, descripcion, img, justificacion, idEstado from promocion
inner join empresa
on promocion.codigoEmpresa = empresa.codigoEmpresa WHERE idRubro='RU0001' AND idEstado=2$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarRubro2` ()  select titulo, precioRegular, precioOferta, descripcion from promocion
inner join empresa
on promocion.codigoEmpresa = empresa.codigoEmpresa WHERE idRubro='RU0002'$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarRubro2Apro` ()  select idPromocion, titulo, precioRegular, precioOferta, fechaInicio, fechaFin, fechaLimite, descripcion, img, justificacion, idEstado from promocion
inner join empresa
on promocion.codigoEmpresa = empresa.codigoEmpresa WHERE idRubro='RU0002' AND idEstado=2$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarRubro3` ()  select titulo, precioRegular, precioOferta, descripcion from promocion
inner join empresa
on promocion.codigoEmpresa = empresa.codigoEmpresa WHERE idRubro='RU0003'$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarRubro3Apro` ()  select idPromocion, titulo, precioRegular, precioOferta, fechaInicio, fechaFin, fechaLimite, descripcion, img, justificacion, idEstado from promocion
inner join empresa
on promocion.codigoEmpresa = empresa.codigoEmpresa WHERE idRubro='RU0003' AND idEstado=2$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarRubro4` ()  select titulo, precioRegular, precioOferta, descripcion from promocion
inner join empresa
on promocion.codigoEmpresa = empresa.codigoEmpresa WHERE idRubro='RU0004'$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarRubro4Apro` ()  select idPromocion, titulo, precioRegular, precioOferta, fechaInicio, fechaFin, fechaLimite, descripcion, img, justificacion, idEstado from promocion
inner join empresa
on promocion.codigoEmpresa = empresa.codigoEmpresa WHERE idRubro='RU0004' AND idEstado=2$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarRubro5` ()  select titulo, precioRegular, precioOferta, descripcion from promocion
inner join empresa
on promocion.codigoEmpresa = empresa.codigoEmpresa WHERE idRubro='RU0005'$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarRubro5Apro` ()  select idPromocion, titulo, precioRegular, precioOferta, fechaInicio, fechaFin, fechaLimite, descripcion, img, justificacion, idEstado from promocion
inner join empresa
on promocion.codigoEmpresa = empresa.codigoEmpresa WHERE idRubro='RU0005' AND idEstado=2$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarRubro6` ()  select titulo, precioRegular, precioOferta, descripcion from promocion
inner join empresa
on promocion.codigoEmpresa = empresa.codigoEmpresa WHERE idRubro='RU0006'$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarRubro6Apro` ()  select idPromocion, titulo, precioRegular, precioOferta, fechaInicio, fechaFin, fechaLimite, descripcion, img, justificacion, idEstado from promocion
inner join empresa
on promocion.codigoEmpresa = empresa.codigoEmpresa WHERE idRubro='RU0006' AND idEstado=2$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarRubro7` ()  select titulo, precioRegular, precioOferta, descripcion from promocion
inner join empresa
on promocion.codigoEmpresa = empresa.codigoEmpresa WHERE idRubro='RU0007'$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarRubro7Apro` ()  select idPromocion, titulo, precioRegular, precioOferta, fechaInicio, fechaFin, fechaLimite, descripcion, img, justificacion, idEstado from promocion
inner join empresa
on promocion.codigoEmpresa = empresa.codigoEmpresa WHERE idRubro='RU0007' AND idEstado=2$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `idAdmin` varchar(25) NOT NULL,
  `nombre` varchar(75) DEFAULT NULL,
  `apellido` varchar(75) DEFAULT NULL,
  `usuario` varchar(75) NOT NULL,
  `correo` varchar(75) DEFAULT NULL,
  `clave` varchar(75) DEFAULT NULL,
  `confirmado` tinyint(1) NOT NULL,
  `idConfirmacion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`idAdmin`, `nombre`, `apellido`, `usuario`, `correo`, `clave`, `confirmado`, `idConfirmacion`) VALUES
('AD0001', 'Cristofer ', 'Ayala', 'albo503', 'ayala_albo@hotmail.com', '9e2596d29191caf9c4378da866ecc239f5954e2dcac6e64c957a8e93f88e69ca', 0, '80bf08ce-b270-4094-856d-5f255f1594b7'),
('AD0002', 'Kevin', 'Ayala', 'Homunculo', 'kevnoegarcia@gmail.com', 'd5e8ad4b142bbb69a30fe85f5577a90a4dc7959d0d24ffc774122c17fedeffcf', 0, '504f3c92-b704-4d66-86f8-b3671aa46051'),
('AD0003', 'Gerson', 'Alas', 'jerson', 'gerson_alberto@hotmail.es', '288ab9766fc6973b893db75ce842cba25c893b52e0a20fdc3cbd9ea515500fef', 0, '8ddcbfbd-0b74-4032-8591-6900c1b618e6'),
('AD0004', 'Kevin', 'Molina', 'kevlev', 'alex3molina@gmail.com', '7530c8354bcb916eed2c6ef114c2f9a8ba3fdee3bf3e03c07b1c6018c49bf48c', 0, '9e939aff-c7a6-449a-b8c9-1e776a8a4978'),
('AD0005', 'Griselda', 'Guerrero', 'gris503', 'grismaryz@hotmail.com', '62c7ff050d80c08ac2387637dc3cffdb9898060d7749752903325359c31648eb', 0, '2e38a7d8-8315-4af6-bf6d-6856d234658c');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `dui` varchar(20) NOT NULL,
  `nombre` varchar(75) NOT NULL,
  `apellido` varchar(75) NOT NULL,
  `telefono` varchar(50) NOT NULL,
  `correo` varchar(75) NOT NULL,
  `clave` varchar(75) DEFAULT NULL,
  `img` varchar(100) NOT NULL,
  `confirmado` tinyint(11) NOT NULL,
  `idConfirmacion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`dui`, `nombre`, `apellido`, `telefono`, `correo`, `clave`, `img`, `confirmado`, `idConfirmacion`) VALUES
('0123456789', 'Alex', 'Molina', '7472-4370', 'alex3molina@gmail.com', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'imgestatica.jpg', 1, '815d1583-6289-4f78-ad3a-7fb3ad233169');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cupones`
--

CREATE TABLE `cupones` (
  `idCupon` char(25) NOT NULL,
  `codigoCupon` char(25) NOT NULL,
  `nombreCliente` varchar(75) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `fechaCompra` date DEFAULT NULL,
  `estado` varchar(35) DEFAULT NULL,
  `fechaCanje` date DEFAULT NULL,
  `dui` varchar(20) NOT NULL,
  `idPromocion` char(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dependiente`
--

CREATE TABLE `dependiente` (
  `idDependiente` varchar(25) NOT NULL,
  `nombreDep` varchar(75) DEFAULT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `correo` varchar(70) DEFAULT NULL,
  `clave` varchar(95) DEFAULT NULL,
  `codigoEmpresa` char(25) NOT NULL,
  `confirmado` tinyint(1) NOT NULL,
  `idConfirmacion` varchar(95) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `dependiente`
--

INSERT INTO `dependiente` (`idDependiente`, `nombreDep`, `apellido`, `correo`, `clave`, `codigoEmpresa`, `confirmado`, `idConfirmacion`) VALUES
('DEP002', 'dross', 'Molina', 'betoalas503@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'EMP003', 0, 'c968ff4f-04c5-45d4-84b8-fbe040c04269');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `codigoEmpresa` char(25) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `contactoNom` varchar(30) DEFAULT NULL,
  `telefono` varchar(30) DEFAULT NULL,
  `correo` varchar(30) DEFAULT NULL,
  `comision` double(4,0) DEFAULT NULL,
  `clave` varchar(75) DEFAULT NULL,
  `idRubro` char(25) NOT NULL,
  `confirmado` tinyint(1) DEFAULT NULL,
  `idConfirmacion` varchar(95) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`codigoEmpresa`, `nombre`, `direccion`, `contactoNom`, `telefono`, `correo`, `comision`, `clave`, `idRubro`, `confirmado`, `idConfirmacion`) VALUES
('EMP001', 'Microsoft', 'USA', 'Bill gates', '2451-7845', 'jersonfeik@gmail.com', 9, '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'RU0004', 0, '4614d903-7875-4aaf-8940-ff5cdf46ed55'),
('EMP002', 'Facebook', 'USA', 'Mark zuckberg', '2134-7584', 'betoalas503@gmail.com', 10, '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'RU0005', 1, '81f746a5-b7e3-4157-a5f5-4bfd5cd4887b'),
('EMP003', 'Linux', 'USA', 'Jerson', '2596-7841', 'gerson_alberto@hotmail.es', 11, '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'RU0004', 0, 'bce7e5ab-519e-4841-8b29-14544607d31e'),
('EMP004', 'Cisco', 'USA', 'Dross', '2257-7777', 'betoalas503@gmail.com', 3, '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'RU0002', 0, '69fd1af2-783f-4c8c-8fd9-e3f39b3d7ffa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadopromocion`
--

CREATE TABLE `estadopromocion` (
  `idEstado` char(25) NOT NULL,
  `estado` varchar(75) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estadopromocion`
--

INSERT INTO `estadopromocion` (`idEstado`, `estado`) VALUES
('1', 'En espera'),
('2', 'Oferta aprobada'),
('3', 'Oferta rechazada'),
('4', 'Oferta descartada'),
('5', 'Oferta activa'),
('6', 'Oferta pasada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promocion`
--

CREATE TABLE `promocion` (
  `idPromocion` char(25) NOT NULL,
  `titulo` varchar(65) NOT NULL,
  `precioRegular` decimal(5,2) DEFAULT NULL,
  `precioOferta` decimal(5,2) DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  `fechaLimite` date DEFAULT NULL,
  `descripcion` varchar(350) DEFAULT NULL,
  `justificacion` varchar(100) DEFAULT NULL,
  `img` varchar(100) DEFAULT NULL,
  `idEstado` char(25) DEFAULT NULL,
  `codigoEmpresa` char(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `promocion`
--

INSERT INTO `promocion` (`idPromocion`, `titulo`, `precioRegular`, `precioOferta`, `fechaInicio`, `fechaFin`, `fechaLimite`, `descripcion`, `justificacion`, `img`, `idEstado`, `codigoEmpresa`) VALUES
('PRO002', 'Donas 2x1', '1.00', '0.50', '2017-08-01', '2017-08-31', '2017-08-31', 'Donas al 2x1, llevate 2 por el precio de una.', NULL, 'imagendelaoferta.jpg', '2', 'EMP001'),
('PRO003', 'Descuentos', '9.99', '4.50', '2017-08-01', '2017-08-31', '2017-08-31', 'Esta oferta es de la base de datos.', NULL, 'immagfagagfs', '3', 'EMP001'),
('PRO004', 'Otra promocion', '4.88', '2.90', '2017-08-01', '2017-08-23', '2017-08-16', 'Otra promocion de la base de datos.', NULL, 'asfdsgagsdg', '1', 'EMP004'),
('PRO005', 'Otra Promocino xd', '4.80', '4.00', '2017-08-01', '2017-08-16', '2017-08-16', 'Excelente promocion!                                     ', NULL, 'eeded', '1', 'EMP003'),
('PRO006', 'Zapatos', '15.20', '10.20', '2017-08-30', '2017-09-01', '2017-09-02', 'Baratos xd                                                       \r\n                                                            ', NULL, 'img.jpg', '2', 'EMP003');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rubro`
--

CREATE TABLE `rubro` (
  `idRubro` char(25) NOT NULL,
  `nombreRubro` varchar(75) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `rubro`
--

INSERT INTO `rubro` (`idRubro`, `nombreRubro`) VALUES
('RU0001', 'Salud'),
('RU0002', 'Entretenimiento'),
('RU0003', 'Moda'),
('RU0004', 'Restaurante'),
('RU0005', 'Tecnologia'),
('RU0007', 'Automotriz');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`idAdmin`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`dui`);

--
-- Indices de la tabla `cupones`
--
ALTER TABLE `cupones`
  ADD PRIMARY KEY (`idCupon`),
  ADD KEY `FK_idPromocion` (`idPromocion`),
  ADD KEY `FK_dui` (`dui`);

--
-- Indices de la tabla `dependiente`
--
ALTER TABLE `dependiente`
  ADD PRIMARY KEY (`idDependiente`),
  ADD KEY `FK_codigoEmpresaa` (`codigoEmpresa`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`codigoEmpresa`),
  ADD KEY `FK_idRubro` (`idRubro`);

--
-- Indices de la tabla `estadopromocion`
--
ALTER TABLE `estadopromocion`
  ADD PRIMARY KEY (`idEstado`);

--
-- Indices de la tabla `promocion`
--
ALTER TABLE `promocion`
  ADD PRIMARY KEY (`idPromocion`),
  ADD KEY `FK_idEstado` (`idEstado`),
  ADD KEY `FK_codigoEmpresa` (`codigoEmpresa`);

--
-- Indices de la tabla `rubro`
--
ALTER TABLE `rubro`
  ADD PRIMARY KEY (`idRubro`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cupones`
--
ALTER TABLE `cupones`
  ADD CONSTRAINT `FK_dui` FOREIGN KEY (`dui`) REFERENCES `cliente` (`dui`),
  ADD CONSTRAINT `FK_idPromocion` FOREIGN KEY (`idPromocion`) REFERENCES `promocion` (`idPromocion`);

--
-- Filtros para la tabla `dependiente`
--
ALTER TABLE `dependiente`
  ADD CONSTRAINT `FK_codigoEmpresa` FOREIGN KEY (`codigoEmpresa`) REFERENCES `empresa` (`codigoEmpresa`);

--
-- Filtros para la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD CONSTRAINT `FK_idRubro` FOREIGN KEY (`idRubro`) REFERENCES `rubro` (`idRubro`);

--
-- Filtros para la tabla `promocion`
--
ALTER TABLE `promocion`
  ADD CONSTRAINT `promocion_ibfk_1` FOREIGN KEY (`idEstado`) REFERENCES `estadopromocion` (`idEstado`) ON UPDATE CASCADE,
  ADD CONSTRAINT `promocion_ibfk_2` FOREIGN KEY (`codigoEmpresa`) REFERENCES `empresa` (`codigoEmpresa`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

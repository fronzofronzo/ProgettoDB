-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: ristorante
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `allergeni`
--

DROP TABLE IF EXISTS `allergeni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allergeni` (
  `CodiceAllergene` varchar(6) NOT NULL,
  `Nome` varchar(10) NOT NULL,
  PRIMARY KEY (`CodiceAllergene`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergeni`
--

LOCK TABLES `allergeni` WRITE;
/*!40000 ALTER TABLE `allergeni` DISABLE KEYS */;
INSERT INTO `allergeni` VALUES ('ALL001','Glutine'),('ALL002','Crostacei'),('ALL003','Uova'),('ALL004','Pesce'),('ALL005','Arachidi'),('ALL006','Soia'),('ALL007','Latte'),('ALL008','FruttaGusc'),('ALL009','Sedano'),('ALL010','Senape'),('ALL011','Sesamo'),('ALL012','AnidSolfor'),('ALL013','Lupini'),('ALL014','Molluschi');
/*!40000 ALTER TABLE `allergeni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amministratori`
--

DROP TABLE IF EXISTS `amministratori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amministratori` (
  `CodiceAmministratore` varchar(10) NOT NULL,
  `Nome` varchar(15) NOT NULL,
  `Cognome` varchar(25) NOT NULL,
  `Telefono` varchar(10) DEFAULT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(20) NOT NULL,
  PRIMARY KEY (`CodiceAmministratore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amministratori`
--

LOCK TABLES `amministratori` WRITE;
/*!40000 ALTER TABLE `amministratori` DISABLE KEYS */;
INSERT INTO `amministratori` VALUES ('CAM001','Giuseppe','Rossi','345678990','giuseppe.rossi@gmail.com','sdujT679'),('CAM002','Gabriele','Bianchi','346789908','gabriele.bianchi@gmail.com','hurT67l'),('CAM003','Paolo','Verdi','376479374','paolo.verdi@gmail.com','Htyuedcs?8');
/*!40000 ALTER TABLE `amministratori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bevande`
--

DROP TABLE IF EXISTS `bevande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bevande` (
  `CodiceBevanda` varchar(6) NOT NULL,
  `Nome` varchar(15) NOT NULL,
  `Capacità` varchar(10) NOT NULL,
  `Prezzo` decimal(3,2) NOT NULL,
  PRIMARY KEY (`CodiceBevanda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bevande`
--

LOCK TABLES `bevande` WRITE;
/*!40000 ALTER TABLE `bevande` DISABLE KEYS */;
INSERT INTO `bevande` VALUES ('BEV001','Coca-Zero','33cl',3.00),('BEV002','AcquaNat','50cl',1.50),('BEV003','AcquaFriz','50cl',1.50),('BEV004','CocaCola','33cl',3.00),('BEV005','FuzeTea','33cl',3.00),('BEV006','Peroni','33cl',4.00);
/*!40000 ALTER TABLE `bevande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carte_fedelta`
--

DROP TABLE IF EXISTS `carte_fedelta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carte_fedelta` (
  `NumeroCarta` int NOT NULL,
  `PuntiFedeltà` int NOT NULL,
  PRIMARY KEY (`NumeroCarta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carte_fedelta`
--

LOCK TABLES `carte_fedelta` WRITE;
/*!40000 ALTER TABLE `carte_fedelta` DISABLE KEYS */;
INSERT INTO `carte_fedelta` VALUES (1,100),(2,50),(3,0),(4,150),(5,10);
/*!40000 ALTER TABLE `carte_fedelta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clienti`
--

DROP TABLE IF EXISTS `clienti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clienti` (
  `CodiceCliente` varchar(10) NOT NULL,
  `NumeroCarta` int NOT NULL,
  `Nome` varchar(15) NOT NULL,
  `Cognome` varchar(25) NOT NULL,
  `Telefono` int NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(20) NOT NULL,
  PRIMARY KEY (`CodiceCliente`),
  KEY `FKPOSSIEDE` (`NumeroCarta`),
  CONSTRAINT `FKPOSSIEDE` FOREIGN KEY (`NumeroCarta`) REFERENCES `carte_fedelta` (`NumeroCarta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clienti`
--

LOCK TABLES `clienti` WRITE;
/*!40000 ALTER TABLE `clienti` DISABLE KEYS */;
INSERT INTO `clienti` VALUES ('CLI001',1,'Stefano','Marchi',378678923,'stefano.marchi@gmail.com','StefMarc8'),('CLI002',2,'Stefania','Nobile',346778183,'stefania.nobile@gmail.com','gat823ndfsk'),('CLI003',3,'Fabiano','Brighi',330399364,'fabiano.brighi@gmail.com','usdyTV673n'),('CLI004',4,'Sara','Salvi',31076289,'sara.salvi@gmail.com','SSa736thdn'),('CLI005',5,'Matteo','Benigni',346197696,'matteo.benigni@gmail.com','minisdvJmfs9');
/*!40000 ALTER TABLE `clienti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composizione_piatti`
--

DROP TABLE IF EXISTS `composizione_piatti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composizione_piatti` (
  `CodiceIngrediente` varchar(6) NOT NULL,
  `CodicePiatto` varchar(6) NOT NULL,
  PRIMARY KEY (`CodiceIngrediente`,`CodicePiatto`),
  KEY `FKCOM_PIA` (`CodicePiatto`),
  CONSTRAINT `FKCOM_ING` FOREIGN KEY (`CodiceIngrediente`) REFERENCES `ingredienti` (`CodiceIngrediente`),
  CONSTRAINT `FKCOM_PIA` FOREIGN KEY (`CodicePiatto`) REFERENCES `piatti` (`CodicePiatto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composizione_piatti`
--

LOCK TABLES `composizione_piatti` WRITE;
/*!40000 ALTER TABLE `composizione_piatti` DISABLE KEYS */;
INSERT INTO `composizione_piatti` VALUES ('ING001','PIA001'),('ING002','PIA001'),('ING003','PIA001'),('ING004','PIA001'),('ING005','PIA001'),('ING006','PIA002'),('ING007','PIA002'),('ING008','PIA002'),('ING009','PIA002'),('ING010','PIA003'),('ING011','PIA003'),('ING012','PIA003'),('ING013','PIA003'),('ING006','PIA004'),('ING011','PIA004'),('ING014','PIA004'),('ING015','PIA004'),('ING021','PIA004'),('ING016','PIA005'),('ING017','PIA005'),('ING018','PIA005'),('ING011','PIA006'),('ING012','PIA006'),('ING019','PIA006'),('ING020','PIA006'),('ING017','PIA007'),('ING021','PIA007'),('ING017','PIA008'),('ING022','PIA008'),('ING023','PIA008'),('ING024','PIA008'),('ING011','PIA009'),('ING025','PIA009'),('ING026','PIA009'),('ING027','PIA009'),('ING028','PIA009'),('ING029','PIA009'),('ING030','PIA009'),('ING006','PIA010'),('ING011','PIA010'),('ING026','PIA010'),('ING027','PIA010'),('ING031','PIA010'),('ING032','PIA010');
/*!40000 ALTER TABLE `composizione_piatti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contiene_allergeni`
--

DROP TABLE IF EXISTS `contiene_allergeni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contiene_allergeni` (
  `CodiceAllergene` varchar(6) NOT NULL,
  `CodicePiatto` varchar(6) NOT NULL,
  PRIMARY KEY (`CodiceAllergene`,`CodicePiatto`),
  KEY `FKCON_PIA` (`CodicePiatto`),
  CONSTRAINT `FKCON_ALL` FOREIGN KEY (`CodiceAllergene`) REFERENCES `allergeni` (`CodiceAllergene`),
  CONSTRAINT `FKCON_PIA` FOREIGN KEY (`CodicePiatto`) REFERENCES `piatti` (`CodicePiatto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contiene_allergeni`
--

LOCK TABLES `contiene_allergeni` WRITE;
/*!40000 ALTER TABLE `contiene_allergeni` DISABLE KEYS */;
INSERT INTO `contiene_allergeni` VALUES ('ALL001','PIA001'),('ALL010','PIA001'),('ALL001','PIA002'),('ALL001','PIA003'),('ALL003','PIA003'),('ALL009','PIA003'),('ALL001','PIA004'),('ALL003','PIA004'),('ALL007','PIA004'),('ALL003','PIA006'),('ALL010','PIA006'),('ALL001','PIA009'),('ALL003','PIA009'),('ALL007','PIA009'),('ALL001','PIA010'),('ALL003','PIA010'),('ALL007','PIA010'),('ALL008','PIA010');
/*!40000 ALTER TABLE `contiene_allergeni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornitori`
--

DROP TABLE IF EXISTS `fornitori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornitori` (
  `PartitaIVA` varchar(11) NOT NULL,
  `RagioneSociale` char(1) NOT NULL,
  `Via` varchar(20) NOT NULL,
  `Ncivico` varchar(5) NOT NULL,
  `Cap` int NOT NULL,
  `Città` varchar(18) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Telefono` int NOT NULL,
  PRIMARY KEY (`PartitaIVA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornitori`
--

LOCK TABLES `fornitori` WRITE;
/*!40000 ALTER TABLE `fornitori` DISABLE KEYS */;
/*!40000 ALTER TABLE `fornitori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `include_bevande`
--

DROP TABLE IF EXISTS `include_bevande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `include_bevande` (
  `CodiceBevanda` varchar(6) NOT NULL,
  `CodiceOrdine` varchar(6) NOT NULL,
  `Quantità` int NOT NULL,
  PRIMARY KEY (`CodiceBevanda`,`CodiceOrdine`),
  KEY `FKINC_ORD` (`CodiceOrdine`),
  CONSTRAINT `FKINC_BEV` FOREIGN KEY (`CodiceBevanda`) REFERENCES `bevande` (`CodiceBevanda`),
  CONSTRAINT `FKINC_ORD` FOREIGN KEY (`CodiceOrdine`) REFERENCES `ordini` (`CodiceOrdine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `include_bevande`
--

LOCK TABLES `include_bevande` WRITE;
/*!40000 ALTER TABLE `include_bevande` DISABLE KEYS */;
/*!40000 ALTER TABLE `include_bevande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `include_piatti`
--

DROP TABLE IF EXISTS `include_piatti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `include_piatti` (
  `CodiceOrdine` varchar(6) NOT NULL,
  `CodicePiatto` varchar(6) NOT NULL,
  `Quantità` int NOT NULL,
  PRIMARY KEY (`CodicePiatto`,`CodiceOrdine`),
  KEY `FKINC_ORD2` (`CodiceOrdine`),
  CONSTRAINT `FKINC_ORD2` FOREIGN KEY (`CodiceOrdine`) REFERENCES `ordini` (`CodiceOrdine`),
  CONSTRAINT `FKINC_PIA` FOREIGN KEY (`CodicePiatto`) REFERENCES `piatti` (`CodicePiatto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `include_piatti`
--

LOCK TABLES `include_piatti` WRITE;
/*!40000 ALTER TABLE `include_piatti` DISABLE KEYS */;
INSERT INTO `include_piatti` VALUES ('O00002','PIA001',1),('O00001','PIA002',1),('O00001','PIA003',1),('O00005','PIA003',1),('O00002','PIA004',1),('O00004','PIA005',1),('O00003','PIA006',1),('O00004','PIA007',1),('O00005','PIA009',1);
/*!40000 ALTER TABLE `include_piatti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indirizzi`
--

DROP TABLE IF EXISTS `indirizzi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indirizzi` (
  `CodiceIndirizzo` varchar(6) NOT NULL,
  `Via` varchar(20) NOT NULL,
  `NCivico` varchar(5) NOT NULL,
  `Cap` int NOT NULL,
  `Città` varchar(18) NOT NULL,
  `CodiceCliente` varchar(10) NOT NULL,
  PRIMARY KEY (`CodiceIndirizzo`),
  KEY `FKR` (`CodiceCliente`),
  CONSTRAINT `FKR` FOREIGN KEY (`CodiceCliente`) REFERENCES `clienti` (`CodiceCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indirizzi`
--

LOCK TABLES `indirizzi` WRITE;
/*!40000 ALTER TABLE `indirizzi` DISABLE KEYS */;
/*!40000 ALTER TABLE `indirizzi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredienti`
--

DROP TABLE IF EXISTS `ingredienti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredienti` (
  `CodiceIngrediente` varchar(6) NOT NULL,
  `Nome` varchar(40) DEFAULT NULL,
  `Descrizione` varchar(50) NOT NULL,
  `QuantitàMagazzino` int NOT NULL,
  PRIMARY KEY (`CodiceIngrediente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredienti`
--

LOCK TABLES `ingredienti` WRITE;
/*!40000 ALTER TABLE `ingredienti` DISABLE KEYS */;
INSERT INTO `ingredienti` VALUES ('ING001','Farina ai 5 cereali','Farina ai 5 cereali',50),('ING002','Acqua','Acqua',1000),('ING003','Lievito','Lievito Madre',100),('ING004','Salsa Piccante','Salsa casalinga al peperoncino',50),('ING005','Salsa Verde','Salsa casalinga al Basilico',50),('ING006','Farina 00','Farina raffinata di grano tenero',250),('ING007','Strutto ','Strutto',100),('ING008','Prosciutto Crudo','Prosciutto Crudo DOP 24 mesi',20),('ING009','Mortadella','Mortadella Bologna',20),('ING010','Semola di grano duro','Semola di grano duro',200),('ING011','Uova','Uova km 0',100),('ING012','Macinato Misto','Macinato misto bovino-suino',50),('ING013','Passata di pomodoro','Passata Mutti',250),('ING014','Burro','Burro',50),('ING015','Salvia','Salvia',150),('ING016','Coniglio','Coniglio Intero',50),('ING017','Olio extravergine','Olio extavergine di oliva',100),('ING018','Olive','Olive',100),('ING019','Pangrattato','Pangrattato',150),('ING020','Erbe aromatiche','Misto di erbe aromatiche',100),('ING021','Patate','Patate novelle',200),('ING022','Peperoni','Peroni verdi, rossi e gialli',100),('ING023','Zucchine','Zucchinere nere',100),('ING024','Pomodori','Pomodori a grappolo',100),('ING025','Savoiardi','Savoiardi',150),('ING026','Latte Intero','Latte Intero fresco',50),('ING027','Zucchero','Zucchero Bianco',200),('ING028','Panna fresca','Panna fresca',50),('ING029','Cioccolato fondente 70%','Ciccolato fondente',100),('ING030','Alchermes','Liquore Alchermes',100),('ING031','Scorza di limone','scorza di limone biologico',100),('ING032','Pinoli','Pinoli',100);
/*!40000 ALTER TABLE `ingredienti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordini`
--

DROP TABLE IF EXISTS `ordini`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordini` (
  `CodiceOrdine` varchar(6) NOT NULL,
  `CodiceSconto` varchar(6) DEFAULT NULL,
  `PrezzoTotale` decimal(5,2) DEFAULT NULL,
  `Orario` varchar(5) NOT NULL,
  `Data` date NOT NULL,
  `CodiceCliente` varchar(10) NOT NULL,
  PRIMARY KEY (`CodiceOrdine`),
  KEY `FKUTILIZZO` (`CodiceSconto`),
  KEY `FKEFFETTUATO` (`CodiceCliente`),
  CONSTRAINT `FKEFFETTUATO` FOREIGN KEY (`CodiceCliente`) REFERENCES `clienti` (`CodiceCliente`),
  CONSTRAINT `FKUTILIZZO` FOREIGN KEY (`CodiceSconto`) REFERENCES `sconti` (`CodiceSconto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordini`
--

LOCK TABLES `ordini` WRITE;
/*!40000 ALTER TABLE `ordini` DISABLE KEYS */;
INSERT INTO `ordini` VALUES ('O00001',NULL,20.00,'18:30','2024-02-20','CLI001'),('O00002',NULL,19.50,'12:30','2024-02-22','CLI004'),('O00003',NULL,12.50,'20:00','2024-02-19','CLI002'),('O00004',NULL,17.50,'15:00','2024-02-28','CLI004'),('O00005',NULL,19.00,'12:00','2021-02-19','CLI003');
/*!40000 ALTER TABLE `ordini` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `piatti`
--

DROP TABLE IF EXISTS `piatti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `piatti` (
  `CodicePiatto` varchar(6) NOT NULL,
  `NomePiatto` varchar(30) NOT NULL,
  `PrezzoPorzione` decimal(5,2) DEFAULT NULL,
  `ApportoCaloricoPorzione` int NOT NULL,
  `CodicePortata` varchar(6) NOT NULL,
  PRIMARY KEY (`CodicePiatto`),
  KEY `FKAPPARTENENZA` (`CodicePortata`),
  CONSTRAINT `FKAPPARTENENZA` FOREIGN KEY (`CodicePortata`) REFERENCES `portate` (`CodicePortata`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `piatti`
--

LOCK TABLES `piatti` WRITE;
/*!40000 ALTER TABLE `piatti` DISABLE KEYS */;
INSERT INTO `piatti` VALUES ('PIA001','CrostiniMisti',9.00,500,'POR001'),('PIA002','PiadinaAffettato',8.50,530,'POR001'),('PIA003','TagliatelleRagù',11.50,650,'POR002'),('PIA004','TortelloniBurroSalvia',10.50,900,'POR002'),('PIA005','ConiglioAlForno',15.00,890,'POR003'),('PIA006','ArrostoDellaCasa',12.50,750,'POR003'),('PIA007','PatateAlForno',5.00,400,'POR004'),('PIA008','VerdureSaltate',5.00,350,'POR004'),('PIA009','ZuppaInglese',7.50,500,'POR005'),('PIA010','TortaDellaNonna',7.50,600,'POR005');
/*!40000 ALTER TABLE `piatti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `portate`
--

DROP TABLE IF EXISTS `portate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `portate` (
  `CodicePortata` varchar(6) NOT NULL,
  `Nome` varchar(20) NOT NULL,
  PRIMARY KEY (`CodicePortata`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `portate`
--

LOCK TABLES `portate` WRITE;
/*!40000 ALTER TABLE `portate` DISABLE KEYS */;
INSERT INTO `portate` VALUES ('POR001','Antipasti'),('POR002','Primi'),('POR003','Secondi'),('POR004','Contorni'),('POR005','Dolci');
/*!40000 ALTER TABLE `portate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recensioni`
--

DROP TABLE IF EXISTS `recensioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensioni` (
  `CodicePiatto` varchar(6) NOT NULL,
  `CodiceCliente` varchar(10) NOT NULL,
  `Testo` varchar(100) DEFAULT NULL,
  `Voto` int DEFAULT NULL,
  PRIMARY KEY (`CodicePiatto`,`CodiceCliente`),
  KEY `FKREC_CLI` (`CodiceCliente`),
  CONSTRAINT `FKREC_CLI` FOREIGN KEY (`CodiceCliente`) REFERENCES `clienti` (`CodiceCliente`),
  CONSTRAINT `FKREC_PIA` FOREIGN KEY (`CodicePiatto`) REFERENCES `piatti` (`CodicePiatto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recensioni`
--

LOCK TABLES `recensioni` WRITE;
/*!40000 ALTER TABLE `recensioni` DISABLE KEYS */;
INSERT INTO `recensioni` VALUES ('PIA001','CLI004','Molto buono',4),('PIA002','CLI001','Eccellente',5),('PIA004','CLI004','Niente di speciale',3);
/*!40000 ALTER TABLE `recensioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rifornimenti`
--

DROP TABLE IF EXISTS `rifornimenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rifornimenti` (
  `CodiceRifornimento` varchar(6) NOT NULL,
  `Data` date NOT NULL,
  `Quantità` int NOT NULL,
  `CodiceAmministratore` varchar(10) NOT NULL,
  `CodiceIngrediente` varchar(6) NOT NULL,
  `PartitaIVA` varchar(11) NOT NULL,
  PRIMARY KEY (`CodiceRifornimento`),
  KEY `FKSIGLATO` (`CodiceAmministratore`),
  KEY `FKRIGUARDANTE` (`CodiceIngrediente`),
  KEY `FKFORNITURA` (`PartitaIVA`),
  CONSTRAINT `FKFORNITURA` FOREIGN KEY (`PartitaIVA`) REFERENCES `fornitori` (`PartitaIVA`),
  CONSTRAINT `FKRIGUARDANTE` FOREIGN KEY (`CodiceIngrediente`) REFERENCES `ingredienti` (`CodiceIngrediente`),
  CONSTRAINT `FKSIGLATO` FOREIGN KEY (`CodiceAmministratore`) REFERENCES `amministratori` (`CodiceAmministratore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rifornimenti`
--

LOCK TABLES `rifornimenti` WRITE;
/*!40000 ALTER TABLE `rifornimenti` DISABLE KEYS */;
/*!40000 ALTER TABLE `rifornimenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sconti`
--

DROP TABLE IF EXISTS `sconti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sconti` (
  `CodiceSconto` varchar(6) NOT NULL,
  `Valore` decimal(3,2) NOT NULL,
  `Usato` char(1) NOT NULL,
  `NumeroCarta` int NOT NULL,
  PRIMARY KEY (`CodiceSconto`),
  KEY `FKGENERA` (`NumeroCarta`),
  CONSTRAINT `FKGENERA` FOREIGN KEY (`NumeroCarta`) REFERENCES `carte_fedelta` (`NumeroCarta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sconti`
--

LOCK TABLES `sconti` WRITE;
/*!40000 ALTER TABLE `sconti` DISABLE KEYS */;
/*!40000 ALTER TABLE `sconti` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-03 11:50:53

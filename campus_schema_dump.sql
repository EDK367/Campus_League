/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.7.2-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: campus
-- ------------------------------------------------------
-- Server version	11.7.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `coaches`
--

DROP TABLE IF EXISTS `coaches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `coaches` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `experience_years` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fields`
--

DROP TABLE IF EXISTS `fields`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `fields` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `capacity` bigint(20) NOT NULL,
  `location` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsmgn4onfhnpysju4xs1ul66br` (`name`),
  KEY `FK6hu2uweuuy9casdn1bkux5b2y` (`status_id`),
  CONSTRAINT `FK6hu2uweuuy9casdn1bkux5b2y` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `goals`
--

DROP TABLE IF EXISTS `goals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `goals` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goal_time` datetime(6) NOT NULL DEFAULT current_timestamp(6),
  `points` bigint(20) DEFAULT NULL,
  `match_id` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8ccx0cu1ny1aj69vjn4cruo3l` (`match_id`),
  KEY `FKh764uqklexa8ulfbb9c0o0y8c` (`player_id`),
  CONSTRAINT `FK8ccx0cu1ny1aj69vjn4cruo3l` FOREIGN KEY (`match_id`) REFERENCES `matches` (`id`),
  CONSTRAINT `FKh764uqklexa8ulfbb9c0o0y8c` FOREIGN KEY (`player_id`) REFERENCES `players` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `matches` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `match_date` datetime(6) DEFAULT current_timestamp(6),
  `team1_score` int(11) DEFAULT 0,
  `team2_score` int(11) DEFAULT 0,
  `field_id` bigint(20) NOT NULL,
  `referee_id` bigint(20) NOT NULL,
  `status_id` bigint(20) DEFAULT NULL,
  `team1_id` bigint(20) NOT NULL,
  `team2_id` bigint(20) NOT NULL,
  `tournament_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK92d55f2acmj25gbkioflhylum` (`field_id`),
  KEY `FKe93f8jtxkaoyjrmhit15msgm5` (`referee_id`),
  KEY `FKhwtkquyydy5ct7tdi9niyf6uj` (`status_id`),
  KEY `FK3ioil1py4fu8omd77sivakcwi` (`team1_id`),
  KEY `FKdkphr8xw4l2dgywsnbdbe04d7` (`team2_id`),
  KEY `FKeeniokyjgo5k6rmhjujatn27i` (`tournament_id`),
  CONSTRAINT `FK3ioil1py4fu8omd77sivakcwi` FOREIGN KEY (`team1_id`) REFERENCES `teams` (`id`),
  CONSTRAINT `FK92d55f2acmj25gbkioflhylum` FOREIGN KEY (`field_id`) REFERENCES `fields` (`id`),
  CONSTRAINT `FKdkphr8xw4l2dgywsnbdbe04d7` FOREIGN KEY (`team2_id`) REFERENCES `teams` (`id`),
  CONSTRAINT `FKe93f8jtxkaoyjrmhit15msgm5` FOREIGN KEY (`referee_id`) REFERENCES `referees` (`id`),
  CONSTRAINT `FKeeniokyjgo5k6rmhjujatn27i` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`),
  CONSTRAINT `FKhwtkquyydy5ct7tdi9niyf6uj` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `player_positions`
--

DROP TABLE IF EXISTS `player_positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_positions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sport_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq21kdxti31je5jsw44qbpsgu6` (`sport_id`),
  CONSTRAINT `FKq21kdxti31je5jsw44qbpsgu6` FOREIGN KEY (`sport_id`) REFERENCES `sports` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `players` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `carnet` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT current_timestamp(6),
  `names` varchar(255) NOT NULL,
  `position_id` bigint(20) NOT NULL,
  `status_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKna17rmbxyvhuuc4w359la7k2` (`position_id`),
  KEY `FKs89svmcn79kpfxbrwtq8h1yhl` (`status_id`),
  CONSTRAINT `FKna17rmbxyvhuuc4w359la7k2` FOREIGN KEY (`position_id`) REFERENCES `player_positions` (`id`),
  CONSTRAINT `FKs89svmcn79kpfxbrwtq8h1yhl` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `referees`
--

DROP TABLE IF EXISTS `referees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `referees` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `experience_years` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9nvbca2gb705fxtkoc3wbgxrc` (`status_id`),
  CONSTRAINT `FK9nvbca2gb705fxtkoc3wbgxrc` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sanction_types`
--

DROP TABLE IF EXISTS `sanction_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanction_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK722ww3yy983biv61i8vkfd3q0` (`type_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sanctions`
--

DROP TABLE IF EXISTS `sanctions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanctions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `sanction_date` datetime(6) NOT NULL DEFAULT current_timestamp(6),
  `match_id` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `sanction_type_id` bigint(20) NOT NULL,
  `team_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkljdvdmhaq449hs2e7mm6ca3i` (`match_id`),
  KEY `FKly04pncjybigbjc65c4gxvis` (`player_id`),
  KEY `FKs2wa8kagrhn97yd21gm5viwa1` (`sanction_type_id`),
  KEY `FK5t8mjyy01xyohi2k8q0x1ll12` (`team_id`),
  CONSTRAINT `FK5t8mjyy01xyohi2k8q0x1ll12` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`),
  CONSTRAINT `FKkljdvdmhaq449hs2e7mm6ca3i` FOREIGN KEY (`match_id`) REFERENCES `matches` (`id`),
  CONSTRAINT `FKly04pncjybigbjc65c4gxvis` FOREIGN KEY (`player_id`) REFERENCES `players` (`id`),
  CONSTRAINT `FKs2wa8kagrhn97yd21gm5viwa1` FOREIGN KEY (`sanction_type_id`) REFERENCES `sanction_types` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sports`
--

DROP TABLE IF EXISTS `sports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `sports` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `statuses`
--

DROP TABLE IF EXISTS `statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `statuses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKn3o9sngkueva0xxqevwov92qs` (`status_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `team_players`
--

DROP TABLE IF EXISTS `team_players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `team_players` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `player_id` bigint(20) NOT NULL,
  `team_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKddxneji5ow8j3171oe6mc2gu0` (`player_id`),
  KEY `FK3bhsykltbdhsmmb61l2ml12h` (`team_id`),
  CONSTRAINT `FK3bhsykltbdhsmmb61l2ml12h` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`),
  CONSTRAINT `FKddxneji5ow8j3171oe6mc2gu0` FOREIGN KEY (`player_id`) REFERENCES `players` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `teams` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approved_date` datetime(6) DEFAULT NULL,
  `captain` varchar(255) NOT NULL,
  `inscription_date` datetime(6) DEFAULT current_timestamp(6),
  `name` varchar(255) NOT NULL,
  `coach_id` bigint(20) DEFAULT NULL,
  `status_id` bigint(20) NOT NULL,
  `tournament_id` bigint(20) NOT NULL,
  `approved_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKa510no6sjwqcx153yd5sm4jrr` (`name`),
  KEY `FKip6gv8g69o6senngpocesacuk` (`coach_id`),
  KEY `FK8lu8tf5ym5w7s8j4fjyplro3n` (`status_id`),
  KEY `FKo5qehaxeuk4i5bxwpf20dvcws` (`tournament_id`),
  KEY `FKaght53odcsa38xlcd9bcoaotb` (`approved_by`),
  CONSTRAINT `FK8lu8tf5ym5w7s8j4fjyplro3n` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`),
  CONSTRAINT `FKaght53odcsa38xlcd9bcoaotb` FOREIGN KEY (`approved_by`) REFERENCES `users` (`id`),
  CONSTRAINT `FKip6gv8g69o6senngpocesacuk` FOREIGN KEY (`coach_id`) REFERENCES `coaches` (`id`),
  CONSTRAINT `FKo5qehaxeuk4i5bxwpf20dvcws` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tournament_groups`
--

DROP TABLE IF EXISTS `tournament_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `tournament_groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tournament_teams`
--

DROP TABLE IF EXISTS `tournament_teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `tournament_teams` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `points` bigint(20) DEFAULT NULL,
  `group_id` bigint(20) NOT NULL,
  `team_id` bigint(20) NOT NULL,
  `tournament_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcpdfpds5xvlbdfs9r5m7s23wp` (`group_id`),
  KEY `FKfdh1y15gds2l1e08j7qlhm9e2` (`team_id`),
  KEY `FKkbbpiasv8aqbh6uwc1m0wlvw4` (`tournament_id`),
  CONSTRAINT `FKcpdfpds5xvlbdfs9r5m7s23wp` FOREIGN KEY (`group_id`) REFERENCES `tournament_groups` (`id`),
  CONSTRAINT `FKfdh1y15gds2l1e08j7qlhm9e2` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`),
  CONSTRAINT `FKkbbpiasv8aqbh6uwc1m0wlvw4` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tournaments`
--

DROP TABLE IF EXISTS `tournaments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `tournaments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `end_date` datetime(6) NOT NULL,
  `inscriptions_close_date` datetime(6) NOT NULL,
  `inscriptions_open_date` datetime(6) NOT NULL,
  `max_team_members` int(11) NOT NULL,
  `min_team_members` int(11) NOT NULL,
  `start_date` datetime(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sport_id` bigint(20) NOT NULL,
  `status_id` bigint(20) NOT NULL,
  `creator_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtfww9ee33aoy7mkoohd1t3d1o` (`sport_id`),
  KEY `FKdmfbxabudpwijjsa00ibooib` (`status_id`),
  KEY `FKg4711p882h3t4q0u5vwhmvxea` (`creator_user_id`),
  CONSTRAINT `FKdmfbxabudpwijjsa00ibooib` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`),
  CONSTRAINT `FKg4711p882h3t4q0u5vwhmvxea` FOREIGN KEY (`creator_user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKtfww9ee33aoy7mkoohd1t3d1o` FOREIGN KEY (`sport_id`) REFERENCES `sports` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT current_timestamp(6),
  `created_by` bigint(20) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `status_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FKpp127ea8ef1s2x9em2o3bhsvo` (`status_id`),
  CONSTRAINT `FKpp127ea8ef1s2x9em2o3bhsvo` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `winners`
--

DROP TABLE IF EXISTS `winners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `winners` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `position` int(11) NOT NULL,
  `team_id` bigint(20) NOT NULL,
  `tournament_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm94v3pmn1f18sex9fay8apb5t` (`team_id`),
  KEY `FKtahr3huogbgu9m1ssnwq3god3` (`tournament_id`),
  CONSTRAINT `FKm94v3pmn1f18sex9fay8apb5t` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`),
  CONSTRAINT `FKtahr3huogbgu9m1ssnwq3god3` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2025-05-25  4:45:13

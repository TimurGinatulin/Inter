CREATE TABLE `film_table` (
  `id` BINARY(36) NOT NULL DEFAULT (uuid()),
  `title` VARCHAR(127) NOT NULL,
  `duration` int NULL,
  PRIMARY KEY (`id`)
  );
CREATE TABLE `film_session_table` (
  `id` BINARY(36) NOT NULL DEFAULT (uuid()),
  `film_id` BINARY(36) NOT NULL,
  `start_time` datetime NOT NULL,
  CONSTRAINT `fk_film_session` FOREIGN KEY (`film_id`) REFERENCES `film_table` (`id`),
  PRIMARY KEY (`id`)
  );
CREATE TABLE `ticket_table` (
  `id` BINARY(36) NOT NULL DEFAULT (uuid()),
  `film_session_id` BINARY(36) NOT NULL,
  `film_id` BINARY(36) NOT NULL,
  `cost` DECIMAL(8, 2) NOT NULL,
  `is_sold` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_ticket_session` FOREIGN KEY (`film_session_id`) REFERENCES `film_session_table` (`id`),
  CONSTRAINT `fk_ticket_film` FOREIGN KEY (`film_id`) REFERENCES `film_table` (`id`)
  );
INSERT INTO `film_table` (`title`,`duration`) values ("Terminator",90);
INSERT INTO `film_table` (`title`,`duration`) values ("Matrix",60);
INSERT INTO `film_table` (`title`,`duration`) values ("Star Wars",120);
INSERT INTO `film_table` (`title`,`duration`) values ("Harry Potter",90);
INSERT INTO `film_table` (`title`,`duration`) values ("Terminator 2",60);

INSERT INTO `film_session_table` (`film_id`,`start_time`) values ((SELECT id FROM `film_table` WHERE `title`="Terminator"),'2021-11-28 12:00:00');
INSERT INTO `film_session_table` (`film_id`,`start_time`) values ((SELECT id FROM `film_table` WHERE `title`="Matrix"),'2021-11-28 13:00:00');
INSERT INTO `film_session_table` (`film_id`,`start_time`) values ((SELECT id FROM `film_table` WHERE `title`="Star Wars"),'2021-11-28 14:00:00');
INSERT INTO `film_session_table` (`film_id`,`start_time`) values ((SELECT id FROM `film_table` WHERE `title`="Harry Potter"),'2021-11-28 14:30:00');
INSERT INTO `film_session_table` (`film_id`,`start_time`) values ((SELECT id FROM `film_table` WHERE `title`="Terminator 2"),'2021-11-28 17:00:00');
INSERT INTO `film_session_table` (`film_id`,`start_time`) values ((SELECT id FROM `film_table` WHERE `title`="Matrix"),'2021-11-28 18:45:00');

INSERT INTO `ticket_table` (`film_session_id`,`film_id`,`cost`,`is_sold`) values ((SELECT id FROM `film_session_table` WHERE `start_time`='2021-11-28 12:00:00'),
(SELECT id FROM `film_table` WHERE `title`="Terminator"),150.00,TRUE);
INSERT INTO `ticket_table` (`film_session_id`,`film_id`,`cost`,`is_sold`) values ((SELECT id FROM `film_session_table` WHERE `start_time`='2021-11-28 12:00:00'),
(SELECT id FROM `film_table` WHERE `title`="Terminator"),180.00,TRUE);
INSERT INTO `ticket_table` (`film_session_id`,`film_id`,`cost`,`is_sold`) values ((SELECT id FROM `film_session_table` WHERE `start_time`='2021-11-28 13:00:00'),
(SELECT id FROM `film_table` WHERE `title`="Matrix"),200.00,TRUE);
INSERT INTO `ticket_table` (`film_session_id`,`film_id`,`cost`,`is_sold`) values ((SELECT id FROM `film_session_table` WHERE `start_time`='2021-11-28 14:00:00'),
(SELECT id FROM `film_table` WHERE `title`="Star Wars"),180.00,TRUE);
INSERT INTO `ticket_table` (`film_session_id`,`film_id`,`cost`,`is_sold`) values ((SELECT id FROM `film_session_table` WHERE `start_time`='2021-11-28 14:30:00'),
(SELECT id FROM `film_table` WHERE `title`="Harry Potter"),180.00,TRUE);
INSERT INTO `ticket_table` (`film_session_id`,`film_id`,`cost`,`is_sold`) values ((SELECT id FROM `film_session_table` WHERE `start_time`='2021-11-28 17:00:00'),
(SELECT id FROM `film_table` WHERE `title`="Terminator 2"),200.00,TRUE);
INSERT INTO `ticket_table` (`film_session_id`,`film_id`,`cost`,`is_sold`) values ((SELECT id FROM `film_session_table` WHERE `start_time`='2021-11-28 17:00:00'),
(SELECT id FROM `film_table` WHERE `title`="Terminator 2"),180.00,TRUE);

CREATE VIEW `session_intersection` AS
SELECT
	tft1.title AS "Фильм 1" ,
	f1.start_time AS "Время начала фильм 1",
	tft1.duration AS "Длительность фильма 1",
	tft2.title AS "Фильм 2",
	f2.start_time AS "Время начала фильма 2",
	tft2.duration AS "Длительность фильма 2"
FROM film_session_table AS f1
LEFT JOIN film_session_table AS f2
	ON f1.start_time < DATE_ADD(f2.start_time,INTERVAL (SELECT duration FROM film_table AS ft WHERE ft.id = f2.film_id) MINUTE)
		AND DATE_ADD(f1.start_time,INTERVAL (SELECT duration FROM film_table AS ft WHERE ft.id = f1.film_id) MINUTE) > f2.start_time
		AND f1.start_time < f2.start_time
		AND f1.id <> f2.id
JOIN film_table tft1 ON tft1.id = f1.film_id
JOIN film_table tft2 ON tft2.id = f2.film_id
ORDER BY f1.start_time;

CREATE VIEW `find_downtime` AS
SELECT
	tft1.title AS "Фильм 1" ,
	f1.start_time AS "Время начала фильм 1",
	tft1.duration AS "Длительность фильма 1",
	tft2.title AS "Фильм 2",
	f2.start_time AS "Время начала фильма 2",
	tft2.duration AS "Длительность фильма 2",
    TIMESTAMPDIFF(MINUTE,DATE_ADD(f1.start_time,INTERVAL (SELECT duration FROM film_table AS ft WHERE ft.id = f1.film_id) MINUTE),f2.start_time ) AS "DIFF"
FROM film_session_table AS f1
 JOIN film_session_table AS f2
	ON TIMESTAMPDIFF(MINUTE,DATE_ADD(f1.start_time,INTERVAL (SELECT duration FROM film_table AS ft WHERE ft.id = f1.film_id) MINUTE),f2.start_time )  >= 30
JOIN film_table tft1 ON tft1.id = f1.film_id
JOIN film_table tft2 ON tft2.id = f2.film_id
/* Единственное не понял как это работает*/
WHERE NOT EXISTS ( SELECT NULL FROM film_session_table AS f3 WHERE f1.start_time < f3.start_time AND f3.start_time < f2.start_time)
ORDER BY f1.start_time;

CREATE VIEW `total_report` AS
SELECT ft1.title,
(SELECT count(*) FROM `ticket_table` AS tt1 WHERE tt1.film_id = ft1.id) AS "Total",
(SELECT AVG(res.c) FROM (SELECT count(*) AS c FROM `ticket_table` AS tt2 WHERE tt2.film_id = ft1.id GROUP BY tt2.film_session_id) AS res) AS "AVG",
(SELECT sum(tt3.cost) FROM `ticket_table` AS tt3 WHERE tt3.film_id = ft1.id) AS "Total cost"
FROM `film_table` AS ft1
UNION
SELECT "Total" AS title,
(SELECT count(*) FROM `ticket_table`)  AS "Total",
(SELECT AVG(res.c) FROM (SELECT count(*) AS c FROM `ticket_table` AS tt1 GROUP BY tt1.film_id) AS res) AS "AVG",
(SELECT sum(cost) FROM `ticket_table`) AS "Total cost"
ORDER BY `Total cost`;
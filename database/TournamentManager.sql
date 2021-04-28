CREATE DATABASE IF NOT EXISTS TournamentManager DEFAULT CHARSET = utf8mb4;
use TournamentManager;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS Tournaments;
DROP TABLE IF EXISTS Matches;
DROP TABLE IF EXISTS Teams;
DROP TABLE IF EXISTS TeamMatches;
DROP TABLE IF EXISTS Players;
SET FOREIGN_KEY_CHECKS=1;

CREATE table Tournaments(
id tinyint primary key auto_increment,
name varchar(250)
)ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE table Matches(
id tinyint primary key auto_increment, 
tournamentID tinyint,
matchType varchar(250),
date int,
time int,
active boolean DEFAULT 1,
FOREIGN KEY(tournamentID) REFERENCES Tournaments(id)
)ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE table Teams(
id tinyint primary key auto_increment,
tournamentID tinyint,
name varchar(250),
knockOut boolean DEFAULT 0,
FOREIGN KEY(tournamentID) REFERENCES Tournaments(id)
)ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE table TeamMatches(
tournamentID tinyint,
matchID tinyint,
teamID tinyint,
goals int,
points int,
FOREIGN KEY(matchID) REFERENCES Matches(id),
FOREIGN KEY(teamID) REFERENCES Teams(id),
FOREIGN KEY(tournamentID) REFERENCES Tournaments(id)
)ENGINE=InnoDB AUTO_INCREMENT=1;

 CREATE table Players(
 teamID tinyint,
 name varchar(250),
 FOREIGN KEY(teamID) REFERENCES Teams(id)
 )ENGINE=InnoDB AUTO_INCREMENT=1;

INSERT INTO Tournaments (name) VALUES ("SuperLiga");
INSERT INTO Tournaments (name) VALUES ("taberTunnering");

INSERT INTO Matches (tournamentID,matchType,date,time,active) VALUES(1,"GruppeSpil",2020-09-03,12.00,true);
INSERT INTO Matches (tournamentID,matchType,date,time,active) VALUES(1,"GruppeSpil",2020-09-03,12.00,true);
INSERT INTO Matches (tournamentID,matchType,date,time,active) VALUES(1,"GruppeSpil",2020-09-04,12.00,true);
INSERT INTO Matches (tournamentID,matchType,date,time,active) VALUES(1,"GruppeSpil",2020-09-04,12.00,true);
INSERT INTO Matches (tournamentID,matchType,date,time,active) VALUES(1,"GruppeSpil",2020-09-04,12.00,true);
INSERT INTO Matches (tournamentID,matchType,date,time,active) VALUES(1,"GruppeSpil",2020-09-04,12.00,true);
INSERT INTO Matches (tournamentID,matchType,date,time,active) VALUES(1,"GruppeSpil",2020-09-04,12.00,true);
INSERT INTO Matches (tournamentID,matchType,date,time,active) VALUES(1,"GruppeSpil",2020-09-04,12.00,true);
INSERT INTO Matches (tournamentID,matchType,date,time,active) VALUES(2,"gruppeSpil",2020-09-04,12.00,true);

INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldChristian",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldHidesh",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldLouise",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldA",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldB",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldC",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldD",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldE",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldF",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldG",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldH",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldI",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldJ",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldK",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldL",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (1,"HoldM",false);

INSERT INTO Teams (tournamentID,name,knockOut) VALUES (2,"HoldN",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (2,"HoldO",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (2,"HoldP",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (2,"HoldQ",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (2,"HoldR",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (2,"HoldS",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (2,"HoldT",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (2,"HoldU",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (2,"HoldV",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (2,"HoldX",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (2,"HoldY",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (2,"HoldZ",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (2,"HoldÆ",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (2,"HoldØ",false);
INSERT INTO Teams (tournamentID,name,knockOut) VALUES (2,"HoldÅ",false);

INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,1,1,0,2);
INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,1,2,0,0);
INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,2,3,0,0);
INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,2,4,0,0);
INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,3,5,0,0);
INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,3,6,0,0);
INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,4,7,0,0);
INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,4,8,0,0);
INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,5,9,0,0);
INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,5,10,0,0);
INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,6,11,0,0);
INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,6,12,0,0);
INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,7,13,0,0);
INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,7,14,0,0);
INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,8,15,0,0);
INSERT INTO TeamMatches (tournamentID,matchID,teamID,goals,points) VALUES (1,8,16,0,0);


INSERT INTO Players (teamID,name) VALUES (1,"Hidesh");
INSERT INTO Players (teamID,name) VALUES (1,"Mikkel");
INSERT INTO Players (teamID,name) VALUES (2,"Christian");
INSERT INTO Players (teamID,name) VALUES (2,"Louise");
INSERT INTO Players (teamID,name) VALUES (3,"Louise3");
INSERT INTO Players (teamID,name) VALUES (3,"Christian3");
INSERT INTO Players (teamID,name) VALUES (4,"Louise4");
INSERT INTO Players (teamID,name) VALUES (4,"Christian4");
INSERT INTO Players (teamID,name) VALUES (5,"Louise5");
INSERT INTO Players (teamID,name) VALUES (5,"Christian5");
INSERT INTO Players (teamID,name) VALUES (6,"Louise6");
INSERT INTO Players (teamID,name) VALUES (6,"Christian6");
INSERT INTO Players (teamID,name) VALUES (7,"Louise7");
INSERT INTO Players (teamID,name) VALUES (7,"Christian7");
INSERT INTO Players (teamID,name) VALUES (8,"Louise8");
INSERT INTO Players (teamID,name) VALUES (8,"Christian8");
INSERT INTO Players (teamID,name) VALUES (9,"Louise9");
INSERT INTO Players (teamID,name) VALUES (9,"Christian9");
INSERT INTO Players (teamID,name) VALUES (10,"Louise10");
INSERT INTO Players (teamID,name) VALUES (10,"Christian10");
INSERT INTO Players (teamID,name) VALUES (11,"Louise11");
INSERT INTO Players (teamID,name) VALUES (11,"Christian11");
INSERT INTO Players (teamID,name) VALUES (12,"Louise12");
INSERT INTO Players (teamID,name) VALUES (12,"Christian12");
INSERT INTO Players (teamID,name) VALUES (13,"Louise13");
INSERT INTO Players (teamID,name) VALUES (13,"Christian13");
INSERT INTO Players (teamID,name) VALUES (14,"Louise14");
INSERT INTO Players (teamID,name) VALUES (14,"Christian14");
INSERT INTO Players (teamID,name) VALUES (15,"Louise15");
INSERT INTO Players (teamID,name) VALUES (15,"Christian15");
INSERT INTO Players (teamID,name) VALUES (16,"Louise16");
INSERT INTO Players (teamID,name) VALUES (16,"Christian16");
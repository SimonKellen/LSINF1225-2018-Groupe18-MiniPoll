--
-- File generated with SQLiteStudio v3.1.1 on jeu. mai 10 13:38:23 2018
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Ami
DROP TABLE IF EXISTS Ami;
CREATE TABLE Ami (ID_User1 int NOT NULL REFERENCES Utilisateurs (ID_User), ID_User2 int NOT NULL REFERENCES Utilisateurs (ID_User), Etat char NOT NULL CHECK (Etat IN ('R', 'A', 'E')));

-- Table: Poll
DROP TABLE IF EXISTS Poll;
CREATE TABLE Poll (ID_Poll int PRIMARY KEY UNIQUE, ID_User int NOT NULL REFERENCES Utilisateurs (ID_User), Etat STRING NOT NULL CHECK (Etat IN ("F", "O")), Titre varchar (45) NOT NULL, Type STRING NOT NULL CHECK (Type IN ("Q", "S", "A")));

-- Table: QuestionReponse
DROP TABLE IF EXISTS QuestionReponse;
CREATE TABLE QuestionReponse (ID_Question int NOT NULL REFERENCES Questions (ID_Question), ID_Reponse int PRIMARY KEY NOT NULL REFERENCES Reponses (ID_Reponse) UNIQUE, Bonne_Reponse STRING CHECK (Bonne_Reponse IN ("V", "F")));

-- Table: Questions
DROP TABLE IF EXISTS Questions;
CREATE TABLE Questions (ID_Question int PRIMARY KEY NOT NULL UNIQUE, Numero_Ordre int, Format_Reponse char NOT NULL CHECK (Format_Reponse IN ('P', 'T')), Enonce varchar (45) NOT NULL, ID_Poll int NOT NULL REFERENCES Poll (ID_Poll), Nombres_de_Propositions int);

-- Table: Reponses
DROP TABLE IF EXISTS Reponses;
CREATE TABLE Reponses (ID_Reponse int PRIMARY KEY NOT NULL UNIQUE, Valeur varchar (60) NOT NULL);

-- Table: UtilisateurPoll
DROP TABLE IF EXISTS UtilisateurPoll;
CREATE TABLE UtilisateurPoll (ID_User int NOT NULL REFERENCES Utilisateurs (ID_User), ID_Poll int NOT NULL REFERENCES Poll (ID_Poll), A_repondu STRING CHECK (A_repondu IN ("V", "F")));

-- Table: UtilisateurRéponse
DROP TABLE IF EXISTS UtilisateurRéponse;
CREATE TABLE UtilisateurRéponse (Id_User int NOT NULL REFERENCES Utilisateurs (ID_User), ID_Reponse int NOT NULL REFERENCES Reponses (ID_Reponse), Score int);

-- Table: Utilisateurs
DROP TABLE IF EXISTS Utilisateurs;
CREATE TABLE Utilisateurs (ID_User IDU NOT NULL PRIMARY KEY UNIQUE, Prenom varchar (45) NOT NULL, Nom varchar (45) NOT NULL, Password STRING NOT NULL, BestFriend IDU, Pic photo, Mail STRING UNIQUE NOT NULL, Identifiant STRING UNIQUE);

-- Index: Idx UserReponse
DROP INDEX IF EXISTS "Idx UserReponse";
CREATE UNIQUE INDEX "Idx UserReponse" ON UtilisateurRéponse (Id_User, ID_Reponse);

-- Index: Idx_Rel_Ami
DROP INDEX IF EXISTS Idx_Rel_Ami;
CREATE UNIQUE INDEX Idx_Rel_Ami ON Ami (ID_User1, ID_User2);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;

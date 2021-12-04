CREATE TABLE IF NOT EXISTS chapter (
  chapterID INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS scene (
  sceneID INT AUTO_INCREMENT PRIMARY KEY,
  text VARCHAR(max) NOT NULL,
  corkboardtext VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS location (
  locationID INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(max) NOT NULL,
  uniquefeatures VARCHAR(255) NOT NULL,
  image LONGBLOB
);

CREATE TABLE IF NOT EXISTS FWcharacter (
  characterID INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  age INT NOT NULL,
  physicaldescription VARCHAR(255) NOT NULL,
  quirks VARCHAR(255) NOT NULL,
  motivation VARCHAR(255) NOT NULL,
  quotes VARCHAR(255) NOT NULL,
  history VARCHAR(255) NOT NULL,
  image LONGBLOB
);

CREATE TABLE IF NOT EXISTS note (
  noteID INT AUTO_INCREMENT PRIMARY KEY,
  text VARCHAR(max) NOT NULL,
  image LONGBLOB
);

CREATE TABLE IF NOT EXISTS corkboard (
  corkboardID INT AUTO_INCREMENT PRIMARY KEY,
  orderofsceneids VARCHAR(255) NOT NULL
);
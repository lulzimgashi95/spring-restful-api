CREATE TABLE projects
(
  id VARCHAR(36) PRIMARY KEY NOT NULL,
  name TEXT,
  description TEXT,
  start_date DATETIME,
  dead_line DATETIME,
  active TINYINT(4) DEFAULT '1'
);

CREATE TABLE members
(
  id VARCHAR(36) PRIMARY KEY NOT NULL,
  first_name TEXT,
  last_name TEXT,
  start_date DATETIME,
  position TEXT,
  image LONGBLOB,
  project_id VARCHAR(36),
  active TINYINT(4) DEFAULT '1'
);


CREATE TABLE sponsors
(
  id VARCHAR(36) PRIMARY KEY NOT NULL,
  name TEXT,
  amount DOUBLE,
  comment TEXT,
  project_id VARCHAR(36),
  active TINYINT(4) DEFAULT '1'
);


CREATE TABLE comments
(
  id VARCHAR(36) PRIMARY KEY NOT NULL,
  comment TEXT,
  date DATETIME,
  project_id VARCHAR(36),
  active TINYINT(4) DEFAULT '1'
);


CREATE TABLE activities
(
  id VARCHAR(36) PRIMARY KEY NOT NULL,
  name TEXT,
  details TEXT,
  member_id VARCHAR(36),
  active TINYINT(4) DEFAULT '1'
);


DELIMITER $$
CREATE TRIGGER delete_childs AFTER UPDATE ON projects
FOR EACH ROW
  BEGIN
    IF NEW.active = 0
    THEN
      UPDATE sponsors SET active = new.active WHERE project_id = new.id;
      UPDATE members SET active = new.active WHERE project_id = new.id;
      UPDATE comments SET active=new.active WHERE project_id = new.id;
    END IF;
  END $$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER delete_activities AFTER UPDATE ON members
FOR EACH ROW
  BEGIN
    IF NEW.active = 0
    THEN
      UPDATE activities SET active = new.active WHERE member_id = new.id;
    END IF;
  END $$
DELIMITER ;

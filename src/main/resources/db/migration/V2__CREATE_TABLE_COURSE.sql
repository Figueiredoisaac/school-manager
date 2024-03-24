CREATE TABLE COURSE (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  code VARCHAR(10) NULL UNIQUE,
  instructor_id INT NULL,
  description VARCHAR(150) NULL,
  status VARCHAR(45) NULL,
  created_at DATETIME NULL,
  disabled_at DATETIME NULL,
  PRIMARY KEY (id),
  INDEX instructor_id_idx (instructor_id),
  CONSTRAINT fk_instructor_id
      FOREIGN KEY (instructor_id)
      REFERENCES users (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

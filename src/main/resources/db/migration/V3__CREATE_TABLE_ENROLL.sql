CREATE TABLE ENROLL (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  course_id INT NOT NULL,
  enrolled_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  INDEX user_id_idx (user_id),
  INDEX course_id_idx (course_id),
  CONSTRAINT fk_user_id
      FOREIGN KEY (user_id)
      REFERENCES users (id)
      ON DELETE CASCADE
      ON UPDATE NO ACTION,
  CONSTRAINT fk_course_id
      FOREIGN KEY (course_id)
      REFERENCES course (id)
      ON DELETE CASCADE
      ON UPDATE NO ACTION,
  CONSTRAINT uk_user_course UNIQUE (user_id, course_id)
);
CREATE TABLE IF NOT EXISTS rating (
  id INT NOT NULL AUTO_INCREMENT,
  rating INT NOT NULL,
  user_id INT NOT NULL,
  course_id INT NOT NULL,
  description TEXT NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  INDEX user_id_idx (user_id),
  INDEX course_id_idx (course_id),
  CONSTRAINT fk_user_id_rating
      FOREIGN KEY (user_id)
      REFERENCES users (id)
      ON DELETE CASCADE
      ON UPDATE NO ACTION,
  CONSTRAINT fk_course_id_rating
      FOREIGN KEY (course_id)
      REFERENCES course (id)
      ON DELETE CASCADE
      ON UPDATE NO ACTION,
  CONSTRAINT uk_user_course_rating UNIQUE (user_id, course_id)
);
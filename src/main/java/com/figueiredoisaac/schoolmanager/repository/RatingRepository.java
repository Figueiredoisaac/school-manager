package com.figueiredoisaac.schoolmanager.repository;

import com.figueiredoisaac.schoolmanager.domain.RatingEntity;
import com.figueiredoisaac.schoolmanager.domain.dto.RatingDtoNpc;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

    @Query("""
            SELECT new com.figueiredoisaac.schoolmanager.domain.dto.RatingDtoNpc(r.course.id as course_id,
                ((SUM(CASE WHEN r.rating >= 9 THEN 1 ELSE 0 END) -
                SUM(CASE WHEN r.rating <= 6 THEN 1 ELSE 0 END)) / COUNT(r.id)) * 100.0  AS nps,
                c.code as code,
                c.name as course_name)
                FROM RatingEntity r
                JOIN r.course c
                GROUP BY r.course.id, c.code, c.name
            """)
    List<RatingDtoNpc> calculateNpsByCourse(Sort sort);

}

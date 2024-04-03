package com.figueiredoisaac.schoolmanager.domain.dto;

import java.math.BigDecimal;

public class RatingDtoNpc {

    Long course_id;
    Double nps;
    Long enroll_count;
    String code;
    String course_name;

    public RatingDtoNpc() {
    }

    public RatingDtoNpc(Long course_id, Double nps, Long enroll_count, String code, String course_name) {
        this.course_id = course_id;
        this.nps = nps;
        this.enroll_count = enroll_count;
        this.code = code;
        this.course_name = course_name;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Double getNps() {
        return nps;
    }

    public void setNps(Double nps) {
        this.nps = nps;
    }

    public Long getEnroll_count() {
        return enroll_count;
    }
    public void setEnroll_count(Long enroll_count) {
        this.enroll_count = enroll_count;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}

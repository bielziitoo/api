package br.com.aula.api;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Discipl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String teacher;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Integer hours;
    private Integer grade;

    public Integer getId() {
        return id;
    }  
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public LocalDate getDateStart() {
        return dateStart;
    }
    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }
    public LocalDate getDateEnd() {
        return dateEnd;
    }
    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
    public Integer getHours() {
        return hours;
    }
    public void setHours(Integer hours) {
        this.hours = hours;
    }
    public Integer getGrade() {
        return grade;
    }
    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}

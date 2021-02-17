package com.example.demo.dao;

import com.example.demo.model.Student;
import com.example.demo.model.StudentGroup;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentGroupJdbc {

    private final JdbcTemplate jdbcTemplate;

    public StudentGroupJdbc(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //  Просмотр группы
    public StudentGroup get(int id){
        String sql = "SELECT * FROM STUDENT_GROUP WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapStudentGroup, id);
    }

    //  Просмотр всех групп
    public List<StudentGroup> getAll(){
        String sql = "SELECT * FROM STUDENT_GROUP";
        return jdbcTemplate.query(sql, this::mapStudentGroup);
    }

    private StudentGroup mapStudentGroup(ResultSet rs, int i) throws SQLException{
        return new StudentGroup(
                rs.getInt("id"),
                rs.getString("name")
        );
    }

    // TODO
    //  Редактирование группы

    //  TODO
    //   Создание группы


    //  Удаление студента
    public int delete(String id) {
        return jdbcTemplate.update("DELETE FROM STUDENT_GROUP WHERE id = ?", id);
    }
}

package com.example.demo.dao;

import com.example.demo.model.Journal;
import com.example.demo.model.StudentGroup;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JournalJdbc {

    private final JdbcTemplate jdbcTemplate;

    public JournalJdbc(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //  Просмотр записи журнала
    public Journal get(int id){
        String sql = "SELECT * FROM JOURNAL WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapJournal, id);
    }

    //  Просмотр записей журнала по студенту
    public Journal getByStudent(int student_id){
        String sql = "SELECT * FROM JOURNAL WHERE student_id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapJournal, student_id);
    }

    //  Просмотр записей журнала по группе
    public Journal getBy(int study_group_id){
        String sql = "SELECT * FROM JOURNAL WHERE STUDENT_ID IN" +
                "(SELECT ID FROM STUDENTWHERE STUDY_GROUP_ID = ?) ";
        return jdbcTemplate.queryForObject(sql, this::mapJournal, study_group_id);
    }

    //  TODO
    //   Редактирование оценок в журнале

    private Journal mapJournal(ResultSet rs, int i) throws SQLException{
        return new Journal(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("study_plan_id"),
                rs.getByte("in_time"),
                rs.getInt("count"),
                rs.getInt("mark_id")
        );
    }
}

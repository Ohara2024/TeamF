package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    private String baseSql = "select student.no as student_no, student.name, student.ent_year, student.class_num, student.is_attend, student.school_cd, test.subject_cd, test.no as count, test.point from student left join (select * from test where subject_cd = ? and no = ?) as test on student.no = test.student_no";

    public Test get(Student student, Subject subject, School school, int no) throws Exception {

        Test test = null;

        String sql = "select * from test where student_no = ? and school_cd = ? and subject_cd = ? and no = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getNo());
            statement.setString(2, school.getCd());
            statement.setString(3, subject.getCd());
            statement.setInt(4, no);

            try (ResultSet rSet = statement.executeQuery()) {

                StudentDao studentDao = new StudentDao();
                SubjectDao subjectDao = new SubjectDao();
                SchoolDao schoolDao = new SchoolDao();

                if (rSet.next()) {
                    test = new Test();
                    test.setStudent(studentDao.get(rSet.getString("student_no")));
                    test.setSubject(subjectDao.get(rSet.getString("subject_cd"), schoolDao.get(rSet.getString("school_cd"))));
                    test.setSchool(schoolDao.get(rSet.getString("school_cd")));
                    test.setNo(rSet.getInt("no"));
                    test.setPoint(rSet.getInt("point"));
                    test.setClassNum(rSet.getString("class_num"));
                }
            }
        }

        return test;
    }

    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {

        List<Test> list = new ArrayList<>();
        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();

        try {
            while (rSet.next()) {
                Test test = new Test();
                test.setStudent(studentDao.get(rSet.getString("student_no")));
                test.setClassNum(rSet.getString("class_num"));
                test.setSubject(subjectDao.get(rSet.getString("subject_cd"), school));
                test.setSchool(school);
                test.setNo(rSet.getInt("count"));
                test.setPoint(rSet.getInt("point"));
                list.add(test);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {

        List<Test> list = new ArrayList<>();

        String condition = " where student.ent_year = ? and student.class_num = ? and student.school_cd = ?";
        String order = " order by student.no asc";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(baseSql + condition + order)) {

            // SQLパラメータのセット順序に注意
            statement.setString(1, subject.getCd());  // from baseSql
            statement.setInt(2, num);                  // from baseSql
            statement.setInt(3, entYear);              // where句の1つ目
            statement.setString(4, classNum);          // where句の2つ目
            statement.setString(5, school.getCd());    // where句の3つ目

            try (ResultSet rSet = statement.executeQuery()) {
                list = postFilter(rSet, school);
            }
        }

        return list;
    }

    public boolean save(List<Test> list) throws Exception {

        // コネクションを一度だけ取得して使いまわす
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try {
                for (Test test : list) {
                    save(test, connection);
                }
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw e;
            }
        }

        return true;
    }

    private boolean save(Test test, Connection connection) throws Exception {

        PreparedStatement statement = null;
        int count = 0;

        try {
            Test old = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());
            if (old == null) {
                statement = connection.prepareStatement("insert into test(student_no, subject_cd, school_cd, no, point, class_num) values(?, ?, ?, ?, ?, ?)");
                statement.setString(1, test.getStudent().getNo());
                statement.setString(2, test.getSubject().getCd());
                statement.setString(3, test.getSchool().getCd());
                statement.setInt(4, test.getNo());
                statement.setInt(5, test.getPoint());
                statement.setString(6, test.getClassNum());
            } else {
                statement = connection.prepareStatement("update test set point = ? where student_no = ? and subject_cd = ? and school_cd = ? and no = ?");
                statement.setInt(1, test.getPoint());
                statement.setString(2, test.getStudent().getNo());
                statement.setString(3, test.getSubject().getCd());
                statement.setString(4, test.getSchool().getCd());
                statement.setInt(5, test.getNo());
            }

            count = statement.executeUpdate();

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return count > 0;
    }
}

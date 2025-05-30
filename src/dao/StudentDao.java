package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao {

    private Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        String url = "jdbc:h2:tcp://localhost/~/exam";
        String user = "sa";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }

    public Student get(String no) throws Exception {
        Student student = null;
        String sql = "SELECT * FROM student WHERE no = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, no);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                SchoolDao schoolDao = new SchoolDao();
                student = new Student();
                student.setNo(rs.getString("no"));
                student.setName(rs.getString("name"));
                student.setEntYear(rs.getInt("ent_year"));
                student.setClassNum(rs.getString("class_num"));
                student.setAttend(rs.getBoolean("is_attend"));
                student.setSchool(schoolDao.get(rs.getString("school_cd")));
            }
        }
        return student;
    }

    public List<Student> findAll() throws Exception {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setNo(rs.getString("NO"));
                student.setName(rs.getString("NAME"));
                student.setEntYear(rs.getInt("ENT_YEAR"));
                student.setClassNum(rs.getString("CLASS_NUM"));
                student.setAttend(rs.getBoolean("IS_ATTEND"));
                student.setSchoolCd(rs.getString("SCHOOL_CD"));
                students.add(student);
            }
        }
        return students;
    }

    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        String sql = "SELECT * FROM student WHERE school_cd = ? AND ent_year = ? AND class_num = ?"
                + (isAttend ? " AND is_attend = true" : "") + " ORDER BY no ASC";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            ResultSet rs = statement.executeQuery();

            return postFilter(rs, school);
        }
    }

    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        String sql = "SELECT * FROM student WHERE school_cd = ? AND ent_year = ?"
                + (isAttend ? " AND is_attend = true" : "") + " ORDER BY no ASC";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            ResultSet rs = statement.executeQuery();

            return postFilter(rs, school);
        }
    }

    public List<Student> filter(School school, boolean isAttend) throws Exception {
        String sql = "SELECT * FROM student WHERE school_cd = ?"
                + (isAttend ? " AND is_attend = true" : "") + " ORDER BY no ASC";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, school.getCd());
            ResultSet rs = statement.executeQuery();

            return postFilter(rs, school);
        }
    }

    private List<Student> postFilter(ResultSet rs, School school) throws Exception {
        List<Student> list = new ArrayList<>();

        while (rs.next()) {
            Student student = new Student();
            student.setNo(rs.getString("no"));
            student.setName(rs.getString("name"));
            student.setEntYear(rs.getInt("ent_year"));
            student.setClassNum(rs.getString("class_num"));
            student.setAttend(rs.getBoolean("is_attend"));
            student.setSchool(school);
            list.add(student);
        }

        return list;
    }

    public boolean save(Student student) throws Exception {
        String insertSql = "INSERT INTO student(no, name, ent_year, class_num, is_attend, school_cd) VALUES(?, ?, ?, ?, ?, ?)";
        String updateSql = "UPDATE student SET name = ?, ent_year = ?, class_num = ?, is_attend = ? WHERE no = ?";

        try (Connection connection = getConnection()) {
            Student existing = get(student.getNo());

            try (PreparedStatement statement = connection.prepareStatement(existing == null ? insertSql : updateSql)) {
                if (existing == null) {
                    statement.setString(1, student.getNo());
                    statement.setString(2, student.getName());
                    statement.setInt(3, student.getEntYear());
                    statement.setString(4, student.getClassNum());
                    statement.setBoolean(5, student.isAttend());
                    statement.setString(6, student.getSchoolCd() != null ? student.getSchoolCd() : "");
                } else {
                    statement.setString(1, student.getName());
                    statement.setInt(2, student.getEntYear());
                    statement.setString(3, student.getClassNum());
                    statement.setBoolean(4, student.isAttend());
                    statement.setString(5, student.getNo());
                }
                int result = statement.executeUpdate();
                return result > 0;
            }
        }
    }

    public static Student findById(int id) {
        // 実装予定
        return null;
    }

    public static void update(int id, String name, String grade, String email) {
        // 実装予定（引数が Student と整合していないため注意）
    }
}

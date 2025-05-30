package bean;

public class Student {
    private int id; // ← 追加
    private String name;
    private int age; // ← 追加
    private String department; // ← 追加

    private String no;
    private int score;
    private int entYear;
    private String classNum;
    private boolean attend;
    private String schoolCd;
    private int id1;  // ← フィールド追加

    public int getId1() {
        return id;
    }

    public void setId1(int id) {
        this.id = id;
    }


    // ★ 追加: id, age, department の getter/setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    // --- 既存の getter/setter ---
    public String getNo() { return no; }
    public void setNo(String no) { this.no = no; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getEntYear() { return entYear; }
    public void setEntYear(int entYear) { this.entYear = entYear; }

    public String getClassNum() { return classNum; }
    public void setClassNum(String classNum) { this.classNum = classNum; }

    public boolean isAttend() { return attend; }
    public void setAttend(boolean attend) { this.attend = attend; }

    public String getSchoolCd() { return schoolCd; }
    public void setSchoolCd(String schoolCd) { this.schoolCd = schoolCd; }

    public School getSchool() {
        return null; // 必要に応じて実装
    }

    public void setSchool(School school) {
        // 必要に応じて実装
    }
}

package scoremanager;

import java.io.Serializable;

public class StudentCreateExecuteAction implements Serializable {
    private String no;
    private String name;
    private int entYear;
    private String classNum;
    private boolean isAttend;
    private String schoolCd; // ← 追加

    // 学生番号
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    // 名前
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 入学年度
    public int getEntYear() {
        return entYear;
    }

    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    // クラス番号
    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    // 在学フラグ
    public boolean isAttend() {
        return isAttend;
    }

    public void setAttend(boolean isAttend) {
        this.isAttend = isAttend;
    }

    // 学校コード（新規追加）
    public String getSchoolCd() {
        return schoolCd;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }
}

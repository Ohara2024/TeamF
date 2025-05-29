package bean;

public class Student {
    private String no;
    private String name;
    private int score;
    private int entYear;
    private String classNum;
    private boolean attend;
    private String schoolCd;

    public String getNo() { return no; }
    public void setNo(String no) { this.no = no; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    // 追加する getter / setter
    public int getEntYear() { return entYear; }
    public void setEntYear(int entYear) { this.entYear = entYear; }

    public String getClassNum() { return classNum; }
    public void setClassNum(String classNum) { this.classNum = classNum; }

    public boolean isAttend() { return attend; }
    public void setAttend(boolean attend) { this.attend = attend; }

    public String getSchoolCd() { return schoolCd; }
    public void setSchoolCd(String schoolCd) { this.schoolCd = schoolCd; }
	public School getSchool() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	public void setSchool(School school) {
		// TODO 自動生成されたメソッド・スタブ

	}
}

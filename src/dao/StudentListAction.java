package dao;

import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentListAction {
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();

        // ダミーデータ例。実際はDBアクセスなどで取得してください
        Student s1 = new Student();
        s1.setNo("S001");
        s1.setName("山田 太郎");
        s1.setEntYear(2022);
        s1.setClassNum("A1");
        s1.setAttend(true);
        list.add(s1);

        Student s2 = new Student();
        s2.setNo("S002");
        s2.setName("鈴木 次郎");
        s2.setEntYear(2021);
        s2.setClassNum("B2");
        s2.setAttend(false);
        list.add(s2);

        return list;
    }
}

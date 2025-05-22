package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School; // School オブジェクトを使用するため必要

//... (前略) ...

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;

public class SubjectCreateExecuteAction implements Action {

 @Override
 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
     HttpSession session = request.getSession();
     Teacher teacher = (Teacher) session.getAttribute("user");

     if (teacher == null) {
         return "redirect:login.jsp";
     }

     // Teacher オブジェクトから関連付けられた School オブジェクトを取得し、その School オブジェクトから学校コードを取得
     // Teacher Bean に getSchool() メソッドがあり、かつ bean.School に getCd() メソッドがあることを想定
     // また、Teacher オブジェクトに School オブジェクトが正しくセットされている必要があります（LoginExecuteAction や TeacherDao での設定）
     School teacherSchool = teacher.getSchool(); // Teacher Bean に getSchool() があると仮定

     if (teacherSchool == null) {
         // Teacher オブジェクトに School オブジェクトがセットされていない場合のハンドリング
         request.setAttribute("error", "教員に学校情報が紐づいていません。");
         return "subject_create.jsp"; // エラー画面に戻るか、別のページに遷移
     }

     String schoolCd = teacherSchool.getCd(); // School Bean に getCd() があると仮定

     String cd = request.getParameter("cd");
     String name = request.getParameter("name");

     if (cd == null || cd.isEmpty()) {
         request.setAttribute("error", "科目コードを入力してください");
         request.setAttribute("name", name);
         return "subject_create.jsp";
     }
     if (name == null || name.isEmpty()) {
         request.setAttribute("error", "科目名を入力してください");
         request.setAttribute("cd", cd);
         return "subject_create.jsp";
     }

     Subject subject = new Subject();
     subject.setCd(cd);
     subject.setName(name);

     // SubjectDao.save() は Subject オブジェクトに School オブジェクトがセットされていることを期待
     // 上で取得した teacherSchool オブジェクトを Subject にセット
     subject.setSchool(teacherSchool); // Subject Bean に setSchool(School) があると仮定

     SubjectDao subjectDao = new SubjectDao();
     boolean success = subjectDao.save(subject);

     if (success) {
         return "redirect:subject_done.jsp";
     } else {
         request.setAttribute("error", "科目コードが重複しています");
         request.setAttribute("cd", cd);
         request.setAttribute("name", name);
         return "subject_create.jsp";
     }
 }
}
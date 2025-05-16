package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bean.Student;
import bean.Subject;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;

public class TestListStudentAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        String studentId = request.getParameter("studentId");

        // 学生IDのバリデーション
        if (studentId == null || studentId.trim().isEmpty()) {
            request.setAttribute("errorMessage", "学生番号を入力してください。");
            return mapping.findForward("input"); // 入力画面に戻る定義が必要です (struts-config.xml)
        }

        StudentDao studentDao = new StudentDao();
        Student student = studentDao.getStudent(Integer.parseInt(studentId)); // STUDENT_IDがINT型と仮定

        if (student == null) {
            request.setAttribute("errorMessage", "該当する学生が見つかりませんでした。");
            return mapping.findForward("error"); // エラー画面の定義が必要です (struts-config.xml)
        }

        TestDao testDao = new TestDao();
        List<Test> testList = testDao.getTestsByStudentId(Integer.parseInt(studentId)); // STUDENT_IDがINT型と仮定

        SubjectDao subjectDao = new SubjectDao();
        for (Test test : testList) {
            Subject subject = subjectDao.getSubject(test.getSubjectId());
            test.setSubject(subject); // Test BeanにSubject Beanを設定
        }

        request.setAttribute("student", student);
        request.setAttribute("testList", testList);

        return mapping.findForward("success"); // 成績一覧画面へのフォワード定義が必要です (struts-config.xml)
    }
}
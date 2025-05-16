package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bean.ClassNum;
import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import dao.ClassNumDao;
import dao.SchoolDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;

public class TestListSubjectAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        String schoolIdParam = request.getParameter("schoolId");
        String classIdParam = request.getParameter("classId");
        String subjectIdParam = request.getParameter("subjectId");

        // 簡単なバリデーション
        if (schoolIdParam == null || schoolIdParam.trim().isEmpty() ||
            classIdParam == null || classIdParam.trim().isEmpty() ||
            subjectIdParam == null || subjectIdParam.trim().isEmpty()) {
            request.setAttribute("errorMessage", "学年、クラス、科目を選択してください。");
            // 検索条件入力画面に戻る (定義が必要です)
            return mapping.findForward("input");
        }

        int schoolId = Integer.parseInt(schoolIdParam);
        int classId = Integer.parseInt(classIdParam);
        int subjectId = Integer.parseInt(subjectIdParam);

        TestDao testDao = new TestDao();
        List<Test> testList = testDao.getTestsBySchoolClassSubject(schoolId, classId, subjectId);

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.getSubject(subjectId);

        StudentDao studentDao = new StudentDao();
        ClassNumDao classNumDao = new ClassNumDao();
        SchoolDao schoolDao = new SchoolDao();

        for (Test test : testList) {
            Student student = studentDao.getStudent(test.getStudentId());
            ClassNum classNum = classNumDao.getClassNum(student.getClassNum().getClassId());
            School school = schoolDao.getSchool(classNum.getSchoolId());
            classNum.setSchool(school);
            student.setClassNum(classNum);
            test.setStudent(student); // Test BeanにStudent Beanを設定
            test.setSubject(subject); // Test BeanにSubject Beanを設定
        }

        request.setAttribute("subject", subject);
        request.setAttribute("testList", testList);

        return mapping.findForward("success"); // 科目別成績一覧画面へのフォワード定義が必要です
    }

    // (必要に応じて) 検索条件の初期表示に必要な学校、クラス、科目のリストを取得する処理などを追加
}
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

// 成績の登録処理を行うアクションクラス
public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// ▼ 1. セッションやDAOなどの初期設定 -----------------------------
		HttpSession session = req.getSession();                         // セッションからログイン中の教員を取得
		Teacher teacher = (Teacher) session.getAttribute("user");

		// 入力情報などの変数宣言
		String subject = "";            // 科目コード
		int count = 0;                  // テストの回数
		int point = 0;                  // 得点

		// 各種DAOインスタンス生成
		StudentDao studentDao = new StudentDao();
		SubjectDao subjectDao = new SubjectDao();
		ClassNumDao cNumDao = new ClassNumDao();
		TestDao testDao = new TestDao();

		List<Test> list = new ArrayList<>();        // 登録用のTestオブジェクトリスト
		List<Test> testlist = new ArrayList<>();    // エラー時に再表示するためのテストリスト

		LocalDate todaysDate = LocalDate.now();     // 現在日付を取得
		int year = todaysDate.getYear();            // 現在の西暦年
		School teacherSchool = teacher.getSchool(); // 教員が所属する学校

		int c = 1;                                   // エラーインデックス（エラーの位置を追跡）
		Map<Integer, String> errors = new HashMap<>(); // エラーメッセージを格納


		// ▼ 2. フォームから送信されたデータを取得 -----------------------
		subject = req.getParameter("subject");               // 科目コード
		count = Integer.parseInt(req.getParameter("count")); // 回数（文字列→数値）
		String[] student_no = req.getParameterValues("regist"); // 対象学生の学籍番号配列


		// ▼ 3. DBから取得するデータはこの処理ではなし -------------------

		// ▼ 4. 入力チェックとTestインスタンスの生成 --------------------
		for (String item : student_no) { // 学生ごとにループ
			Test test = new Test();      // Testオブジェクトを作成

			try {
				// 入力された得点を取得
				point = Integer.parseInt(req.getParameter("point_" + item));

				// 得点が0〜100の範囲なら登録対象とする
				if (point >= 0 && point <= 100) {

					// 学生情報・テスト情報を設定
					test.setStudent(studentDao.get(item)); // 学生情報
					test.setClassNum(studentDao.get(item).getClassNum()); // クラス番号
					test.setSubject(subjectDao.get(subject, teacherSchool)); // 科目
					test.setSchool(teacherSchool); // 学校情報
					test.setNo(count);             // 回数
					test.setPoint(point);          // 得点

					// 登録用リストに追加
					list.add(test);

				} else {
					// 得点が範囲外なら例外を投げる
					throw new Exception();
				}

			} catch (Exception e) {
				// 例外が発生した場合（未入力や範囲外など）
				errors.put(c, "0～100の範囲で入力してください");

				// 最初のエラー時のみ再表示用の情報を準備する
				if (testlist.size() == 0) {

					// 各リスト取得
					List<String> cNumlist = cNumDao.filter(teacherSchool);         // クラスリスト
					List<Subject> subjectlist = subjectDao.filter(teacherSchool);  // 科目リスト

					// 条件に一致する成績データを取得
					testlist = testDao.filter(
						studentDao.get(item).getEntYear(),
						studentDao.get(item).getClassNum(),
						subjectDao.get(subject, teacherSchool),
						count,
						teacherSchool
					);

					// 科目名取得
					String subject_name = subjectDao.get(subject, teacherSchool).getName();

					// 年度リスト作成（前後10年）
					List<Integer> entYearSet = new ArrayList<>();
					for (int i = year - 10; i < year + 11; i++) {
						entYearSet.add(i);
					}

					// 回数リスト作成（1回〜2回）
					List<Integer> countSet = new ArrayList<>();
					for (int i = 1; i < 3; i++) {
						countSet.add(i);
					}

					// 再表示に必要なデータをリクエストにセット
					req.setAttribute("testlist", testlist);
					req.setAttribute("subject_name", subject_name);
					req.setAttribute("f1", studentDao.get(item).getEntYear()); // 入学年度
					req.setAttribute("f2", studentDao.get(item).getClassNum()); // クラス
					req.setAttribute("f3", subject);                            // 科目
					req.setAttribute("f4", count);                              // 回数
					req.setAttribute("entYearList", entYearSet);                // 入学年度リスト
					req.setAttribute("cNumList", cNumlist);                     // クラスリスト
					req.setAttribute("list", subjectlist);                      // 科目リスト
					req.setAttribute("countList", countSet);                    // 回数リスト
				}
			} finally {
				// エラー番号（インデックス）をインクリメント
				c++;
			}
		}

		// ▼ 5. 成績の登録またはエラー処理 -----------------------------
		if (errors.isEmpty()) {
			// エラーがない場合：成績情報をDBに保存
			testDao.save(list);
		} else {
			// エラーがあった場合：画面にエラー情報を渡す
			req.setAttribute("errors", errors);
		}

		// ▼ 6. レスポンスのセット（特になし）

		// ▼ 7. 次の画面へ遷移 ---------------------------------------
		if (errors.isEmpty()) {
			// 登録成功 → 完了画面へ遷移
			req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
		} else {
			// 入力エラー → 再入力画面へ遷移
			req.getRequestDispatcher("test_regist.jsp").forward(req, res);
		}
	}
}

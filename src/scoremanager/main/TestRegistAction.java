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
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

// 成績登録画面の表示および検索処理を行うアクションクラス
public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// セッションの取得（ログインしている教員情報を取得するため）
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// 入力値や内部処理用の変数定義
		String entYearStr = null;   // 入力：入学年度（文字列）
		int entYear = 0;            // 入学年度（int）
		String classNum = "";       // 入力：クラス番号
		String subject = "";        // 入力：科目コード
		String countStr = null;     // 入力：回数（文字列）
		int count = 0;              // 回数（int）

		// 各種DAOの初期化
		ClassNumDao cNumDao = new ClassNumDao();       // クラス情報用DAO
		SubjectDao subjectDao = new SubjectDao();      // 科目情報用DAO
		TestDao testDao = new TestDao();               // 成績情報用DAO

		// 教員が所属する学校情報を取得
		School teacherSchool = teacher.getSchool();

		// 現在日付を取得し、今年の西暦を取り出す
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();

		// エラー情報格納用Map
		Map<String, String> errors = new HashMap<>();

		// ▼ 画面から送信されたパラメータを取得（フィルター条件）
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subject = req.getParameter("f3");
		countStr = req.getParameter("f4");

		// ▼ DBから現在の学校に属するクラス・科目情報を取得
		List<String> cNumlist = cNumDao.filter(teacherSchool);       // クラス一覧
		List<Subject> list = subjectDao.filter(teacherSchool);       // 科目一覧

		// ▼ 入力された文字列から数値に変換（未入力の場合はスキップ）
		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}
		if (countStr != null) {
			count = Integer.parseInt(countStr);
		}

		// ▼ 入学年度選択用リスト作成（現在年から±10年分）
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 11; i++) {
			entYearSet.add(i);
		}

		// ▼ 回数選択用リスト作成（1～2回）
		List<Integer> countSet = new ArrayList<>();
		for (int i = 1; i < 3; i++) {
			countSet.add(i);
		}

		// ▼ 検索条件がすべて未指定なら何もしない
		if (entYear == 0 && classNum == null && subject == null && count == 0) {
			// 初期表示時など：検索は行わない
		}
		// ▼ 全ての条件が選択されていた場合のみ検索処理を実行
		else if (entYear != 0 && !(classNum.equals("0")) && !(subject.equals("0")) && count != 0) {
			// 該当する成績データを検索
			List<Test> testlist = testDao.filter(entYear, classNum, subjectDao.get(subject, teacherSchool), count, teacherSchool);

			// 科目名の取得（コードから）
			String subject_name = subjectDao.get(subject, teacherSchool).getName();

			// 検索結果をリクエストにセット
			req.setAttribute("testlist", testlist);
			req.setAttribute("subject_name", subject_name);
		}
		// ▼ 一部条件が不足している場合はエラーメッセージを表示
		else {
			errors.put("a", "入学年度とクラスと科目と回数を選択してください");
			req.setAttribute("errors", errors);
		}

		// ▼ 入力情報やリスト類を画面に渡すためにリクエストに保存
		req.setAttribute("f1", entYear);         // 入学年度（再表示用）
		req.setAttribute("f2", classNum);        // クラス番号（再表示用）
		req.setAttribute("f3", subject);         // 科目コード（再表示用）
		req.setAttribute("f4", count);           // 回数（再表示用）
		req.setAttribute("entYearList", entYearSet); // 入学年度リスト
		req.setAttribute("cNumList", cNumlist);      // クラスリスト
		req.setAttribute("list", list);              // 科目リスト
		req.setAttribute("countList", countSet);     // 回数リスト

		// ▼ JSPへ画面遷移（表示するためにフォワード）
		req.getRequestDispatcher("test_regist.jsp").forward(req , res);
	}
}

<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Teacher" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>得点管理システム</title>
    <%-- 実際のCSSファイルへのパスに置き換えてください --%>
    <link rel="stylesheet" href="path/to/your/styles.css">
    <style>
        /* 簡単なスタイリング例 (必要に応じて調整・削除してください) */
        body { font-family: Arial, sans-serif; margin: 0; }
        header { background-color: #f0f0f0; padding: 10px 20px; display: flex; justify-content: space-between; align-items: center; }
        header h1 { margin: 0; font-size: 1.5em; }
        header p { margin: 0; }
        .main-content { display: flex; }
        nav { width: 200px; background-color: #f8f8f8; padding: 15px; }
        nav ul { list-style-type: none; padding: 0; margin: 0; }
        nav ul li { margin-bottom: 10px; }
        nav ul ul { margin-top: 5px; padding-left: 15px; }
        main { flex-grow: 1; padding: 20px; }
        main h2 { margin-top: 0; border-bottom: 1px solid #ccc; padding-bottom: 10px; }
        .form-panel { background-color: #f9f9f9; padding: 20px; border: 1px solid #ddd; margin-top:10px;}
        .form-panel div { margin-bottom: 15px; }
        .form-panel label { display: inline-block; width: 100px; } /* ラベル幅の調整 */
        .form-panel input[type="text"] { width: 250px; padding: 5px; } /* 入力フィールド幅の調整 */
        .form-panel button { padding: 8px 15px; margin-right: 10px; }
        footer { background-color: #333; color: white; text-align: center; padding: 10px 0; position: fixed; bottom: 0; width: 100%;}
    </style>
</head>
<body>
    <%-- ヘッダー --%>
    <header>
        <h1>得点管理システム</h1>
        <%
            Teacher teacher = (Teacher)session.getAttribute("user");
            String teacherName = "";
            if (teacher != null) {
                teacherName = teacher.getName(); // bean.Teacher に getName() があると仮定
            }
        %>
        <p><%= teacherName %> <a href="Logout.action">ログアウト</a></p>
    </header>

    <div class="main-content">
        <%-- 左側メニュー --%>
        <nav>
            <ul>
                <li><a href="Menu.action">メニュー</a></li>
                <li><a href="StudentList.action">学生管理</a></li>
                <li>成績管理
                    <ul>
                        <li><a href="TestRegist.action">成績登録</a></li>
                        <li><a href="TestList.action">成績参照</a></li>
                    </ul>
                </li>
                <li>科目管理
                    <ul>
                        <li><a href="SubjectList.action">科目一覧</a></li>
                        <li><a href="SubjectCreate.action">科目登録</a></li>
                    </ul>
                </li>
            </ul>
        </nav>

        <%-- メインコンテンツ --%>
        <main>
            <%-- ① 科目情報登録 (タイトル) --%>
            <h2>科目情報登録</h2>

            <div class="form-panel"> <%-- 画面設計図のパネルのような部分を表現するためのdiv --%>
                <%
                    String error = (String)request.getAttribute("error");
                    if (error != null && !error.isEmpty()) {
                %>
                    <p style="color:red;"><%= error %></p>
                <%
                    }
                %>
                <%
                    String subjectCodeError = (String)request.getAttribute("subject_code_error");
                    if (subjectCodeError != null && !subjectCodeError.isEmpty()) {
                %>
                    <p style="color:red;">科目コード：<%= subjectCodeError %></p>
                <%
                    }
                %>
                <%
                    String subjectNameError = (String)request.getAttribute("subject_name_error");
                    if (subjectNameError != null && !subjectNameError.isEmpty()) {
                %>
                    <p style="color:red;">科目名：<%= subjectNameError %></p>
                <%
                    }
                %>

                <%
                    String subjectCdInput = (String)request.getAttribute("subject_cd_input");
                    if (subjectCdInput == null) {
                        subjectCdInput = "";
                    }
                    String subjectNameInput = (String)request.getAttribute("subject_name_input");
                    if (subjectNameInput == null) {
                        subjectNameInput = "";
                    }
                %>
             <form action="SubjectCreateExecute.action" method="post">
                    <div>
                        <%-- ② 科目コード (ラベル) --%>
                        <label for="subject_cd">科目コード</label>
                        <%-- ③ 科目コード (入力欄) --%>
                        <input type="text" id="subject_cd" name="subject_cd" value="<%= subjectCdInput %>" required maxlength="3" placeholder="科目コードを入力してください">
                    </div>
                    <div>
                        <%-- ④ 科目名 (ラベル) --%>
                        <label for="subject_name">科目名</label>
                        <%-- ⑤ 科目名 (入力欄) --%>
                        <input type="text" id="subject_name" name="subject_name" value="<%= subjectNameInput %>" required placeholder="科目名を入力してください">
                    </div>
                    <div>
                        <%-- ⑥ 登録 (ボタン) --%>
                        <button type="submit" name="save">登録</button>
                        <%-- ⑦ 戻る (ボタン) --%>
                        <button type="button" onclick="location.href='SubjectList.action'">戻る</button>
                    </div>
                </form>
            </div>
        </main>
    </div>

    <%-- フッター --%>
    <footer>
        <p>&copy; 2023 TIC 大原学園</p>
    </footer>
</body>
</html>
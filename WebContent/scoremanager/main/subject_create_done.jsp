<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Teacher" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>得点管理システム</title>
    <%-- 実際のCSSファイルへのパスに置き換えてください --%>
    <link rel="stylesheet" href="path/to/your/styles.css">
    <%-- subject_create.jsp と同じ簡易CSSを適用する場合 --%>
    <style>
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
        main h2 { margin-top: 0; }
        footer { background-color: #333; color: white; text-align: center; padding: 10px 0; position: fixed; bottom: 0; width: 100%;}
    </style>
</head>
<body>
    <%-- ヘッダー --%>
    <header>
        <h1>得点管理システム</h1>
        <%
            Teacher teacherDone = (Teacher)session.getAttribute("user");
            String teacherNameDone = "";
            if (teacherDone != null) {
                teacherNameDone = teacherDone.getName();
            }
        %>
        <p><%= teacherNameDone %> <a href="Logout.action">ログアウト</a></p>
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
            <h2>科目登録完了</h2>
            <p>科目を登録しました。</p>
            <p><a href="SubjectCreate.action">続けて科目を登録する</a></p>
            <p><a href="SubjectList.action">科目一覧へ戻る</a></p>
        </main>
    </div>

    <%-- フッター --%>
    <footer>
        <p>&copy; 2023 TIC 大原学園</p>
    </footer>
</body>
</html>
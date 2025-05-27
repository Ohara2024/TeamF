<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
  .side-menu-container {
    width: 180px;
    background-color: rgba(0, 0, 0, 0.05);
    padding: 15px;
    font-family: "Yu Gothic", "游ゴシック", sans-serif;
  }

  .side-menu ul {
    list-style: none;
    padding-left: 0;
  }

  .side-menu ul li {
    margin-bottom: 15px;
  }

  .side-menu ul li a {
    color: #0040a0;
    text-decoration: none;
    font-size: 14px;
  }

  .side-menu ul li a:hover {
    text-decoration: underline;
  }

  /* Menu chính */
  .side-menu ul.menu-main li a {
    font-weight: bold;
    font-size: 16px;
    color: #a00000;
  }


  .side-menu ul.menu-sub li a {
    font-weight: normal;
    color: #000080;
    font-size: 13px;
    padding-left: 10px;
  }
</style>

<div class="side-menu-container">
  <div class="side-menu">
    <ul class="menu-main">
      <li><a href="/TeamF/main/menu.jsp">メニュー</a></li>
      <li><a href="">学生管理</a></li>
      <li>
        <a href="#">成績管理</a>
        <ul class="menu-sub">
          <li><a href="#">成績登録</a></li>
          <li><a href="/TeamF/testmanager/list">成績参照</a></li>
        </ul>
      </li>
      <li><a href="#">科目管理</a></li>
    </ul>
  </div>
</div>

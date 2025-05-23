<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
  .header-container {
    background-color: #d9e9f8;
    height: 80px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 20px;
    font-family: "Yu Gothic", "游ゴシック", sans-serif;
  }
  .header-title {
    font-size: 24px;
    font-weight: bold;
    color: #333;
  }
  .header-user {
    font-size: 14px;
    color: #333;
  }
  .header-user a {
    color: #3333cc;
    text-decoration: none;
    margin-left: 10px;
  }
  .header-user a:hover {
    text-decoration: underline;
  }
</style>

<div class="header-container">
  <div class="header-title">得点管理システム</div>
  <div class="header-user">
    矢島 翔様
    <a href="/TeamE/logout/logoutaction">ログアウト</a>
  </div>
</div>

<%-- メニュー画面のJSPファイル --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
	<%-- base.jspにパラメータを渡して共通レイアウトを利用 --%>

	<c:param name="title">
		得点管理システム
		<%-- ページタイトルとして「得点管理システム」を指定 --%>
	</c:param>

	<c:param name="scripts"></c:param>
		<%-- scriptsパラメータは空。追加のJavaScriptをここで指定可能 --%>

	<c:param name="content">
		<%-- ページのメインコンテンツ部分 --%>
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">メニュー</h2>
			<%-- メニュー見出し --%>

			<div class="row text-center px-4 fs-3 my-5">
				<%-- Bootstrapのグリッドを使って横並びのメニューを作成 --%>

				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #dbb;">
					<%-- 学生管理のリンク。背景色は薄いピンク系 --%>
					<a href="StudentList.action">学生管理</a>
				</div>

				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #bdb;">
					<%-- 成績管理の枠。背景色は薄い緑系 --%>
					<div>
						<div class="">成績管理</div>
						<div class="">
							<a href="TestRegist.action">成績登録</a>
							<%-- 成績登録画面へのリンク --%>
						</div>
						<div class="">
							<a href="TestList.action">成績参照</a>
							<%-- 成績参照画面へのリンク --%>
						</div>
					</div>
				</div>

				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #bbd;">
					<%-- 科目管理のリンク。背景色は薄い青系 --%>
					<a href="SubjectList.action">科目管理</a>
				</div>

				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #ddb;">
					<%-- クラス管理のリンク。背景色は薄いオレンジ系 --%>
					<a href="ClassList.action">クラス管理</a>
				</div>
			</div>
		</section>
	</c:param>
</c:import>

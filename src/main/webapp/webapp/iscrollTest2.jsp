<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>iScroll demo: simple</title>
<link rel="stylesheet" href="assets/css/iscroll2.css" />
</head>
<body onload="loaded()">
	<header>iScroll</header>

	<div id="wrapper">

		<div id="scroller">

			<div id="pullDown">

				<span>下拉刷新…</span>

			</div>

			<ul id="list">

				<li>llll 1</li>

				<li>llll 2</li>

				<li>llll 3</li>

				<li>llll 4</li>

				<li>llll 5</li>

				<li>llll 6</li>

				<li>llll 7</li>

				<li>llll 8</li>

				<li>llll 9</li>

				<li>llll 10</li>

				<li>llll 11</li>

				<li>llll 12</li>

				<li>llll 13</li>

				<li>llll 14</li>

				<li>llll 15</li>

				<li>llll 16</li>

				<li>llll 17</li>

				<li>llll 18</li>

				<li>llll 19</li>

				<li>llll 20</li>

				<li>llll 21</li>

				<li>llll 22</li>

				<li>llll 23</li>

				<li>llll 24</li>

				<li>llll 25</li>

				<li>llll 26</li>

				<li>llll 27</li>

				<li>llll 28</li>

			</ul>

			<div id="pullUp">
				<span>上拉加载更多…</span>
			</div>
		</div>
	</div>
	<div id="spinner">
		<div></div>
		<div></div>
	</div>
	<script type="text/javascript" src="assets/js/iscroll-probe.js"></script>
	<script type="text/javascript" src="assets/js/iscrollTest2.js"></script>
</body>
</html>
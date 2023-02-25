<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<body>
	<h2>Jersey RESTful Web Application!</h2>
	<p>ボタンをクリックするとAPIをコールします。</p>

	<form>
		<input type="button" value="GET" onClick="callGet()">
		<input	type="button" value="POST" onClick="callPost()">
	</form>

	<div id="result"></div>

	<script>
		/**
		 * XMLHttpRequestによるGETメソッドを実行します
		 */
		function callGet() {
			var url = '<%=request.getContextPath()%>/webapi/myresource';
			var param = '?id=100'

			let request = new XMLHttpRequest();

			request.onreadystatechange = function() {
				if (request.readyState == 4) {
					if (request.status == 200) {
						let node = document.getElementById("result");
						node.innerHTML = request.responseText;
					}
				}
			}

			request.open("GET", url + param, true);
			request.send(null);
		}

		/**
		 * XMLHttpRequestによるPOSTメソッドを実行します
		 */
		function callPost() {
			var url = '<%=request.getContextPath()%>/webapi/myresource';
			var data = {
				id : "200",
				name : "tanaka",
			}
			var postData = JSON.stringify(data);

			let request = new XMLHttpRequest();

			request.onreadystatechange = function() {
				if (request.readyState == 4) {
					if (request.status == 200) {
						let node = document.getElementById("result");
						node.innerHTML = request.responseText;
					}
				}
			}

			request.open("POST", url, true);
			request.setRequestHeader('Content-Type', 'application/json');
			request.send(postData);
		}
	</script>

</body>
</html>

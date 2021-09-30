<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>RTMP RSO Stats</title>
	</head>
	<body>

		<table border="1">
			<tr>
				<th>RSO Name</th>
				<th>Version</th>
			</tr>
			<#list rsoStats as stat>
			<tr>
				<td>${stat.rsoName}</td>
				<td align="right">${stat.version}</td>
			</tr>
			</#list>

		</table>
	</body>
</html>
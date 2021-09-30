<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>RTMP Connection Stats</title>
	</head>
	<body>

		<table border="1">
			<tr>
				<th>IP Address</th>
				<th>Incoming Traffic (Bytes)</th>
				<th>Outgoing Traffic (Bytes)</th>
			</tr>
			<#list summary.stats as stat>
			<tr>
				<td>${stat.remoteAddress}</td>
				<td align="right">${stat.readBytes}</td>
				<td align="right">${stat.writtenBytes}</td>
			</tr>
			</#list>
			<tr>
			<th>Total</th>
			<th align="right">${summary.totalIncomingTraffic} </th>
			<th align="right">${summary.totalOutgoingTraffic} </th>
			</tr>

		</table>
	</body>
</html>
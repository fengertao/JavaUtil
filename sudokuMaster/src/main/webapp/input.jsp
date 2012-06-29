<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>input</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<Script>
function checkInput(value) {
	if (value>=0 && value<=9) {
		return true;
	} else {
		alert("Wrong Input, must between 0 and 9");
		return false;
	}
}

function splitAll(value) {
	for ( var r = 0; r < 9; r++) {
		for ( var c = 0; c < 9; c++) {
			var char = value.charAt(r*9+c);
			if (char=="0") char="";
			var cellId = "cell"+r+c;
			document.all[cellId].value=char;
		}
		
	}
}

</Script>
</head>
<body onload="document.all['all'].focus();document.all['all'].select()">
<form action="acceptInput.jsp">
<Table >
	<% for (int r=0; r<9; r++) {%>
	<TR >
		<% for (int c=0; c<9; c++) {
			String color = "blue";
			if ((r/3+c/3)%2==0) {color = "red";}
		%>
			<TD bgcolor="<%=color %>">
				<input type="text" name="<%= "cell"+r+c %>" size="1" maxlength="1">
			</TD>
			
		<% } %>
	</TR>
	<% } %>
</Table>
<Table>
	<TR>
	<TD colspan="9">
		<!-- pass:=000000018948007050000008020053702000009000000000901430090600000030500876060000000 -->
		<!-- 100:=460001000002096000030000068000000037000607000510000000840000050000710900000300024-->
		<!-- 77:=750090046901000302000000000200601007080000020100308005000000000309000204840030079-->
		<!-- 99:=300100004000000090002009030100200700003000800008006001010400200050000000400007008-->
		<!-- 97, must imply yusanshu:=030000740000000080000485620200009000060104030000800009096731000020000000017000060 -->
		<!-- pass (20~30min 3021912511):=002100000060000750010008004009010008000304000100090500600200070058000040000009300 -->
		<!-- questionToBlogMaster, seems error:=910070080004900020300000000000000002000000100000800300000000068002500004000420900-->
		<!-- (20~30min 4151554541):=010000052204000397070302014007060200000509100006020900080407520705000401040000730 -->
		<!-- (Champion test):= 000750000030048020100000006040000008790000031200000070500000007080320040000069000 -->
		
		<input type="text" name="all" value="010000052204000397070302014007060200000509100006020900080407520705000401040000730" onblur="splitAll(this.value)"  size=100 maxlength=81>
	</TD>
	</TR>
</Table>
<input type="submit" >
</form>

</body>
</html>
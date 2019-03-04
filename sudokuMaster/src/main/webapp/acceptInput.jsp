<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ page import="charlie.feng.game.sudoku.Matrix" %>
<%@ page import="charlie.feng.game.sudoku.SodukuMaster" %>
<%@ page import="charlie.feng.game.sudoku.TraceableBoolean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page
        language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" %>


<% Matrix matrix = new Matrix();
    matrix.initial();
    matrix.acceptInput(request);
    for (int k = 0; k < 9; k++) {
        matrix.cells[2][5].candidates[k].traceFlag = true;
    }
    new SodukuMaster().play(matrix);
%>


<html>
<head>
    <title>input</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script>
        function showCandidate(value) {
            document.all["candidate"].value = value;
        }
        function clearCandidate(value) {
            document.all["candidate"].value = "";
        }

    </script>
</head>
<body>
<form action="acceptInput.jsp">
    <Table>
        <TR>
            <TD colspan="9">
                <%=matrix.result %> <a href="input.jsp"> Another Game?</a>
            </TD>
        </TR>
        <TR>
            <TD colspan="9">
                <input readonly="readonly" type="text" name="candidate" size="20">
                <% for (int r = 0; r < 9; r++) {%>
            </TD>
        </TR>
        <TR>
            <% for (int c = 0; c < 9; c++) {
                String color = "blue";
                if ((r / 3 + c / 3) % 2 == 0) {
                    color = "red";
                }

            %>
            <TD bgcolor="<%=color %>">
                <%
                    Integer iValue = (matrix.cells[r][c]).getValue();
                    String sValue = "";
                    if (iValue != null) sValue = iValue.toString();
                    String candidateStr = "";
                    TraceableBoolean[] flag = matrix.cells[r][c].candidates;
                    for (int k = 0; k < 9; k++) {
                        if (flag[k].getValue()) {
                            candidateStr += k + 1;
                        } else {
                            candidateStr += " ";
                        }
                    }
                %>
                <input type="text" name="<%= "cell"+r+c %>" value="<%=sValue%>" size="1" maxlength="1"
                       onmouseover='showCandidate("<%=candidateStr %>")' onmouseout="clearCandidate()">
            </TD>
            <% } %>
        </TR>

        <% } %>

    </Table>
    <input type="submit">

</form>

</body>
</html>
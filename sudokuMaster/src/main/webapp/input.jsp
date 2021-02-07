<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page
        language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" %>
<html>
<head>
    <title>input</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <Script>
        function checkInput(value) {
            if (value >= 0 && value <= 9) {
                return true;
            } else {
                alert("Wrong Input, must between 0 and 9");
                return false;
            }
        }

        function splitAll(value) {
            for (var r = 0; r < 9; r++) {
                for (var c = 0; c < 9; c++) {
                    var char = value.charAt(r * 9 + c);
                    if (char == "0") char = "";
                    var cellId = "cell" + r + c;
                    document.all[cellId].value = char;
                }

            }
        }

    </Script>
</head>
<body onload="document.all['all'].focus();document.all['all'].select()">
<form action="acceptInput.jsp">
    <Table>
        <% for (int r = 0; r < 9; r++) {%>
        <TR>
            <% for (int c = 0; c < 9; c++) {
                String color = "blue";
                if ((r / 3 + c / 3) % 2 == 0) {
                    color = "red";
                }
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
                <!-- unresolved:= 500300182018250397002080456080000039120030800003800000294503608007098520851062903 -->

                <!-- http://www.sudokufans.org.cn/forums/topic/42/ -->
                <!-- http://www.sudokufans.org.cn/forums/topic/69/ -->
                <!--
                001002300043000002050100000000006020400257003070800000000003080800000750005600100
                001002030004000056020740000040300500000050000008007090000019080590000600010200400
                123000040005060070000008000000006003600437002400100000000800000040020600010000327
                001200000034000050000050306700000020005684700040000003502070000070000210000001400
                030009050010000804700002000000006090005837400080900000000100009103000040020700010
                002010000040500360706300500000100000500030007000004000004001203061003040000020800
                000300504007000060015002000009028006000010000500490700000500680060000300802006000
                009400007000070000800002096001000529000040000657000300120800004000090000500006800
                010000079600000003009065000800700090000854000050003008000610900700000002180000030
                000001003004800500016040000280400000500080006000002097000070980008009100600500000
                000000005090400002675100000003009400900040007001600200000003594200005060100000000
                004002100000500030030040090400200080300090007070008009080030010090001000002900800
                000008004409000800006300590800050000040090060000060007095007200004000306600400000
                010000300000509082000600190400300050000040000030008007076004000540107000008000030
                080006010500803000090040500402000000900010008000000905005060040000204001060900030
                -->
                <h4>&nbsp;</h4>
                <h4>Please input matrix into below inputbox, "0" stand for empty cell. Click "Submit" for auto
                    resolve.</h4>
                <input type="text" name="all"
                       value="010000052204000397070302014007060200000509100006020900080407520705000401040000730"
                       onblur="splitAll(this.value)" size=100 maxlength=81>
            </TD>
        </TR>
    </Table>
    <input type="submit">
</form>

</body>
</html>
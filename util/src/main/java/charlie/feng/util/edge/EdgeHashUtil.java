package charlie.feng.util.edge;

public class EdgeHashUtil {

    public static void main(String[] args) {

        String acct = "1820682070420910223:MM16_EBAY";

        String schema = new EdgeHashUtil().getDBSchemaByAcct(acct);
        System.out.println("Acct :" + acct);
        System.out.println(schema);

    }

    public String getDBSchemaByAcct(String account) {
        account = account.trim().replaceAll(",", "").trim();
        long dbSchemaSeq = new JenkinsHash().hash(account.getBytes()) % 24;
        long dbSeq = dbSchemaSeq / 8;
        if (dbSchemaSeq % 8 > 3) {
            dbSeq += 3;
        }
        return String.format("Schema IDI%02dDBA in Database DR%02d", dbSchemaSeq, dbSeq);
    }
}




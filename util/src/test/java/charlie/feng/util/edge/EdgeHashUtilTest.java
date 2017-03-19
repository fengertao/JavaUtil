package charlie.feng.util.edge;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jfeng1 on 7/20/2016.
 */
public class EdgeHashUtilTest {
    @Test public void getDBSchemaByAcct() throws Exception {
        String schema = new EdgeHashUtil().getDBSchemaByAcct("                           1,187,861,537,802,221,537");
        assertEquals("DB Schema and database should correct", "Schema IDI06DBA in Database DR00", schema);
    }

}
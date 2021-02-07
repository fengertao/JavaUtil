/**
 * Copyright (c) CITIBANK
 * All rights reserved.
 * <p>
 * This software is the confidential and proprietary information
 * of CITIBANK. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license
 * agreement you entered into with CITIBANK.
 */
package charlie.feng.util.debug;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Charlie Feng
 * Created on Nov 18, 2004
 */
@SuppressWarnings("rawtypes")
public class CollectionShower {

    public static void show(List col) {
        for (Object element : col) {
            System.out.println(element.toString());
        }
    }

    public static void show(Set col) {
        for (Object element : col) {
            System.out.println(element.toString());
        }
    }

    public static void show(Map col) {
        for (Object o : col.entrySet()) {
            Map.Entry element = (Map.Entry) o;
            System.out.println(element.getKey().toString() + "  :  " + element.getValue().toString());
        }
    }

    public static void show(Map col, String colName) {
        System.out.println("======== start output " + colName + "========");
        show(col);
        System.out.println("========  End output " + colName + " ========");
    }

    public static void show(List col, String colName) {
        System.out.println("======== start output " + colName + "========");
        show(col);
        System.out.println("======== End output " + colName + "========");
    }

    public static void show(Set col, String colName) {
        System.out.println("======== start output " + colName + "========");
        show(col);
        System.out.println("======== End output " + colName + "========");
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Map m = new HashMap();
        m.put("111", "222");
        m.put("1", "2");
        show(m, "m");
    }
}

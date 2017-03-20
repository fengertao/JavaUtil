/**
 * This source code is a component of Charlie Feng's source library,
 * All copyright reverved.
 *
 * @author Charlie Feng (Fengertao@hotmail.com)
 */
package charlie.feng.xml.XML2Table;

/**
 * Acturely this class should name as Columns, because it represent multi column
 * for example: sn = 50, desc=Traits, count=2 means
 * the 50th column is the first trait
 * the 51st column is the second trait. 
 *
 */

public class Column {

    protected int sn;            // the sn of first column
    protected String desc;        // the column desc
    protected int length;        // the column length, I hard code it to 50 for lazy reason.
    protected int count;        // how many column define in it

    public Column(int sn, String desc, int length) {
        this(sn, desc, length, 1);
    }

    public Column(int sn, String desc, int length, int count) {
        this.sn = sn;
        this.desc = desc;
        this.length = length;
        this.count = count;
    }

    /**
     * @return
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @return
     */
    public int getLength() {
        return length;
    }

    /**
     * @param i
     */
    public void setLength(int i) {
        length = i;
    }

    /**
     * @return
     */
    public int getSn() {
        return sn;
    }

    /**
     * @return
     */
    public int getCount() {
        return count;
    }

}

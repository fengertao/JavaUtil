/**
 * This source code is a component of Charlie Feng's source library,
 * All copyright reverved.
 * @author Charlie Feng (Fengertao@hotmail.com)
 */
package charlie.feng.xml.XML2Table.sax;

/**
 * <P>
 * <P>
 * <PRE>
 * AUTHOR    DATE     VERSION NO. REVISION HISTORY  
 * --------- -------- ----------- ------------------
 * Charlie.F          1.0
 * (limit to 5 latest entries) 
 * </PRE>
 *
 * @author Charlie Feng
 */
public class LevelInfo {
	int level;
	int sibling = 0;

	/**
	 * @return
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return
	 */
	public int getSibling() {
		return sibling;
	}

	/**
	 * @param i
	 */
	public void setLevel(int i) {
		level = i;
	}

	/**
	 * @param i
	 */
	public void setSibling(int i) {
		sibling = i;
	}

}

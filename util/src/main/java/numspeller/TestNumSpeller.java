/*
 * Created on May 17, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package numspeller;

/**
 * @author jf25468
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestNumSpeller {

	public static void main(String[] args) {
		String[] numbers= new String[] {
			"-1234567890.12",
			"-100000012",
			"-8765432",
			"-765432",
			"-65432",
			"-5432",
			"-432",
			"-32",
			"-2",
			"-2.00",
			"-0.15",
			"0",
			"0.00",
			"0.01",
			"0.11",
			"0.21",
			"0.91",
			"1.11",
			"1.21",
			"1.91",
			"1",
			"2",
			"3",
			"4",
			"5",
			"6",
			"7",
			"8",
			"9",
			"10",
			"11",
			"12",
			"13",
			"14",
			"15",
			"16",
			"17",
			"18",
			"19",
			"20",
			"21",
			"22",
			"23",
			"24",
			"25",
			"26",
			"27",
			"28",
			"29",
			"30",
			"30.00",
			"30.01",
			"30.11",
			"100",
			"200",
			"201",
			"1234567890.12",
			"234567890.12",
			"34567890.12",
			"4567890.12",
			"567890.12",
			"67890.12",
			"7890.12",
			"890.12",
			"90.12",
		};
		
		NumSpeller__ sp = new NumSpellerFR();
		for (int i = 0; i < numbers.length; i++) {
			String number = numbers[i];
			System.out.println(number + " represent as " + sp.leer(number));
			
		}
	}
}

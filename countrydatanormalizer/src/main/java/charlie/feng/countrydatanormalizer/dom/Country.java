package charlie.feng.countrydatanormalizer.dom;

import charlie.feng.countrydatanormalizer.App;

public class Country {

//	public Map<String, Object> distinctAttrMap = new HashMap<String, Object>();
	// public Map<String, Object> yeardAttrMap = new HashMap<String, Object>();
	public CountryYearData[] rows;

	public Country() {
		int numberOfYear = App.END_YEAR - App.START_YEAR + 1;
		rows = new CountryYearData[numberOfYear];
		                                             
		for (int i = 0; i < numberOfYear; i++) {
			rows[i] = new CountryYearData();
			rows[i].parent = this;
			rows[i].year = App.START_YEAR + i;
		}
	}

}

package charlie.feng.tools.excel.dom;

import charlie.feng.tools.excel.PoiConverter;


public class Country {

	public Object filter0;
	public Object filter1;
	public Object filter2;
	public Object filter3;
	public String country_Zh;
	public String country_En;
	public Object dist;
	public Object lang;
	
	public DenormRow[] rows = new DenormRow[PoiConverter.END_YEAR - PoiConverter.START_YEAR + 1];
	
	public Country() {
		for (int i=0; i<rows.length; i++) {
			rows[i] = new DenormRow();
			rows[i].parent=this;
		}
	}

}

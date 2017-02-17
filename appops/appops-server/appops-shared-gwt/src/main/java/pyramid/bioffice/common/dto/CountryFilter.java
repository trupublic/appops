package pyramid.bioffice.common.dto;

import java.io.Serializable;

import jsinterop.annotations.JsType;

@SuppressWarnings("serial")
@JsType (namespace = "JsHost")
public class CountryFilter implements Serializable {
		
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	private int count ; 
	
}

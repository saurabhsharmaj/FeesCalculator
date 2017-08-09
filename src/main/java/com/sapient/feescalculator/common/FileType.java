package com.sapient.feescalculator.common;

/**
 * 
 * @author Saurabh
 *
 */
public class FileType {
	public enum FILETYPE {
		EXCEL(0), CSV(1), XML(2), TEXT(3);
		int type;

		FILETYPE(int type) {
			this.type = type;
		}

		public boolean getValue() {
			return false;
		}
	}
}

package org.mobiledl.dlverifier;

public class GeorgiaLicenseParser {
	static final String DL_TAG = "DAQ";
	static final int DL_LENGTH = 9;
	public static String getDLNumber(String contents) {
		int start = contents.indexOf(DL_TAG)+DL_TAG.length();
		int end = start + DL_LENGTH;
		return contents.substring(start, end);
	}
}

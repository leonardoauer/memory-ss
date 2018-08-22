package com.memory.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

/**
 * Utility methods for use with Strings
 */
public class StringUtil {

	private StringUtil() {
	}

	/**
	 * Encodes a string using HTML form encoding in UTF-8
	 * 
	 * @param stringToEncode
	 *            a <code>String</code> to encode
	 * @return returns the encoded <code>String</code>
	 * @throws <code>UnsupportedEncodingException</code> when the string given
	 *         is not encoded in UTF-8
	 */
	public static String encodeString(String stringToEncode) throws UnsupportedEncodingException {
		return URLEncoder.encode(stringToEncode, "UTF-8");
	}

	/**
	 * Decodes a string using HTML form encoding in UTF-8
	 * 
	 * @param stringToDecode
	 *            a <code>String</code> to decode
	 * @return returns the decoded <code>String</code>
	 * @throws <code>UnsupportedEncodingException</code> when the string given
	 *         is not encoded in UTF-8
	 */
	public static String decodeString(String stringToDecode) throws UnsupportedEncodingException {
		return URLDecoder.decode(stringToDecode, "UTF-8");
	}

	/**
	 * Replaces all special character in a string with their unicode codes. This
	 * function is incomplete so feel free to add in more rules as necessary.
	 * 
	 * @param string
	 *            a <code>String</code> of the content containing characters to
	 *            replace.
	 * @return a <code>String</code> of the content with replaced characters.
	 */
	public static String escapeAllSpecialCharacters(String string) {
		string = string.replaceAll("é", "\\\\" + "u00e9");
		string = string.replaceAll("á", "\\\\" + "u00e1");
		string = string.replaceAll("£", "\\\\" + "u00a3");
		return string;
	}

	/**
	 * Checks to see if the string is <code>null</code>, contains the string
	 * 'null' or is empty.
	 * 
	 * @param string
	 *            the <code>String</code> to perform the check on
	 * @return <code>true</code> if the string is <code>null</code>, contains
	 *         'null' or is an empty string;
	 */
	public static boolean isNullOrEmpty(String string) {

		if (string == null) {
			return true;
		}

		if (string.equals("null") || string.isEmpty()) {
			return true;
		}

		return false;
	}

	/**
	 * Splits a text by the length passed as parameter
	 * @param str
	 * @param length
	 * @return
	 */
	public static String[] splitByLength(final String str, int length) {
		
		if(StringUtils.isEmpty(str)) {
			return null;
		}
		
		return str.split(String.format("(?<=\\G.{%d})", length));		
	}

}

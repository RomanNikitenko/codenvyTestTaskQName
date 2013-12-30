package com.codenvy.testtask.qname;

public class Parser {

	private static final String oneCharSimpleName_RegExp = "[^./:\\[\\]*'\"|\\s]";
	private static final String nonSpace_RegExp = "[^/:\\[\\]*'\"|\\s]";

	private static final String XMLNameStartChar = ":_A-Za-z\\u00C0-\\u00D6\\u00D8-\\u00F6\\u00F8-\\u02FF" +
			"\\u0370-\\u037D\\u037F-\\u1FFF\\u200C-\\u200D\\u2070-\\u218F\\u2C00-\\u2FEF\\u3001-\\uD7FF" +
			"\\uF900-\\uFDCF\\uFDF0-\\uFFFD\\u10000-\\uEFFFF";
	
	private static final String XMLNameChar = XMLNameStartChar + "\\-.0-9\\u00B7\\u0300-\\u036F\\u203F-\\u2040";
	
	private static final String prefix_RegExp = "[" + XMLNameStartChar + "][" + XMLNameChar +"]*";

	
	private boolean correspondSimpleName(String simpleName) {
		
		switch (simpleName.length()) {
		
		case 0:
			return false;
		case 1:
			return correspondOneCharSimpleName(simpleName);
		case 2:
			return correspondTwoCharSimpleName(simpleName);
		default:
			return correspondThreeOrMoreCharName(simpleName);
		}
	}

	private boolean correspondOneCharSimpleName(String oneCharSimpleName) {
		return oneCharSimpleName.matches(oneCharSimpleName_RegExp);
	}

	private boolean correspondTwoCharSimpleName(String twoCharSimpleName) {

		boolean retVal = false;

		String firstChar =  twoCharSimpleName.substring(0, 1);
		String secondChar = twoCharSimpleName.substring(1, 2);

		if (firstChar.equals(".") && correspondOneCharSimpleName(secondChar)) {
			retVal = true;
		} else if (correspondOneCharSimpleName(firstChar)
				&& secondChar.equals(".")) {
			retVal = true;
		} else if (correspondOneCharSimpleName(firstChar)
				&& correspondOneCharSimpleName(secondChar)) {
			retVal = true;
		}
		return retVal;
	}

	private boolean correspondThreeOrMoreCharName(String threeOrMoreCharName) {

		String firstChar = 			threeOrMoreCharName.substring(0, 1);
		String middleExpression = 	threeOrMoreCharName.substring(1, threeOrMoreCharName.length() - 1);
		String lastChar = 			threeOrMoreCharName.substring(threeOrMoreCharName.length() - 1, threeOrMoreCharName.length());

		return (correspondNonspace(firstChar)
				&& correspondString(middleExpression) && correspondNonspace(lastChar));
	}

	private boolean correspondChar(String charForCheck) {
		return (correspondNonspace(charForCheck) || charForCheck.equals(" "));
	}

	private boolean correspondString(String stringForCheck) {

		boolean retVal = true;

		String[] arrChar = stringForCheck.split("(?!^)");

		for (String str : arrChar) {
			if (!correspondChar(str)) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}

	private boolean correspondNonspace(String charForCheck) {
		return charForCheck.matches(nonSpace_RegExp);
	}
	
	private boolean correspondPrefixedName(String prefixedName) {

		/*
		 * use the method lastIndexOf, because localName CAN NOT contain a
		 * colon, and the prefix CAN contain a colon
		 */
		int indexColon = prefixedName.lastIndexOf(":");
		
		if (indexColon == (-1)) {
			return false;
		} else {

			String prefix = prefixedName.substring(0, indexColon);
			String localName = prefixedName.substring(indexColon + 1,
					prefixedName.length());

			return (correspondPrefix(prefix) && correspondLocalName(localName));
		}
	}

	private boolean correspondPrefix(String prefix) {
		return prefix.matches(prefix_RegExp);
	}

	private boolean correspondLocalName(String localName) {
		
		if (localName.length() == 0) {
			return false;
		} else if (localName.length() == 1) {
			return correspondOneCharLocalName(localName);
		} else if (localName.length() == 2) {
			return correspondTwoCharLocalName(localName);
		} else
			return correspondThreeOrMoreCharName(localName);
	}

	private boolean correspondOneCharLocalName(String oneCharLocalName) {
		return correspondNonspace(oneCharLocalName);
	}

	private boolean correspondTwoCharLocalName(String twoCharLocalName) {
		
		String firstChar = 	twoCharLocalName.substring(0, 1);
		String secondChar = twoCharLocalName.substring(1, 2);
		
		return (correspondNonspace(firstChar) && (correspondNonspace(secondChar)));
	}
}

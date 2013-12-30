package com.codenvy.testtask.qname;

public class Parser {

	private static final String oneCharSimpleName_RegExp = "[^./:\\[\\]*'\"|\\s]";
	private static final String nonSpace_RegExp = "[^/:\\[\\]*'\"|\\s]";
	
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
}

package com.codenvy.testtask.qname;
/**
* Description of Parser
* @author <a href="mailto:nikitenko.roman@gmail.com">Roman Nikitenko</a>
* @version Revision: 1.0 Date: 2013/12/31 
*/
public class Parser {
	
	/**
	 * Regular Expression for oneCharSimpleName
	 */
	private static final String oneCharSimpleName_RegExp = "[^./:\\[\\]*'\"|\\s]";
	
	/**
	 * Regular Expression for nonSpace
	 */
	private static final String nonSpace_RegExp = "[^/:\\[\\]*'\"|\\s]";

	/**
	 * Constant for prefix regular expression(XML-Name start char) 
	 */
	private static final String XMLNameStartChar = "_A-Za-z\\u00C0-\\u00D6\\u00D8-\\u00F6\\u00F8-\\u02FF" +
			"\\u0370-\\u037D\\u037F-\\u1FFF\\u200C-\\u200D\\u2070-\\u218F\\u2C00-\\u2FEF\\u3001-\\uD7FF" +
			"\\uF900-\\uFDCF\\uFDF0-\\uFFFD\\u10000-\\uEFFFF";
	
	/**
	 * Constant for prefix regular expression(XML-Name char) 
	 */
	private static final String XMLNameChar = XMLNameStartChar + "\\-.0-9\\u00B7\\u0300-\\u036F\\u203F-\\u2040";
	
	/**
	 * Regular Expressions for prefix
	 */
	private static final String prefix_RegExp = "[" + XMLNameStartChar + "][" + XMLNameChar +"]*";

	/**
	* <p>This method is parses name, creates and returns the new QName object</p>
    *
    * @param name  String - name for parse
    * 
    * @return QName object
    * 
    * @throws IllegalNameException When (correspondSimpleName(name) && 
    * (correspondPrefixedName(name) return 'false'
    */

	public QName parse(String name) throws IllegalNameException{

		if (correspondSimpleName(name)) {
			return new QName(name);
		} else if (correspondPrefixedName(name)) {

			int indexColon = name.indexOf(":");

			String prefix = getPrefixPartFromName(name, indexColon);
			String localName = getLocalNamePartFromName(name, indexColon);

			return new QName(prefix, localName, name);
		} else
			throw new IllegalNameException("The name '" + name
					+ "' is not valid");
	}
	/**
	 * This method checks if name corresponds 
	 * to the simple name or to the prefixed name
	 * @param name String - name for check
	 * @return true if name corresponds 
	 * to the simple name or to the prefixed name
	 */
	public boolean correspondName(String name){
		return (correspondSimpleName(name) || correspondPrefixedName(name));
	}
	
	/**
	 * This method checks if name corresponds 
	 * to the simple name
	 * @param simpleName String - name for check
	 * @return true if name corresponds 
	 * to the OneCharSimpleName or TwoCharSimpleName
	 * or ThreeOrMoreCharName
	 */
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
	
	/**
	 * This method checks if name corresponds 
	 * to the OneCharSimpleName
	 * @param oneCharSimpleName String - name for check
	 * @return true if name corresponds 
	 * to the OneCharSimpleName
	 */
	private boolean correspondOneCharSimpleName(String oneCharSimpleName) {
		return oneCharSimpleName.matches(oneCharSimpleName_RegExp);
	}
	
	/**
	 * This method checks if name corresponds 
	 * to the TwoCharSimpleName
	 * @param twoCharSimpleName String - name for check
	 * @return true if name corresponds ('.'oneCharSimpleName) or 
	 *  (oneCharSimpleName'.') or (oneCharSimpleName oneCharSimpleName)
	 */
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
	
	/**
	 * This method checks if name corresponds 
	 * to the ThreeOrMoreCharName
	 * @param ThreeOrMoreCharName String - name for check
	 * @return true if firstChar and lastChar corresponds 
	 * to the Nonspace & middleExpression corresponds to the String
	 */
	private boolean correspondThreeOrMoreCharName(String threeOrMoreCharName) {

		String firstChar = 			threeOrMoreCharName.substring(0, 1);
		String middleExpression = 	threeOrMoreCharName.substring(1, threeOrMoreCharName.length() - 1);
		String lastChar = 			threeOrMoreCharName.substring(threeOrMoreCharName.length() - 1, threeOrMoreCharName.length());

		return (correspondNonspace(firstChar)
				&& correspondString(middleExpression) && correspondNonspace(lastChar));
	}
	
	/**
	 * This method checks if name corresponds 
	 * to the Char
	 * @param charForCheck String - char for check
	 * @return true if char corresponds 
	 * to the Nonspace or equals(" ")
	 */
	private boolean correspondChar(String charForCheck) {
		return (correspondNonspace(charForCheck) || charForCheck.equals(" "));
	}
	
	/**
	 * This method checks if input String corresponds 
	 * to the 'String'
	 * @param stringForCheck String - string for check
	 * @return true if each char of input string corresponds 
	 * to the Char
	 */

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
	
	/**
	 * This method checks if charForCheck corresponds 
	 * to the Nonspace regex
	 * @param charForCheck String - char For Check
	 * @return true if char corresponds 
	 * to the Nonspace regex
	 */
	private boolean correspondNonspace(String charForCheck) {
		return charForCheck.matches(nonSpace_RegExp);
	}
	
	/**
	 * This method checks if name corresponds 
	 * to the prefixedName
	 * @param prefixedName String - name For Check
	 * @return true if prefix corresponds 
	 * to the 'Prefix' and localName corresponds 
	 * to the 'localName'
	 */
	private boolean correspondPrefixedName(String prefixedName) {

		int indexColon = prefixedName.indexOf(":");
		
		if (indexColon == (-1)) {
			return false;
		} else {

			String prefix = getPrefixPartFromName(prefixedName, indexColon);
			String localName = getLocalNamePartFromName(prefixedName, indexColon);
			
			return (correspondPrefix(prefix) && correspondLocalName(localName) );
		}
	}

	/**
	 * This method return Prefix Part From Name
	 * @param name String - name 
	 * @param indexColon int - position of colon
	 * @return Prefix - substring from 0 to position of colon
	 */
	private String getPrefixPartFromName(String name, int indexColon) {
		return name.substring(0, indexColon);
	}
	
	/**
	 * This method return LocalName Part From Name
	 * @param name String - name 
	 * @param indexColon int - position of colon
	 * @return localName - substring from indexColon + 1 to the end of String
	 */
	private String getLocalNamePartFromName(String name, int indexColon) {
		return name.substring(indexColon + 1, name.length());
	}

	/**
	 * This method checks if prefix part corresponds 
	 * to the prefix regex
	 * @param prefix String - prefix part for check 
	 * @return true if prefix part corresponds 
	 * to the prefix regex
	 */
	private boolean correspondPrefix(String prefix) {
		return prefix.matches(prefix_RegExp);
	}

	/**
	 * This method checks if localName part corresponds 
	 * to the localName
	 * @param localName String - localName part for check 
	 * @return true if localName part corresponds 
	 * to the 'OneCharLocalName' or 'TwoCharLocalName'
	 * or 'ThreeOrMoreCharName'
	 */

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

	/**
	 * This method checks if char corresponds 
	 * to the Nonspace
	 * @param oneCharLocalName String - char for check 
	 * @return true if char corresponds 
	 * to the 'Nonspace'
	 */
	private boolean correspondOneCharLocalName(String oneCharLocalName) {
		return correspondNonspace(oneCharLocalName);
	}
	
	/**
	 * This method checks if chars corresponds 
	 * to the TwoCharLocalName
	 * @param twoCharLocalName String - chars for check 
	 * @return true if chars corresponds 
	 * to the 'Nonspace'
	 */

	private boolean correspondTwoCharLocalName(String twoCharLocalName) {
		
		String firstChar = 	twoCharLocalName.substring(0, 1);
		String secondChar = twoCharLocalName.substring(1, 2);
		
		return (correspondNonspace(firstChar) && (correspondNonspace(secondChar)));
	}
}

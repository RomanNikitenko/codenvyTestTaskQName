package com.codenvy.testtask.qname;

/**
* Description of QName
* @author <a href="mailto:nikitenko.roman@gmail.com">Roman Nikitenko</a>
* @version Revision: 1.0 Date: 2013/12/31 
*/

public class QName 
{
	/**
	 * Prefix for input name
	 */
	private String prefix;
	
	/**
	 * localName for input name
	 */
	private String localName;

	/**
	 * Full Name
	 */
	private String fullName;
	
	/**
	 * QName constructor specifying the prefix, localName 
     * and fullName.
	 * @param prefix String - prefix of the QName 
	 * @param localName String - local part of the QName
	 * @param fullName - String - full name of the QName
	 */
	public QName(String prefix, String localName, String fullName) {
		this.prefix = 		prefix;
		this.localName = 	localName;
		this.fullName = 	fullName;
	}
	
	/**
	 * QName constructor specifying the localName.
	 * @param localName String - local part of the QName
	 */
	public QName(String localName) {
		this.prefix = 		"";
		this.localName = 	localName;
		this.fullName = 	localName;
	}
	/**
	* <p>Get the prefix of this QName</p>
    *  @return prefix of this QName
    */
	public String getPrefix() {
		return prefix;
	}
	
	/**
	* <p>Get the LocalName of this QName</p>
    *  @return LocalName of this QName
    */
	public String getLocalName() {
		return localName;
	}
	
	/**
	* <p>Get the fullName of this QName</p>
    *  @return fullName of this QName
    */
	public String getAsString() {
		return fullName;
	}
}

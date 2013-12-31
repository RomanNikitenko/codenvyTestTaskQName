package com.codenvy.testtask.qname;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
* Description of ParserTest
* @author <a href="mailto:nikitenko.roman@gmail.com">Roman Nikitenko</a>
* @version Revision: 1.0 Date: 2013/12/31 
*/
public class ParserTest 
{
	/**
	 * parser is object for test parse of name
	 */
	private Parser parser;
	
	/**
	 * qname is object for test creation of QName object
	 */
	private QName qname;
	
	@Before
	public void setup() throws IllegalNameException {
		parser = new Parser();
		qname = parser.parse("prefix:name");
	}
	
	/**
	 * Check of Parser object creation
	 * @result the Parser object is not null
	 */
	@Test
	public void testCreateObjParser() {
		assertNotNull(parser);
	}
	
	/**
	 * Check of QName object creation
	 * @result the Parser object is not null
	 */
	@Test
	public void testParse() {
		assertNotNull(qname);
	}
	
	/**
	 * Check the correct operation of parse()
	 * @result expected prefix = actual prefix
	 */
	@Test
	public void testGetPrefix() {
		String expected = "prefix";
		String actual 	= qname.getPrefix();
		assertEquals(expected, actual);
	}
	/**
	 * Check the correct operation of parse()
	 * @result expected localName = actual localName
	 */
	@Test
	public void testLocalName() {
		String expected = "name";
		String actual 	= qname.getLocalName();
		assertEquals(expected, actual);
	}
	/**
	 * Check the correct operation of parse()
	 * @result expected fullName = actual fullName
	 */
	@Test
	public void testGetAsString() {
		String expected = "prefix:name";
		String actual 	= qname.getAsString();
		assertEquals(expected, actual);
	}
	/**
	 * Check the correct operation of parse()
	 * @result throws IllegalNameException
	 */
	@Test(expected = IllegalNameException.class)
	public void testCallIllegalNameException() throws IllegalNameException {
		parser.parse("name/name");
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name matches a pattern
	 */
	@Test
	public void testCorrespondName1() {
		assertTrue(parser.correspondName("name"));
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name matches a pattern
	 */
	@Test
	public void testCorrespondName2() {
		assertTrue(parser.correspondName("prefix:name"));
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name matches a pattern
	 */
	@Test
	public void testCorrespondName3() {
		assertTrue(parser.correspondName("prefix:na me"));
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name not matches a pattern
	 */
	@Test
	public void testCorrespondName4() {
		assertFalse(parser.correspondName(""));
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name not matches a pattern
	 */
	@Test
	public void testCorrespondName5() {
		assertFalse(parser.correspondName(":name"));
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name not matches a pattern
	 */
	@Test
	public void testCorrespondName6() {
		assertFalse(parser.correspondName("."));
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name not matches a pattern
	 */
	@Test
	public void testCorrespondName7() {
		assertFalse(parser.correspondName(".."));
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name not matches a pattern
	 */
	@Test
	public void testCorrespondName8() {
		assertFalse(parser.correspondName("prefix:"));
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name not matches a pattern
	 */
	@Test
	public void testCorrespondName9() {
		assertFalse(parser.correspondName(" name"));
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name not matches a pattern
	 */
	@Test
	public void testCorrespondName10() {
		assertFalse(parser.correspondName(" prefix:name"));
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name not matches a pattern
	 */
	@Test
	public void testCorrespondName11() {
		assertFalse(parser.correspondName("prefix: name"));
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name not matches a pattern
	 */
	@Test
	public void testCorrespondName12() {
		assertFalse(parser.correspondName("prefix:name "));
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name not matches a pattern
	 */
	@Test
	public void testCorrespondName13() {
		assertFalse(parser.correspondName("pre fix:name"));
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name not matches a pattern
	 */
	@Test
	public void testCorrespondName14() {
		assertFalse(parser.correspondName("name/name"));
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name not matches a pattern
	 */
	@Test
	public void testCorrespondName15() {
		assertFalse(parser.correspondName("name[name"));
	}
	/**
	 * Check the correct operation of correspondName()
	 * @result name not matches a pattern
	 */
	@Test
	public void testCorrespondName16() {
		assertFalse(parser.correspondName("prefix:name:name"));
	}
	
}

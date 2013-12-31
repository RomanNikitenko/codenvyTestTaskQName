package com.codenvy.testtask.qname;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ParserTest 
{
	private Parser parser;
	private QName qname;
	
	@Before
	public void setup() throws IllegalNameException {
		parser = new Parser();
		qname = parser.parse("prefix:name");
	}
	
	@Test
	public void testCreateObjParser() {
		assertNotNull(parser);
	}
	
	@Test
	public void testParse() {
		assertNotNull(qname);
	}
	
	@Test
	public void testGetPrefix() {
		String expected = "prefix";
		String actual 	= qname.getPrefix();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLocalName() {
		String expected = "name";
		String actual 	= qname.getLocalName();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetAsString() {
		String expected = "prefix:name";
		String actual 	= qname.getAsString();
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalNameException.class)
	public void testCallIllegalNameException() throws IllegalNameException {
		parser.parse("name/name");
	}

	@Test
	public void testCorrespondName1() {
		assertTrue(parser.correspondName("name"));
	}
	
	@Test
	public void testCorrespondName2() {
		assertTrue(parser.correspondName("prefix:name"));
	}
	
	@Test
	public void testCorrespondName3() {
		assertTrue(parser.correspondName("prefix:na me"));
	}
	
	@Test
	public void testCorrespondName4() {
		assertFalse(parser.correspondName(""));
	}
	
	@Test
	public void testCorrespondName5() {
		assertFalse(parser.correspondName(":name"));
	}
	
	@Test
	public void testCorrespondName6() {
		assertFalse(parser.correspondName("."));
	}
	
	@Test
	public void testCorrespondName7() {
		assertFalse(parser.correspondName(".."));
	}
	
	@Test
	public void testCorrespondName8() {
		assertFalse(parser.correspondName("prefix:"));
	}
	
	@Test
	public void testCorrespondName9() {
		assertFalse(parser.correspondName(" name"));
	}
	
	@Test
	public void testCorrespondName10() {
		assertFalse(parser.correspondName(" prefix:name"));
	}
	
	@Test
	public void testCorrespondName11() {
		assertFalse(parser.correspondName("prefix: name"));
	}
	
	@Test
	public void testCorrespondName12() {
		assertFalse(parser.correspondName("prefix:name "));
	}
	
	@Test
	public void testCorrespondName13() {
		assertFalse(parser.correspondName("pre fix:name"));
	}
	
	@Test
	public void testCorrespondName14() {
		assertFalse(parser.correspondName("name/name"));
	}
	
	@Test
	public void testCorrespondName15() {
		assertFalse(parser.correspondName("name[name"));
	}
	
	@Test
	public void testCorrespondName16() {
		assertFalse(parser.correspondName("prefix:name:name"));
	}
	
}


package st;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Task3_TDD_1 {

	private Parser parser;

	@Before
	public void set_up() {
		parser = new Parser();
	}

// Given as an example of working behaviour
	@Test
	public void exampleTest() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=e-ck.txt");
		List<Character> l = parser.getCharacterList("list");
		List<Character> testList = new ArrayList<Character>();
		testList.add('e');
		testList.add('d');
		testList.add('c');
		testList.add('k');
		testList.add('.');
		testList.add('t');
		testList.add('x');
		testList.add('t');
		assertEquals(l, testList);
	}

	// # SPEC TESTS

// 1

	@Test
	public void orderTest1() {
		parser.add("o", "option", Parser.STRING);
		parser.add("option", "o", Parser.STRING);
		parser.parse("--o=a --option=b");
		assertEquals("Name priority over shortcut", parser.getCharacterList("o"), "a");
	}

// 2 

	@Test
	public void noValueTest() {
		List<Character> emptyList = new ArrayList<Character>();
		assertEquals("Empty input should give empty list", parser.getCharacterList(""), emptyList);
	}

	@Test
	public void noValueTest2() {
		parser.add("o", "option", Parser.STRING);
		parser.add("option", "o", Parser.STRING);
		parser.parse("--o=a --option=b");
		List<Character> emptyList = new ArrayList<Character>();
		assertEquals("Empty input should give empty list", parser.getCharacterList(""), emptyList);
	}
	
	@Test
	public void noValueTest3() {
		parser.add("option", Parser.STRING);
		parser.add("option", "o", Parser.STRING);
		parser.parse("--o=a --option=b");
		List<Character> emptyList = new ArrayList<Character>();
		assertEquals("Empty input should give empty list", parser.getCharacterList(""), emptyList);
	}

// 3

	@Test
	public void illegalChars() {
		parser.add("option", Parser.STRING);
		parser.parse("--option=test123?.txt");
		List<Character> testList = new ArrayList<Character>();
		testList.add('t');
		testList.add('e');
		testList.add('s');
		testList.add('t');
		testList.add('1');
		testList.add('2');
		testList.add('3');
		testList.add('.');
		testList.add('t');
		testList.add('x');
		testList.add('t');
		assertEquals("may only contain letters, digits and full stops", parser.getCharacterList("option"), testList);
	}

	@Test
	public void illegalChars2() {
		parser.add("o", Parser.STRING);
		parser.parse("--o=@$@-ou13ut.txt");// this should return ou13ut.txt// because
		// this string does not start with hyphen(-)
		List<Character> testList = new ArrayList<Character>();
		testList.add('o');
		testList.add('u');
		testList.add('1');
		testList.add('3');
		testList.add('u');
		testList.add('t');
		testList.add('.');
		testList.add('t');
		testList.add('x');
		testList.add('t');
		assertEquals(parser.getCharacterList("o"), testList);
	}
	
	@Test
	public void illegalChars3() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=outp1-.5ut.txt");
		List<Character> testList = new ArrayList<Character>();
		testList.add('o');
		testList.add('u');
		testList.add('t');
		testList.add('p');
		testList.add('1');
		testList.add('.');
		testList.add('5');
		testList.add('u');
		testList.add('t');
		testList.add('.');
		testList.add('t');
		testList.add('x');
		testList.add('t');
		assertEquals("returns => outp1.5ut.txt",parser.getCharacterList("list"),testList);
	}

// 4 

	@Test
	public void caseTest1() {
		parser.add("value1", Parser.STRING);
		parser.add("value2", Parser.STRING);
		parser.parse("--value1=Test123.txt");
		parser.parse("--value2=test123.txt");
		assertNotSame(parser.getCharacterList("value1"), null);
		assertEquals("Case shouldn't matter", parser.getCharacterList("value1"), parser.getCharacterList("value2"));
	}
	
	@Test
	public void caseTest2() {
		parser.add("value1", "v1", Parser.STRING);
		parser.add("value2", "v2", Parser.STRING);
		parser.parse("-v1=aBaBBa.txt");
		parser.parse("-v2=AbAbbA.txt");
		assertNotSame(parser.getCharacterList("value1"), null);
		assertEquals("Case shouldn't matter", parser.getCharacterList("value1"), parser.getCharacterList("value2"));
	}

// 5

	@Test
	public void hyphenBad1() {
		parser.add("option", Parser.STRING);
		parser.parse("--option=-test123.txt");
		List<Character> emptyList = new ArrayList<Character>();
		assertEquals(parser.getCharacterList("option"), emptyList);
	}

	@Test
	public void hyphenBad2() {
		parser.add("option", Parser.STRING);
		parser.parse("--option=-t-e-s-t-1-2-3-.-t-x-t");
		List<Character> emptyList = new ArrayList<Character>();
		assertEquals(parser.getCharacterList("option"), emptyList); // This returns an empty list
	}

	@Test
	public void hyphenBad3() {
		parser.add("option", Parser.STRING);
		parser.parse("--option=-");
		List<Character> emptyList = new ArrayList<Character>();
		assertEquals(parser.getCharacterList("option"), emptyList);
	}

	@Test
	public void hyphenBad4() {
		parser.add("option", Parser.STRING);
		parser.parse("--option=-text-text-text");
		List<Character> emptyList = new ArrayList<Character>();
		assertEquals(parser.getCharacterList("option"), emptyList);
	}

	@Test
	public void hyphenBad5() {
		parser.add("option", Parser.STRING);
		parser.parse("--option=a- ");
		List<Character> emptyList = new ArrayList<Character>();
		assertEquals(parser.getCharacterList("option"), emptyList);
	}

	@Test
	public void hyphenBad6() {
		parser.add("option", Parser.STRING);
		parser.parse("--option=a- b");
		List<Character> emptyList = new ArrayList<Character>();
		assertEquals(parser.getCharacterList("option"), emptyList);
	}
	
	@Test 
	public void hyphenBad7() {
		parser.add("list", Parser.STRING);
		parser.parse("--list =test1-3-.txt");
		List<Character> testList = new ArrayList<Character>();
		testList.add('t');
		testList.add('e');
		testList.add('s');
		testList.add('t');
		testList.add('1');
		testList.add('2');
		testList.add('3');
		testList.add('.');
		testList.add('t');
		testList.add('x');
		testList.add('t');
		assertEquals("This should return[t, e, s, t, 1, 2, 3, ., t, x, t]",parser.getCharacterList("list"), testList);
	}
	
	@Test
	public void hyphenGood1() {
		parser.add("option", Parser.STRING);
		parser.parse("--option=test123 -.txt");// This returns
		List<Character> testList = new ArrayList<Character>();
		testList.add('t');
		testList.add('e');
		testList.add('s');
		testList.add('t');
		testList.add('1');
		testList.add('2');
		testList.add('3');
		testList.add('.');
		testList.add('t');
		testList.add('x');
		testList.add('t');
		assertEquals(parser.getCharacterList("option"), testList);
	}

	@Test
	public void hyphenGood2() {
		parser.add("option", Parser.STRING);
		parser.parse("--option=test123 -.txt");
		List<Character> testList = new ArrayList<Character>();
		testList.add('t');
		testList.add('e');
		testList.add('s');
		testList.add('t');
		testList.add('1');
		testList.add('2');
		testList.add('3');
		testList.add('.');
		testList.add('t');
		testList.add('x');
		testList.add('t');
		assertEquals(parser.getCharacterList("option"), testList);
	}

// 6  

	@Test
	public void rangeTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("option=a-c");
		List<Character> testList = new ArrayList<Character>();
		testList.add('a');
		testList.add('b');
		testList.add('c');
		assertEquals("should return c,b,a", parser.getCharacterList("option"), testList);
	}

	@Test
	public void rangeTest2() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("option=c-8");
		List<Character> testList = new ArrayList<Character>();
		testList.add('c');
		testList.add('b');
		testList.add('a');
		testList.add('9');
		testList.add('8');
		assertEquals("this should return c,b,a,9,8", parser.getCharacterList("option"), testList);
	}

	@Test
	public void rangeTest3() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("option=a-z");
		List<Character> testList = new ArrayList<Character>();
		testList.add('a');
		testList.add('b');
		testList.add('c');
		testList.add('d');
		testList.add('e');
		testList.add('f');
		testList.add('g');
		testList.add('h');
		testList.add('i');
		testList.add('j');
		testList.add('k');
		testList.add('l');
		testList.add('m');
		testList.add('n');
		testList.add('o');
		testList.add('p');
		testList.add('q');
		testList.add('r');
		testList.add('s');
		testList.add('t');
		testList.add('u');
		testList.add('v');
		testList.add('w');
		testList.add('x');
		testList.add('y');
		testList.add('z');
		assertEquals("this should return alphabet", parser.getCharacterList("option"), testList);
	}

	@Test
	public void rangeTest4() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("option=A-Z");
		List<Character> testList = new ArrayList<Character>();
		testList.add('a');
		testList.add('b');
		testList.add('c');
		testList.add('d');
		testList.add('e');
		testList.add('f');
		testList.add('g');
		testList.add('h');
		testList.add('i');
		testList.add('j');
		testList.add('k');
		testList.add('l');
		testList.add('m');
		testList.add('n');
		testList.add('o');
		testList.add('p');
		testList.add('q');
		testList.add('r');
		testList.add('s');
		testList.add('t');
		testList.add('u');
		testList.add('v');
		testList.add('w');
		testList.add('x');
		testList.add('y');
		testList.add('z');
		assertEquals("this should return alphabet", parser.getCharacterList("option"), testList);
	}

	@Test
	public void rangeTest5() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("option=a-Z");
		List<Character> testList = new ArrayList<Character>();
		testList.add('a');
		testList.add('b');
		testList.add('c');
		testList.add('d');
		testList.add('e');
		testList.add('f');
		testList.add('g');
		testList.add('h');
		testList.add('i');
		testList.add('j');
		testList.add('k');
		testList.add('l');
		testList.add('m');
		testList.add('n');
		testList.add('o');
		testList.add('p');
		testList.add('q');
		testList.add('r');
		testList.add('s');
		testList.add('t');
		testList.add('u');
		testList.add('v');
		testList.add('w');
		testList.add('x');
		testList.add('y');
		testList.add('z');
		assertEquals("this should return alphabet", parser.getCharacterList("option"), testList);
	}

	@Test
	public void rangeTest6() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("option=1-9");
		List<Character> testList = new ArrayList<Character>();
		testList.add('1');
		testList.add('2');
		testList.add('3');
		testList.add('4');
		testList.add('5');
		testList.add('6');
		testList.add('7');
		testList.add('8');
		testList.add('9');
		assertEquals("this should return 1-9 inclusive", parser.getCharacterList("option"), testList);
	}

	@Test
	public void rangeTest7() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("option=9-1");
		List<Character> testList = new ArrayList<Character>();
		testList.add('9');
		testList.add('8');
		testList.add('7');
		testList.add('6');
		testList.add('5');
		testList.add('4');
		testList.add('3');
		testList.add('2');
		testList.add('1');
		assertEquals("this should return 1-9 inclusive", parser.getCharacterList("option"), testList);
	}

// ### Tests from the bug finding ###

	@Test // finds bug 1
	public void shortCutTest() {
		parser.add("string", "string", Parser.STRING);
		parser.parse("--string=1");
		assertEquals(parser.getCharacterList("-stringLonger"), "1");
	}

}

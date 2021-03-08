package st;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Task1_Functional {
	
	private Parser parser;
	
	@Before
	public void set_up() {
		parser = new Parser();
	}
	
	@Test // finds bug 1
	public void freestyle4() {
		parser.add("string", "string", Parser.STRING);
		parser.parse("--string=1");
		assertEquals(parser.getString("-stringLonger"), "1");
	}
	
	@Test // finds bug 2
	public void replaceTest2() {
		parser.add( "number2", "n" , Parser.BOOLEAN);
		parser.parse("-n=0");
		assertEquals(parser.getBoolean("number2"), 0);
	}
	
	@Test // finds bug 3
	public void trueTest2() {
		parser.add( "optimise", "O" , Parser.BOOLEAN);
		parser.parse("-O");
		assertEquals(parser.getBoolean("O"), true);
	}
	
	@Test // finds bug 4
	public void replaceTest3() {
		parser.add( "number", "num" , Parser.BOOLEAN);
		parser.parse("-num=0");
		assertEquals(parser.getBoolean("num"), false);
	}
	
	@Test // finds bug 5
	public void testEmpty() {
		parser.add("char", Parser.CHAR);
		assertEquals(parser.getChar("char"), '\0');
	}
		
	@Test //finds bug 6
	public void assignTest() {
		parser.add("number", "n", Parser.INTEGER);	
		parser.parse("--number=1");
		parser.parse("--number=0");
		assertEquals(parser.getInteger("--number"), 0);
	}

	@Test // finds bug 7
	public void parserAddIntSingleTest() {
		parser.add("number", Parser.INTEGER);
		parser.parse("--number=-1");
		assertEquals(parser.getInteger("number"), -1);
	}
	
	@Test // finds bug 8
	public void newtstsbjha() {
		parser.add("number","n", Parser.INTEGER);
		parser.parse("-n=_");
		assertEquals(parser.getInteger("number"), "_");
	}
	
	@Test // finds bug 9
	public void parserLongCheck() {
		parser.add("number", Parser.INTEGER);
		parser.parse("                                                                                                                                                            ");
		assertEquals(parser.getInteger("number"), -1);
	}
	
	/*
	 * @Test // finds bug 10 ? public void newTestinnit() { parser.add("n",
	 * Parser.INTEGER); parser.parse("string");
	 * assertEquals(parser.getInteger("number"), -1); }
	 */
	
	@Test
	public void sameNameTest() {
		parser.add("number", "o", Parser.INTEGER);
		parser.add("o", Parser.INTEGER);
		parser.parse("--o=2");
		parser.parse("-o=1");
		assertEquals(parser.getInteger("number"), 1);
		assertEquals(parser.getInteger("o"), 2);
	}
	
	@Test // finds bug 11
	public void freestyle5() {
		parser.add("s", "string", Parser.STRING);
		parser.parse("--string=1");
		assertEquals(parser.getString("-string"), "1");
	}
	
	@Test // finds bug 12
	public void parseNoinpCheck() {
		parser.add("number", Parser.INTEGER);
		parser.parse("");
		assertEquals(parser.getInteger("number"), -1);
	}
	

	@Test // finds bug 13
	public void freestyle6() {
		parser.add("string", "str", Parser.STRING);
		parser.add("str", "s", Parser.STRING);
		parser.parse("--string=s");
		assertEquals(parser.getString("str"), "s");
	}


}

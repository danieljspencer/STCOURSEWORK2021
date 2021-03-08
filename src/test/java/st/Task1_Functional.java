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
	
	@Test // finds bug 3
	public void trueTest2() {
		parser.add( "optimise", "O" , Parser.BOOLEAN);
		parser.parse("-O");
		assertEquals(parser.getBoolean("O"), true);
	}
	
	@Test // finds bug 4
	public void replaceTest2() {
		parser.add( "number2", "n" , Parser.BOOLEAN);
		parser.parse("-n=0");
		assertEquals(parser.getBoolean("n"), 0);
	}
	
	@Test // finds bug 5
	public void testEmpty() {
		parser.add("char", Parser.CHAR);
		assertEquals(parser.getChar("char"), '\0');
	}
	
	@Test // finds bug 7
	public void parserAddIntSingleTest() {
		parser.add("number", Parser.INTEGER);
		parser.parse("--number=-1");
		assertEquals(parser.getInteger("number"), -1);
	}

}

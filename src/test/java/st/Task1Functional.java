package st;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Task1Functional {
	
private Parser parser;

	@Before
	public void set_up() {
		parser = new Parser();
	}
	
//1.2	
	
//	1.3 
	
//1
	@Test
	public void replaceTest() {
		parser.add( "number", "n" , Parser.INTEGER);
		parser.add( "number2", "n" , Parser.INTEGER);
		parser.parse("-n=1");
		assertEquals(parser.getInteger("n"), 1);
	}

//2
	@Test
	public void digitFirstTest() {
	    try {
	    	parser.add( "2number", "n" , Parser.INTEGER);
	        fail("Exception not thrown");
	    } catch (RuntimeException e) {
	        assertEquals("Check exception thrown",null, e.getMessage());
	    }
	    
	}
	
//3
	@Test
	public void shortcutCaseTest() {
		parser.add( "Number", "N" , Parser.INTEGER);
		parser.add( "number", "n" , Parser.INTEGER);
		parser.parse("-N=1");
		parser.parse("-n=2");
		assertNotSame(parser.getInteger("N"),parser.getInteger("n"));
	}

//4
	
	@Test
	public void sameNameTest() {
		parser.add( "number", "n" , Parser.INTEGER);
		parser.add( "n", "number" , Parser.INTEGER);
		parser.parse("-n=1 -number=2");
		assertEquals(parser.getInteger("n"),2);
		assertEquals(parser.getInteger("number"),1);
	}
	
//5 			
	
	//Add int
	@Test
	public void parserAddIntSingleTest() {
		parser.add( "number" , Parser.INTEGER );
		parser.parse("--number=1");
		assertEquals(parser.getString("number"), "1");
	}
	
	@Test
	public void parserAddIntOptTest() {
		parser.add( "number" , "n", Parser.INTEGER );
		parser.parse("-n=2");
		assertEquals(parser.getString("n"), "2");
	}
	
	@Test
	public void parserAddIntBothTest() {
		parser.add( "number" , "n", Parser.INTEGER );
		parser.parse("--number=-1");
		assertEquals(parser.getString("number"), "-1");
	}
	
	//add Boolean
	@Test
	public void parserAddBoolSingleTest() {
		parser.add( "condition" , Parser.BOOLEAN );
		parser.parse("--condition=true");
		assertEquals(parser.getString("condition"), "true");
	}
	
	@Test
	public void parserAddBoolOptTest() {
		parser.add( "condition" , Parser.BOOLEAN );
		parser.parse("--condition=true");
		assertEquals(parser.getString("condition"), "true");
	}
	
	@Test
	public void parserAddBoolBothTest() {
		parser.add( "condition" , Parser.BOOLEAN );
		parser.parse("--condition=true");
		assertEquals(parser.getString("condition"), "true");
	}
	
	
}

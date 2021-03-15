package st;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Task3_TDD_2 {

	private Parser parser;

	@Before
	public void set_up() {
		parser = new Parser();
	}
	
	@Test
	public void orderTest1() {
		parser.add("o", "option", Parser.STRING);
		parser.add("option", "o", Parser.STRING);
		parser.parse("--o=a --option=b");
		assertEquals("Name priority over shortcut", parser.getCharacterList("o"), "a");
	}

}

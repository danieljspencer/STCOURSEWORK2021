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

//	###### SECTION : 0 Basic functionality checking #######
		
	@Test
	public void nameAndShorcut() {
		parser.add("number", "n", Parser.INTEGER);
		parser.parse("--number=1");
		assertEquals(parser.getInteger("number"), parser.getInteger("n"));
	}
	
	@Test
	public void nameTesting() {
		parser.add("number", "n", Parser.INTEGER);
		parser.parse("--number=1");
		assertEquals(parser.getInteger("number"), 1);
	}

		// finds bug 7
		public void parserAddIntSingleTest() {
			parser.add("number", Parser.INTEGER);
			parser.parse("--number 1");
			assertEquals(parser.getInteger("number"), 1);
		}

		@Test
		public void parserAddIntOptTest() {
			parser.add("number", "n", Parser.INTEGER);
			parser.parse("-n=2");
			assertEquals(parser.getInteger("n"), 2);
		}

		// @Test repeat of 7
		public void parserAddIntBothTest() {
			parser.add("number", "n", Parser.INTEGER);
			parser.parse("--number=-1");
			assertEquals(parser.getInteger("number"), "-1");
		}

		// add Boolean
		//@Test
		public void parserAddBoolSingleTest() {
			parser.add("condition", Parser.BOOLEAN);
			parser.parse("--condition=true");
			assertEquals(parser.getString("condition"), "true");
		}

		//@Test
		public void parserAddBoolOptTest() {
			parser.add("condition", Parser.BOOLEAN);
			parser.parse("--condition=true");
			assertEquals(parser.getString("condition"), "true");
		}

		//@Test
		public void parserAddBoolBothTest() {
			parser.add("condition", Parser.BOOLEAN);
			parser.parse("--condition=true");
			assertEquals(parser.getString("condition"), "true");
		}

//	###### SECTION : 1.3  Add options with a shortcut #######
//1

	//@Test
	public void sameName() {
		parser.add("number", "n", Parser.INTEGER);
		parser.parse("--number=1");
		assertEquals(parser.getInteger("number"), 1);
	}

	//@Test
	public void replaceTest() {
		parser.add("number", "n", Parser.INTEGER);
		parser.parse("-n=1");
		parser.add("number2", "n", Parser.INTEGER);
		parser.parse("-n=2");
		assertEquals(parser.getInteger("n"), 2);
	}

	// finds bug 4
	public void replaceTest2() {
		parser.add("number", "n", Parser.INTEGER);
		parser.add("number2", "n", Parser.BOOLEAN);
		parser.parse("-n=0");
		assertEquals(parser.getBoolean("n"), 0);
	}

	//@Test
	public void replaceTest2a() {
		parser.add("number", "n", Parser.INTEGER);
		parser.add("number2", "n", Parser.BOOLEAN);
		parser.parse("-n=1");
		assertEquals(parser.getBoolean("n"), true);
	}

	//@Test
	public void replaceTest3() {
		parser.add("number", "n", Parser.INTEGER);
		parser.add("number2", "n", Parser.BOOLEAN);
		parser.parse("-n=1");
		assertEquals(parser.getBoolean("n"), true);
	}

	//@Test
	public void replaceTest4() {
		parser.add("number", "n", Parser.INTEGER);
		parser.add("number2", "n", Parser.STRING);
		parser.parse("-n=yes");
		assertEquals(parser.getString("n"), "yes");
	}

//2
	//@Test
	public void digitFirstTest() {
		try {
			parser.add("2number", "n", Parser.INTEGER);
			fail("Exception not thrown");
		} catch (RuntimeException e) {
			assertEquals("Check exception thrown", null, e.getMessage());
		}

	}

//3
	//@Test
	public void shortcutCaseTest() {
		parser.add("NUMBER", "N", Parser.INTEGER);
		parser.add("number", "n", Parser.INTEGER);
		parser.parse("-N=1");
		parser.parse("-n=2");
		assertNotSame(parser.getInteger("N"), parser.getInteger("n"));
	}

	//@Test
	public void shortcutCaseTest2() {
		parser.add("NUMBER", "N", Parser.BOOLEAN);
		parser.add("number", "n", Parser.INTEGER);
		parser.parse("-N=1");
		parser.parse("-n=2");
		assertNotSame(parser.getInteger("N"), parser.getInteger("n"));
	}

//4

	@Test
	public void sameNameTest() {
		parser.add("number", "o", Parser.INTEGER);
		parser.add("o", Parser.INTEGER);
		parser.parse("--o=2");
		parser.parse("-o=1");
		assertEquals(parser.getInteger("number"), 1);
		assertEquals(parser.getInteger("o"), 2);
	}

//5 

	// finds bug 4
	public void falseTest() {
		parser.add("number2", "n", Parser.BOOLEAN);
		parser.parse("-n=0");
		assertEquals(parser.getBoolean("n"), 0);
	}

	//@Test
	public void trueTest() {
		parser.add("number2", "n", Parser.BOOLEAN);
		parser.parse("-n=100000000000000000000000000000000");
		assertEquals(parser.getBoolean("n"), true);
	}

	// finds bug 3
	public void trueTest2() {
		parser.add("optimise", "O", Parser.BOOLEAN);
		parser.parse("-O");
		assertEquals(parser.getBoolean("O"), true);
	}

	// repeat of bug 3
	public void trueTest2a() {
		parser.add("optimise", "O", Parser.BOOLEAN);
		parser.parse("--optimise");
		assertEquals(parser.getBoolean("O"), true);
	}

	//@Test
	public void trueTest2b() {
		parser.add("optimise", "O", Parser.BOOLEAN);
		parser.parse("-O=true");
		assertEquals(parser.getBoolean("O"), true);
	}

	// repeat of 3
	public void trueTest2c() {
		parser.add("optimise", "O", Parser.BOOLEAN);
		parser.parse("-O 100");
		assertEquals(parser.getBoolean("O"), true);
	}

	//@Test
	public void trueTest2d() {
		parser.add("optimise", "O", Parser.BOOLEAN);
		parser.parse("-O=true");
		assertEquals(parser.getBoolean("O"), true);
	}

	//@Test
	public void trueTest3() {
		parser.add("number2", "n", Parser.BOOLEAN);
		parser.parse("-n=-1");
		assertEquals(parser.getBoolean("n"), true);
	}

//	###### SECTION : 1.4  Add options without a shortcut #######

//1
	//@Test
	public void overideTest() {
		parser.add("number", Parser.INTEGER);
		parser.parse("--number=1");
		parser.add("number", Parser.INTEGER);
		parser.parse("--number=2");
		assertEquals(parser.getInteger("number"), 2);
	}

	//@Test
	public void overideTest2() {
		parser.add("number", "n", Parser.INTEGER);
		parser.add("number", "n", Parser.BOOLEAN);
		parser.parse("--number=1");
		assertEquals(parser.getBoolean("n"), true);
	}

	//@Test
	public void overideTest3() {
		parser.add("number", Parser.INTEGER);
		parser.parse("--number=1");
		parser.add("number", Parser.STRING);
		parser.parse("--number=yes");
		assertEquals(parser.getString("number"), "yes");
	}

	// 2
	//@Test
	public void badName() {
		try {
			parser.add("2number", Parser.INTEGER);
			fail("Exception not thrown");
		} catch (RuntimeException e) {
			assertEquals("Check exception thrown", null, e.getMessage());
		}

	}

	// 3

	// 4

	// 5

	// finds bug 4
	public void falseTestv2() {
		parser.add("number2", Parser.BOOLEAN);
		parser.parse("--number2=0");
		assertEquals(parser.getBoolean("number2"), 0);
	}

	//@Test
	public void trueTestv2() {
		parser.add("number", Parser.BOOLEAN);
		parser.parse("--number=100000000000000000000000000000000");
		assertEquals(parser.getBoolean("number"), true);
	}

	// finds bug 3
	public void trueTestv22() {
		parser.add("optimise", Parser.BOOLEAN);
		parser.parse("--optimise");
		assertEquals(parser.getBoolean("optimise"), true);
	}

//	###### SECTION : 1.5  Parse command line options #######

//1
	//@Test
	public void parserUseName() {
		parser.add("number", Parser.INTEGER);
		assertEquals(parser.parse("--number=1"), 0);
	}

//2
	//@Test
	public void parserUseShortcut() {
		parser.add("number", "n", Parser.INTEGER);
		assertEquals(parser.parse("-n=-3"), 0);
	}

//3
	// Repeat of bug 7
	public void parserSpace() {
		parser.add("number", "n", Parser.INTEGER);
		assertEquals(parser.parse("-n = 0"), 0);
	}

	//@Test
	public void parserSpaceTest() {
		parser.add("number", "n", Parser.INTEGER);
		parser.parse("n = 0");
		assertEquals(parser.parse("-n=-3"), 0);
		assertEquals(parser.getInteger("-n"), 0);
	}

//4
	// @Test
	public void Quotes1() {
		parser.add("option", "o", Parser.INTEGER);
		parser.parse("option='value'");
		assertEquals(parser.parse("option='--value'"), 0);
		assertEquals(parser.getString("--option"), "'--value'");
	}

	//@Test
	public void Quotes3() {
		parser.add("number", "n", Parser.INTEGER);
		assertEquals(parser.parse("-n = 0"), 0);
	}

//5
	@Test
	public void QuotesDecor() {
		parser.add("number", "n", Parser.INTEGER);
		// assertEquals(parser.parse("option=’value=\"abc\"’"), 0);
		parser.parse("-n='value=\"abc\"'");
		assertEquals(parser.getString("n"), "value=\"abc\"");
	}

//6
	@Test
	public void loopAssignTest() {
		parser.add("number", "n", Parser.INTEGER);
		for (int i = 0; i < 100; i++) {
			parser.parse("--number=1");
		}
		parser.parse("--number=0");
		assertEquals(parser.getInteger("--number"), 0);
	}

//7

//8

//	###### SECTION : 1.6  Retrieve info #######

//1
	@Test
	public void shortCutPrecedence() {
		parser.add("number", "n", Parser.INTEGER);
		parser.add("n", "no", Parser.INTEGER);
		parser.parse("--number=0");
		parser.parse("-no=2");
		assertEquals(parser.getInteger("n"), 2);
	}

//2

	@Test
	public void testEmptyInteger() {
		parser.add("number", Parser.INTEGER);
		assertEquals(parser.getInteger("0"), 0);
	}

	@Test
	public void testEmptyInteger2() {
		assertEquals(parser.getInteger("test"), 0);
	}

	@Test
	public void testEmptyString() {
		parser.add("string", Parser.STRING);
		assertEquals(parser.getString("string"), "");
	}

	@Test
	public void testEmptyString2() {
		assertEquals(parser.getString("string"), "");
	}

	@Test
	public void testEmptyBool() {
		parser.add("bool", Parser.BOOLEAN);
		assertEquals(parser.getBoolean("bool"), false);
	}

	@Test
	public void testEmptyBool2() {
		assertEquals(parser.getBoolean("bool"), false);
	}

	// @Test finds bug 5
	public void testEmptyChar() {
		parser.add("char", Parser.CHAR);
		assertEquals(parser.getChar("char"), '\0');
	}

	// @Test repeat of bug 5
	public void testEmptyChar2() {
		assertEquals(parser.getChar("char"), '\0');
	}

//	###### SECTION : FREESTYLE #######
	@Test
	public void freestyle1() {
		parser.add("number1", "n", Parser.INTEGER);
		parser.add("number2", "n", Parser.INTEGER);
		parser.add("number3", "n", Parser.INTEGER);
		parser.parse("--number1=1 --number2=2 --number3=3");
		assertEquals(parser.getInteger("number1"), 1);
		assertEquals(parser.getInteger("number2"), 2);
		assertEquals(parser.getInteger("number3"), 3);
		assertEquals(parser.getInteger("n"), 1);
	}

	@Test
	public void freestyle2() {
		parser.add("str1", "n", Parser.STRING);
		parser.add("str2", "n", Parser.STRING);
		parser.add("str3", "n", Parser.STRING);
		parser.parse("--str1='-1' str2 '/n' --str1 1");
		assertEquals(parser.getString("str1"), "-1");
	}
	
	@Test
	public void freestyle3() {
		parser.add("str1", "str1", Parser.STRING);
		parser.add("str1", "str1", Parser.STRING);
		parser.parse("--str1='-1' str2 '/n' --str1 1");
		assertEquals(parser.getString("str1"), "-1");
	}
	
	@Test
	public void freestyle4() {
		parser.add("string", "stringLonger", Parser.STRING);
		parser.parse("--string=1");
		assertEquals(parser.getString("-stringLonger"), "1");
	}
}

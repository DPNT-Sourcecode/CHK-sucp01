package befaster.solutions.HLO;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HelloSolutionTest {
	
	HelloSolution helloSolution;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		helloSolution = new HelloSolution();
	}

	@After
	public void tearDown() throws Exception {
		helloSolution = null;
	}

	@Test
	public void testToCheckReturnedMessage() {
		assertTrue(helloSolution.hello("World") instanceof String);
	}

}

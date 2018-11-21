package befaster.solutions.CHK;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CheckoutSolutionTest {
	
	CheckoutSolution checkoutSolution;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		checkoutSolution = new CheckoutSolution();
	}

	@After
	public void tearDown() throws Exception {
		checkoutSolution = null;
	}

	@Test
	public void testToCheckCheckoutInvalidInputWithNull() {
		assertEquals(Integer.valueOf(-1), checkoutSolution.checkout(null));
	}
	
	@Test
	public void testToCheckInvalidInputWit() {
		
	}
	/*
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	*/

}

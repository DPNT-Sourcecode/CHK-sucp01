package befaster.solutions.CHK;

import static org.junit.Assert.*;

import java.util.Map;

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
	public void testToCheckBasicAdditionOfSingleItem() {
		assertEquals(Integer.valueOf(100), checkoutSolution.addSingleItem("A", 2));
	}
	
	@Test
	public void testToCheckItemHasSpecialOfferWhenTrue() {
		assertTrue(checkoutSolution.checkSpecialOffer("A"));
	}
	
	@Test
	public void testToCheckItemDoesNotHaveSpecialOfferWhenFalse() {
		assertFalse(checkoutSolution.checkSpecialOffer("C"));
	}
	
	@Test
	public void testToCheckItemSpecialOfferCountAndAmount() {
		Integer[] testCountAndAmount = checkoutSolution.getSpecialOfferCountAndAmount("A"); 
		assertEquals(Integer.valueOf(3), testCountAndAmount[0]);
		assertEquals(Integer.valueOf(130), testCountAndAmount[1]);
	}
	
	@Test
	public void testToCheckSpecialOfferCalculationWith2ItemA() {
		assertEquals(Integer.valueOf(100), checkoutSolution.calculateSpecialOfferItem("A", 2));
	}
	
	@Test
	public void testToCheckSpecialOfferCalculationWith3ItemA() {
		assertEquals(Integer.valueOf(130), checkoutSolution.calculateSpecialOfferItem("A", 3));
	}
	
	@Test
	public void testToCheckSpecialOfferCalculationWith6ItemA() {
		assertEquals(Integer.valueOf(260), checkoutSolution.calculateSpecialOfferItem("A", 6));
	}
	
	public void testToCheckSpecialOfferCalculationWith7ItemA() {
		assertEquals(Integer.valueOf(310), checkoutSolution.calculateSpecialOfferItem("A", 7));
	}
	
	@Test
	public void testToCheckSpecialOfferItemTotalCalculationWithSpecialOffer() {
		assertEquals(Integer.valueOf(310), checkoutSolution.calculateItemTotal("A", 7));
	}
	
	@Test
	public void testToCheckSpecialOfferItemTotalCalculationWithNonSpecialOffer() {
		assertEquals(Integer.valueOf(80), checkoutSolution.calculateItemTotal("C", 4));
	}
	
	@Test
	public void testToCheckItemAndCountMapGenerationFromString() {
		Map<String, Integer> testItemAndCountMap = checkoutSolution.getItemAndCountMapFromString("AAABCDD");
		assertEquals(Integer.valueOf(3), testItemAndCountMap.get("A"));
		assertEquals(Integer.valueOf(1), testItemAndCountMap.get("B"));
		assertEquals(Integer.valueOf(1), testItemAndCountMap.get("C"));
		assertEquals(Integer.valueOf(2), testItemAndCountMap.get("D"));
	}
	
	@Test
	public void testToCheckTotalCheckoutAmountWithMultipleItems() {
		assertEquals(Integer.valueOf(210), checkoutSolution.checkout("AAABCDD"));
	}
	
	@Test
	public void testToCheckTotalCheckoutAmountWithMultipleItemsAndInvalidChar() {
		assertEquals(Integer.valueOf(210), checkoutSolution.checkout("AAABCDDa"));
	}
	
//	@Test
//	public void testToCheckSpecialOfferOnSingleItem() {
//		assertEquals(Integer.valueOf(130), checkoutSolution.addItems("A", 3));
//	}
	/*
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	*/

}

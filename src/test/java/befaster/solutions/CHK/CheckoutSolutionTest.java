package befaster.solutions.CHK;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	public void testToCheckSpecialOfferCalculationWith2ItemA() {
		assertEquals(Integer.valueOf(100), checkoutSolution.calculateSpecialOfferItem("A", 2));
	}
	
	@Test
	public void testToCheckSpecialOfferCalculationWith3ItemA() {
		assertEquals(Integer.valueOf(130), checkoutSolution.calculateSpecialOfferItem("A", 3));
	}
	
	@Test
	public void testToCheckSpecialOfferCalculationWith5ItemA() {
		assertEquals(Integer.valueOf(200), checkoutSolution.calculateSpecialOfferItem("A", 5));
	}
	
	@Test
	public void testToCheckSpecialOfferCalculationWith8ItemA() {
		assertEquals(Integer.valueOf(330), checkoutSolution.calculateSpecialOfferItem("A", 8));
	}
	
	@Test
	public void testToCheckSpecialOfferCalculationWith9ItemA() {
		assertEquals(Integer.valueOf(380), checkoutSolution.calculateSpecialOfferItem("A", 9));
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
	public void testToCheckTotalCheckoutReturns40WithE() {
		assertEquals(Integer.valueOf(40), checkoutSolution.checkout("E"));
	}
	
	@Test
	public void testToCheckTotalCheckoutReturns155WithABCDE() {
		assertEquals(Integer.valueOf(155), checkoutSolution.checkout("ABCDE"));
	}
	
	@Test
	public void testToCheckTotalCheckoutReturns80WithEE() {
		assertEquals(Integer.valueOf(80), checkoutSolution.checkout("EE"));
	}
	
	@Test
	public void testToCheckTotalCheckoutReturns160WithBEBEEE() {
		assertEquals(Integer.valueOf(160), checkoutSolution.checkout("BEBEEE"));
	}
	
	@Test
	public void testToCheckTotalCheckoutReturns280WithABCDEABCDE() {
		assertEquals(Integer.valueOf(280), checkoutSolution.checkout("ABCDEABCDE"));
	}
	
	@Test
	public void testToCheckTotalCheckoutReturns20WithFF() {
		assertEquals(Integer.valueOf(20), checkoutSolution.checkout("FF"));
	}
	
	@Test
	public void testToCheckTotalCheckoutReturns20WithFFF() {
		assertEquals(Integer.valueOf(20), checkoutSolution.checkout("FFF"));
	}
	
	@Test
	public void testToCheckTotalCheckoutReturns30WithFFFF() {
		assertEquals(Integer.valueOf(30), checkoutSolution.checkout("FFFF"));
	}
	
	@Test
	public void testToCheckTotalCheckoutReturns40WithFFFFF() {
		assertEquals(Integer.valueOf(40), checkoutSolution.checkout("FFFFF"));
	}
	
	@Test
	public void testToCheckTotalCheckoutReturns40WithFFFFFF() {
		assertEquals(Integer.valueOf(40), checkoutSolution.checkout("FFFFFF"));
	}
	
	@Test
	public void testToCheckTotalCheckoutReturns145WithASBTXC() {
		assertEquals(Integer.valueOf(145), checkoutSolution.checkout("ASBTXC"));
	}
	
	@Test
	public void testToCheckTotalCheckoutReturns65WithSSSZ() {
		assertEquals(Integer.valueOf(65), checkoutSolution.checkout("SSSZ"));
	}
	
	@Test
	public void testToCheckTotalCheckoutReturnsMinusOneWithMultipleItemsAndInvalidChar() {
		assertEquals(Integer.valueOf(-1), checkoutSolution.checkout("AAABCDDa"));
	}
	
	@Test
	public void testToCheckTotalCheckoutReturnsMinusOneWithMultipleItemsAndNonAlphaChar() {
		assertEquals(Integer.valueOf(-1), checkoutSolution.checkout("AAABCDD-"));
	}
	
	@Test
	public void testToCheckTotalCheckoutReturnsZeroWithEmptyString() {
		assertEquals(Integer.valueOf(0), checkoutSolution.checkout(""));
	}
	
	@Test
	public void testToCheckDoGetOneFreeWithBEE() {
		assertEquals("EE", checkoutSolution.doGetOneFree("BEE"));
	}
	
	@Test
	public void testToCheckDoGetOneFreeWithBBEE() {
		assertEquals("BEE", checkoutSolution.doGetOneFree("BBEE"));
	}
	
	@Test
	public void testToCheckDoGetOneFreeWithBBEEEE() {
		assertEquals("EEEE", checkoutSolution.doGetOneFree("BBEEEE"));
	}
	
	@Test
	public void testToCheckDoGetOneFreeWithAABBCDEE() {
		assertEquals("AABCDEE", checkoutSolution.doGetOneFree("AABBCDEE"));
	}
	
	@Test
	public void testToCheckDoGetOneFreeWithFFF() {
		assertEquals("FF", checkoutSolution.doGetOneFree("FFF"));
	}
	
	@Test
	public void testToCheckDoGetOneFreeWithFFFFF() {
		assertEquals("FFFF", checkoutSolution.doGetOneFree("FFFFF"));
	}
	
	@Test
	public void testToCheckIsAllSameItemWhenTrue() {
		assertTrue(checkoutSolution.triggerisAllSameItemAsKey("F", "FF"));
	}
	
	@Test
	public void testToCheckIsAllSameItemWhenFalse() {
		assertFalse(checkoutSolution.triggerisAllSameItemAsKey("F", "EF"));
	}
	
	@Test
	public void testToCheckGroupDiscountis45With3Items() {
		Map<String, Integer> result = checkoutSolution.doGroupDiscounts("ASBTXC");
		for (String key: result.keySet()) {
			assertEquals("ABC", key);
			assertEquals(Integer.valueOf(45), result.get(key));			
		}
	}
	
	@Test
	public void testToCheckGroupDiscountis45With4Items() {
		Map<String, Integer> result = checkoutSolution.doGroupDiscounts("ASBTXCY");
		for (String key: result.keySet()) {
			assertEquals("ABCY", key);
			assertEquals(Integer.valueOf(45), result.get(key));			
		}
	}
	
	@Test
	public void testToCheckGroupDiscountis90With6Items() {
		Map<String, Integer> result = checkoutSolution.doGroupDiscounts("ASBTXCYDZS");
		for (String key: result.keySet()) {
			assertEquals("ABCD", key);
			assertEquals(Integer.valueOf(90), result.get(key));			
		}
	}
	
	@Test
	public void testToCheckShortestStringFromSet() {
		Set<String> testSet = new HashSet<String>();
		testSet.add("ABCD");
		testSet.add("ABC");
		testSet.add("ABCDE");
		assertEquals("ABC", checkoutSolution.getShortestStringFromSet(testSet));
		
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

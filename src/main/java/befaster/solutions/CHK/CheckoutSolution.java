package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import befaster.runner.SolutionNotImplementedException;

public class CheckoutSolution {
	
//	private HashMap<String, Integer> itemPriceMap = new HashMap<String, Integer>();
//	private HashMap<String, Integer[]> specialOffers = new HashMap<String, Integer[]>();
	private HashMap<String, CheckoutItem> itemPriceMap = new HashMap<String, CheckoutItem>();
	
	public CheckoutSolution() {
		itemPriceMap.put("A", new CheckoutItem(50, new Integer[]{3}, new Integer[]{130}, null, null));
		itemPriceMap.put("B", new CheckoutItem(30, new Integer[]{2}, new Integer[]{45}, null, null));
		itemPriceMap.put("C", new CheckoutItem(20, null, null, null, null));
		itemPriceMap.put("D", new CheckoutItem(15, null, null, null, null));
		itemPriceMap.put("E", new CheckoutItem(15, null, null, 2, "B"));
//		itemPriceMap.put("B", 30);
//		itemPriceMap.put("C", 20);
//		itemPriceMap.put("D", 15);
//		specialOffers.put("A", new Integer[]{3, 130});
//		specialOffers.put("B", new Integer[]{2, 45});
		
	}
	
	Map<String, Integer> getItemAndCountMapFromString(String itemString) {
		Map<String, Integer> itemAndCountMap = new HashMap<String, Integer>();
		for (int i = 0; i < itemString.length(); i++) {
			char currentChar = itemString.charAt(i);
			String charString = "" + currentChar;
			if (null != itemAndCountMap.get(charString)) {				
				itemAndCountMap.put(charString, itemAndCountMap.get(charString) + 1);
			} else {
				itemAndCountMap.put(charString, 1);
			}
		}
		return itemAndCountMap;
	}
	
	Integer calculateItemTotal(String itemCode, int count) {
		if (checkSpecialOffer(itemCode)) {
			return calculateSpecialOfferItem(itemCode, count);
		} else {
			return addSingleItem(itemCode, count);
		}
	}
	
	boolean checkSpecialOffer(String itemCode) {
		CheckoutItem item = itemPriceMap.get(itemCode);
		return (null != item.getSpecialOfferCounts());  					
	}
	
	Integer[] getSpecialOfferCounts(String itemCode) {
		CheckoutItem item = itemPriceMap.get(itemCode);
		return item.getSpecialOfferCounts();
	}
	
	Integer[] getSpecialOfferAmounts(String itemCode) {
		CheckoutItem item = itemPriceMap.get(itemCode);
		return item.getSpecialOfferAmounts();
	}
	
	Integer calculateSpecialOfferItem(String itemCode, int count) {
		Integer total = 0;
		Integer[] itemOfferCounts = getSpecialOfferCounts(itemCode);
		Integer[] itemOfferAmounts = getSpecialOfferAmounts(itemCode);
		for (int i = 0; i < itemOfferCounts.length; i++) {
			Integer itemOfferCount = itemOfferCounts[i];
			if (count >= itemOfferCount) {
				total += itemOfferAmounts[i];
				count = count - itemOfferCount;
			}
		}
		total += addSingleItem(itemCode, count);
//		if (count >= itemOfferCountAndAmount[0]) {
//			Integer remainder = count % itemOfferCountAndAmount[0];
//			Integer offerCount = (count - remainder) / itemOfferCountAndAmount[0];
//			total += offerCount * itemOfferCountAndAmount[1];
//			total += addSingleItem(itemCode, remainder);
//		} else {
//			total += addSingleItem(itemCode, count);  
//		}
		return total;
	}
	
	Integer addSingleItem(String itemCode, int count) {
		CheckoutItem item = itemPriceMap.get(itemCode);		
		return item.getPrice() * count;
	}
	
    public Integer checkout(String skus) {
        if (skus instanceof String && skus.matches("([A-D])+")) {
        	Integer total = 0;
        	Map<String, Integer> itemAndCountMap = getItemAndCountMapFromString(skus);
        	Set<String> itemKeys = itemAndCountMap.keySet();
        	for (String itemKey : itemKeys) {
        		if (itemPriceMap.containsKey(itemKey)) {        			
        			total += calculateItemTotal(itemKey, itemAndCountMap.get(itemKey));
        		}
        	}
        	return total;
        } else if ("".equals(skus)) {
        	return Integer.valueOf(0);
    	} else {
        	return Integer.valueOf(-1);        	
        }
    }
}

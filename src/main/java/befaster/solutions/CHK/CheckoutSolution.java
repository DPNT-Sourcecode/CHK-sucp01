package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import befaster.runner.SolutionNotImplementedException;

public class CheckoutSolution {
	
	private HashMap<String, CheckoutItem> itemPriceMap = new HashMap<String, CheckoutItem>();
	
	public CheckoutSolution() {
		itemPriceMap.put("A", new CheckoutItem(50, new Integer[]{5, 3}, new Integer[]{200, 130}, null));
		itemPriceMap.put("B", new CheckoutItem(30, new Integer[]{2}, new Integer[]{45}, "EE"));
		itemPriceMap.put("C", new CheckoutItem(20, null, null, null));
		itemPriceMap.put("D", new CheckoutItem(15, null, null, null));
		itemPriceMap.put("E", new CheckoutItem(15, null, null, null));		
	}
	
	String doGetOneFree(String skus) {
		String newSkus = skus;
		Set<String> priceMapKeys = itemPriceMap.keySet();
		for (String priceMapKey : priceMapKeys) {
			CheckoutItem item = itemPriceMap.get(priceMapKey);
			if (null != item.getGetOneFreeTrigger()) {
				int triggerCount = 0;
				String trigger = item.getGetOneFreeTrigger();
				if (skus.contains(trigger)) {					
					triggerCount++;
					int triggerEndIndex =  skus.indexOf(trigger + (trigger.length()));
					for (int i = triggerEndIndex; i < skus.length(); i++) {
						String remainingSkus = skus.substring(i);
						if (remainingSkus.contains(trigger)) {
							triggerCount++;
							i = skus.indexOf(trigger + trigger.length());
						}
					}
					for (int i = 0; i < triggerCount; i++) {
						newSkus = newSkus.replaceFirst(priceMapKey, "");
					}
				}
					
				
			}
		}
		return newSkus;
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
			while (count >= itemOfferCount) {
				total += itemOfferAmounts[i];
				count = count - itemOfferCount;
			}
		}
		total += addSingleItem(itemCode, count);
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

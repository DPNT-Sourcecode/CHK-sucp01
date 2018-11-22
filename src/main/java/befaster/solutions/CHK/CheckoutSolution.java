package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import befaster.runner.SolutionNotImplementedException;

public class CheckoutSolution {
	
	private HashMap<String, CheckoutItem> itemPriceMap = new HashMap<String, CheckoutItem>();
	
	public CheckoutSolution() {
		itemPriceMap.put("A", new CheckoutItem(50, new Integer[]{5, 3}, new Integer[]{200, 130}, null));
		itemPriceMap.put("B", new CheckoutItem(30, new Integer[]{2}, new Integer[]{45}, "EE"));
		itemPriceMap.put("C", new CheckoutItem(20, null, null, null));
		itemPriceMap.put("D", new CheckoutItem(15, null, null, null));
		itemPriceMap.put("E", new CheckoutItem(40, null, null, null));
		itemPriceMap.put("F", new CheckoutItem(10, null, null, "FF"));
	}
	
	String doGetOneFree(String skus) {
		char[] skuCharArray = skus.toCharArray();
		Arrays.sort(skuCharArray);
		String sortedSkus = new String(skuCharArray);
		String newSkus = sortedSkus;
		Set<String> priceMapKeys = itemPriceMap.keySet();
		for (String priceMapKey : priceMapKeys) {
			CheckoutItem item = itemPriceMap.get(priceMapKey);
			if (null != item.getGetOneFreeTrigger()) {
//				int triggerCount = 0;
				String trigger = item.getGetOneFreeTrigger();
				if (sortedSkus.contains(trigger)) {
					List<String> matches = new ArrayList<String>();
					Matcher matcher = Pattern.compile(trigger).matcher(sortedSkus);
					while (matcher.find()) {
						matches.add(trigger);
					}
					if (triggerisAllSameItemAsKey(priceMapKey, trigger)) {		
						for (int i = 0; i < matches.size() - 1; i++) {
							newSkus = newSkus.replaceFirst(priceMapKey, "");
						}
					} else {							
						for (String match : matches) {
							newSkus = newSkus.replaceFirst(priceMapKey, "");
						}
					}
					
				}
					
				
			}
		}
		return newSkus;
	}
	
	boolean triggerisAllSameItemAsKey(String itemCode, String trigger) {
		char[] triggerArray = trigger.toCharArray();
		for (char c : triggerArray) {
			if (!itemCode.equals("" + c)) {
				return false;
			}
		}
		return true;
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
        if (skus instanceof String && skus.matches("([A-F])+")) {
        	Integer total = 0;
        	skus = doGetOneFree(skus);
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

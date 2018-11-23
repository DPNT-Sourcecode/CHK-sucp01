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
	//Group discount - any 3 of STXYZ for 45
	private HashMap<String, Integer> groupDiscountMap = new HashMap<String, Integer>();
	
	public CheckoutSolution() {
		itemPriceMap.put("A", new CheckoutItem(50, new Integer[]{5, 3}, new Integer[]{200, 130}, null));
		itemPriceMap.put("B", new CheckoutItem(30, new Integer[]{2}, new Integer[]{45}, "EE"));
		itemPriceMap.put("C", new CheckoutItem(20, null, null, null));
		itemPriceMap.put("D", new CheckoutItem(15, null, null, null));
		itemPriceMap.put("E", new CheckoutItem(40, null, null, null));
		itemPriceMap.put("F", new CheckoutItem(10, null, null, "FF"));
		itemPriceMap.put("G", new CheckoutItem(20, null, null, null));
		itemPriceMap.put("H", new CheckoutItem(10, new Integer[]{10, 5}, new Integer[]{80, 45}, null));
		itemPriceMap.put("I", new CheckoutItem(35, null, null, null));
		itemPriceMap.put("J", new CheckoutItem(60, null, null, null));
		itemPriceMap.put("K", new CheckoutItem(70, new Integer[]{2}, new Integer[]{120}, null));
		itemPriceMap.put("L", new CheckoutItem(90, null, null, null));
		itemPriceMap.put("M", new CheckoutItem(15, null, null, "NNN"));
		itemPriceMap.put("N", new CheckoutItem(40, null, null, null));
		itemPriceMap.put("O", new CheckoutItem(10, null, null, null));
		itemPriceMap.put("P", new CheckoutItem(50, new Integer[]{5}, new Integer[]{200}, null));
		itemPriceMap.put("Q", new CheckoutItem(30, new Integer[]{3}, new Integer[]{80}, "RRR"));
		itemPriceMap.put("R", new CheckoutItem(50, null, null, null));
		itemPriceMap.put("S", new CheckoutItem(20, null, null, null));
		itemPriceMap.put("T", new CheckoutItem(20, null, null, null));
		itemPriceMap.put("U", new CheckoutItem(40, null, null, "UUU"));
		itemPriceMap.put("V", new CheckoutItem(50, new Integer[]{3, 2}, new Integer[]{130, 90}, null));
		itemPriceMap.put("W", new CheckoutItem(20, null, null, null));
		itemPriceMap.put("X", new CheckoutItem(17, null, null, null));
		itemPriceMap.put("Y", new CheckoutItem(20, null, null, null));
		itemPriceMap.put("Z", new CheckoutItem(20, null, null, null));
		
		groupDiscountMap.put("STXYZ", 45);
	}
	
	Map<String, Integer> doGroupDiscounts(String skus) {
		String newSkus = skus;
		Integer total = 0;
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		for (String groupDiscountKey : groupDiscountMap.keySet()) {
			String pattern = "([" + groupDiscountKey + "])+";
			List<String> matches = new ArrayList<String>();
			Matcher matcher = Pattern.compile(pattern).matcher(newSkus);
			while (matcher.find()) {
				matches.add(matcher.group());
			}
			String matchesString = String.join("", matches);
			Integer totalMultiplier = (int) Math.floor(matchesString.length() / 3);
			Integer numberOfItemsToRemove = 3 * totalMultiplier;
			String itemsToRemove = matchesString.substring(0, numberOfItemsToRemove);
			total += groupDiscountMap.get(groupDiscountKey) * totalMultiplier;
			for (int i = 0; i < numberOfItemsToRemove; i++) {
				newSkus = newSkus.replaceFirst("" + itemsToRemove.charAt(i), "");
			}
			result.put(newSkus, total);
			total = 0;
			String y = "";
			
		}
		return result;
		
	}
	
	String getShortestStringFromSet(Set stringSet) {
		String[] stringArray = (String[]) stringSet.toArray(new String[stringSet.size()]);
		List<String> stringList = Arrays.asList(stringArray);
		String shortest = stringList.get(0);
		for (String string : stringList) {
			if (string.length() < shortest.length()) {
				shortest = string;
			}
		}
		return shortest;
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
						newSkus = newSkus.replaceAll(priceMapKey + trigger, trigger);
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
        if (skus instanceof String && skus.matches("([A-Z])+")) {
        	Integer total = 0;
        	Map<String, Integer> groupDiscountResult = doGroupDiscounts(skus);
        	for (Integer groupTotal : groupDiscountResult.values()) {
        		total += groupTotal;
        	}
        	Set<String> groupDiscountSkuSet = groupDiscountResult.keySet();
        	skus = getShortestStringFromSet(groupDiscountSkuSet);
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

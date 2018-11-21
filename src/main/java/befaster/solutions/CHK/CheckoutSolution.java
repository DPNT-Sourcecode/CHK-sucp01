package befaster.solutions.CHK;

import java.util.HashMap;

import befaster.runner.SolutionNotImplementedException;

public class CheckoutSolution {
	
	private HashMap<String, Integer> itemPriceMap = new HashMap<String, Integer>();
	private HashMap<String, Integer[]> specialOffers = new HashMap<String, Integer[]>();
	
	public CheckoutSolution() {
		itemPriceMap.put("A", 50);
		itemPriceMap.put("B", 30);
		itemPriceMap.put("C", 20);
		itemPriceMap.put("D", 15);
		specialOffers.put("A", new Integer[]{3, 130});
		specialOffers.put("B", new Integer[]{2, 45});
		
	}
	
	boolean checkSpecialOffer(String itemCode) {
		return specialOffers.containsKey(itemCode); 					
	}
	
	Integer[] getSpecialOfferCountAndAmount(String itemCode) {
		return specialOffers.get(itemCode);
	}
	
	Integer calculateSpecialOfferItem(String itemCode, int count) {
		Integer total = 0;
		Integer[] itemOfferCountAndAmount = getSpecialOfferCountAndAmount(itemCode);
		if (count >= itemOfferCountAndAmount[0]) {
			Integer remainder = count % itemOfferCountAndAmount[0];
			Integer offerCount = (count - remainder) / itemOfferCountAndAmount[0];
			total += offerCount * itemOfferCountAndAmount[1];
			total += addSingleItem(itemCode, remainder);
		} else {
			total += addSingleItem(itemCode, count);  
		}
		return total;
	}
	
	Integer addSingleItem(String itemCode, int count) {
		Integer itemPrice = itemPriceMap.get(itemCode);
		return itemPrice * count;
	}
	
    public Integer checkout(String skus) {
        if (skus instanceof String) {
        	return Integer.valueOf(0);
        } else {
        	return Integer.valueOf(-1);        	
        }
    }
}

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
	
	public boolean checkSpecialOffer(String itemCode) {
		
	}
	
	public Integer addSingleItem(String itemCode, int count) {
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

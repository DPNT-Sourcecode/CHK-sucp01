package befaster.solutions.CHK;

import java.util.Arrays;
import java.util.Map;

public class CheckoutItem {
	
	private Integer price;
	private Integer[] specialOfferCounts;
	private Integer[] specialOfferAmounts;
	private String getOneFreeItem;
	
	public CheckoutItem(Integer price, 
						Integer[] specialOfferCounts,
						Integer[] specialOfferAmounts,
						String getOneFreeItem) {
			this.price = price;
			this.specialOfferCounts = specialOfferCounts;
			this.specialOfferAmounts = specialOfferAmounts;
			this.getOneFreeItem = getOneFreeItem;
	}
	
	public Integer getPrice() {
		return price;
	}

	public Integer[] getSpecialOfferCounts() {
		return specialOfferCounts;
	}

	public Integer[] getSpecialOfferAmounts() {
		return specialOfferAmounts;
	}

	public String getGetOneFreeItem() {
		return getOneFreeItem;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getOneFreeItem == null) ? 0 : getOneFreeItem.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + Arrays.hashCode(specialOfferAmounts);
		result = prime * result + Arrays.hashCode(specialOfferCounts);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckoutItem other = (CheckoutItem) obj;
		if (getOneFreeItem == null) {
			if (other.getOneFreeItem != null)
				return false;
		} else if (!getOneFreeItem.equals(other.getOneFreeItem))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (!Arrays.equals(specialOfferAmounts, other.specialOfferAmounts))
			return false;
		if (!Arrays.equals(specialOfferCounts, other.specialOfferCounts))
			return false;
		return true;
	}
}

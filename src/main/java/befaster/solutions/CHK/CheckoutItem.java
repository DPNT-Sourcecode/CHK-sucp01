package befaster.solutions.CHK;

import java.util.Arrays;
import java.util.Map;

public class CheckoutItem {
	
	private Integer price;
	private Integer[] specialOfferCounts;
	private Integer[] specialOfferAmounts;
	private String getOneFreeTrigger;
	
	public CheckoutItem(Integer price, 
						Integer[] specialOfferCounts,
						Integer[] specialOfferAmounts,
						String getOneFreeTrigger) {
			this.price = price;
			this.specialOfferCounts = specialOfferCounts;
			this.specialOfferAmounts = specialOfferAmounts;
			this.getOneFreeTrigger = getOneFreeTrigger;
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

	public String getGetOneFreeTrigger() {
		return getOneFreeTrigger;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getOneFreeTrigger == null) ? 0 : getOneFreeTrigger.hashCode());
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
		if (getOneFreeTrigger == null) {
			if (other.getOneFreeTrigger != null)
				return false;
		} else if (!getOneFreeTrigger.equals(other.getOneFreeTrigger))
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

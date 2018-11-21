package befaster.solutions.CHK;

import java.util.Map;

public class CheckoutItem {
	
	private Integer price;
	private Map<Integer, Integer> specialOfferCountAndAmount;
	private String getOneFreeItem;

	public CheckoutItem(Integer price, 
						Map<Integer, Integer> specialOfferCountAndAmount,
						String getOneFreeItem) {
		this.price = price;
		this.specialOfferCountAndAmount = specialOfferCountAndAmount;
		this.getOneFreeItem = getOneFreeItem;
	}

	public Integer getPrice() {
		return price;
	}

	public Map<Integer, Integer> getSpecialOfferCountAndAmount() {
		return specialOfferCountAndAmount;
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
		result = prime * result + ((specialOfferCountAndAmount == null) ? 0 : specialOfferCountAndAmount.hashCode());
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
		if (specialOfferCountAndAmount == null) {
			if (other.specialOfferCountAndAmount != null)
				return false;
		} else if (!specialOfferCountAndAmount.equals(other.specialOfferCountAndAmount))
			return false;
		return true;
	}

}

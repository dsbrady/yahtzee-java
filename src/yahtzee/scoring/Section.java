package yahtzee.scoring;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import yahtzee.categories.Category;

public class Section {
	private String bonusName;
	private Integer bonusScore;
	private Integer bonusValue;
	private LinkedHashMap<String, Category> categories;
	private String name;

	public Section() {

	}

	public Section(String name) {
		setName(name);
	}

	public int calculateSubtotal() {
		LinkedHashMap<String, Category> categories = getCategories();
		Map.Entry<String, Category> categoryEntry;
		Set<Map.Entry<String, Category>> categorySet = categories.entrySet();
		Iterator<Map.Entry<String, Category>> categoryIterator = categorySet.iterator();

		int subtotal = 0;

		while(categoryIterator.hasNext()) {
			categoryEntry = categoryIterator.next();
			subtotal += categoryEntry.getValue().getScore() == null ? 0 : categoryEntry.getValue().getScore();
		}

		return subtotal;
	}

	public String getBonusName() {
		return bonusName;
	}

	public Integer getBonusScore() {
		return bonusScore;
	}

	public Integer getBonusValue() {
		return bonusValue;
	}

	public LinkedHashMap<String, Category> getCategories() {
		return categories;
	}

	public String getName() {
		return name;
	}

	public void populateCategories() throws NoSuchMethodException {
		throw new NoSuchMethodException("Method must be implemented by child class (" + getName() + ")!");
	}

	public void setCategories(LinkedHashMap<String, Category> categories) {
		this.categories = categories;
	}

	public void setBonusName(String name) {
		this.bonusName = name;
	}

	public void setBonusScore(Integer bonusScore) {
		this.bonusScore = bonusScore;
	}

	public void setBonusValue(Integer bonusValue) {
		this.bonusValue = bonusValue;
	}

	public void setName(String name) {
		this.name = name;
	}

}

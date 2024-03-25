package yahtzee.scoring;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import yahtzee.categories.Category;
import yahtzee.categories.DieValue;

public class UpperSection extends Section {
	private final int REQUIRED_VALUE = 63;

	public UpperSection() {
		super("Upper");
		setBonusName("Bonus");
		setBonusValue(35);
	}

	public boolean checkForBonus() {
		LinkedHashMap<String, Category> categories = getCategories();
		Set<Map.Entry<String, Category>> categorySet = categories.entrySet();

		Map.Entry<String, Category> categoryEntry;
		Iterator<Map.Entry<String, Category>> categoryIterator = categorySet.iterator();
		boolean qualifiesForBonus = false;
		int sectionScore = 0;

		while(categoryIterator.hasNext()) {
			categoryEntry = categoryIterator.next();

			sectionScore += (categoryEntry.getValue().getScore() == null ? 0 : categoryEntry.getValue().getScore());
		}

		if(sectionScore >= getRequiredValue()) {
			qualifiesForBonus = true;
		}

		return qualifiesForBonus;
	}

	private int getRequiredValue() {
		return REQUIRED_VALUE;
	}

	@Override public void populateCategories() {
		final LinkedHashMap<String, Category> categories = new LinkedHashMap<String, Category>();

		categories.put("1", new DieValue("Aces", 1));
		categories.put("2", new DieValue("Twos", 2));
		categories.put("3", new DieValue("Threes", 3));
		categories.put("4", new DieValue("Fours", 4));
		categories.put("5", new DieValue("Fives", 5));
		categories.put("6", new DieValue("Sixes", 6));

		super.setCategories(categories);
	}
}

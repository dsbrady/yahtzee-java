package yahtzee.scoring;

import java.util.LinkedHashMap;

import yahtzee.categories.*;
import yahtzee.Die;

public class LowerSection extends Section {
	private int yahtzeeBonusCount = 0;

	public LowerSection() {
		super("Lower");
		setBonusName("YAHTZEE Bonus");
		setBonusValue(100);
	}

	public boolean checkForBonus(Die[] dice) {
		int dieValueToCompare;

		dieValueToCompare = dice[0].getValue();

		for(int i = 1; i < dice.length; i++) {
			if(dice[i].getValue() != dieValueToCompare) {
				return false;
			}
		}

		return true;
	}

	public int getYahtzeeBonusCount() {
		return yahtzeeBonusCount;
	}

	@Override public void populateCategories() {
		final LinkedHashMap<String, Category> categories = new LinkedHashMap<String, Category>();

		categories.put("3K", new ThreeOfAKind());
		categories.put("4K", new FourOfAKind());
		categories.put("FH", new FullHouse());
		categories.put("SS", new SmallStraight());
		categories.put("LS", new LargeStraight());
		categories.put("Y", new Yahtzee());
		categories.put("C", new Category("Chance"));

		super.setCategories(categories);
	}

	public void setBonusScore() {
		if(getYahtzeeBonusCount() > 0) {
			super.setBonusScore(getYahtzeeBonusCount() * getBonusValue());
		} else {
			super.setBonusScore(null);
		}
	}

	public void setYahtzeeBonusCount(int yahtzeeBonusCount) {
		this.yahtzeeBonusCount = yahtzeeBonusCount;
	}

}

package yahtzee.categories;

import yahtzee.Die;

public class DieValue extends Category {

	private int requiredValue;

	public DieValue(String name, int requiredValue) {
		super(name);
		setRequiredValue(requiredValue);
	}

	public int getRequiredValue() {
		return requiredValue;
	}

	public void setRequiredValue(int requiredValue) {
		this.requiredValue = requiredValue;
	}

	@Override
	public int score(Die[] dice) {
		int score = 0;

		for(Die die : dice) {
			if(die.getValue() == requiredValue) {
				score += die.getValue();
			}
		}

		return score;
	}

}

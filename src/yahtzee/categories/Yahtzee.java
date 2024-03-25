package yahtzee.categories;

import yahtzee.Die;

public class Yahtzee extends Category {

	public Yahtzee() {
		super("YAHTZEE", 50);
	}

	@Override
	public int score(Die[] dice) {
		int diceValues[] = new int[6];

		for(Die die : dice) {
			diceValues[die.getValue() - 1]++;
		}

		for(int i = 0; i < diceValues.length; i++) {
			if(diceValues[i] == 5) {
				return getValue();
			}
		}

		return 0;
	}

}

package yahtzee.categories;

import java.util.Arrays;

import yahtzee.Die;

public class LargeStraight extends Category {

	public LargeStraight() {
		super("Large Straight", 40);
	}

	@Override
	public int score(Die[] dice) {
		int diceValues[] = new int[5];
		boolean hasLargeStraight = true;

		for(int i = 0; i < dice.length; i++) {
			diceValues[i] = dice[i].getValue();
		}

		Arrays.sort(diceValues);

		// We want to remove any duplicate values and make sure there are still at 5 dice values
		diceValues = removeDuplicateDice(diceValues);
		if(diceValues.length < 5) {
			return 0;
		}

		for(int i = 1; i < diceValues.length ; i++) {
			if(diceValues[i-1] + 1 != diceValues[i]) {
				hasLargeStraight = false;
				break;
			}
		}

		if(!hasLargeStraight) {
			return 0;
		} else {
			return getValue();
		}
	}

}

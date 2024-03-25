package yahtzee.categories;

import java.util.Arrays;

import yahtzee.Die;

public class SmallStraight extends Category {

	public SmallStraight() {
		super("Small Straight", 30);
	}

	@Override
	public int score(Die[] dice) {
		int diceValues[] = new int[5];
		boolean hasSmallStraight = true;

		diceValues = getDiceValues(dice);

		Arrays.sort(diceValues);

		// We want to remove any duplicate values and make sure there are still at least 4 dice values
		diceValues = removeDuplicateDice(diceValues);
		if(diceValues.length < 4) {
			return 0;
		}

		// Look at the first 4 dice and see if they're consecutive.  If It's still not a small straight, look at the last 4 dice
		for(int i = 1; i < diceValues.length - 1; i++) {
			// If the next die is a duplicate of the previous one, just skip this iteration of the loop
			if(diceValues[i-1] == diceValues[i]) {
				continue;
			}
			if(diceValues[i-1] + 1 != diceValues[i]) {
				hasSmallStraight = false;
				break;
			}
		}

		if(!hasSmallStraight) {
			hasSmallStraight = true;
			for(int i = 2; i < diceValues.length; i++) {
				// If the next die is a duplicate of the previous one, just skip this iteration of the loop
				if(diceValues[i-1] == diceValues[i]) {
					continue;
				}
				if(diceValues[i-1] + 1 != diceValues[i]) {
					hasSmallStraight = false;
					break;
				}
			}
		}

		if(!hasSmallStraight) {
			return 0;
		} else {
			return getValue();
		}
	}

}

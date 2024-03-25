package yahtzee.categories;

import yahtzee.Die;

public class FourOfAKind extends Category {

	public FourOfAKind() {
		super("Four of a Kind");
	}

	@Override
	public int score(Die[] dice) {
		int diceValueCounts[] = new int[6];
		boolean has4OfAKind = false;

		diceValueCounts = getDiceValueCounts(dice);

		for(int i = 0; i < diceValueCounts.length; i++) {
			if(diceValueCounts[i] >= 3) {
				has4OfAKind = true;
				break;
			}
		}

		if(!has4OfAKind) {
			return 0;
		} else {
			return super.score(dice);
		}
	}

}

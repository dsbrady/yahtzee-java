package yahtzee.categories;

import yahtzee.Die;

public class ThreeOfAKind extends Category {

	public ThreeOfAKind() {
		super("Three of a Kind");
	}

	@Override
	public int score(Die[] dice) {
		int diceValueCounts[] = new int[6];
		boolean has3OfAKind = false;

		diceValueCounts = getDiceValueCounts(dice);

		for(int i = 0; i < diceValueCounts.length; i++) {
			if(diceValueCounts[i] >= 3) {
				has3OfAKind = true;
				break;
			}
		}

		if(!has3OfAKind) {
			return 0;
		} else {
			return super.score(dice);
		}
	}

}

package yahtzee.categories;

import yahtzee.Die;

public class FullHouse extends Category {

	public FullHouse() {
		super("Full House", 25);
	}

	@Override
	public int score(Die[] dice) {
		int diceValueCounts[] = new int[6];
		boolean hasTwoOfAKind = false,
			hasThreeOfAKind = false;

		diceValueCounts = getDiceValueCounts(dice);

		for(int i = 0; i < diceValueCounts.length; i++) {
			if(diceValueCounts[i] == 2) {
				hasTwoOfAKind = true;
			} else if(diceValueCounts[i] == 3) {
				hasThreeOfAKind = true;
			}
		}

		if(hasThreeOfAKind && hasTwoOfAKind) {
			return getValue();
		} else {
			return 0;
		}
	}

}

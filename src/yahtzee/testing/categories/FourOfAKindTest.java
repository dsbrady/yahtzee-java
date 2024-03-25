package yahtzee.testing.categories;

import org.junit.Test;
import static org.junit.Assert.*;

import yahtzee.Die;
import yahtzee.categories.FourOfAKind;

public class FourOfAKindTest {
	Die dice[] = new Die[]{new Die(), new Die(), new Die(), new Die(), new Die()};
	FourOfAKind fourOfAKind = new FourOfAKind();

	@Test
	public void testScoreFourOfAKind() {
		dice[0].setValue(6);
		dice[1].setValue(6);
		dice[2].setValue(6);
		dice[3].setValue(6);
		dice[4].setValue(2);

		int score = fourOfAKind.score(dice);
		assertEquals(score, 26);
	}

	@Test
	public void testScoreZero() {
		dice[0].setValue(1);
		dice[1].setValue(2);
		dice[2].setValue(3);
		dice[3].setValue(4);
		dice[4].setValue(5);

		int score = fourOfAKind.score(dice);
		assertEquals(score, 0);
	}

}

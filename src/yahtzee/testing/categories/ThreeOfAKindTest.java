package yahtzee.testing.categories;

import org.junit.Test;
import static org.junit.Assert.*;

import yahtzee.Die;
import yahtzee.categories.ThreeOfAKind;

public class ThreeOfAKindTest {
	Die dice[] = new Die[]{new Die(), new Die(), new Die(), new Die(), new Die()};
	ThreeOfAKind threeOfAKind = new ThreeOfAKind();

	@Test
	public void testScoreThreeOfAKind() {
		dice[0].setValue(1);
		dice[1].setValue(1);
		dice[2].setValue(1);
		dice[3].setValue(2);
		dice[4].setValue(2);

		int score = threeOfAKind.score(dice);
		assertEquals(score, 7);
	}

	@Test
	public void testScoreZero() {
		dice[0].setValue(1);
		dice[1].setValue(2);
		dice[2].setValue(3);
		dice[3].setValue(4);
		dice[4].setValue(5);

		int score = threeOfAKind.score(dice);
		assertEquals(score, 0);
	}

}

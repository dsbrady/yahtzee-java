package yahtzee.testing.categories;

import org.junit.Test;
import static org.junit.Assert.*;

import yahtzee.Die;
import yahtzee.categories.SmallStraight;

public class SmallStraightTest {
	Die dice[] = new Die[]{new Die(), new Die(), new Die(), new Die(), new Die()};
	SmallStraight smallStraight = new SmallStraight();

	@Test
	public void testScoreSmallStraightFirstFour() {
		dice[0].setValue(5);
		dice[1].setValue(2);
		dice[2].setValue(3);
		dice[3].setValue(4);
		dice[4].setValue(1);

		int score = smallStraight.score(dice);
		assertEquals(score, 30);
	}

	@Test
	public void testScoreSmallStraightSecondFour() {
		dice[0].setValue(3);
		dice[1].setValue(3);
		dice[2].setValue(4);
		dice[3].setValue(5);
		dice[4].setValue(6);

		int score = smallStraight.score(dice);

		assertEquals(score, 30);
	}

	@Test
	public void testScoreZero() {
		dice[0].setValue(1);
		dice[1].setValue(2);
		dice[2].setValue(2);
		dice[3].setValue(3);
		dice[4].setValue(3);

		int score = smallStraight.score(dice);
		assertEquals(score, 0);
	}

}

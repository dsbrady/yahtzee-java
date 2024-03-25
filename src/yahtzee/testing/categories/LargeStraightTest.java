package yahtzee.testing.categories;

import org.junit.Test;
import static org.junit.Assert.*;

import yahtzee.Die;
import yahtzee.categories.LargeStraight;

public class LargeStraightTest {
	Die dice[] = new Die[]{new Die(), new Die(), new Die(), new Die(), new Die()};
	LargeStraight largeStraight = new LargeStraight();

	@Test
	public void testScoreLargeStraight() {
		dice[0].setValue(2);
		dice[1].setValue(3);
		dice[2].setValue(4);
		dice[3].setValue(5);
		dice[4].setValue(6);

		int score = largeStraight.score(dice);
		assertEquals(score, 40);
	}

	@Test
	public void testScoreZero() {
		dice[0].setValue(1);
		dice[1].setValue(2);
		dice[2].setValue(2);
		dice[3].setValue(3);
		dice[4].setValue(3);

		int score = largeStraight.score(dice);
		assertEquals(score, 0);
	}

}

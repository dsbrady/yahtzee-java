package yahtzee.testing.categories;

import org.junit.Test;
import static org.junit.Assert.*;

import yahtzee.Die;
import yahtzee.categories.Yahtzee;

public class YahtzeeTest {
	Die dice[] = new Die[]{new Die(), new Die(), new Die(), new Die(), new Die()};
	Yahtzee yahtzee = new Yahtzee();

	@Test
	public void testScoreYahtzee() {
		dice[0].setValue(1);
		dice[1].setValue(1);
		dice[2].setValue(1);
		dice[3].setValue(1);
		dice[4].setValue(1);

		int score = yahtzee.score(dice);
		assertEquals(score, 50);
	}

	@Test
	public void testScoreZero() {
		dice[0].setValue(2);
		dice[1].setValue(1);
		dice[2].setValue(1);
		dice[3].setValue(1);
		dice[4].setValue(1);

		int score = yahtzee.score(dice);
		assertEquals(score, 0);
	}

}

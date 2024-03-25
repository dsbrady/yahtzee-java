package yahtzee.testing.categories;

import org.junit.Test;
import static org.junit.Assert.*;

import yahtzee.Die;
import yahtzee.categories.FullHouse;

public class FullHouseTest {
	Die dice[] = new Die[]{new Die(), new Die(), new Die(), new Die(), new Die()};
	FullHouse fullHouse = new FullHouse();

	@Test
	public void testScoreFullHouse() {
		dice[0].setValue(1);
		dice[1].setValue(1);
		dice[2].setValue(1);
		dice[3].setValue(2);
		dice[4].setValue(2);

		int score = fullHouse.score(dice);
		assertEquals(score, 25);
	}

	@Test
	public void testScoreZero() {
		dice[0].setValue(2);
		dice[1].setValue(1);
		dice[2].setValue(1);
		dice[3].setValue(1);
		dice[4].setValue(1);

		int score = fullHouse.score(dice);
		assertEquals(score, 0);
	}

}

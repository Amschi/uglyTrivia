package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Assert;
import org.junit.Test;

public class SomeTest {



    @Test
    public void should_get_out_of_the_penalty_box_odd_number() {
        // GIVEN
        Game aGame = new Game();
        aGame.add("Bob");
        aGame.add("Alice");

        int rollNumber = 5;


        // WHEN
        aGame.roll(rollNumber);
        aGame.wrongAnswer();
        aGame.roll(rollNumber);
        aGame.wrongAnswer();
        aGame.roll(rollNumber);


        // THEN
        Assert.assertEquals(true, aGame.isGettingOutOfPenaltyBox());

    }

    @Test
    public void should_be_sent_in_penalty_box_when_wrong_answer() {
        // GIVEN
        Game aGame = new Game();
        aGame.add("Bob");

        // WHEN
        aGame.roll(2);
        aGame.wrongAnswer();

        // THEN
        Assert.assertEquals(true, aGame.getInPenaltyBox()[0]);
    }

    @Test
    public void should_purse_player_increments_if_player_not_in_penalty_box_with_correct_answer() {
        // GIVEN
        Game aGame = new Game();
        aGame.add("Bob");
        aGame.add("Alice");

        // WHEN
        aGame.roll(3);
        aGame.wrongAnswer();
        aGame.roll(5);
        boolean actualAnswer = aGame.wasCorrectlyAnswered();

        // THEN
        assertEquals(false, aGame.getInPenaltyBox()[1]);
        assertEquals(true, actualAnswer);
        assertEquals(1, aGame.getPurses()[1]);
    }

    @Test
    public void should_return_true_when_has_correct_answer_in_penalty_box() {
        // GIVEN
        Game aGame = new Game();
        aGame.add("Bob");
        aGame.getInPenaltyBox()[0] = true;


        // WHEN
        aGame.roll(4);

        // THEN
        assertEquals(0, aGame.getCurrentPlayer());
        assertEquals(true, aGame.wasCorrectlyAnswered());
        assertEquals(false, aGame.isGettingOutOfPenaltyBox());
    }

    @Test
    public void should_purse_be_incremented_when_playerInPenaltyBox_and_getting_out() {
        // GIVEN
        Game aGame = new Game();
        aGame.add("Bob");
        aGame.getInPenaltyBox()[0] = true;

        // WHEN
        aGame.roll(3);
        aGame.wasCorrectlyAnswered();

        // THEN
        assertEquals(1, aGame.getPurses()[0]);

    }


}

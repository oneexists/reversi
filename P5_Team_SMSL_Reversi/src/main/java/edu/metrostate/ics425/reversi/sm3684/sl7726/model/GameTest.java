package edu.metrostate.ics425.reversi.team_smsl.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.metrostate.ics425.reversi.team_smsl.model.Game.Disk;

class GameTest {
	Game game = new Game();

	@Test
	void testInitialBoard() {
		Disk[] disks = game.getDisks();
		assertEquals(Disk.DARK, disks[27]);
		assertEquals(Disk.DARK, disks[36]);
		assertEquals(Disk.LIGHT, disks[28]);
		assertEquals(Disk.LIGHT, disks[35]);
	}
	
	@Test 
	void testPlaceDisk() {
		assertEquals(Disk.DARK, game.getCurrentPlayer());
		game.placeDisk(43);
		Disk[] board = game.getDisks();
		assertEquals(Disk.DARK, board[43]);
	}
	@Test
	void testValidMove() {
		// valid move is valid
		// invalid move is invalid
	}
	
	@Test
	void testEndGame() {
		// ends if neither player can move
		// verify over-the-board score
	}
	
	@Test
	void testWinner() {
		// player with most disks wins
	}

}

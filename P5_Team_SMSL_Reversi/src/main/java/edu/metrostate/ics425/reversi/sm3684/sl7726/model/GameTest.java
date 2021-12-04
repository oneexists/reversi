package edu.metrostate.ics425.reversi.sm3684.sl7726.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.metrostate.ics425.reversi.sm3684.sl7726.model.Game.Disk;

class GameTest {
	Game game = new Game();

	@Test
	void testInitialBoard() {
		Disk[] disks = game.getDisks();
		assertEquals(Disk.LIGHT, disks[27]);
		assertEquals(Disk.LIGHT, disks[36]);
		assertEquals(Disk.DARK, disks[28]);
		assertEquals(Disk.DARK, disks[35]);
	}
	
	@Test
	void testGetScore() {
		assertEquals(2, game.getDarkScore());
		assertEquals(2, game.getLightScore());
	}
	@Test 
	void testPlaceDisk() {
		assertEquals(Disk.DARK, game.getCurrentPlayer());
		game.placeDisk(46);
		assertEquals(null, game.getDisks()[46]);
		game.placeDisk(43);
		assertEquals(null, game.getDisks()[43]);
		game.placeDisk(44);
		assertEquals(Disk.DARK, game.getDisks()[44]);
	}
	
	@Test
	void testFlipDisks() {
		game.placeDisk(26);
		assertEquals(Disk.DARK, game.getDisks()[26]);
		assertEquals(Disk.DARK, game.getDisks()[27]);
	}
}

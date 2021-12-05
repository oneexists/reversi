package edu.metrostate.ics425.reversi.sm3684.sl7726.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.metrostate.ics425.reversi.sm3684.sl7726.model.Game.Disk;;

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
	void testPlaceDisk() {
		assertEquals(Disk.DARK, game.getCurrentPlayer());
		game.placeDisk(44);
		Disk[] disks = game.getDisks();
		assertEquals(Disk.DARK, disks[44]);
	}

	@Test
	void testPassMove() {
		assertFalse(game.passMove());
	}
	
	@Test
	void testEdge() {
		game.placeDisk(44);
		game.placeDisk(45);
		game.placeDisk(37);
		game.placeDisk(43);
		game.placeDisk(52);
		game.placeDisk(53);
		game.placeDisk(54);
		game.placeDisk(63);
		game.placeDisk(55);
		game.placeDisk(47);
		game.placeDisk(51);
		game.placeDisk(29);
		game.placeDisk(18);
		game.placeDisk(62);
		game.placeDisk(42);
		game.placeDisk(60);
		game.placeDisk(61);
		assertEquals(Disk.DARK, game.getDisks()[53]);
		assertEquals(Disk.DARK, game.getDisks()[52]);
	}
	
	@Test
	void testLookBothWays() {
		game.placeDisk(44);
		game.placeDisk(45);
		game.placeDisk(19);
		game.placeDisk(34);
		game.placeDisk(41);
		game.placeDisk(11);
		game.placeDisk(37);
		game.placeDisk(43);
		game.placeDisk(10);
		game.placeDisk(9);
		game.placeDisk(3);
		game.placeDisk(12);
		game.placeDisk(1);
		game.placeDisk(20);
		game.placeDisk(53);
		game.placeDisk(33);
		game.placeDisk(25);
		game.placeDisk(32);
		game.placeDisk(8);
		game.placeDisk(18);
		game.placeDisk(26);
		assertEquals(Disk.DARK, game.getDisks()[18]);
		assertEquals(Disk.DARK, game.getDisks()[35]);
	}
	
	@Test
	void testFindMoves() {
		int[] openingMoves = {19, 26, 37, 44};
		for (int i=0; i<openingMoves.length; i++) {
			assertEquals(openingMoves[i], game.findMoves()[i]);
		}
	}
	
//	@Test
//	void testIsOccupied() {
//		assertTrue(game.isOccupied(27));
//		assertTrue(game.isOccupied(36));
//	}
//	@Test
//	void testFlipDisks() {
//		game.flipDisks(new int[] {26, 27});
//		assertEquals(Disk.DARK, game.getDisks()[26]);
//		assertEquals(Disk.DARK, game.getDisks()[27]);
//	}
}

package edu.metrostate.ics425.reversi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author skylar
 *
 */
public class Game implements Serializable {
	private static final long serialVersionUID = 202206001L;

	enum Column {
		a(new int[] {0, 8, 16, 24, 32, 40, 48, 56}),
		b(new int[] {1, 9, 17, 25, 33, 41, 49, 57}),
		c(new int[] {2, 10, 18, 26, 34, 42, 50, 58}),
		d(new int[] {3, 11, 19, 27, 35, 43, 51, 59}),
		e(new int[] {4, 12, 20, 28, 36, 44, 52, 60}),
		f(new int[] {5, 13, 21, 29, 37, 45, 53, 61}),
		g(new int[] {6, 14, 22, 30, 38, 46, 54, 62}),
		h(new int[] {7, 15, 23, 31, 39, 47, 55, 63});

		private int[] column;
		
		Column(int[] column) {
			this.column = column;
		}
	}
	enum Disk {
		LIGHT, DARK;
	}
	enum Rows {
		HORIZONTAL(new int[][] { 
			{0, 1, 2, 3, 4, 5, 6, 7}, 
			{8, 9, 10, 11, 12, 13, 14, 15},
			{16, 17, 18, 19, 20, 21, 22, 23},
			{24, 25, 26, 27, 28, 29, 30, 31},
			{32, 33, 34, 35, 36, 37, 38, 39},
			{40, 41, 42, 43, 44, 45, 46, 47},
			{48, 49, 50, 51, 52, 53, 54, 55},
			{56, 57, 58, 59, 60, 61, 62, 63}
			}), 
		VERTICAL(new int[][] {
			{0, 8, 16, 24, 32, 40, 48, 56},
			{1, 9, 17, 25, 33, 41, 49, 57},
			{2, 10, 18, 26, 34, 42, 50, 58},
			{3, 11, 19, 27, 35, 43, 51, 59},
			{4, 12, 20, 28, 36, 44, 52, 60},
			{5, 13, 21, 29, 37, 45, 53, 61},
			{6, 14, 22, 30, 38, 46, 54, 62},
			{7, 15, 23, 31, 39, 47, 55, 63}
		}), 
		DIAGONAL(new int[][] {
			{40, 49, 58}, 
			{32, 41, 50, 59},
			{24, 33, 42, 51, 60}, 
			{16, 25, 34, 43, 52, 61},
			{8, 17, 26, 35, 44, 53, 62},
			{0, 9, 18, 27, 36, 45, 54, 63},
			{1, 10, 19, 28, 37, 46, 55},
			{2, 11, 20, 29, 38, 47},
			{3, 12, 21, 30, 39},
			{4, 13, 22, 31}, 
			{5, 14, 23},
			{16, 9, 2},
			{24, 17, 10, 3},
			{32, 25, 18, 11, 4},
			{40, 33, 26, 19, 12, 5},
			{48, 41, 34, 27, 20, 13, 6},
			{56, 49, 42, 35, 28, 21, 14, 7},
			{57, 50, 43, 36, 29, 22, 15},
			{58, 51, 44, 37, 30, 23},
			{59, 52, 45, 38, 31},
			{60, 53, 46, 39},
			{61, 54, 47},
			{2, 9, 16},
			{3, 10, 17, 24},
			{4, 11, 18, 25, 32},
			{5, 12, 19, 26, 33, 40},
			{6, 13, 20, 27, 34, 41, 48},
			{7, 14, 21, 28, 35, 42, 49, 56},
			{15, 22, 29, 36, 43, 50, 57},
			{23, 30, 37, 44, 51, 58},
			{31, 38, 45, 52, 59}, 
			{39, 46, 53, 60},
			{47, 54, 61}
		});
		
		private int[][] rows;
		
		Rows(int[][] rows) {
			this.rows = rows;
		}
	}
	private static final Logger LOGGER = Logger.getLogger("model.Game");

	private static final int NUM_DISKS = 64;
	private Disk[] disks;
	private Disk currentPlayer;
	private String turnString;
	
	/**
	 * No-arg constructor, initializes game
	 */
	public Game() {
		disks = new Disk[NUM_DISKS];
		currentPlayer = Disk.DARK;
		
		// set initial pieces
		disks[27] = Disk.LIGHT;
		disks[36] = Disk.LIGHT;
		disks[28] = Disk.DARK;
		disks[35] = Disk.DARK;
	}

	private int[] checkRow(int[] row, int loc) {
		for (int i=0; i<row.length; i++) {
			if (row[i] == loc) {
				if (i<row.length-2) {
					if (isOccupied(row[i+1])) {
						if (getRowUp(Arrays.copyOfRange(row, i+1, row.length)) != null) {
							return getRowUp(Arrays.copyOfRange(row, i+1, row.length));							
						}
					}					
				}
				if (i>1) {
					if (isOccupied(row[i-1])) {
						return getRowDown(Arrays.copyOfRange(row, 0, i));
					}					
				}
			}
		}
		return null;
	}

	private List<int[]> findRows(int loc) {
		List<int[]> foundRows = new ArrayList<>();
		for (Rows rows : Rows.values()) {
			for (var row : rows.rows) {
				for (var space : row) {
					if (space == loc) {
						int[] foundRow = checkRow(row, loc);
						if (foundRow != null) {
							foundRows.add(foundRow);
						}
					}
				}
			}
		}
		return foundRows;
	}

	private void flipDisks(int[] row) {
		Arrays.stream(row).forEach(x -> disks[x] = currentPlayer);
	}

	private Column getColumn(int loc) {
		for (Column column : Column.values()) {
			for (var val : column.column) {
				if (val == loc) {
					return column;
				}
			}
		}
		return null;
	}

	/**
	 * Returns Disk value of the current player
	 * 
	 * @return the currentPlayer
	 */
	public Disk getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Returns the score for the game
	 * 
	 * @return an array of the game score
	 */
	public int[] getScore() {
		int [] scores = new int[2];
		for(Disk disk : disks) {
			if (disk != null) {
				scores[disk.ordinal()]++;
			}
		}
		return scores;
	}
	
	/**
	 * Returns the disks on the board
	 * 
	 * @return the disks
	 */
	public Disk[] getDisks() {
		return disks.clone();
	}
	
	/**
	 * Returns the row number for the specified location on the board
	 * 
	 * @param loc the location on the board
	 * @return row the row the location belongs to
	 */
	public int getRow(Integer loc) {
		return 1 + Math.floorDiv(loc, 8);
	}
	
	private int[] getRowDown(int[] checkRow) {
		end: for (int i=checkRow.length-1; i>0; i--) {
			if (isOccupied(checkRow[i])) {
				if (disks[checkRow[i-1]] == currentPlayer) {
					return Arrays.copyOfRange(checkRow, i-1, checkRow.length);
				}
			} else {
				break end;
			}
		}
		return null;
	}
	
	private int[] getRowUp(int[] checkRow) {
		end: for (int i=0; i<checkRow.length-1; i++) {
			if (isOccupied(checkRow[i])) {
				if (disks[checkRow[i+1]] == currentPlayer) {
					return Arrays.copyOfRange(checkRow, 0, i+1);
				}
			} else {
				break end;
			}
		}
		return null;
	}
	
	/**
	 * Returns the last move's player and location
	 * 
	 * @param loc the location of the placed disk
	 * @return message String of the turn summary
	 */
	public String getTurnString() {
		return turnString;
	}
	
	/**
	 * Returns the disk value of the player with the highest score
	 * 
	 * @return winner or {@code null} if a tie
	 */
	public Disk getWinner() {
		int[] score = getScore();
		if (score[0] > score[1]) { return Disk.LIGHT; }
		else if (score[1] > score [0]) { return Disk.DARK; } 
		else { return null; }
	}
	
	private boolean isEmpty(Disk disk) {
		return (disk == null);
	}

	private boolean isOccupied(int loc) {
		return (disks[loc] != null && disks[loc] != currentPlayer);
	}

	private boolean isOnBoard(int space) {
		return space >= 0 && space < NUM_DISKS;
	}
	
	/**
	 * Returns status of the current game
	 * 
	 * @return {@code true} iff neither player can make a turn
	 */
	public boolean isOver() {
		if (passMove()) {
			nextPlayer();
			if (passMove()) {
				getWinner();
				return true;
			}
		}
		return false;
	}
	
	private boolean isValidMove(int loc) {
		return !isOver() && isOnBoard(loc) && isEmpty(disks[loc]);
	}
	
	/**
	 * Sets current player to next player
	 */
	public void nextPlayer() {
		this.currentPlayer = (getCurrentPlayer() == Disk.DARK) ? Disk.LIGHT : Disk.DARK;
	}

	/**
	 * Returns the player's ability to pass to the next player
	 * 
	 * @return {@code true} if player has no possible moves
	 */
	public boolean passMove() {
		for (Rows rows : Rows.values()) {
			for (var row : rows.rows) {
				for (var space : row) {
					if (disks[space] == null) {
						List<int[]> foundRow = findRows(space);
						if (!foundRow.isEmpty()) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Places disk on specified location and updates to next player's turn
	 * 
	 * @param loc location of the disk
	 */
	public boolean placeDisk(int loc) {
		if (isValidMove(loc) && !findRows(loc).isEmpty()) {
			for (int[] row : findRows(loc)) {
				flipDisks(row);
			}
			setTurnString(loc);
			LOGGER.info(getTurnString());
			disks[loc] = currentPlayer;
			nextPlayer();
			return true;
		}
		return false;
	}
	
	private void setTurnString(int loc) {
		turnString = currentPlayer + " selected " + getColumn(loc).toString() + (1 + (loc/8)) + ".";
	}
}

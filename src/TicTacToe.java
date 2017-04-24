public class TicTacToe extends Thread{
	private final int dim = 3;
	public enum Moves{B, X, O}; // B - Blank
	private Moves[][] tile;
	private int moveCount;
	private Moves winner;

	public TicTacToe(){
		createBoard();
	}

	private void createBoard(){
		this.tile = new Moves[dim][dim];

		for (int i=0; i<dim; i++) {
			for (int j=0; j<dim; j++) {
				tile[i][j] = Moves.B;
			}
		}
	}

	public void move(int x, int y, Moves m) {
		if (tile[x][y] == Moves.B) {
			moveCount++;
			tile[x][y] = m;
			checkWinner(x, y, m);
		}
	}

	// Checks if there is a winner or call for a draw.
	private void checkWinner(final int x, final int y, final Moves m) {
		Thread[] checker = new Thread[4];

		checker[0] = new Thread() {
			@Override
			public void run() {
				if(winner == null) {
					winner = checkRow(x, y, m);
				}
			}
		};
		checker[1] = new Thread() {
			@Override
			public void run() {
				if(winner == null) {
					winner = checkCol(x, y, m);
				}
			}
		};
		checker[2] = new Thread() {
			@Override
			public void run() {
				if(winner == null) {
					winner = checkDiag(x, y, m);
				}
			}
		};
		checker[3] = new Thread() {
			@Override
			public void run() {
				if(winner == null) {
					winner = checkOtherDiag(x, y, m);
				}
			}
		};

		// Starts the threads.
		for(int i = 0; i < 4; i++) {
			try {
				checker[i].start();
			} catch(Exception e) {}
		}
		for(int i = 0; i < 4; i++) {
			try {
				checker[i].join();
			} catch(Exception e) {}
		}
	}

	private synchronized Moves checkRow(int x, int y, Moves m) {
		// Checks the row
		for(int i = 0; i < dim; i++) {
			if(tile[x][i] != m) {
				return null;
			}
		}
		return m;
	}

	private synchronized Moves checkCol(int x, int y, Moves m) {
		// Checks the column
		for(int i = 0; i < dim; i++) {
			if(tile[i][y] != m) {
				return null;
			}
		}
		return m;
	}

	private synchronized Moves checkDiag(int x, int y, Moves m) {
		// Checks the diagonal
		for(int i = 0; i < dim; i++) {
			if(tile[i][i] != m) {
				return null;
			}
		}
		return m;
	}

	private synchronized Moves checkOtherDiag(int x, int y, Moves m) {
		//Checks the other diagonal
		for(int i = 0; i < dim; i++) {
			if(tile[i][dim - 1 - i] != m) {
				return null;
			}
		}
		return m;
	}

	// Returns the winner of the round.
	public Moves getWinner() {
		return this.winner;
	}

	// Returns the number of moves both players have made.
	public int getMoveCount() {
		return this.moveCount;
	}

	// Prints the 3x3 grid on the console.
	public void printTile() {
		System.out.println("\t\t+---+---+---+");
		for(int i = 0; i < dim; i++) {
			System.out.print("\t\t| ");
			for(int j = 0; j < dim; j++) {
				System.out.print(tile[i][j] + " | ");
			}
			System.out.println("\n\t\t+---+---+---+");
		}
	}
}
import java.util.LinkedList;

public class State {
	
	private int dim = 3;
	private char[][] config;
	
	private State parent;
	
	private boolean terminal_node;
	private boolean max_node;
	private boolean min_node;
	
	private char currentPlayer;
	private int utilityX;
	private int utilityO;
	
	private LinkedList<State> successors;
	
	public State(char[][] config, char currentPlayer) {
		this.config = new char[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				this.config[i][j] = config[i][j];
			}
		}
		this.currentPlayer = currentPlayer;
		this.terminal_node = false;
		this.max_node = false;
		this.min_node = false;
	}

	/* turn = 1 - X, turn = 0 - O*/
	public void generateActions() {
		successors = new LinkedList<State>();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (this.config[i][j] == '-') {
					State state;
					if (this.currentPlayer == 'X') {
						state = new State(result(config, i, j, 'X'), 'O');
						config[i][j] = '-';
						if (state.checkWin() || state.checkDraw()) {
							state.setTerminalNode()
						}
						state.setParent(this);
					} else {
						state = new State(result(config, i, j, 'O'), 'X');
						config[i][j] = '-';
						if (state.checkWin() || state.checkDraw()) {
							state.setTerminalNode()
						}
						state.setParent(this);
					}
					successors.add(state);;
				}

			}
		}
	}

	public char[][] result(char[][] config, int row, int col, char c){
		config[row][col] = c;
		return config;
	}


	public boolean checkWin(){
		return (checkRows() || checkCols() || checkDiags());
	}
	public boolean checkRows(){
		for (int i = 0; i < SIZE; i++) {
			if (checkRowCol(config[i][0], config[i][1], config[i][2])) return true;
		}
		return false;
	}
	public boolean checkCols(){
		for (int i = 0; i < SIZE; i++) {
			if (checkRowCol(config[0][i], config[1][i], config[2][i])) return true;
		}
		return false;
	}
	public boolean checkDiags(){
		return (checkRowCol(config[0][0], config[1][1], config[2][2]) || checkRowCol(config[0][2], config[1][1], config[2][0]));
	}
	public boolean checkRowCol(char c1, char c2, char c3){
		if (c1 != '-' && (c1 == c2) && (c2 == c3)) {
			// JOptionPane.showMessageDialog(null, c1 + " wins!");
			if (c1 == 'X') {
				utilityX = 1;
				utilityO = -1;
			}
			else if (c1 == 'O') {
				utilityO = 1;
				utilityX = -1;
			}
			return true;
		}
		return false;
	}

	public boolean checkDraw(){
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (config[i][j] == '-') {
					return false;
				}
			}
		}
		// JOptionPane.showMessageDialog(null, "Draw!");
		utilityX = 0;
		utilityO = 0;
		return true;
	}


	/* Setters */
	public void setTerminalNode() {
		this.terminal_node = true;
	}
	public void setMaxNode() {
		this.max_node = true;
	}
	public void setMinNode() {
		this.min_node = true;
	}
	public void setParent(State parent){
		this.parent = parent;
	}

	/* Getters */
	public boolean getTerminalNode() {
		return terminal_node;
	}
	public boolean getMaxNode() {
		return max_node;
	}

	public boolean getMinNode() {
		return min_node;
	}
	public char[][] getBoard() {
		return config;
	}
	public LinkedList<State> getSuccessors(){
		return successors;
	}
}
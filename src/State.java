import java.util.LinkedList;

public class State {
	
	private int dim = 3;
	private char[][] config;
	
	private State parent;
	private boolean terminal_node;
	private boolean max_node;
	private boolean min_node;
	
	private char currentPlayer;
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
						state.setParent();
					} else {
						state = new State(result(config, i, j, 'O'), 'X');
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
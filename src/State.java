public class State {
	
	private TicTacToe.Moves[][] tiles;
	private boolean terminal_node;
	private boolean max_node;
	private boolean min_node;
	private int utility;
	
	public State(TicTacToe.Moves[][] tiles) {
		this.tiles = tiles;
		this.terminal_node = false;
		this.max_node = false;
		this.min_node = false;
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

	public void setUtility(int utility) {
		this.utility = utility;
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

	public int getUtility() {
		return utility;
	}
}
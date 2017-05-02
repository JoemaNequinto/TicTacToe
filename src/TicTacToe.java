import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class TicTacToe {
	
	private static final int SIZE = 3;
	private JButton[][] tiles;
	private char[][] config;
	
	private char PLAYER;
	private char AI;
	private char currentPlayer;
	
	public TicTacToe(){
		this.tiles = new JButton[SIZE][SIZE];
		this.config = new char[SIZE][SIZE];
		init();
	}

	public void init(){
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.config[i][j] = '-';
			}
		}
	}

	public void addComponentsToPane (JFrame frame) {

		Container c = frame.getContentPane();
		JPanel mainPanel = new JPanel();
		JPanel topPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();

		JLabel header = new JLabel("TicTacToe", SwingConstants.CENTER);
		header.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		mainPanel.setLayout(new BorderLayout(30, 30));
		
		centerPanel.setLayout(new GridLayout(SIZE, SIZE, 10, 10));
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				final int a = i;
				final int b = j;
				tiles[a][b] = new JButton();
				tiles[a][b].setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(0, 0, 0, 0)));
				tiles[a][b].setFocusPainted(false);
				tiles[a][b].setFont(new Font("Tahoma", Font.BOLD, 30));
				tiles[a][b].setBackground(Color.BLACK);
				tiles[a][b].addActionListener(new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						
						// Player Turn
						tiles[a][b].setEnabled(false);
						tiles[a][b].setText(String.valueOf(currentPlayer));
						toggle(a, b, currentPlayer);
						switchPlayer();
						// AI Turn
						State state = new State(config, currentPlayer, AI);
						aiMove(state);
						switchPlayer();
					
					}
				});

				centerPanel.add(tiles[a][b]);
			}
		}
		
		topPanel.add(header);

		mainPanel.add(topPanel, BorderLayout.PAGE_START);
		mainPanel.add(leftPanel, BorderLayout.LINE_START);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(rightPanel, BorderLayout.LINE_END);
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		c.add(mainPanel);

		chooseFirst();
	}

	public void createAndShowGUI () {
		JFrame frame = new JFrame("TicTacToe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 500));
		addComponentsToPane(frame);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	public void chooseFirst() {
		String[] options = {"Player", "AI"};

		int n = JOptionPane.showOptionDialog(null, "Who'll start?", "Choose Your Turn!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		if (n == -1) System.exit(0);
		else if (n == 1) {
			// AI first turn
			AI = 'X';
			PLAYER = 'O';
			currentPlayer = AI;
			
			State state = new State(config, currentPlayer, AI);
			aiMove(state);
			switchPlayer();

		} else if (n == 0) {
			// Player first turn
			PLAYER = 'X';
			AI = 'O';
			currentPlayer = PLAYER;
		}
	}
	public void updateConfig(State state){
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				config[i][j] = state.getBoard()[i][j];
				if (state.getBoard()[i][j] != '-') {
					tiles[i][j].setEnabled(false);
					tiles[i][j].setText(String.valueOf(state.getBoard()[i][j]));
				}
			}
		}
		if (checkWin() || checkDraw()) System.exit(0);
	}
	public boolean equalBoard(State s1, State s2){
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (s1.getBoard()[i][j] != s2.getBoard()[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	public void toggle(int x, int y, char c) {
		if (config[x][y] == '-') {
			config[x][y] = c;
			if (checkWin() || checkDraw()) System.exit(0);
		}
	}
	public void switchPlayer(){
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
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
			if (c1 == AI) {
				JOptionPane.showMessageDialog(null, c1 + ": AI wins!");
			} else {
				JOptionPane.showMessageDialog(null, c1 + ": Player wins!");
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
		JOptionPane.showMessageDialog(null, "Draw!");
		return true;
	}

	// public void resetGame () {
	// 	for (int i = 0; i < SIZE; i++) {
	// 		for (int j = 0; j < SIZE; j++) {
	// 			tiles[i][j].setEnabled(true);
	// 			tiles[i][j].setText("");
	// 			config[i][j] = '-';
	// 		}
	// 	}
	// 	chooseFirst();
	// }
	
	public void aiMove(State state) {
		MinMax minmax = new MinMax();
		State aiMove = minmax.value(state, -99999, 99999);
		
		while (equalBoard(aiMove.getParent(), state) == false) {
			aiMove = aiMove.getParent();
		}
		
		updateConfig(aiMove);
	}

}
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Game {
	
	private static final int dim = 3;
	private static TicTacToe ttt = new TicTacToe();
	private static JButton[][] tiles;
	private static int counter = 1;

	public static void addComponentsToPane (JFrame frame) {
		tiles = new JButton[dim][dim];

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
		
		centerPanel.setLayout(new GridLayout(dim, dim, 10, 10));
		
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				final int a = i;
				final int b = j;
				tiles[a][b] = new JButton();
				tiles[a][b].setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(0, 0, 0, 0)));
				tiles[a][b].setFocusPainted(false);
				tiles[a][b].setForeground(Color.YELLOW);
				tiles[a][b].setFont(new Font("Tahoma", Font.BOLD, 30));
				tiles[a][b].setBackground(Color.BLACK);
				tiles[a][b].addActionListener(new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						
						tiles[a][b].setEnabled(false);
						if (counter == 1) {
							tiles[a][b].setText("X");
							ttt.move(a, b, TicTacToe.Moves.X);
							counter = 0;
						} else {
							tiles[a][b].setText("O");
							ttt.move(a, b, TicTacToe.Moves.O);
							counter = 1;
						}

						if (ttt.getWinner() != null) {
							JOptionPane.showMessageDialog(mainPanel, ttt.getWinner() + " wins!");
							reset(tiles);
						} else if (ttt.getMoveCount() == 9) {
							JOptionPane.showMessageDialog(mainPanel, "Draw!");
							reset(tiles);
						}
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

	}

	public static void createAndShowGUI () {
		JFrame frame = new JFrame("TicTacToe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 500));
		addComponentsToPane(frame);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	public static void reset(JButton[][] tiles){
		ttt = new TicTacToe();
		counter = 1;
		for (int i=0; i<dim; i++) {
			for (int j=0; j<dim; j++) {
				tiles[i][j].setEnabled(true);
				tiles[i][j].setText("");
			}
		}
	}

	/*
	value(s){
		if s is terminal_node : return utility(s)
		if s is max_node : return max_value(s)
		if s is min_node : return min_value(s)
	}

	max_value(s){
		m = -infinity
		for a, s' in successors		// s' = result(s, a) // a -> action
			v = value(s')
			m = max(m, v)
		return m
	}

	min_value(s){
		m = +infinity
		for a, s' in successors // s' = result(s, a) // a -> action
			v = value(s')
			m = min(m, v)
		return m
	}
	*/
}
public class Main {
	
	private static TicTacToe ttt = new TicTacToe();

	public static void main (String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable () {
			public void run () {
				ttt.createAndShowGUI();
			}
		});
	}

}
public class MinMax {

	public State value (State s) {
		if (s.getTerminalNode()) {
			return utility(s);
		} else if (s.getMaxNode()) {
			return max_value(s);
		} else if (s.getMinNode()) {
			return min_value(s);
		}
		return null;
	}

	public State max_value(State state) {
		int m = -99999;
		State returnState = null;
		
		state.generateActions();

		for (State s : state.getSuccessors()) {
			v = value(s);
			m = max(m, v.value);
			// System.out.println("V: " + v.value + "\nM: " + m);
		}
		// Utility utility = new Utility(m, v.row, v.column);
		return utility;
	}

	public State min_value(State state) {
		int m = 99999;
		State returnState = null;

		state.generateActions();
		
		for (State s : state.getSuccessors()) {
			v = value(s);
			m = min(m, v.value);
		}
		// Utility utility = new Utility(m, v.row, v.column);
		return utility;
	}

	public int min(int m, int v) {
		return m < v ? m : v;
	}

	public int max(int m, int v) {
		return m > v ? m : v;
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
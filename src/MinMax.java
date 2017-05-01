public class MinMax {

	public static State value (State s) {
		if (s.getTerminalNode()) {
			return s;
		} else if (s.getMaxNode()) {
			return max_value(s);
		} else if (s.getMinNode()) {
			return min_value(s);
		}
		return null;
	}

	public static State max_value(State state) {
		int m = -99999;
		State returnState = null;
		
		state.generateActions();

		for (State s : state.getSuccessors()) {
			State v = value(s);

			if (v.getUtility() > m) {
				m = v.getUtility();
				returnState = s;
			}
		}
		return returnState;
	}

	public static State min_value(State state) {
		int m = 99999;
		State returnState = null;

		state.generateActions();
		
		for (State s : state.getSuccessors()) {
			State v = value(s);
			
			if (v.getUtility() < m) {
				m = v.getUtility();
				returnState = s;
			}
		}
		return returnState;
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
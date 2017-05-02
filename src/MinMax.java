public class MinMax {

	public static State value (State s, int alpha, int beta) {
		if (s.getTerminalNode()) {
			return s;
		} else if (s.getMaxNode()) {
			return max_value(s, alpha, beta);
		} else if (s.getMinNode()) {
			return min_value(s, alpha, beta);
		}
		return null;
	}

	public static State max_value(State state, int alpha, int beta) {
		int m = -99999;
		State returnState = null;
		
		state.generateActions();

		for (State s : state.getSuccessors()) {
			State v = value(s, alpha, beta);

			if (v.getUtility() > m) {
				m = v.getUtility();
				returnState = s;
				returnState.setUtility(v.getUtility());
			}

			if (m >= beta) return returnState;
			alpha = alpha > m ? alpha : m;
		}
		return returnState;
	}

	public static State min_value(State state, int alpha, int beta) {
		int m = 99999;
		State returnState = null;

		state.generateActions();
		
		for (State s : state.getSuccessors()) {
			State v = value(s, alpha, beta);
			
			if (v.getUtility() < m) {
				m = v.getUtility();
				returnState = s;
				returnState.setUtility(v.getUtility());
			}

			if (m <= alpha) return returnState;
			beta = beta < m ? beta : m;
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

/*
	Alpha-Beta Pruning



value(s, alpha, beta)
	if s is terminal : return utility(s)
	if s is max_node : return max_value(s, alpha, beta)
	if s is min_node : return min_value(s, alpha, beta)



max_value(s, alpha, beta)
	v = -infinity
	for a, s' in successors(s)
		v = max(v, value(s, alpha, beta))
		if (v >= beta) return v
		alpha = max(alpha, v)
	return v



min_value(s, alpha, beta)
	v = +infinity
	for a, s' in successors(s)
		v = min(v, value(s, alpha, beta))
		if (v <= alpha) return v
		beta = min(v, beta)
	return v



alpha - best value for max_node on the way to the root (init: -infinity)
beta - best value for min_node on the way to the root (init: +infinity)
*/
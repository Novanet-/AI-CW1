package search;

import java.util.Stack;

import game.board.BoardState;

public class Search
{

	/**
	 * @param solutionState
	 *            The final state of a solution
	 * @return A stack containing all the board states leading to the solution, minus the start state
	 */
	protected static Stack<BoardState> calculateSolutionPath(Node solutionState)
	{
		Stack<BoardState> solutionPath = new Stack<BoardState>();
		while (solutionState.getParent() != null)
		{
			solutionPath.push(solutionState.getVal());
			solutionState = solutionState.getParent();
		}
		return solutionPath;
	}

}

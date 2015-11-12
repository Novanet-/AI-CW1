package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import game.GameResult;
import game.board.BoardState;
import utilities.Tree;

public class Search
{
	
	/**
	 * @param solutionState
	 *            The final state of a solution
	 * @return A stack containing all the board states leading to the solution, minus the start state
	 */
	protected static Stack<BoardState> calculateSolutionPath(Tree<BoardState> solutionState)
	{
		Stack<BoardState> solutionPath = new Stack<BoardState>();
		while (solutionState.getParent() != null)
		{
			solutionPath.push(solutionState.getVal());
			solutionState = solutionState.getParent();
		}
		return solutionPath;
	}



	public static GameResult iterativeDeepening(BoardState initBoardState)
	{
		return null;

	}


	public static GameResult aStar(BoardState initBoardState)
	{
		return null;

	}

}

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
	 * Performs a depth first search on the given initial board state to calculate the moves the agent can perform to
	 * make the board reach a goal state
	 * 
	 * @param initBoardState
	 *            The start state of the game
	 * @return An object containing the solution path, number of nodes expanded, and number of moves to reach solution
	 * @throws NoSolutionPossibleException
	 *             If a goal state can't be reached from the start state
	 */
	public static GameResult depthFirst(BoardState initBoardState) throws NoSolutionPossibleException
	{
		Tree<BoardState> searchTree = new Tree<BoardState>(initBoardState, null, new ArrayList<Tree<BoardState>>());
		HashSet<BoardState> visitedBoardStates = new HashSet<BoardState>();

		int nodeCounter = 1;
		boolean goalStateFound = false;

		while (!(goalStateFound))
		{
			BoardState currentBoardState = searchTree.getVal();
			if (currentBoardState.isGoalState())
			{
				goalStateFound = true;

				Stack<BoardState> solutionPath = calculateSolutionPath(searchTree);
				return new GameResult(solutionPath, nodeCounter, solutionPath.size());
			}

			visitedBoardStates.add(currentBoardState);

			ArrayList<BoardState> possibleMoves = currentBoardState.generatePossibleMoves();

			ArrayList<Tree<BoardState>> nextMoves = searchTree.getChildren();
			for (BoardState bState : possibleMoves)
			{
				if (!(visitedBoardStates.contains(bState))) //If one of the possible moves has already been visited, then do not add it to nextMoves
				{
					nextMoves.add(new Tree<BoardState>(bState, searchTree, new ArrayList<Tree<BoardState>>()));
					nodeCounter++;
				}
			}

			if (nextMoves.isEmpty())
			{
				if (!(searchTree.getParent() == null)) //If there are no valid moves, and the current node has  parent, move up to the parent
				{
					searchTree = searchTree.getParent();
				}
				else
				{
					throw new NoSolutionPossibleException();
				}
			}
			else
			{
				Random rand = new Random();
				searchTree = nextMoves.get(rand.nextInt(nextMoves.size())); //Picks a random element out of the list of next moves
			}

		}
		return null;
	}


	/**
	 * @param solutionState
	 *            The final state of a solution
	 * @return A stack containing all the board states leading to the solution, minus the start state
	 */
	private static Stack<BoardState> calculateSolutionPath(Tree<BoardState> solutionState)
	{
		Stack<BoardState> solutionPath = new Stack<BoardState>();
		while (solutionState.getParent() != null)
		{
			solutionPath.push(solutionState.getVal());
			solutionState = solutionState.getParent();
		}
		return solutionPath;
	}


	public static GameResult breadthFirst(BoardState initBoardState)
	{
		return null;

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

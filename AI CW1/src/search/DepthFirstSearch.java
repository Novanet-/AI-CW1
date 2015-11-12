/**
 * 
 */
package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

import game.GameResult;
import game.board.BoardState;
import utilities.Tree;

/**
 * @author Will
 *
 */
public class DepthFirstSearch extends Search
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
			ArrayList<Tree<BoardState>> nextMoves = filterOutSeenStates(searchTree, possibleMoves, visitedBoardStates);
			
			nodeCounter = nodeCounter + nextMoves.size();

			if (nextMoves.isEmpty())
			{
				searchTree = moveUpToParent(searchTree);
			}
			else
			{
				searchTree = expandRandomChild(nextMoves);
			}

		}
		return null;
	}

	/**
	 * @param nextMoves
	 * @return
	 */
	private static Tree<BoardState> expandRandomChild(ArrayList<Tree<BoardState>> nextMoves)
	{
		Tree<BoardState> searchTree;
		Random rand = new Random();
		searchTree = nextMoves.get(rand.nextInt(nextMoves.size())); //Picks a random element out of the list of next moves
		return searchTree;
	}

	/**
	 * @param searchTree
	 * @return
	 * @throws NoSolutionPossibleException
	 */
	private static Tree<BoardState> moveUpToParent(Tree<BoardState> searchTree) throws NoSolutionPossibleException
	{
		if (!(searchTree.getParent() == null)) //If there are no valid moves, and the current node has  parent, move up to the parent
		{
			searchTree = searchTree.getParent();
		}
		else
		{
			throw new NoSolutionPossibleException();
		}
		return searchTree;
	}
	
	private static ArrayList<Tree<BoardState>> filterOutSeenStates(Tree<BoardState> searchTree, ArrayList<BoardState> possibleMoves, HashSet<BoardState> visitedBoardStates)
	{
		ArrayList<Tree<BoardState>> nextMoves = searchTree.getChildren();
		for (BoardState bState : possibleMoves)
		{
			if (!(visitedBoardStates.contains(bState))) //If one of the possible moves has already been visited, then do not add it to nextMoves
			{
				nextMoves.add(new Tree<BoardState>(bState, searchTree, new ArrayList<Tree<BoardState>>()));
			}
		}
		return nextMoves;
	}
}

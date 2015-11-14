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
public class IterativeDeepeningSearch extends Search
{

	static int	maxLevel;
	static int	currentLevel;


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
	public static GameResult iterativeDeepening(BoardState initBoardState)
	{
		GameResult solution = new GameResult(false, null, null, null);
		int maxDepth = 1;
		int highestLevelReached = 0;

		while (solution.isSolutionFound() == false)
		{
			try
			{
				solution = depthFirst(initBoardState, maxDepth);
				if (solution.getAgentMoves() > highestLevelReached)
				{
					highestLevelReached++;
					maxDepth++;
				}
				else
				{
					break;
				}
			}
			catch (NoSolutionPossibleException e)
			{
				e.printStackTrace();
			}
		}
		return solution;
	}


	private static GameResult depthFirst(BoardState initBoardState, int maxDepth) throws NoSolutionPossibleException
	{
		int currentLevel = 0;
		Tree<BoardState> currentNode = new Tree<BoardState>(initBoardState, null, new ArrayList<Tree<BoardState>>());
		HashSet<BoardState> visitedBoardStates = new HashSet<BoardState>();

		int nodeCounter = 1;
		boolean goalStateFound = false;

		while (!(goalStateFound))
		{
			BoardState currentBoardState = currentNode.getVal();
			if (currentBoardState.isGoalState())
			{
				goalStateFound = true;
				Stack<BoardState> solutionPath = calculateSolutionPath(currentNode);
				return new GameResult(true, solutionPath, nodeCounter, solutionPath.size());
			}

			visitedBoardStates.add(currentBoardState);

			ArrayList<Tree<BoardState>> possibleMoves = currentBoardState.generatePossibleMoves(currentNode);
			ArrayList<Tree<BoardState>> nextMoves = filterOutSeenStates(currentNode, possibleMoves, visitedBoardStates); //nextMoves: pointer to the children of the current ndoe

			nodeCounter = nodeCounter + nextMoves.size();

			if (nextMoves.isEmpty())
			{
				currentNode = moveUpToParent(currentNode);
			}
			else
			{
				if (currentLevel < maxDepth)
				{
					currentLevel++;
					currentNode = expandRandomChild(nextMoves);
				}
				else
				{
					return new GameResult(false, null, nodeCounter, currentLevel + 1);
				}

			}

		}
		return new GameResult(false, null, nodeCounter, currentLevel);
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
	 * @param currentNode
	 * @return
	 * @throws NoSolutionPossibleException
	 */
	private static Tree<BoardState> moveUpToParent(Tree<BoardState> currentNode) throws NoSolutionPossibleException
	{
		if (!(currentNode.getParent() == null)) //If there are no valid moves, and the current node has  parent, move up to the parent
		{
			currentNode = currentNode.getParent();
		}
		else
		{
			throw new NoSolutionPossibleException();
		}
		return currentNode;
	}


	private static ArrayList<Tree<BoardState>> filterOutSeenStates(Tree<BoardState> currentNode, ArrayList<Tree<BoardState>> possibleMoves,
			HashSet<BoardState> visitedBoardStates)
	{
		ArrayList<Tree<BoardState>> nextMoves = currentNode.getChildren();
		for (Tree<BoardState> bState : possibleMoves)
		{
			if (!(visitedBoardStates.contains(bState))) //If one of the possible moves has already been visited, then do not add it to nextMoves
			{
				nextMoves.add(new Tree<BoardState>(bState.getVal(), currentNode, new ArrayList<Tree<BoardState>>()));
			}
		}
		return nextMoves;
	}
}

/**
 * 
 */
package search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Stack;

import game.GameResult;
import game.board.BoardState;

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
	public static GameResult depthFirst(BoardState initBoardState)
	{	
		int currentLevel = 0;
		Node currentNode = new Node(initBoardState, null, new ArrayList<Node>(), currentLevel);
		HashSet<BoardState> visitedBoardStates = new HashSet<BoardState>();
		Deque<Node> fringe = new ArrayDeque<Node>();

		int nodeCounter = 0;
		boolean goalStateFound = false;

		fringe.addFirst(currentNode);
		BoardState currentBoardState;

		while ((!(goalStateFound) || !(fringe.isEmpty())))
		{
			currentNode = fringe.removeFirst();
			currentBoardState = currentNode.getVal();
			currentLevel = currentNode.getDepth();

			if (currentBoardState.isGoalState())
			{
				goalStateFound = true;
				Stack<BoardState> solutionPath = calculateSolutionPath(currentNode);
				return new GameResult(true, solutionPath, visitedBoardStates.size(), solutionPath.size());
			}
			else
			{
				if (visitedBoardStates.add(currentBoardState))
				{
					nodeCounter++;
				}
				ArrayList<Node> possibleMoves = currentBoardState.generatePossibleMoves(currentNode, currentLevel);
				ArrayList<Node> nextMoves = filterOutSeenStates(currentNode, possibleMoves, visitedBoardStates, currentLevel);
				while (!(nextMoves.isEmpty()))
				{
					fringe.addFirst(nextMoves.remove(0));
				}
			}
		}
		return new GameResult(false, null, nodeCounter, 0);
	}

	
	private static ArrayList<Node> filterOutSeenStates(Node currentNode, ArrayList<Node> possibleMoves, HashSet<BoardState> visitedBoardStates, int currentLevel)
	{
		ArrayList<Node> nextMoves = currentNode.getChildren();
		nextMoves.clear();
		for (Node possibleMove : possibleMoves)
		{
			BoardState succesorBState = possibleMove.getVal();
			if (!(visitedBoardStates.contains(succesorBState))) //If one of the possible moves has already been visited, then do not add it to nextMoves
			{
				nextMoves.add(new Node(succesorBState, currentNode, new ArrayList<Node>(), currentLevel + 1));
			}
		}
		return nextMoves;
	}

}

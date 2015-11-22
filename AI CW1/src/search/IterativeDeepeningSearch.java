/**
 * 
 */
package search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

import game.GameResult;
import game.board.BoardState;

public class IterativeDeepeningSearch extends Search
{

	/**
	 * Performs a search that keeps calling a depth first search, iterating the max depth it can search to, until a
	 * solution is found
	 * 
	 * @param initBoardState
	 *            The start state of the game
	 * @return An object containing the solution path, number of nodes expanded, and number of moves to reach solution
	 */
	public static GameResult iterativeDeepening(BoardState initBoardState)
	{

		int maxDepth = 0;
		boolean goalStateFound = false;
		Node rootNode = new Node(initBoardState, null, new ArrayList<Node>(), 0);
		while (!(goalStateFound))
		{
			GameResult searchResult = altDFS(rootNode, maxDepth);
			if (searchResult.isSolutionFound())
			{
				return searchResult;
			}
			maxDepth++;
		}
		return new GameResult(false, null, null, null);
	}


	/**
	 * A depth first search with a max depth parameter, stopping the search when all the nodes at a given max depth have
	 * been explored
	 * 
	 * @param node
	 *            The start node
	 * @param maxDepth
	 *            The maximum depth to search to
	 * @return A GameResult object, which contains a parameter on whether the search has found a solution or not
	 */
	private static GameResult altDFS(Node node, int maxDepth)
	{
		int currentDepth = -1;
		Node currentNode = node;
		HashSet<Node> visitedBoardStates = new HashSet<Node>();
		Deque<Node> fringe = new ArrayDeque<Node>();

		int nodeCounter = 0;

		fringe.addFirst(currentNode);
		BoardState currentBoardState;

		while (!(fringe.isEmpty()))
		{
			currentNode = fringe.removeFirst();
			currentBoardState = currentNode.getVal();

			currentDepth = currentNode.getDepth();

			if (currentBoardState.isGoalState())
			{
				Stack<BoardState> solutionPath = calculateSolutionPath(currentNode);
				return new GameResult(true, solutionPath, visitedBoardStates.size(), solutionPath.size());
			}
			else if (currentDepth < maxDepth)
			{

				if (visitedBoardStates.add(currentNode))
				{
					nodeCounter++;
				}
				ArrayList<Node> possibleMoves = currentBoardState.generatePossibleMoves(currentNode, currentDepth);
				currentNode.setChildren(possibleMoves);
				ArrayList<Node> nextMoves = filterOutSeenStates(currentNode, possibleMoves, visitedBoardStates, currentDepth);
				for (Node move : nextMoves)
				{
					fringe.addFirst(move);
				}
			}
		}
		return new GameResult(false, null, nodeCounter, 0);
	}


	/**
	 * Iterates through the list of possible moves from the current node, checking for each one if it is in the set of
	 * visited board states, if it is, check if the depth of it is lower than the depth of the node when it was last
	 * seen
	 * 
	 * @param currentNode
	 * @param possibleMoves
	 * @param visitedNodes
	 * @param currentDepth
	 * @return
	 */
	private static ArrayList<Node> filterOutSeenStates(Node currentNode, ArrayList<Node> possibleMoves, HashSet<Node> visitedNodes, int currentDepth)
	{
		ArrayList<Node> nextMoves = new ArrayList<Node>(currentNode.getChildren());
		nextMoves.clear();
		for (Node possibleMove : possibleMoves)
		{
			boolean shouldAddToFringe = true;
			BoardState succesorBState = possibleMove.getVal();
			Iterator<Node> visitedNodesIterator = visitedNodes.iterator();

			while (visitedNodesIterator.hasNext())
			{
				Node nextnode = visitedNodesIterator.next();
				if ((succesorBState.equals(nextnode.getVal())) && (currentDepth >= nextnode.getDepth()))
				{
					shouldAddToFringe = false;
				}
			}

			if (shouldAddToFringe)
				nextMoves.add(new Node(succesorBState, currentNode, new ArrayList<Node>(), currentDepth + 1));
		}
		return nextMoves;
	}
}

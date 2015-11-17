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

/**
 * @author Will
 *
 */
public class IterativeDeepeningSearch extends Search
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
	//	public static GameResult iterativeDeepening(BoardState initBoardState)
	//	{
	//		GameResult solution = new GameResult(false, null, null, null);
	//		int maxDepth = 1;
	//		int highestLevelReached = 0;
	//
	//		while (solution.isSolutionFound() == false)
	//		{
	//			try
	//			{
	//				solution = depthFirst(initBoardState, maxDepth);
	//				if (solution.getAgentMoves() > highestLevelReached)
	//				{
	//					highestLevelReached++;
	//					maxDepth++;
	//				}
	//				else
	//				{
	//					break;
	//				}
	//			}
	//			catch (NoSolutionPossibleException e)
	//			{
	//				e.printStackTrace();
	//			}
	//		}
	//		return solution;
	//	}
	//
	//
	//	private static GameResult depthFirst(BoardState initBoardState, int maxDepth) throws NoSolutionPossibleException
	//	{
	//		int currentLevel = 0;
	//		Node currentNode = new Node(initBoardState, null, new ArrayList<Node>());
	//		HashSet<Node> visitedNode = new HashSet<Node>();
	//
	//		int nodeCounter = 1;
	//		boolean goalStateFound = false;
	//
	//		while (!(goalStateFound))
	//		{
	//			BoardState currentBoardState = currentNode.getVal();
	//			if (currentBoardState.isGoalState())
	//			{
	//				goalStateFound = true;
	//				Stack<BoardState> solutionPath = calculateSolutionPath(currentNode);
	//				return new GameResult(true, solutionPath, visitedBoardStates.size(), solutionPath.size());
	//			}
	//
	//			visitedBoardStates.add(currentBoardState);
	//
	//			ArrayList<Node> possibleMoves = currentBoardState.generatePossibleMoves(currentNode);
	//			ArrayList<Node> nextMoves = filterOutSeenStates(currentNode, possibleMoves, visitedBoardStates); //nextMoves: pointer to the children of the current ndoe
	//
	//			//nodeCounter = nodeCounter + nextMoves.size();
	//
	//			if (nextMoves.isEmpty())
	//			{
	//				currentLevel--;
	//				currentNode = moveUpToParent(currentNode);
	//				if (currentNode == null)
	//				{
	//					return new GameResult(false, null, nodeCounter, maxDepth);
	//				}
	//			}
	//			else
	//			{
	//				if (currentLevel < maxDepth)
	//				{
	//					currentLevel++;
	//					nodeCounter++;
	//					currentNode = expandRandomChild(nextMoves);
	//				}
	//				else
	//				{
	//					currentLevel--;
	//					currentNode = moveUpToParent(currentNode);
	//					if (currentNode == null)
	//					{
	//						return new GameResult(false, null, nodeCounter, maxDepth);
	//					}
	//				}
	//
	//			}
	//
	//		}
	//		return new GameResult(false, null, nodeCounter, currentLevel);
//	}


	public static GameResult altIterativeDeepening(BoardState initBoardState)
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
//					System.out.println(currentBoardState);
//					System.out.println(nodeCounter);

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
//				while (!(nextMoves.isEmpty()))
//				{
//					Random rand = new Random();
//					int index = rand.nextInt(nextMoves.size());
//					Node nextNode = nextMoves.get(index);
//					fringe.addFirst(nextNode);
//				}
				for (Node move : nextMoves)
				{
					fringe.addFirst(move);
				}
			}
		}
		return new GameResult(false, null, nodeCounter, 0);
	}


//	/**
//	 * @param nextMoves
//	 * @return
//	 */
//	private static Node expandRandomChild(ArrayList<Node> nextMoves)
//	{
//		Node searchTree;
//		Random rand = new Random();
//		searchTree = nextMoves.get(rand.nextInt(nextMoves.size())); //Picks a random element out of the list of next moves
//		return searchTree;
//	}
//
//
//	/**
//	 * @param currentNode
//	 * @return
//	 * @throws NoSolutionPossibleException
//	 */
//	private static Node moveUpToParent(Node currentNode) throws NoSolutionPossibleException
//	{
//		if (!(currentNode.getParent() == null)) //If there are no valid moves, and the current node has  parent, move up to the parent
//		{
//			currentNode = currentNode.getParent();
//		}
//		else
//		{
//			return null;
//		}
//		return currentNode;
//	}


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

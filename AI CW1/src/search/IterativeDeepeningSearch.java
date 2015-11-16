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

/**
 * @author Will
 *
 */
public class IterativeDeepeningSearch extends Search
{

	static int	maxLevel;
	static int	currentLevel;
	HashSet<BoardState> visitedBoardStates = new HashSet<BoardState>();


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
		Node currentNode = new Node(initBoardState, null, new ArrayList<Node>());
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

			ArrayList<Node> possibleMoves = currentBoardState.generatePossibleMoves(currentNode);
			ArrayList<Node> nextMoves = filterOutSeenStates(currentNode, possibleMoves, visitedBoardStates); //nextMoves: pointer to the children of the current ndoe

			//nodeCounter = nodeCounter + nextMoves.size();

			if (nextMoves.isEmpty())
			{
				currentLevel--;
				currentNode = moveUpToParent(currentNode);
				if (currentNode == null)
				{
					return new GameResult(false, null, nodeCounter, maxDepth);
				}
			}
			else
			{
				if (currentLevel < maxDepth)
				{
					currentLevel++;
					nodeCounter++;
					currentNode = expandRandomChild(nextMoves);
				}
				else
				{
					currentLevel--;
					currentNode = moveUpToParent(currentNode);
					if (currentNode == null)
					{
						return new GameResult(false, null, nodeCounter, maxDepth);
					}
				}

			}

		}
		return new GameResult(false, null, nodeCounter, currentLevel);
	}


//	public GameResult altIterativeDeepening(BoardState initBoardState)
//	{
//		int depth = 0;
//		boolean goalStateFound = false;
//		Tree<BoardState> rootNode = new Tree<BoardState>(initBoardState, null, new ArrayList<Tree<BoardState>>());
//		while (!(goalStateFound))
//		{
//			GameResult found = altDFS(rootNode, depth);
//			if (found != null)
//			{
//				return found;
//			}
//		}
//		return null;
//	}


//public boolean altDFS(Tree<BoardState> node, int depth)
//{
//    BoardState currentBoardState = node.getVal();
//    if (!(visitedBoardStates.contains(currentBoardState)))
//    {
//    	visitedBoardStates.add(currentBoardState);
//    }
//	if ((depth == 0) && (currentBoardState.isGoalState()))
//	{
//        return true;
//	}
//    else if (depth > 0)
//    {
//    	ArrayList<Tree<BoardState>> possibleMoves = currentBoardState.generatePossibleMoves(node);
//    	ArrayList<Tree<BoardState>> nextMoves = filterOutSeenStates(node, possibleMoves, visitedBoardStates); //nextMoves: pointer to the children of the current node
//    	node.setChildren(nextMoves);
//        for (Tree<BoardState> children : node.getChildren())
//            found = altDFS(child, depth−1)
//            if found ≠ null
//                return true;
//    }
//    return null
//}


	/**
	 * @param nextMoves
	 * @return
	 */
	private static Node expandRandomChild(ArrayList<Node> nextMoves)
	{
		Node searchTree;
		Random rand = new Random();
		searchTree = nextMoves.get(rand.nextInt(nextMoves.size())); //Picks a random element out of the list of next moves
		return searchTree;
	}


	/**
	 * @param currentNode
	 * @return
	 * @throws NoSolutionPossibleException
	 */
	private static Node moveUpToParent(Node currentNode) throws NoSolutionPossibleException
	{
		if (!(currentNode.getParent() == null)) //If there are no valid moves, and the current node has  parent, move up to the parent
		{
			currentNode = currentNode.getParent();
		}
		else
		{
			return null;
		}
		return currentNode;
	}


	private static ArrayList<Node> filterOutSeenStates(Node currentNode, ArrayList<Node> possibleMoves,
			HashSet<BoardState> visitedBoardStates)
	{
		ArrayList<Node> nextMoves = currentNode.getChildren();
		nextMoves.clear();
		for (Node possibleMove : possibleMoves)
		{
			BoardState succesorBState = possibleMove.getVal();
			if (!(visitedBoardStates.contains(succesorBState))) //If one of the possible moves has already been visited, then do not add it to nextMoves
			{
				nextMoves.add(new Node(succesorBState, currentNode, new ArrayList<Node>()));
			}
		}
		return nextMoves;
	}
}

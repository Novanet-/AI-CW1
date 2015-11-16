/**
 * 
 */
package search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Random;
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
	public static GameResult depthFirst(BoardState initBoardState) throws NoSolutionPossibleException
	{
		Node currentNode = new Node(initBoardState, null, new ArrayList<Node>());
		HashSet<BoardState> visitedBoardStates = new HashSet<BoardState>();

		int nodeCounter = 0;
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
			
			if (!(visitedBoardStates.contains(currentBoardState)))
			{
				nodeCounter++;
				visitedBoardStates.add(currentBoardState);
			}
			
//			System.out.println(currentBoardState);
//			System.out.println(nodeCounter);


			ArrayList<Node> possibleMoves = currentBoardState.generatePossibleMoves(currentNode);
			ArrayList<Node> nextMoves = filterOutSeenStates(currentNode, possibleMoves, visitedBoardStates); //nextMoves: pointer to the children of the current ndoe
			

			
			if (nextMoves.isEmpty())
			{
				currentNode = moveUpToParent(currentNode);
			}
			else
			{
				currentNode = expandRandomChild(nextMoves);
			}

		}
		return new GameResult(false, null, nodeCounter, 0);
	}
	
	public static GameResult altDepthFirst(BoardState initBoardState)
	{
		Node currentNode = new Node(initBoardState, null, new ArrayList<Node>());
		HashSet<BoardState> visitedBoardStates = new HashSet<BoardState>();
		Deque<Node> fringe = new ArrayDeque<Node>();

		int nodeCounter = 0;
		boolean goalStateFound = false;

		fringe.addFirst(new Node(initBoardState, null, new ArrayList<Node>()));
		BoardState currentBoardState;

		while ((!(goalStateFound) || !(fringe.isEmpty())))
		{
			currentNode = fringe.removeFirst();
			currentBoardState = currentNode.getVal();
//			System.out.println(currentBoardState);
//			System.out.println(nodeCounter);

			if (currentBoardState.isGoalState())
			{
				goalStateFound = true;
				Stack<BoardState> solutionPath = calculateSolutionPath(currentNode);
				return new GameResult(true, solutionPath, nodeCounter, solutionPath.size());
			}
			else
			{
				visitedBoardStates.add(currentBoardState);
				nodeCounter++;
				ArrayList<Node> possibleMoves = currentBoardState.generatePossibleMoves(currentNode);
				ArrayList<Node> nextMoves = filterOutSeenStates(currentNode, possibleMoves, visitedBoardStates);
				while (!(nextMoves.isEmpty()))
				{
					Random rand = new Random();
					int index = rand.nextInt(nextMoves.size());
					fringe.addFirst(nextMoves.remove(index));
				}
			}
		}
		return new GameResult(false, null, nodeCounter, 0);
	}

	/**
	 * @param nextMoves
	 * @return
	 */
	private static Node expandRandomChild(ArrayList<Node> nextMoves)
	{
		Node currentNode;
		Random rand = new Random();
		currentNode = nextMoves.get(rand.nextInt(nextMoves.size())); //Picks a random element out of the list of next moves
		return currentNode;
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
			throw new NoSolutionPossibleException();
		}
		return currentNode;
	}
	
	private static ArrayList<Node> filterOutSeenStates(Node currentNode, ArrayList<Node> possibleMoves, HashSet<BoardState> visitedBoardStates)
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

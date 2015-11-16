/**
 * 
 */
package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

import game.GameResult;
import game.board.BoardState;

/**
 * @author Will
 *
 */
public class BreadthFirstSearch extends Search
{

	public static GameResult breadthFirst(BoardState initBoardState)
	{
		Node currentNode = new Node(initBoardState, null, new ArrayList<Node>());
		HashSet<BoardState> visitedBoardStates = new HashSet<BoardState>();
		LinkedList<Node> fringe = new LinkedList<Node>();

		int nodeCounter = 1;
		boolean goalStateFound = false;

		fringe.add(new Node(initBoardState, null, new ArrayList<Node>()));
		BoardState currentBoardState;

		while ((!(goalStateFound) || !(fringe.isEmpty())))
		{
			currentNode = fringe.remove();
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
				//ArrayList<Tree<BoardState>> nextMoves = convertMovesToChildTrees(searchTree, possibleMoves, visitedBoardStates);
				ArrayList<Node> nextMoves = filterOutSeenStates(currentNode, possibleMoves, visitedBoardStates, fringe);
				fringe.addAll(nextMoves);
			}
		}
		return new GameResult(false, null, nodeCounter, 0);
	}


	private static ArrayList<Node> filterOutSeenStates(Node currentNode, ArrayList<Node> possibleMoves,
			HashSet<BoardState> visitedBoardStates, LinkedList<Node> fringe)
	{
		ArrayList<Node> nextMoves = currentNode.getChildren();
		for (Node bState : possibleMoves)
		{
			if (!(visitedBoardStates.contains(bState.getVal()))) //If one of the possible moves has already been visited, then do not add it to nextMoves
			{
				boolean nodeInFringe = false;
				for (Node fringeNode : fringe)
				{
					if (fringeNode.getVal().equals(bState.getVal()))
					{
						nodeInFringe = true;
					}
				}
				if (!(nodeInFringe))
				{
					nextMoves.add(new Node(bState.getVal(), currentNode, new ArrayList<Node>()));
				}
			}
		}
		return nextMoves;
	}


	private static ArrayList<Node> convertMovesToChildTrees(Node currentNode, ArrayList<BoardState> possibleMoves,
			HashSet<BoardState> visitedBoardStates)
	{
		ArrayList<Node> nextMoves = currentNode.getChildren();
		for (BoardState bState : possibleMoves)
		{
			nextMoves.add(new Node(bState, currentNode, new ArrayList<Node>()));
		}
		return nextMoves;
	}
}

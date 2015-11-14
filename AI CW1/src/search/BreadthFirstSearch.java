/**
 * 
 */
package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import game.GameResult;
import game.board.BoardState;
import utilities.Tree;

/**
 * @author Will
 *
 */
public class BreadthFirstSearch extends Search
{

	public static GameResult breadthFirst(BoardState initBoardState)
	{
		Tree<BoardState> currentNode = new Tree<BoardState>(initBoardState, null, new ArrayList<Tree<BoardState>>());
		HashSet<BoardState> visitedBoardStates = new HashSet<BoardState>();
		LinkedList<Tree<BoardState>> fringe = new LinkedList<Tree<BoardState>>();

		int nodeCounter = 1;
		boolean goalStateFound = false;

		fringe.add(new Tree<BoardState>(initBoardState, null, new ArrayList<Tree<BoardState>>()));
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
				ArrayList<Tree<BoardState>> possibleMoves = currentBoardState.generatePossibleMoves(currentNode);
				//ArrayList<Tree<BoardState>> nextMoves = convertMovesToChildTrees(searchTree, possibleMoves, visitedBoardStates);
				ArrayList<Tree<BoardState>> nextMoves = filterOutSeenStates(currentNode, possibleMoves, visitedBoardStates, fringe);
				fringe.addAll(nextMoves);
			}
		}
		return new GameResult(false, null, nodeCounter, 0);
	}


	private static ArrayList<Tree<BoardState>> filterOutSeenStates(Tree<BoardState> currentNode, ArrayList<Tree<BoardState>> possibleMoves,
			HashSet<BoardState> visitedBoardStates, LinkedList<Tree<BoardState>> fringe)
	{
		ArrayList<Tree<BoardState>> nextMoves = currentNode.getChildren();
		for (Tree<BoardState> bState : possibleMoves)
		{
			if (!(visitedBoardStates.contains(bState.getVal()))) //If one of the possible moves has already been visited, then do not add it to nextMoves
			{
				boolean nodeInFringe = false;
				for (Tree<BoardState> fringeNode : fringe)
				{
					if (fringeNode.getVal().equals(bState.getVal()))
					{
						nodeInFringe = true;
					}
				}
				if (!(nodeInFringe))
				{
					nextMoves.add(new Tree<BoardState>(bState.getVal(), currentNode, new ArrayList<Tree<BoardState>>()));
				}
			}
		}
		return nextMoves;
	}


	private static ArrayList<Tree<BoardState>> convertMovesToChildTrees(Tree<BoardState> currentNode, ArrayList<BoardState> possibleMoves,
			HashSet<BoardState> visitedBoardStates)
	{
		ArrayList<Tree<BoardState>> nextMoves = currentNode.getChildren();
		for (BoardState bState : possibleMoves)
		{
			nextMoves.add(new Tree<BoardState>(bState, currentNode, new ArrayList<Tree<BoardState>>()));
		}
		return nextMoves;
	}
}

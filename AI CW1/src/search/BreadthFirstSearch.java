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
		Tree<BoardState> searchTree = new Tree<BoardState>(initBoardState, null, new ArrayList<Tree<BoardState>>());
		HashSet<BoardState> visitedBoardStates = new HashSet<BoardState>();
		LinkedList<Tree<BoardState>> fringe = new LinkedList<Tree<BoardState>>();

		int nodeCounter = 1;
		boolean goalStateFound = false;

		fringe.add(new Tree<BoardState>(initBoardState, null, new ArrayList<Tree<BoardState>>()));
		BoardState currentBoardState = fringe.remove().getVal();

		while (!(goalStateFound))
		{
			if (currentBoardState.isGoalState())
			{
				goalStateFound = true;
				Stack<BoardState> solutionPath = calculateSolutionPath(searchTree);
				return new GameResult(solutionPath, nodeCounter, solutionPath.size());
			}
			else
			{
				visitedBoardStates.add(currentBoardState);
				ArrayList<BoardState> possibleMoves = currentBoardState.generatePossibleMoves();
				ArrayList<Tree<BoardState>> nextMoves = filterOutSeenStates(searchTree, possibleMoves, visitedBoardStates);
				fringe.addAll(possibleMoves);
			}
		}

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

package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import game.GameResult;
import game.board.BoardState;
import utilities.Tree;

public class Search
{

	public static GameResult depthFirst(BoardState boardState) throws NoSolutionPossibleException
	{
		Tree<BoardState> searchTree = new Tree<BoardState>(boardState, null, new ArrayList<Tree<BoardState>>());
		HashSet<BoardState> visitedBoardStates = new HashSet<BoardState>();
		int nodeCounter = 1;
		boolean goalStateFound = false;
		while (!(goalStateFound) || (nodeCounter < 6))
		{
			if (searchTree.getVal().isGoalState())
			{
				goalStateFound = true;
				
				Stack<BoardState> solutionPath = calculateSolutionPath(searchTree);
				return new GameResult(solutionPath, nodeCounter, solutionPath.size());
			}
			
			visitedBoardStates.add(searchTree.getVal());

			ArrayList<BoardState> possibleMoves = searchTree.getVal().generatePossibleMoves();

			for (BoardState bState : possibleMoves)
			{
				if (!(visitedBoardStates.contains(bState)))
				{
					searchTree.getChildren().add(new Tree<BoardState>(bState, searchTree, new ArrayList<Tree<BoardState>>()));
					nodeCounter++;
				}
			}
			

			if (searchTree.getChildren().isEmpty())
			{
				if (!(searchTree.getParent() == null))
				{
					searchTree = searchTree.getParent();
				}
				else
				{
					throw new NoSolutionPossibleException();
				}
			}
			else
			{
				Random rand = new Random();
				searchTree = searchTree.getChildren().get(rand.nextInt(searchTree.getChildren().size()));
			}

		}
		return null;

	}


	private static Stack<BoardState> calculateSolutionPath(Tree<BoardState> searchTree)
	{
		Stack<BoardState> solutionPath = new Stack<BoardState>();
		int agentMoves = 0;
		while (searchTree.getParent() != null)
		{
			solutionPath.push(searchTree.getVal());
			agentMoves += 1;
			searchTree = searchTree.getParent();
		}
		System.out.println(agentMoves);
		return solutionPath;
	}


	public static GameResult breadthFirst(BoardState initBoardState)
	{
		return null;

	}


	public static GameResult iterativeDeepening(BoardState initBoardState)
	{
		return null;

	}


	public static GameResult aStar(BoardState initBoardState)
	{
		return null;

	}

}

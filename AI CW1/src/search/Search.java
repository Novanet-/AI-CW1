package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
			System.out.println(searchTree.getVal());
			if (searchTree.getVal().isGoalState())
			{
				goalStateFound = true;
				return new GameResult(searchTree.getVal(), nodeCounter, 0);
			}
			
			visitedBoardStates.add(searchTree.getVal());

			ArrayList<BoardState> possibleMoves = searchTree.getVal().generatePossibleMoves();

			for (BoardState bState : possibleMoves)
			{
				if (!(visitedBoardStates.contains(bState)))
				{
					searchTree.getChildren().add(new Tree<BoardState>(bState, searchTree, new ArrayList<Tree<BoardState>>()));
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
				nodeCounter++;
			}

		}
		return null;

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

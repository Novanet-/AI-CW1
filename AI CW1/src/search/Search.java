package search;

import java.util.HashSet;
import java.util.Set;

import game.GameResult;
import game.board.BoardState;
import utilities.Tree;

public class Search
{

	public static GameResult depthFirst(BoardState boardState)
	{
		Tree<BoardState> searchTree = new Tree<BoardState>(boardState, null, null);
		boolean goalStateFound = false;
		while (!(goalStateFound))
		{
			if (boardState.isGoalState())
			{
				goalStateFound = true;
				return new GameResult(boardState, 0, 0);
			}
			
			HashSet<BoardState> visitedBoardStates = new HashSet<>();
			HashSet<BoardState> possibleMoves = boardState.generatePossibleMoves();
			
			for (BoardState bState : possibleMoves)
			{
				if (!(visitedBoardStates.contains(bState)))
				{
					searchTree.getChildren().add(new Tree<BoardState>(bState, searchTree, children))
				}
			}
			
			
			searchTree.getChildren().add(e)
			
			if (searchTree.getParent() == null && searchTree.getChildren().isEmpty())
			{
				return null;
			}
			
		}

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

package search;

import java.util.Set;

import game.GameResult;
import game.board.BoardState;
import utilities.Tree;

public class Search
{
	
	public static GameResult depthFirst(BoardState initBoardState)
	{
		Tree<BoardState> searchTree = new Tree<BoardState>(initBoardState, null, null);
		Set<BoardState> possibleMoves = initBoardState.generatePossibleMoves();
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

/**
 * 
 */
package game;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * @author Will
 *
 */
public class Game
{
	BoardState boardstate;

	/**
	 * 
	 */
	public Game()
	{
		Rectangle board = null;
		//define board dimensions
		ArrayList<BoardPiece> pieces = null;
		//add board pieces
		boardstate = new BoardState(board, pieces);
	}
	
	public GameResult runGame(BoardState initBoardState, SearchType search)
	{
		GameResult gameResult = null;
		switch (search)
        {
			case DEPTH_FIRST:
				return gameResult;
			default:
				return gameResult;
        }
	}

}

/**
 * 
 */
package game;

import java.awt.Rectangle;
import java.util.ArrayList;

import game.board.BoardState;
import game.piece.Agent;
import game.piece.Block;
import game.piece.Block;
import game.piece.PiecePosition;
import game.piece.PieceType;
import search.SearchType;
import utilities.Pair;

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
		Rectangle board = new Rectangle(4, 4);
		ArrayList<Block> pieces = new ArrayList<Block>();
		pieces.add(new Block("a", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(0,0))));
		pieces.add(new Block("b", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(1,0))));
		pieces.add(new Block("c", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(2,0))));
		Agent agent = new Agent("agent", PieceType.AGENT, new PiecePosition(new Pair<Integer, Integer>(3,0)));
		boardstate = new BoardState(board, agent, pieces);
	}
	
	public Game(Rectangle board, Agent agent,  ArrayList<Block> blocks)
	{

		boardstate = new BoardState(board, agent, blocks);
	}
	
	public GameResult runGame(BoardState initBoardState, BoardState goalState, SearchType search)
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
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.runGame(game.getBoardstate(), new BoardState(null, null, null), SearchType.DEPTH_FIRST);
	}
	
	public BoardState getBoardstate()
	{
		return boardstate;
	}

}

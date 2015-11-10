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

	BoardState			boardstate;
	static BoardState	goalState;


	/**
	 * 
	 */
	public Game()
	{
		Rectangle board = new Rectangle(4, 4);
		ArrayList<Block> pieces = new ArrayList<Block>();
		pieces.add(new Block("a", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3, 0))));
		pieces.add(new Block("b", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3, 1))));
		pieces.add(new Block("c", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3, 2))));
		Agent agent = new Agent("agent", PieceType.AGENT, new PiecePosition(new Pair<Integer, Integer>(3, 3)));
		boardstate = new BoardState(board, agent, pieces);

		Rectangle boardG = new Rectangle(4, 4);
		ArrayList<Block> piecesG = new ArrayList<Block>();
		piecesG.add(new Block("a", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(1, 1))));
		piecesG.add(new Block("b", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(2, 1))));
		piecesG.add(new Block("c", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3, 1))));
		Agent agentG = new Agent("agent", PieceType.AGENT, new PiecePosition(new Pair<Integer, Integer>(3, 3)));
		goalState = new BoardState(boardG, agentG, piecesG);
	}


	public Game(Rectangle board, Agent agent, ArrayList<Block> blocks)
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


		//game.runGame(game.getBoardstate(), new BoardState(null, null, null), SearchType.DEPTH_FIRST);
	}


	public BoardState getBoardstate()
	{
		return boardstate;
	}

}

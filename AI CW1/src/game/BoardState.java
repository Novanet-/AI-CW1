package game;

import java.awt.Rectangle;
import java.util.ArrayList;

public class BoardState
{
	Rectangle board;
	ArrayList<BoardPiece> pieces;
	
	/**
	 * @param board
	 * @param pieces
	 */
	public BoardState(Rectangle board, ArrayList<BoardPiece> pieces)
	{
		super();
		this.board = board;
		this.pieces = pieces;
	}




}

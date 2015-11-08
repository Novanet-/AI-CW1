package game;

import java.awt.Rectangle;
import java.util.ArrayList;

public class BoardState
{
	private Rectangle board;
	private ArrayList<BoardPiece> pieces;
	
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
	
	public boolean equals(BoardState boardState)
	{
		boolean equalBoardState = true;
		if (!(this.board.equals(boardState.getBoard())))
		{
			equalBoardState = false;
		}
		if (!(this.getPieces().equals(boardState.getPieces())))
		{
			equalBoardState = false;
		}
		
		return equalBoardState;
	}

	
	public Rectangle getBoard()
	{
		return board;
	}

	
	public ArrayList<BoardPiece> getPieces()
	{
		return pieces;
	}

	


}

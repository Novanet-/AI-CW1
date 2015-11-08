package game.board;

import java.awt.Rectangle;
import java.util.ArrayList;

import game.piece.Agent;
import game.piece.Block;
import game.piece.BoardPiece;
import game.piece.PiecePosition;

public class BoardState
{
	private Rectangle board;
	private Agent agent;
	private ArrayList<Block> blocks;
	
	/**
	 * @param board
	 * @param pieces
	 */
	public BoardState(Rectangle board, Agent agent, ArrayList<Block> blocks)
	{
		super();
		this.board = board;
		this.blocks = blocks;
	}
	
	/*public Set<AgentMove> determineValidAgentMoves()
	{
		
	}*/
	
	public boolean isPositionWithinBoard(PiecePosition position)
	{
		return false;
		//return ((position ))
	}
	
	public boolean equals(BoardState boardState)
	{
		boolean equalBoardState = true;
		if (!(this.board.equals(boardState.getBoard())))
		{
			equalBoardState = false;
		}
		if (!(this.getAgent().equals(boardState.getAgent())))
		{
			equalBoardState = false;
		}
		if (!(this.getBlocks().equals(boardState.getBlocks())))
		{
			equalBoardState = false;
		}
		
		return equalBoardState;
	}

	
	public Rectangle getBoard()
	{
		return board;
	}
	
	
	public Agent getAgent()
	{
		return agent;
	}

	public ArrayList<Block> getBlocks()
	{
		return blocks;
	}

	

	


}

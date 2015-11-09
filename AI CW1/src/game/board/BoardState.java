package game.board;

import java.awt.Rectangle;
import java.util.ArrayList;

import game.piece.Agent;
import game.piece.Block;
import game.piece.BoardPiece;
import game.piece.PiecePosition;
import utilities.Pair;

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

	@Override
	public String toString()
	{
		PiecePosition drawHead = new PiecePosition(new Pair<Integer, Integer>());
		char tileImage = ' ';
		for (int x = 0; x > this.getBoard().getWidth(); x++)
		{
			for (int y= 0; y > this.getBoard().getHeight(); y++)
			{
				drawHead.setPosition(new Pair<Integer, Integer>(x, y));
				for (Block b: this.getBlocks())
				{
					if (drawHead.equals(b.getPosition()))
					{
						tileImage = b.getName().charAt(0);
					}
				}
				if (drawHead)
			}
		}
		return null;
		
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

package game.board;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Set;

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
		this.agent = agent;
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
		StringBuilder boardImage = new StringBuilder();
		boardImage.append("");
		char tileImage;
		for (int x = 0; x < this.getBoard().getWidth(); x++)
		{
			for (int y= 0; y < this.getBoard().getHeight(); y++)
			{
				tileImage = '-';
				drawHead.setPosition(new Pair<Integer, Integer>(x, y));
				for (Block b: this.getBlocks())
				{
					if ((drawHead.x().equals(b.getPosition().x())) && (drawHead.y().equals(b.getPosition().y())))
					{
						tileImage = b.getName().charAt(0);
					}
				}
				if ((drawHead.x().equals(this.getAgent().getPosition().x())) && (drawHead.y().equals(this.getAgent().getPosition().y())))
				{
					tileImage = 'x';
				}
				boardImage.append(tileImage);
			}
			boardImage.append("\n");
			
		}
		return boardImage.toString();
		
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

	public Set<BoardState> generatePossibleMoves()
	{
		try
		{
			BoardState boardStateProbe = (BoardState) this.clone();
			
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	

	


}

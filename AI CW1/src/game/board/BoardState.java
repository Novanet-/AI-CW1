package game.board;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import game.piece.Agent;
import game.piece.Block;
import game.piece.BoardPiece;
import game.piece.PiecePosition;
import utilities.Pair;

public class BoardState
{

	private Rectangle			board;
	private Agent				agent;
	private ArrayList<Block>	blocks;


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
			for (int y = 0; y < this.getBoard().getHeight(); y++)
			{
				tileImage = '-';
				drawHead.setPosition(new Pair<Integer, Integer>(x, y));
				for (Block b : this.getBlocks())
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


	public HashSet<BoardState> generatePossibleMoves()
	{
		HashSet<BoardState> validMoves = new HashSet<>();
		PiecePosition initAgent = new PiecePosition(new Pair<Integer, Integer>(this.getAgent().getPosition().x(), this.getAgent().getPosition().y()));
		PiecePosition boardStateProbe = this.getAgent().getPosition();
		HashSet<Pair> moveVectors = new HashSet<>();
		moveVectors.add(new Pair<Integer, Integer>(-1, 0));
		moveVectors.add(new Pair<Integer, Integer>(0, 1));
		moveVectors.add(new Pair<Integer, Integer>(1, 0));
		moveVectors.add(new Pair<Integer, Integer>(0, -1));
		for (Pair<Integer, Integer> moveVector : moveVectors)
		{
			boardStateProbe.setPosition(new Pair<Integer, Integer>(boardStateProbe.x() + moveVector.getLeft(), boardStateProbe.y() + moveVector.getRight()));
			if (isPieceInBounds(boardStateProbe))
			{
				validMoves.add(new BoardState(this.getBoard(), this.getAgent().copy(), new ArrayList<Block>(this.getBlocks())));
			}
		}

		return null;
	}


	private boolean isPieceInBounds(PiecePosition boardStateProbe)
	{
		if ((boardStateProbe.x() >= 0) && (boardStateProbe.x() < this.getBoard().getHeight()))
		{
			if ((boardStateProbe.y() >= 0) && (boardStateProbe.y() < this.getBoard().getWidth()))
			{
				return true;
			}
		}
		return false;
	}


	public boolean isGoalState()
	{
		Block lastBlock = this.getBlocks().get(getBlocks().size() - 1);
		if (blockOnTable(lastBlock))
		{
			for (int i = this.getBlocks().size() - 2; i >= 0; i--)
			{
				Block smallerBlock = getBlocks().get(i);
				if (!(smallerBlock.getPosition().x() == (lastBlock.getPosition().y() - 1)))
				{
					return false;
				}
				lastBlock = smallerBlock;

			}
			return true;
		}
		return false;
	}


	/**
	 * @param block
	 * @return
	 */
	private boolean blockOnTable(Block lastBlock)
	{
		return lastBlock.getPosition().x().equals(this.getBoard().getHeight() - 1);
	}

}

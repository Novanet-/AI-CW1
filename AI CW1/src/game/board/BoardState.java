package game.board;

import java.awt.Rectangle;
import java.util.ArrayList;

import game.Game;
import game.piece.Agent;
import game.piece.Block;
import game.piece.PiecePosition;
import search.Node;
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


	/**
	 * Checks each element of the board state for equality with each element of another board state
	 * 
	 * @param boardState
	 *            The other board state to be checked for equality with this one
	 * @return True if board states are equal, otherwise false
	 */
	@Override
	public boolean equals(Object obj)
	{
		boolean equalBoardState = true;
		if (!(this.getBoard().equals(((BoardState) obj).getBoard())))
		{
			equalBoardState = false;
		}
		if (!(this.getAgent().equals(((BoardState) obj).getAgent())))
		{
			equalBoardState = false;
		}
		if (!(this.getBlocks().equals(((BoardState) obj).getBlocks())))
		{
			equalBoardState = false;
		}

		return equalBoardState;
	}


	/**
	 * Generates a graphical representation of the BoardState, where empty tiles are '-', blocks are dentoed by their
	 * letter, and the agent is represented by an 'x'
	 * 
	 * @return A graphical representation of the BoardState
	 */
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


	/**
	 * Generate all possible moves of the agent, where a possible move is one that does not take it out of the bounds of
	 * the board
	 * @param currentLevel 
	 * 
	 * @return An ArrayList of all possible agent moves
	 */
	public ArrayList<Node> generatePossibleMoves(Node currentNode, int currentDepth)
	{
		ArrayList<Node> validMoves = new ArrayList<Node>();
		BoardState boardStateCopy = this.copy();
		PiecePosition initAgent = new PiecePosition(new Pair<Integer, Integer>(boardStateCopy.getAgent().getPosition().x(), boardStateCopy.getAgent().getPosition().y()));
		PiecePosition boardStateProbe = boardStateCopy.getAgent().getPosition();

		ArrayList<Pair<Integer, Integer>> moveVectors = new ArrayList<Pair<Integer, Integer>>();
		moveVectors.add(new Pair<Integer, Integer>(-1, 0));
		moveVectors.add(new Pair<Integer, Integer>(0, 1));
		moveVectors.add(new Pair<Integer, Integer>(1, 0));
		moveVectors.add(new Pair<Integer, Integer>(0, -1));

		for (Pair<Integer, Integer> moveVector : moveVectors)
		{
			ArrayList<Block> blockProbe = new ArrayList<Block>();
			for (Block b : boardStateCopy.getBlocks())
			{
				blockProbe.add(b.copy());
			}

			//Adjust the agent by each cardinal direction and check for validity
			boardStateProbe.setPosition(new Pair<Integer, Integer>(boardStateProbe.x() + moveVector.getLeft(), boardStateProbe.y() + moveVector.getRight()));
			if (isPieceInBounds(boardStateProbe))
			{
				for (Block b : blockProbe)
				{
					if ((boardStateProbe.x() == b.getPosition().x()) && (boardStateProbe.y() == b.getPosition().y()))
					{
						b.getPosition().setX(initAgent.x());
						b.getPosition().setY(initAgent.y());
					}
				}
				Agent newAgent = boardStateCopy.getAgent().copy();
				newAgent.getPosition().setPosition(new Pair<Integer, Integer>(boardStateProbe.x(), boardStateProbe.y()));
				BoardState validMove = new BoardState(boardStateCopy.getBoard(), newAgent, blockProbe);
				validMoves.add(new Node(validMove, currentNode, new ArrayList<Node>(), currentDepth + 1));
			}

			boardStateProbe.setPosition(new Pair<Integer, Integer>(initAgent.x(), initAgent.y()));
		}
		return validMoves;
	}


	/**
	 * Checks whether the given board piece is within the bounds of the grid of the board
	 * 
	 * @param boardStateProbe
	 *            The piece of which it's position will be checked
	 * @return True if piece is within bounds, otherwise false
	 */
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


	/**
	 * Checks that the heaviest block(biggest letter in the alphabet where B > A, C > B etc.) is on the bottom row on
	 * the grid, and that the lighter letters in the array of blocks, going sequentially backwards along the alphabet
	 * from the heaviest letter to A, are on top of the heavier letter, and that they are all in the same column
	 * 
	 * @return true if this BoardState is a goal state, otherwise return false
	 */
	public boolean isGoalState()
	{
		if (this.equals(Game.goalState))
			return true;
		else
			return false;
	}


	/**
	 * @return A deep copy of this boardstate
	 */
	public BoardState copy()
	{
		return new BoardState(new Rectangle(this.getBoard()), this.getAgent().copy(), new ArrayList<Block>(this.getBlocks()));
	}


	@Override
	public int hashCode()
	{
		int hash = 5;
		hash = (int) (89 * hash + ((this.getBoard().getWidth()) + (this.getBoard().getHeight())));
		hash = 89 * hash + ((this.getAgent().getPosition().x()) + (this.getAgent().getPosition().y()));
		for (Block b : this.getBlocks())
		{
			hash = (89 * hash + (b.getPosition().x()) + (b.getPosition().y()));

		}
		return hash;
	}

}

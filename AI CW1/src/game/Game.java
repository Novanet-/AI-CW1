/**
 * 
 */
package game;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;

import game.board.BoardState;
import game.piece.Agent;
import game.piece.Block;
import game.piece.Block;
import game.piece.PiecePosition;
import game.piece.PieceType;
import search.NoSolutionPossibleException;
import search.Search;
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


	public GameResult runGame(BoardState initBoardState, BoardState goalState, SearchType search) throws NoSolutionPossibleException
	{
		GameResult gameResult = null;
		switch (search)
		{
			case DEPTH_FIRST:
				gameResult = Search.depthFirst(initBoardState);
			default:
				return gameResult;
		}
	}


	public static void main(String[] args)
	{
		Game game = new Game();
		try
		{
			//System.out.println(goalState.isGoalState());
			int nodesAccumulator = 0;
			int agentAccumulator = 0;
			for (int i = 0; i < 100; i++)
			{
				GameResult result = game.runGame(game.getBoardstate(), Game.goalState, SearchType.DEPTH_FIRST);
				nodesAccumulator +=  result.getNodesExpanded();
				agentAccumulator +=  result.getAgentMoves();
				BoardState finalState = result.getSolutionPath().elementAt(result.getSolutionPath().size() - 1);
				System.out.println(finalState);
				System.out.println(result.getNodesExpanded() + ", " + result.getAgentMoves());
			}
			nodesAccumulator = nodesAccumulator / 100;
			agentAccumulator = agentAccumulator /100;
			System.out.println(" ");
			System.out.println(nodesAccumulator + " , " + agentAccumulator);
			
		}
		catch (NoSolutionPossibleException e)
		{
			e.printStackTrace();
		}
	}
	
	public void printSolutionPath(Stack<BoardState> solutionPath)
	{
		while (!(solutionPath.isEmpty()))
		{
			System.out.println(solutionPath.pop());
		}
	}


	public BoardState getBoardstate()
	{
		return boardstate;
	}

}

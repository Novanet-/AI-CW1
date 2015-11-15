/**
 * 
 */
package game;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

import game.board.BoardState;
import game.piece.Agent;
import game.piece.Block;
import game.piece.PiecePosition;
import game.piece.PieceType;
import search.BreadthFirstSearch;
import search.DepthFirstSearch;
import search.Heuristic;
import search.IterativeDeepeningSearch;
import search.NoSolutionPossibleException;
import search.SearchType;
import utilities.Pair;

/**
 *
 */
public class Game
{

	private BoardState			boardstate;
	private static BoardState	goalState;


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


	/**
	 * Runs the block board game with an initial board state, and a search type used to calcualte the solution
	 * 
	 * @param initBoardState
	 * @param goalState
	 * @param search
	 * @return
	 * @throws NoSolutionPossibleException
	 */
	//	public GameResult runGame(BoardState initBoardState, SearchType search) throws NoSolutionPossibleException
	//	{
	//		GameResult gameResult = null;
	//		switch (search)
	//		{
	//			case DEPTH_FIRST:
	//				gameResult = DepthFirstSearch.depthFirst(initBoardState);
	//				break;
	//			case BREADTH_FIRST:
	//				gameResult = BreadthFirstSearch.breadthFirst(initBoardState);
	//				break;
	//			case ITERATIVE_DEEPENING:
	//				gameResult = IterativeDeepeningSearch.iterativeDeepening(initBoardState);
	//			default:
	//				break;
	//		}
	//		return gameResult;
	//	}

	public static void main(String[] args)
	{
		Game game = new Game();
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Selection no. of iterations");
			int iterations = Integer.parseInt(br.readLine());
			System.out.println("Select search type: '1. DFS 2. BFS 3. Iterative Deepening 4. Heuristic");
			int searchTypeIndex = Integer.parseInt(br.readLine());

			GameResult gameResult;
			SearchType searchType = SearchType.values()[searchTypeIndex - 1];

			int nodesAccumulator = 0, agentAccumulator = 0, i;

			switch (searchType)
			{
				case DEPTH_FIRST:

					for (i = 0; i < iterations; i++)
					{
						gameResult = DepthFirstSearch.depthFirst(game.getBoardstate());
						nodesAccumulator += gameResult.getNodesExpanded();
						agentAccumulator += gameResult.getAgentMoves();
						printResult(gameResult);
					}
					break;
				case BREADTH_FIRST:
					for (i = 0; i < iterations; i++)
					{
						gameResult = BreadthFirstSearch.breadthFirst(game.getBoardstate());
						nodesAccumulator += gameResult.getNodesExpanded();
						agentAccumulator += gameResult.getAgentMoves();
						printResult(gameResult);
					}
					break;
				case ITERATIVE_DEEPENING:
					for (i = 0; i < iterations; i++)
					{
						gameResult = IterativeDeepeningSearch.iterativeDeepening(game.getBoardstate());
						nodesAccumulator += gameResult.getNodesExpanded();
						agentAccumulator += gameResult.getAgentMoves();
						printResult(gameResult);
					}
					break;
				case A_STAR:
					for (i = 0; i < iterations; i++)
					{
						gameResult = Heuristic.heuristic(game.getBoardstate());
						nodesAccumulator += gameResult.getNodesExpanded();
						agentAccumulator += gameResult.getAgentMoves();
						printResult(gameResult);
					}
					break;
				default:
					break;
			}

			nodesAccumulator = nodesAccumulator / iterations;
			agentAccumulator = agentAccumulator / iterations;
			System.out.println("::::::::::::::::::::::::::::::::::::::::::::::");
			System.out.println("Average nodes expanded: " + nodesAccumulator + ", Average agent moves for solution: " + agentAccumulator);

		}
		catch (NoSolutionPossibleException | IOException e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * 
	 */
	private static void printResult(GameResult gameResult)
	{
		BoardState finalState = gameResult.getSolutionPath().elementAt(0);
		System.out.println("*************************************\n");
		System.out.println("Solution State: ");
		System.out.println(finalState);
		System.out.println(" ");
		System.out.println("Nodes expanded: " + gameResult.getNodesExpanded() + ", Agent moves for solution: " + gameResult.getAgentMoves());
		System.out.println("*************************************\n");
	}


	/**
	 * @param solutionPath
	 *            The stack of board states that lead to the solution of the game
	 */
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

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
import search.SearchType;
import utilities.Pair;

/**
 *
 */
public class Game
{

	private BoardState			startState;
	public static BoardState	goalState;


	/**
	 * Creates a start state and a goal state for the game
	 */
	public Game()
	{
		Rectangle startBoard = new Rectangle(4, 4);
		ArrayList<Block> pieces = new ArrayList<Block>();
		pieces.add(new Block("a", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3, 0))));
		pieces.add(new Block("b", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3, 1))));
		pieces.add(new Block("c", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3, 2))));
		Agent agent = new Agent("agent", PieceType.AGENT, new PiecePosition(new Pair<Integer, Integer>(3, 3)));
		startState = new BoardState(startBoard, agent, pieces);

		Rectangle goalBoard = new Rectangle(4, 4);
		ArrayList<Block> piecesG = new ArrayList<Block>();
		piecesG.add(new Block("a", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(1, 1))));
		piecesG.add(new Block("b", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(2, 1))));
		piecesG.add(new Block("c", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3, 1))));
		Agent agentG = new Agent("agent", PieceType.AGENT, new PiecePosition(new Pair<Integer, Integer>(3, 3)));
		goalState = new BoardState(goalBoard, agentG, piecesG);
	}


	/**
	 * Menu system for running the game, after a search has been run, adds the results to it's relative accumulator, and
	 * if the csv toggle has been selected, will print out the average result for each search time when all search runs
	 * have been finished
	 * 
	 * @param args
	 */
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
			System.out.println("CSV? (y/n)");
			boolean csvFile = br.readLine().equals("y") ? true : false;

			GameResult dfsGameResult = null, bfsGameResult = null, idGameResult = null, heurGameResult = null;
			SearchType searchType = SearchType.values()[searchTypeIndex - 1];

			int dfsNodesAccumulator = 0, bfsNodesAccumulator = 0, idNodesAccumulator = 0, heurNodesAccumulator = 0, dfsAgentAccumulator = 0, bfsAgentAccumulator = 0,
					idAgentAccumulator = 0, heurAgentAccumulator = 0, i;

			switch (searchType)
			{
				case DEPTH_FIRST:

					for (i = 0; i < iterations; i++)
					{
						dfsGameResult = DepthFirstSearch.depthFirst(game.getBoardstate());
						dfsNodesAccumulator += dfsGameResult.getNodesExpanded();
						dfsAgentAccumulator += dfsGameResult.getAgentMoves();
						printResult(dfsGameResult);
					}
					break;

				case BREADTH_FIRST:
					for (i = 0; i < iterations; i++)
					{
						bfsGameResult = BreadthFirstSearch.breadthFirst(game.getBoardstate());
						bfsNodesAccumulator += bfsGameResult.getNodesExpanded();
						bfsAgentAccumulator += bfsGameResult.getAgentMoves();
						printResult(bfsGameResult);
					}
					break;

				case ITERATIVE_DEEPENING:
					for (i = 0; i < iterations; i++)
					{
						idGameResult = IterativeDeepeningSearch.iterativeDeepening(game.getBoardstate());
						idNodesAccumulator += idGameResult.getNodesExpanded();
						idAgentAccumulator += idGameResult.getAgentMoves();
						printResult(idGameResult);
					}
					break;

				case A_STAR:
					for (i = 0; i < iterations; i++)
					{
						heurGameResult = Heuristic.heuristic(game.getBoardstate());
						heurNodesAccumulator += heurGameResult.getNodesExpanded();
						heurAgentAccumulator += heurGameResult.getAgentMoves();
						printResult(heurGameResult);
					}
					break;
				default:
					break;
			}

			if (csvFile)
			{
				System.out.println(((int) game.getBoardstate().getBoard().getWidth()) + "," + ((int) game.getBoardstate().getBoard().getHeight()) + ","
						+ dfsNodesAccumulator / iterations + "," + dfsAgentAccumulator / iterations);
				System.out.println(((int) game.getBoardstate().getBoard().getWidth()) + "," + ((int) game.getBoardstate().getBoard().getHeight()) + ","
						+ bfsNodesAccumulator / iterations + "," + bfsAgentAccumulator / iterations);
				System.out.println(((int) game.getBoardstate().getBoard().getWidth()) + "," + ((int) game.getBoardstate().getBoard().getHeight()) + ","
						+ idNodesAccumulator / iterations + "," + idAgentAccumulator / iterations);
				System.out.println(((int) game.getBoardstate().getBoard().getWidth()) + "," + ((int) game.getBoardstate().getBoard().getHeight()) + ","
						+ heurNodesAccumulator / iterations + "," + heurAgentAccumulator / iterations);
			}

		}
		catch (IOException e)
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
		return startState;
	}

}

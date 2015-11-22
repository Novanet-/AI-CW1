package search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

import game.Game;
import game.GameResult;
import game.board.BoardState;
import game.piece.Block;
import game.piece.BoardPiece;

public class Heuristic
{

	private static HashMap<Node, Integer>	gScore;
	private static HashMap<Node, Integer>	fScore;
	private static Integer					gScoreDefault	= Integer.MAX_VALUE;
	private static Integer					fScoreDefault	= Integer.MAX_VALUE;


	/**
	 * Finds a solution to the initial board start by applying a heuristic to each neighbour of the node being explored
	 * (h(n) = g(n) + f(n)) where g(n) is the cost from the start board state to the current board state, and f(n) is
	 * the estimated cost of getting from the current board state to a goal state. Once this heuristic has been
	 * calculated for each neighbour, they are all added the the fringe. The search decides the next node from the
	 * fringe to explore by picking the one with the lowest value for its's heuristic
	 * 
	 * @param initBoardState
	 *            The initial state of the board, and the root node of the search
	 * @return A GameResult object containing whether the solution is found, the solution path, the number of nodes
	 *         expanded, and the number of moves the agent took to find the solution
	 */
	public static GameResult heuristic(BoardState initBoardState)
	{
		int currentDepth = 0;

		EstimatedCostComparator comparator = new Heuristic.EstimatedCostComparator();

		HashSet<Node> visitedBoardStates = new HashSet<Node>(); //Already seen nodes
		PriorityQueue<Node> fringe = new PriorityQueue<Node>(comparator); //The potential children to be explored

		Node rootNode = new Node(initBoardState, null, new ArrayList<Node>(), 0);
		fringe.add(rootNode);// The The set of children to be explored, starting with the root node

		gScore = new HashMap<Node, Integer>();
		gScore.put(rootNode, 0); // Cost from start along best path that is known

		fScore = new HashMap<Node, Integer>();
		fScore.put(rootNode, gScore.get(rootNode) + estimateCostToGoal(rootNode)); //Estimated cost from the root node to a goal state

		while (!(fringe.isEmpty()))
		{
			Node currentNode = fringe.peek();

			currentDepth = currentNode.getDepth();
			if (currentNode.getVal().isGoalState())
			{
				Stack<BoardState> solutionPath = Search.calculateSolutionPath(currentNode);
				return new GameResult(true, solutionPath, visitedBoardStates.size(), solutionPath.size());
			}

			currentNode = fringe.remove();

			visitedBoardStates.add(currentNode);

			ArrayList<Node> possibleMoves = currentNode.getVal().generatePossibleMoves(currentNode, currentDepth);
			currentNode.setChildren(new ArrayList<Node>(possibleMoves));

			for (Node childNode : currentNode.getChildren())
			{
				if (visitedBoardStates.contains(childNode))
				{
					break; // Ignore neighbours which are already explored
				}
				Integer tentativeGScore = (gScore.get(currentNode) == null ? gScoreDefault : gScore.get(currentNode)) + 1;// Length of the path from the root node to the current node
				if (!(fringe.contains(childNode)))
				{
					fringe.add(childNode); // Discover a new node
				}
				gScore.put(childNode, tentativeGScore);
				fScore.put(childNode, gScore.get(childNode) + estimateCostToGoal(childNode));
			}
		}

		return new GameResult(false, null, 0, 0);

	}


	/**
	 * Uses a heuristic function (sum of manhattan distances) to generate an estimated cost from the current state to
	 * the goal state
	 * 
	 * @param rootNode
	 * @return
	 */
	private static Integer estimateCostToGoal(Node rootNode)
	{
		int cost = 0;

		ArrayList<Block> currentBlockConfig = rootNode.getVal().getBlocks();
		ArrayList<Block> targetBlockConfig = Game.goalState.getBlocks();
		for (int i = 0; i < currentBlockConfig.size(); i++)
		{
			cost += manhattanDistance(currentBlockConfig.get(i), targetBlockConfig.get(i));
		}

		return cost;
	}


	/**
	 * Calculates the difference between two positions into x and y components, then adds the x and y components to get
	 * the manhattan distance between the two position
	 * 
	 * @param currentPiece
	 * @param targetPiece
	 * @return The manhattan distance between the two parameters
	 */
	private static int manhattanDistance(BoardPiece currentPiece, BoardPiece targetPiece)
	{
		int xDistance = Math.abs(currentPiece.getPosition().x() - targetPiece.getPosition().x());
		int yDistance = Math.abs(currentPiece.getPosition().y() - targetPiece.getPosition().y());
		return xDistance + yDistance;
	}


	/**
	 * A comparator for the fringe so that it orders them where the node with the lowest fScore is at the head of the queue
	 *
	 */
	static class EstimatedCostComparator implements Comparator<Node>
	{

		@Override
		public int compare(Node o1, Node o2)
		{
			Integer o1Cost = fScore.get(o1) == null ? fScoreDefault : fScore.get(o1);
			Integer o2Cost = fScore.get(o2) == null ? fScoreDefault : fScore.get(o2);
			return o1Cost - o2Cost;
		}

	}

}

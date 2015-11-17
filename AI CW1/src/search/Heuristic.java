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
	private static Integer								gScoreDefault	= Integer.MAX_VALUE;
	private static Integer								fScoreDefault	= Integer.MAX_VALUE;


	/**
	 * Finds a solution to the initial board start by applying a heuristic to each neighbour of the node being explored
	 * (h(n) = g(n) + f(n)) where g(n) is the cost from the start board state to the current board state, and f(n) is
	 * the estimated cost of getting from the current board state toa goal state. Once this heuristic has been
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
		int nodeCounter = 0;
		int currentDepth = 0;

		EstimatedCostComparator comparator = new Heuristic.EstimatedCostComparator();

		HashSet<Node> nodesExplored = new HashSet<Node>(); // The set of nodes already explored
		PriorityQueue<Node> fringe = new PriorityQueue<Node>(comparator); //The potential neigbours to be explored

		Node rootNode = new Node(initBoardState, null, new ArrayList<Node>(), 0);
		fringe.add(rootNode);// The set of tentative nodes to be evaluated, initially containing the start node

		gScore = new HashMap<Node, Integer>();
		gScore.put(rootNode, 0); // Cost from start along best known path.

		fScore = new HashMap<Node, Integer>();
		fScore.put(rootNode, gScore.get(rootNode) + estimateCostToGoal(rootNode)); //Estimated cost from the root node to a goal state

		while (!(fringe.isEmpty()))
		{
			Node currentNode = fringe.peek();
			
			currentDepth = currentNode.getDepth();
			if (currentNode.getVal().isGoalState())
			{
				Stack<BoardState> solutionPath = Search.calculateSolutionPath(currentNode);
				return new GameResult(true, solutionPath, nodeCounter, currentDepth);
			}
			
			

			fringe.remove(currentNode);
			
			if (nodesExplored.add(currentNode))
			{
				nodeCounter++;
			}
			
			ArrayList<Node> possibleMoves = currentNode.getVal().generatePossibleMoves(currentNode, currentDepth);
			currentNode.setChildren(new ArrayList<Node>(possibleMoves));

			for (Node childNode : currentNode.getChildren())
			{
				if (nodesExplored.contains(childNode))
				{
					break; // Ignore neighbours which are already explored
				}
				Integer tentativeGScore = (gScore.get(currentNode) == null ? gScoreDefault : gScore.get(currentNode)) + 1;// Length of the path from the root node to the current node
//				Integer childNodeGScore = gScore.get(childNode) == null ? gScoreDefault : gScore.get(childNode); //Length of the path from the root node to the child node
				if (!(fringe.contains(childNode)))// Discover a new node
				{
					fringe.add(childNode);
				}
//				else if (tentativeGScore >= childNodeGScore)
//				{
//					break; // This is not a better path.
//				}

				// Record the current path selected
				gScore.put(childNode, tentativeGScore);
				fScore.put(childNode, gScore.get(childNode) + estimateCostToGoal(childNode));
			}
		}

		return new GameResult(false, null, 0, 0);

	}


	private static Integer estimateCostToGoal(Node rootNode)
	{
		int cost = 0;

//		ArrayList<Block> letterSortedBlockArray = rootNode.getVal().getBlocks();
//		ArrayList<Block> columnSortedBlockArray = new ArrayList<Block>();
//		columnSortedBlockArray.addAll(letterSortedBlockArray);
//		columnSortedBlockArray.sort((o1, o2) -> o1.getPosition().y() - o2.getPosition().y());
//		Block leftBlock = columnSortedBlockArray.get(0);
//		Block middleBlock = columnSortedBlockArray.get(1);
//		Block rightBlock = columnSortedBlockArray.get(2);
//		cost += Math.abs(middleBlock.getPosition().y() - leftBlock.getPosition().y());
//		cost += Math.abs(middleBlock.getPosition().y() - rightBlock.getPosition().y());
//		Rectangle board = rootNode.getVal().getBoard();
//		Block cBlock = letterSortedBlockArray.get(2);
//		Block bBlock = letterSortedBlockArray.get(1);
//		Block aBlock = letterSortedBlockArray.get(0);
//		cost += (board.getHeight() - 1) - (cBlock.getPosition().x());
//		cost += (board.getHeight() - 2) - (bBlock.getPosition().x());
//		cost += (board.getHeight() - 3) - (aBlock.getPosition().x());
		
		ArrayList<Block> currentBlockConfig = rootNode.getVal().getBlocks();
		ArrayList<Block> targetBlockConfig = Game.goalState.getBlocks();
		for (int i = 0; i < currentBlockConfig.size(); i++)
		{
			cost += manhattanDistance(currentBlockConfig.get(i), targetBlockConfig.get(i)); 
		}
		
//		cost += manhattanDistance(rootNode.getVal().getAgent(), Game.goalState.getAgent());

		return cost;
	}

	private static int manhattanDistance(BoardPiece currentPiece, BoardPiece targetPiece)
	{
		int xDistance = Math.abs(currentPiece.getPosition().x() - targetPiece.getPosition().x());
		int yDistance = Math.abs(currentPiece.getPosition().y() - targetPiece.getPosition().y());
		return xDistance + yDistance;
	}

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

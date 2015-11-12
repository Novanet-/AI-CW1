package game;

import java.util.Stack;

import game.board.BoardState;

public class GameResult
{

	Stack<BoardState>	solutionPath;
	Integer				nodesExpanded, agentMoves;


	/**
	 * An object containing the board states that lead to the goal state of a of a run of the board game, the number of
	 * nodes expanded to calculate it, and the number of moves the agent needs to take to reach that goal state
	 * 
	 * @param solutionPath
	 *            Stack of board states that the agent moves to reach goal state
	 * @param nodesExpanded
	 *            The number of nodes expanded to calculate solution
	 * @param agentMoves
	 *            The number of moves the agent has to take to reach the goal state from the start state
	 */
	public GameResult(Stack<BoardState> solutionPath, Integer nodesExpanded, Integer agentMoves)
	{
		super();
		this.solutionPath = solutionPath;
		this.nodesExpanded = nodesExpanded;
		this.agentMoves = agentMoves;
	}


	public Stack<BoardState> getSolutionPath()
	{
		return solutionPath;
	}


	public Integer getNodesExpanded()
	{
		return nodesExpanded;
	}


	public Integer getAgentMoves()
	{
		return agentMoves;
	}

}

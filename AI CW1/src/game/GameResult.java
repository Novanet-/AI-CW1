package game;

import java.util.Stack;

import game.board.BoardState;

public class GameResult
{
	Stack<BoardState> solutionPath;
		Integer nodesExpanded, agentMoves;
		
		/**
		 * @param solutionPath
		 * @param nodesExpanded
		 * @param agentMoves
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

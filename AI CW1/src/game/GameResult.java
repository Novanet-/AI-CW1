package game;

import game.board.BoardState;

public class GameResult
{
		BoardState board;
		Integer nodesExpanded, agentMoves;
		
		/**
		 * @param board
		 * @param nodesExpanded
		 * @param agentMoves
		 */
		public GameResult(BoardState board, Integer nodesExpanded, Integer agentMoves)
		{
			super();
			this.board = board;
			this.nodesExpanded = nodesExpanded;
			this.agentMoves = agentMoves;
		}
		
		
}

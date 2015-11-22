package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import game.Game;
import game.board.BoardState;
import game.piece.Agent;
import game.piece.Block;
import game.piece.BoardPiece;
import game.piece.PiecePosition;
import game.piece.PieceType;
import utilities.Pair;

public class HeuristicTest
{
	BoardState board1, board2, board3;


	@Before
	public void setUp() throws Exception
	{
		ArrayList<Block> blocks1 = new ArrayList<Block>();
		blocks1.add(new Block("a", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3, 0))));
		blocks1.add(new Block("b", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3, 1))));
		blocks1.add(new Block("c", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3, 2))));
		Agent agent1 = new Agent("agent1", PieceType.AGENT, new PiecePosition(new Pair<Integer, Integer>(3, 3)));
		board1 = new BoardState(new Rectangle(4, 4), agent1, blocks1);
		ArrayList<Block> blocks2 = new ArrayList<Block>();
		blocks2.add(new Block("a", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3, 0))));
		blocks2.add(new Block("b", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3, 1))));
		blocks2.add(new Block("c", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3, 2))));
		Agent agent2 = new Agent("agent2", PieceType.AGENT, new PiecePosition(new Pair<Integer, Integer>(3, 3)));
		board2 = new BoardState(new Rectangle(4, 4), agent2, blocks2);
		ArrayList<Block> blocks3 = new ArrayList<Block>();
		blocks3.add(new Block("a", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(1, 1))));
		blocks3.add(new Block("b", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(2, 1))));
		blocks3.add(new Block("c", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3, 1))));
		Agent agent3 = new Agent("agent", PieceType.AGENT, new PiecePosition(new Pair<Integer, Integer>(3, 3)));
		board3 = new BoardState(new Rectangle(4, 4) , agent3, blocks3);
	}


	@After
	public void tearDown() throws Exception
	{
	}


	@Test
	public void manhattandDistanceSameTest()
	{
		int cost = 0;

		
		ArrayList<Block> currentBlockConfig = board1.getBlocks();
		ArrayList<Block> targetBlockConfig = board2.getBlocks();
		for (int i = 0; i < currentBlockConfig.size(); i++)
		{
			cost += manhattanDistance(currentBlockConfig.get(i), targetBlockConfig.get(i)); 
		}
		
		assertTrue(cost == 0);
	}
	
	@Test
	public void manhattandDistanceDifferenceTest()
	{
		int cost = 0;

		
		ArrayList<Block> currentBlockConfig = board1.getBlocks();
		ArrayList<Block> targetBlockConfig = board3.getBlocks();
		for (int i = 0; i < currentBlockConfig.size(); i++)
		{	
			int subCost = manhattanDistance(currentBlockConfig.get(i), targetBlockConfig.get(i));
			cost += subCost; 
		}
		
		System.out.println("total cost: " + cost);
		
		assertTrue(cost != 0);
	}
	
	private static int manhattanDistance(BoardPiece currentPiece, BoardPiece targetPiece)
	{
		System.out.println("coords: " + currentPiece.getPosition() + " -- " +  targetPiece.getPosition());
		int xDistance = Math.abs(currentPiece.getPosition().x() - targetPiece.getPosition().x());
		System.out.println("xDistance: " + xDistance);
		int yDistance = Math.abs(currentPiece.getPosition().y() - targetPiece.getPosition().y());
		System.out.println("yDistance: " + yDistance);
		return xDistance + yDistance;
	}
}

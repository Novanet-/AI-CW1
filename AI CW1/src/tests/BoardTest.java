package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import game.board.BoardState;
import game.piece.Agent;
import game.piece.Block;
import game.piece.PiecePosition;
import game.piece.PieceType;
import utilities.Pair;


public class BoardTest
{
	BoardState board1, board2;


	@Before
	public void setUp() throws Exception
	{
		ArrayList<Block> blocks1 = new ArrayList<Block>();
		blocks1.add(new Block("a", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(0,0))));
		blocks1.add(new Block("b", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(1,0))));
		blocks1.add(new Block("c", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(2,0))));
		Agent agent1 = new Agent("agent", PieceType.AGENT, new PiecePosition(new Pair<Integer, Integer>(3,0)));
		board1 = new BoardState(new Rectangle(8, 8), agent1, blocks1);
		ArrayList<Block> blocks2 = new ArrayList<Block>();
		blocks2.add(new Block("a", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(0,1))));
		blocks2.add(new Block("b", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(1,1))));
		blocks2.add(new Block("c", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(2,1))));
		Agent agent2 = new Agent("agent", PieceType.AGENT, new PiecePosition(new Pair<Integer, Integer>(3,1)));
		board1 = new BoardState(new Rectangle(8, 8), agent2, blocks2);
	}


	@After
	public void tearDown() throws Exception
	{
	}


	@Test
	public void boardEquality()
	{
		assertTrue(board1.equals(board1));
		assertFalse(board1.equals(board2));
	}

}

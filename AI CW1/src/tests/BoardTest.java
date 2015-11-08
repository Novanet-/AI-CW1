package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import game.BoardPiece;
import game.BoardState;
import game.PiecePosition;
import game.PieceType;
import utilities.Pair;


public class BoardTest
{
	BoardState board1, board2;


	@Before
	public void setUp() throws Exception
	{
		ArrayList<BoardPiece> pieces1 = new ArrayList<BoardPiece>();
		pieces1.add(new BoardPiece("a", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(1,1))));
		pieces1.add(new BoardPiece("b", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(2,1))));
		pieces1.add(new BoardPiece("c", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3,1))));
		pieces1.add(new BoardPiece("agent", PieceType.AGENT, new PiecePosition(new Pair<Integer, Integer>(4,1))));
		board1 = new BoardState(new Rectangle(8, 8), pieces1);
		ArrayList<BoardPiece> pieces2 = new ArrayList<BoardPiece>();
		pieces2.add(new BoardPiece("a", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(1,2))));
		pieces2.add(new BoardPiece("b", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(2,2))));
		pieces2.add(new BoardPiece("c", PieceType.BLOCK, new PiecePosition(new Pair<Integer, Integer>(3,2))));
		pieces2.add(new BoardPiece("agent", PieceType.AGENT, new PiecePosition(new Pair<Integer, Integer>(4,2))));
		board2 = new BoardState(new Rectangle(8, 8), pieces2);
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

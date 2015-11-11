package game.piece;

import utilities.Pair;

public class Block extends BoardPiece
{

	public Block(String name, PieceType type, PiecePosition position)
	{
		super(name, type, position);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString()
	{
		return new String(name + "," + type.toString() + "," + position.toString());
	}
	
	public Block copy()
	{
		return new Block(this.name, this.type, new PiecePosition(new Pair<Integer, Integer>(this.position.x(), this.position.y())));
	}

}

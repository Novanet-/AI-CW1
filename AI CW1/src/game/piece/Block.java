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
		return new String(super.getName() + "," + super.getType().toString() + "," + super.getPosition().toString());
	}
	
	public Block copy()
	{
		return new Block(super.getName(), super.getType(), new PiecePosition(new Pair<Integer, Integer>(super.getPosition().x(), super.getPosition().y())));
	}

}

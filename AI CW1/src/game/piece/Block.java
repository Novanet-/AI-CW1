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
	@Override
	public boolean equals(Object obj)
	{
		boolean equalBoardState = true;
		if (!(this.getName().equals(((Block) obj).getName())))
		{
			equalBoardState = false;
		}
		if (!(this.getType().equals(((Block) obj).getType())))
		{
			equalBoardState = false;
		}
		if (!(this.getPosition().equals(((Block) obj).getPosition())))
		{
			equalBoardState = false;
		}

		return equalBoardState;
	}

	@Override
	public int hashCode()
	{
		int hash = 11;
		hash = 101 * hash + (this.getName().hashCode());
		hash = 101 * hash + (this.getType().hashCode());
		hash = (101 * hash + (this.getPosition().x()) + (this.getPosition().y()));
		return hash;
	}

}

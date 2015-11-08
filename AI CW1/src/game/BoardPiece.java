package game;

import utilities.Pair;

public class BoardPiece
{
	String name;
	PieceType type;
	PiecePosition position;
	
	/**
	 * @param name
	 * @param type
	 * @param position
	 */
	public BoardPiece(String name, PieceType type, PiecePosition position)
	{
		super();
		this.name = name;
		this.type = type;
		this.position = position;
	}

	
	public PiecePosition getPosition()
	{
		return position;
	}

	
	public void setPosition(Integer x, Integer y)
	{
		this.position.setPosition(new Pair<Integer, Integer>(x, y));
	}

	
	public String getName()
	{
		return name;
	}

	
	public PieceType getType()
	{
		return type;
	}
	
	
	
	

}

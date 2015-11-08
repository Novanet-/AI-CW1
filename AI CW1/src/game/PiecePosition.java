package game;

import utilities.Pair;

public class PiecePosition
{
	Pair<Integer, Integer> position;

	/**
	 * @param position
	 */
	public PiecePosition(Pair<Integer, Integer> position)
	{
		super();
		this.position = position;
	}
	
	public Integer x()
	{
		return position.getLeft();
	}
	
	public Integer y()
	{
		return position.getRight();
	}

	

}

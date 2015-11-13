package game.piece;

import utilities.Pair;

public class PiecePosition
{

	private Pair<Integer, Integer> position;


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


	public void setX(Integer x)
	{
		position.setLeft(x);
	}


	public void setY(Integer y)
	{
		position.setRight(y);
	}


	public void setPosition(Pair<Integer, Integer> position)
	{
		this.position = position;

	}


	@Override
	public boolean equals(Object obj)
	{
		boolean equalBoardState = true;
		if ((!(this.x().equals(((PiecePosition) obj).x()))) || ((!(this.y().equals(((PiecePosition) obj).y())))))
		{
			equalBoardState = false;
		}

		return equalBoardState;
	}
	
	@Override
	public String toString()
	{
		return new String(this.x() + "," + this.y());

	}

}

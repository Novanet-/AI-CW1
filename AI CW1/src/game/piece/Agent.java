/**
 * 
 */
package game.piece;

import game.board.BoardState;
import utilities.Pair;

public class Agent extends BoardPiece
{

	/**
	 * @param name
	 * @param type
	 * @param position
	 */
	public Agent(String name, PieceType type, PiecePosition position)
	{
		super(name, type, position);
		// TODO Auto-generated constructor stub
	}


	public Agent copy()
	{
		return new Agent(super.getName(), super.getType(), new PiecePosition(new Pair<Integer, Integer>(super.getPosition().x(), super.getPosition().y())));
	}


	public String toString()
	{
		return new String(super.getName() + "," + super.getType().toString() + "," + super.getPosition().toString());
	}


	@Override
	public boolean equals(Object obj)
	{
		boolean equalBoardState = true;
		if (!(this.getName().equals(((Agent) obj).getName())))
		{
			equalBoardState = false;
		}
		if (!(this.getType().equals(((Agent) obj).getType())))
		{
			equalBoardState = false;
		}
		if (!(this.getPosition().equals(((Agent) obj).getPosition())))
		{
			equalBoardState = false;
		}

		return equalBoardState;
	}


	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 97 * hash + (this.getName().hashCode());
		hash = 97 * hash + (this.getType().hashCode());
		hash = (97 * hash + (this.getPosition().x()) + (this.getPosition().y()));
		return hash;
	}

}

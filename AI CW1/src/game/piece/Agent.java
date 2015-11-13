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
		return new Agent(this.name, this.type, new PiecePosition(new Pair<Integer, Integer>(this.position.x(), this.position.y())));
	}
	
	public String toString()
	{
		return new String(name + "," + type.toString() + "," + position.toString());
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

}

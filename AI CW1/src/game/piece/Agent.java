/**
 * 
 */
package game.piece;

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

}

package game.piece;


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
	

}

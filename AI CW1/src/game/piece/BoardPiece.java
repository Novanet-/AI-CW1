package game.piece;


public class BoardPiece
{
	private String name;
	private PieceType type;
	private PiecePosition position;
	
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

	
	public String getName()
	{
		return name;
	}

	
	public PieceType getType()
	{
		return type;
	}

	
	public PiecePosition getPosition()
	{
		return position;
	}
	

	
}

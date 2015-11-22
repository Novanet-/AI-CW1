package search;

/**
 * Thrown if for a given initial board state, there is no way to generate a goal state from it
 */
public class NoSolutionPossibleException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1061082434711759763L;


	/**
	 * 
	 */
	public NoSolutionPossibleException()
	{
		super();
	}


	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NoSolutionPossibleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}


	/**
	 * @param message
	 * @param cause
	 */
	public NoSolutionPossibleException(String message, Throwable cause)
	{
		super(message, cause);
	}


	/**
	 * @param message
	 */
	public NoSolutionPossibleException(String message)
	{
		super(message);
	}


	/**
	 * @param cause
	 */
	public NoSolutionPossibleException(Throwable cause)
	{
		super(cause);
	}

}

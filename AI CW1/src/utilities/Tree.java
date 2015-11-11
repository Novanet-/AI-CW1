package utilities;

import java.util.Set;

public class Tree<T>
{

	private T val;
	private Tree<T> parent;
	private HashSet<Tree<T>> children;


	/**
	 * @param val
	 * @param parent
	 * @param children
	 */
	public Tree(T val, Tree<T> parent, HashSet<Tree<T>> children)
	{
		super();
		this.val = val;
		this.parent = parent;
		this.setChildren(children);
	}


	public T getVal()
	{
		return val;
	}


	
	public Tree<T> getParent()
	{
		return parent;
	}


	
	public Set<Tree<T>> getChildren()
	{
		return children;
	}


	private void setChildren(HashSet<Tree<T>> children)
	{
		this.children = children;
	}

	
	
}

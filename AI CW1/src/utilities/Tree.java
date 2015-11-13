package utilities;

import java.util.ArrayList;
import java.util.HashSet;

import game.board.BoardState;

public class Tree<T>
{

	private T					val;
	private Tree<T>				parent;
	private ArrayList<Tree<T>>	children;


	/**
	 * @param val
	 * @param parent
	 * @param children
	 */
	public Tree(T val, Tree<T> parent, ArrayList<Tree<T>> children)
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


	public ArrayList<Tree<T>> getChildren()
	{
		return children;
	}


	private void setChildren(ArrayList<Tree<T>> children)
	{
		this.children = children;
	}


	@Override
	public boolean equals(Object obj)
	{
		boolean treesEqual = true;
		if (!(this.getVal().equals(((Tree<T>) obj).getVal())))
		{
			treesEqual = false;
		}
		if ((this.getParent() == null) || (((Tree<T>) obj).getParent() == null))
		{
			if (!(this.getParent() == ((Tree<T>) obj).getParent()))
			{
				treesEqual = false;
			}
			else
			{
				if (!(this.getParent().equals(((Tree<T>) obj).getParent())))
				{
					treesEqual = false;
				}
			}
		}
		if (!(this.getChildren().equals(((Tree<T>) obj).getChildren())))
		{
			treesEqual = false;
		}

		return treesEqual;
	}


	@Override
	public String toString()
	{
		ArrayList<T> childVals = new ArrayList<T>();
		for (Tree<T> children : this.getChildren())
		{
			childVals.add(children.getVal());
		}
		return new String("Node: \n" + this.getVal() + "Parent: \n" + this.getParent().getVal() + "Children: \n" + childVals);
	}

}

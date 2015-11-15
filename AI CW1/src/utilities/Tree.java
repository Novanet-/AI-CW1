package utilities;

import java.util.ArrayList;

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


	public void setChildren(ArrayList<Tree<T>> children)
	{
		this.children = children;
	}


	@SuppressWarnings("unchecked")
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
			if (!(((this.getParent() == null) && (((Tree<T>) obj).getParent() == null))))
			{
				treesEqual = false;
			}
		}
		else if (!(this.getParent().getVal().equals(((Tree<T>) obj).getParent().getVal())))
		{
			treesEqual = false;
		}

		//		if (!(this.getChildren().equals(((Tree<T>) obj).getChildren())))
		//		{
		//			treesEqual = false;
		//		}

		for (Tree<T> c1 : this.getChildren())
		{
			for (Tree<T> c2 : this.getChildren())
			{
				if (!(c1.getVal().equals(c2.getVal())))
				{
					treesEqual = false;
				}
			}
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


	@Override
	public int hashCode()
	{
		int hash = 3;
		hash = 37 * hash + (this.getVal().hashCode());
		hash = 37 * hash + (this.getParent() == null ? 0 : this.getParent().getVal().hashCode());
		for (Tree<T> c : this.getChildren())
		{
			hash = (37 * hash + (c.getVal().hashCode()));

		}
		return hash;
	}

}

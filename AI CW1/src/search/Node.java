package search;

import java.util.ArrayList;

import game.board.BoardState;

public class Node
{

	private BoardState		val;
	private Node			parent;
	private ArrayList<Node>	children;


	/**
	 * @param val
	 * @param parent
	 * @param children
	 */
	public Node(BoardState val, Node parent, ArrayList<Node> children)
	{
		super();
		this.val = val;
		this.parent = parent;
		this.setChildren(children);
	}


	public BoardState getVal()
	{
		return val;
	}


	public Node getParent()
	{
		return parent;
	}


	public ArrayList<Node> getChildren()
	{
		return children;
	}


	public void setChildren(ArrayList<Node> children)
	{
		this.children = children;
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		boolean treesEqual = true;

		if (!(this.getVal().equals(((Node) obj).getVal())))
		{
			treesEqual = false;
		}

		if ((this.getParent() == null) || (((Node) obj).getParent() == null))
		{
			if (!(((this.getParent() == null) && (((Node) obj).getParent() == null))))
			{
				treesEqual = false;
			}
		}
		else if (!(this.getParent().getVal().equals(((Node) obj).getParent().getVal())))
		{
			treesEqual = false;
		}

		//		if (!(this.getChildren().equals(((Tree<T>) obj).getChildren())))
		//		{
		//			treesEqual = false;
		//		}

		for (Node c1 : this.getChildren())
		{
			for (Node c2 : this.getChildren())
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
		ArrayList<BoardState> childVals = new ArrayList<BoardState>();
		for (Node children : this.getChildren())
		{
			childVals.add(children.getVal());
		}
		return new String("Node: \n" + this.getVal() + "Parent: \n" + this.getParent().getVal() + "Children: \n" + childVals);
	}


	@Override
	public int hashCode()
	{
		int hash = 3;
//		hash = 37 * hash + (this.getVal().hashCode());
//		hash = 37 * hash + (this.getParent() == null ? 0 : this.getParent().getVal().hashCode());
//		for (Node c : this.getChildren())
//		{
//			hash = (37 * hash + (c.getVal().hashCode()));
//
//		}
//		return hash;
		return hash * this.getVal().hashCode();
	}

}

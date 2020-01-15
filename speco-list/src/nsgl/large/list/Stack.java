package nsgl.large.list;

/**
 * <p>Title: Stack</p>
 *
 * <p>Description: An stack </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Stack extends Queue{
	public Stack() {}

	public boolean add( long data ){
		Node node = new Node(data);
		node.next = head;
		if( head == null ){ last = node; }
		head = node;
		size++;
		return true;
	}

	public void push( long data ){ add( data ); }

	public long pop(){
		long t = head.data;
		del();
		return t;
	}
}
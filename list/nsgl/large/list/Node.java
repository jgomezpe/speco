package nsgl.large.list;

/**
 * Title: Node
 * Description: A list node. The list is long linked
 * Copyright: Copyright (c) 2009
 * Company: Kunsamu
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 *
 */
public class Node {
  /**
   * The next node in the list
   */
  protected Node next = null;

  /**
   * The previous node in the list
   */
  protected Node prev = null;

  /**
   * The object stored in the node
   */
  protected long data;

  /**
   * Constructor: Creates a node with the given data. The next attribute is set to null
   * @param data The object stored by the node
   */
  public Node(long data) { this.data = data; }
  
  protected Node(){}
}

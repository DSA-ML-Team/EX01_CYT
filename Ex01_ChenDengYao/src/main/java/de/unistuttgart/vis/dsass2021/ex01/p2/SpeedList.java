package de.unistuttgart.vis.dsass2021.ex01.p2;

//import de.unistuttgart.vis.dsass2021.ex01.p2.SpeedList.Node;

public class SpeedList<T> implements ISpeedList<T> {
	int sizeOf;
	
	int weite; 
	
	public void SetN(int n){this.weite = n ; }
	
	public int getN(){ return this.weite;}
	
	
	
	
	public class Node<T> {
		
		T obj;
		int weite ;
		Node<T> next;
		Node<T> next_n;
		
		public Node(T obj,Node<T> next, Node<T> next_n) {
			
			this.obj = obj;
			this.next = next;
			this.next_n = next_n;
		}
		
		public Node() {
			obj = null;
			next = null;
			next_n = null;
		}
		
		public T getElement() { return obj; }
		
		
		public void setElement(T obj) {this.obj = obj; }
		
		
		public Node<T> getNext() { return next; }
		
		
		public void setNext(Node<T> next) { this.next = next; }
	
		
		public Node<T> getNext_n() {return this.next_n; }
		
	
		public void setNext_n(Node<T> next_n) { this.next_n = next_n; }
		
	}
		

// Initiating
	
	private Node<T> head;
	
	public SpeedList(){
		head = new Node<T>();
		
	} 
	
	
	
	@Override
	public int size(){
		Node<T> slot = head;
		int i = 1;
		if (slot.next == null) {
			return 0;
		}
		while (slot.next != null) {
             slot=slot.next;
             i=i+1;
		}
		sizeOf = i;
			return sizeOf;
		}
	
	@Override
	public void prepend(T t) {
		//create new link, that node is getting before head
		
		Node<T> new_node = new Node<T>(t, head.getNext(), head.getNext_n() );
		head.setNext(new_node);
		head.setNext_n( (new_node.getNext_n()).getNext()  );
	}
	
	
	@Override
    public void append(T t) {
    	Node<T> l = head;
// firstly, try to jump at pace n
    	while(l.getNext_n() != null )
    	{
    		l = l.getNext_n();
    	}
// until i can not jump at pace n, i walk at pace one
    	while (l.getNext() != null)
    	{
    		l = l.getNext();
    	}
    	
    	Node<T> end_node = new Node<T>(t, null,null);
    	l.setNext(end_node);
	}
	



	@Override
	public T getElementAt(int pos) {
		Node<T> current = head;
		// have to check if pos Value is valid
		if(pos <= 0 || pos> this.size() ) 
		{
			throw new IndexOutOfBoundsException();
		}
		
		else if(pos > 0 && pos <= this.size()  != false ) {
			int i = 0;
			int stepOfJump = pos /this.weite;
			int stepOfWalk = pos % this.weite;
			// jump at pace n then walk at pace 1
			while(current.getNext_n() != null && i< stepOfJump )
			{
				current = current.getNext_n();
				i++;
			}
			while( current.getNext()!= null && i< stepOfWalk) 
			{
				current = current.getNext();
				i++;
			}	
		}
		return current.getElement();
	}

	
	
	@Override
	public T getPrevious(int pos) {
		T target = null;
		if (pos != 0)
		{
			if(pos == 1) {return head.getElement();}
			else if (pos > 1) 
			{
				int pre_pos = pos - 1;
				target = this.getElementAt(pre_pos);
			}
		}
		else if(pos == 0) {throw new IndexOutOfBoundsException();}
		return target;
	}
	
}
	
	


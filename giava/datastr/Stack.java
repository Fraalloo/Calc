package giava.datastr;

public class Stack<T> extends LinkedList<T>{
    public Stack(){}
    
    public Stack(T data){
        super(data);
    }

    public void push(T data){
        addFirst(data);
    }

    public T pop(){
        Node node = getHead();
        removeFirst();
        return node.getData();
    }
}

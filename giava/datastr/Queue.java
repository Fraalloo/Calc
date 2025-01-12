package giava.datastr;

public class Queue<T> extends LinkedList<T>{
    public Queue(){}
    
    public Queue(T data){
        super(data);
    }

    public void enqueue(T data){
        addFirst(data);
    }

    public T dequeue(){
        Node node = getTail();
        removeLast();
        return node.getData();
    }
}

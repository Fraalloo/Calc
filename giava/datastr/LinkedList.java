package giava.datastr;

public class LinkedList<T> implements Iterable<T> {
    protected class Node{
        private T data;
        private Node next;

        public Node(){}
        
        public Node(T data){
            setData(data);
        }
        
        public Node(T data, Node next){
            setData(data);
            setNext(next);
        }
        
        public void setData(T data){
            this.data = data;
        }
        
        public T getData(){
            return data;
        }
        
        public void setNext(Node next){
            this.next = next;
        }
        
        public Node getNext(){
            return next;
        }
        
        public String hex(){
            String addr = super.toString();
            
            return addr.substring(addr.indexOf("@"));
        }
        
        @Override
        public String toString(){
            return "Node[data = " + data + "; next = " + ((next == null) ? null : next.hex()) + "]";
        }
    }

    public class Iterator implements java.util.Iterator<T> {
        private Node node;

        private Iterator(Node node){
            this.node = node;
        }

        public boolean hasNext(){
            return node != null;
        }

        public T next(){
            if(node == null) return null;

            T obj = node.getData();
            node = node.getNext();
            return obj;
        }
    }

    private Node head;

    public LinkedList(){}
    
    public LinkedList(T data){
        head = new Node(data);
    }
    
    @SafeVarargs
    public LinkedList(T... datas){
        head = new Node(datas[0]);
        Node node = head;
        
        for(int i = 1; i < datas.length; i++){
            node.setNext(new Node(datas[i]));
            node = node.getNext();
        }
    }
    
    public void addFirst(T data){
        head = new Node(data, head);
    }

    @SafeVarargs
    public final void addFirst(T... datas){
        for (int i = datas.length - 1; i >= 0; i--) {
            addFirst(datas[i]);
        }
    }
    
    public void addLast(T data){
        if(head == null){
            addFirst(data);
            return;
        }
        Node node = head;
        
        while(node.getNext() != null){
            node = node.getNext();
        }
        
        node.setNext(new Node(data));
    }
    
    @SafeVarargs
    public final void addLast(T... datas){
        if(head == null){
            addFirst(datas);
            return;
        }
        
        Node node = head;
        
        while(node.getNext() != null){
            node = node.getNext();
        }
        
        for(T data: datas){
            node.setNext(new Node(data));
            node = node.getNext();
        }
    }

    public T removeFirst(){
        Node node = head;
        if(head != null) head = head.getNext();
        return node != null ? node.getData() : null;
    }

    public T removeLast(){
        Node node = head;
        
        if(head == null) return null;
        if(head.getNext() == null) return removeFirst();
        
        while(node.getNext().getNext() != null){
            node = node.getNext();
        }
        
        T temp = node.getNext().getData();
        node.setNext(null);

        return temp;
    }

    public T remove(int pos){
        if(head == null || pos > size()) return null;
         
        if(pos == 1) return removeFirst();
        if(pos == size()) return removeLast();
        Node node = getNode(pos - 1);
        T temp = node.getNext().getData();
        node.setNext(node.getNext().getNext());
        return temp;
    }
    
    public boolean contains(T data){
        if(head.getNext() == null) return false;
        
        Node node = head;
        
        while(node.getNext() != null){
            if(data.equals(node.getData())) return true;
            node = node.getNext();
        }
        
        return false;
    }
    
    public int indexOf(T data){
        if(head.getNext() == null) return -1;
        
        Node node = head.getNext();
        int cont = 0;
        
        while(node.getNext() != null){
            if(data.equals(node.getData())) return cont;
            node = node.getNext();
            cont++;
        }
        
        return -1;
    }

    protected Node getNode(int pos){
        if(head == null || pos < 0 || pos >= size()) return null;
        
        Node node = head.getNext();
        
        for(int i = 0; i < pos; i++){
            node = node.getNext();
        }
        
        return node;
    }

    public T get(int pos){
        try{
            return getNode(pos).getData();
        }catch(Exception e){
            return null;
        }
    }
    
    protected Node getHead(){
        return head;
    }  
    
    protected Node getTail(){
        if(head.getNext() == null) return head;
        Node node = head;
        
        while(node.getNext() != null){
            node = node.getNext();
        }
        
        return node;
    }
    
    public T getFirst(){
        if(head == null) return null;
        return head.getData();
    }
    
    public T getLast(){
        if(head == null) return null;
        Node node = head;
        
        while(node.getNext() != null){
            node = node.getNext();
        }
        
        return node.getData();
    }

    public boolean set(int pos, T data) {
        Node node = getNode(pos);
        if(node == null) return false;
        node.setData(data);
        return true;
    }

    public boolean setFirst(T data) {
        if(head == null) return false;
        head.setData(data);
        return true;
    }

    public boolean setLast(T data) {
        Node tail = getTail();
        if(tail == null) return false;
        tail.setData(data);
        return true;
    }
    
    public int size(){
        if(head == null) return 0;

        Node node = head;
        int cont = 1;
        
        while(node.getNext() != null){
            node = node.getNext();
            cont++;
        }
        
        return cont;
    }
    
    public boolean isEmpty(){
        if(head == null) return true;
        else return false;
    }
    
    public void clear(){
        head = null;
    }
    
    public void cat(LinkedList<T> list){
        getTail().setNext(list.getHead());
    }

    public void cat(LinkedList<T> list, int pos){
        list.getTail().setNext(getNode(pos));
        getNode(pos-1).setNext(list.getHead()); 
    }
    
    public Iterator iterator(){
        return new Iterator(head);
    }
    
    @Override
    public String toString(){
        return toString(", ");
    }

    public String toString(String sep){
        return toString(sep, "[", "]");
    }

    public String toString(String sep, String start, String end){
        if(head == null) return start + end;
        Node node = head;
        String ret = start;
        
        while(true){
            ret += node.getData();
            node = node.getNext();
            if(node == null) return ret + end;
            else ret += sep;
        }
    }

    public String hex(){
        String addr = super.toString();
    	
        return addr.substring(addr.indexOf("@"));
    }
    
    public String toStringHex(){
        Node node = head;
        String ret = "";
        
        while(node != null){
            ret += node.toString() + "\n";
            node = node.getNext();
        }
        
        return ret;
    }
}
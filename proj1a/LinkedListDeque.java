import javax.security.sasl.SaslServer;

public class LinkedListDeque <T>{

    public class TNode{
        public TNode prev;
        public T item;
        public TNode next;

        public TNode(){
            prev = null;
            item = null;
            next = null;
        }
    }

    private  TNode SentinelF ;
    private  TNode SentinelB ;
    private  int size;
    public LinkedListDeque(){
        SentinelF = new TNode();
        SentinelB = new TNode();
        SentinelB.prev = SentinelF;
        SentinelF.next = SentinelB;
    }
    public void addFirst(T item){
        TNode x = new TNode();
        x.item = item;
        x.prev = SentinelF;
        TNode temp = SentinelF.next;
        SentinelF.next = x;
        x.next = temp;
        temp.prev = x;
        size = size + 1;
    }
    public void addLast(T item){
        TNode x = new TNode();
        x.item = item;
        x.next = SentinelB;
        TNode temp = SentinelB.prev;
        SentinelF.prev = x;
        x.prev = temp;
        temp.next = x;
        size = size + 1;
    }
    public boolean isEmpty(){
        if(this.size == 0){
            return true;
        }
        else {
            return false;
        }
    }
    public int size(){
        return this.size;
    }
    public void printDeque(){
        TNode temp = SentinelF.next;
        for(int i=0;i<this.size;i=i+1){
            System.out.print(temp.item + "");
        }
    }
    public T removeFirst(){
        TNode temp = SentinelF.next;
        SentinelF.next = temp.next;
        temp.next.prev = SentinelF;
        temp.prev = null;
        temp.next = null;
        size = size - 1;
        return temp.item;
    }
    public T removeLast(){
        TNode temp = SentinelB.prev;
        SentinelB.prev = temp.prev;
        temp.prev.next = SentinelB;
        temp.next = null;
        temp.prev = null;
        size = size - 1;
        return temp.item;
    }
    public T get(int index){
        if(this.size==0){
            return null;
        }
        if (index>=0){
            int IncTimes = index % this.size;
            TNode temp = SentinelF.next;
            for (int i=0;i<IncTimes;i+=1){
                temp = temp.next;
            }
            return temp.item;
        } else if (index < 0 ) {
            int IncTimes = Math.abs(index) % this.size;
            TNode temp = SentinelB;
            for (int i=0;i<IncTimes;i+=1){
                temp = temp.prev;
            }
            return temp.item;

        }
        return null;
    }

    public T getRecursive(int index){
        if (index>=0){
            int IncTimes = index % this.size;
            TNode Temp = SentinelF;
            return getRecursive_helper1(IncTimes,SentinelF);

        } else if (index < 0 ) {
            int IncTimes = Math.abs(index) % this.size;
            TNode Temp = SentinelB;
            return  getRecursive_helper2(IncTimes,SentinelB);

        }
        return null;
    }

    public T getRecursive_helper1(int index,TNode sentinelF){
        if(index==0){
            return sentinelF.next.item;
        }
        return getRecursive_helper1(index-1,sentinelF.next);

    }

    public T getRecursive_helper2(int index,TNode sentinelB){
        if (index==1){
            return  sentinelB.prev.item;
        }
        return getRecursive_helper2(index-1,sentinelB.prev);
    }
    public static void main(String[] args){
        LinkedListDeque<String>D = new LinkedListDeque<>();
        D.addFirst("1");
        D.addFirst("2");
        D.addFirst("3");
        D.addFirst("4");
        System.out.println(D.getRecursive(6));
    }
}

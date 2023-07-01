public class ArrayDeque<T>{
    public T[] items;
    public int size;
    public int nextfirst;
    public int nextlast;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextfirst = 0;
        nextlast = 1;
    }

    public T[] resize(){
        T[] newitems = (T[]) new Object[items.length*2];
        int TempFirst=(nextfirst+1)%items.length;
        int TempLast = nextlast-1;
        if(TempLast<0){
            TempLast = items.length-1;
        }
        int NewFirst = 0;
        while(TempFirst!=TempLast){
            newitems[NewFirst]=items[TempFirst];
            TempFirst=(TempFirst+1)%items.length;
            NewFirst = NewFirst + 1;
        }
        newitems[NewFirst]=items[TempFirst];
        nextfirst=newitems.length-1;
        nextlast=NewFirst+1;
        return newitems;
    }

    public T[] shrink(){
        T[] newitems = (T[]) new Object[items.length/2];
        int TempFirst=(nextfirst+1)%items.length;
        int TempLast = nextlast-1;
        if(TempLast<0){
            TempLast = items.length-1;
        }
        int NewFirst = 0;
        while(TempFirst!=TempLast){
            newitems[NewFirst]=items[TempFirst];
            TempFirst = (TempFirst+1)%items.length;
            NewFirst = NewFirst +1;
        }
        newitems[NewFirst]=items[TempFirst];
        nextfirst=newitems.length-1;
        nextlast=NewFirst+1;
        return newitems;

    }
    public void addFirst(T item){
        if(size == items.length){
            items = this.resize();
            items[nextfirst]=item;
            size=size+1;
            if(nextfirst==0){
                nextfirst = items.length-1;
            }
            else {
                nextfirst = nextfirst-1;
            }
        } else if (size<items.length) {
            items[nextfirst]=item;
            size=size+1;
            if(nextfirst==0){
                nextfirst = items.length-1;
            }
            else {
                nextfirst = nextfirst-1;
            }
        }
    }

    public void addLast(T item){
        if(size == items.length){
            items = this.resize();
            items[nextlast] = item;
            size=size+1;
            nextlast = (nextlast+1)%items.length;
        } else if (size<items.length) {
            items[nextlast] = item;
            size=size+1;
            nextlast = (nextlast+1)%items.length;
        }
    }

    public boolean isEmpty(){
        if (size==0){
            return true;
        }
        else {
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int TempFirst = (nextfirst+1)%items.length;
        int TempLast = nextlast-1;
        //写法可能有问题，如果有更好的循环数组的方式，可以把限制条件减少。
        if(TempLast<0){
            TempLast = items.length-1;
        }
        //以上
        while (TempLast!=TempFirst){
            System.out.print(items[TempFirst]+" ");
            TempFirst=(TempFirst+1)%items.length;
        }
        System.out.println(items[TempFirst]);
    }

    public T removeFirst(){
        int TempFirst = (nextfirst+1)%items.length;
        T item = items[TempFirst];
        items[TempFirst]=null;
        nextfirst = TempFirst;
        size=size-1;
        if(size<=items.length/4 && items.length>=16){
            items=this.shrink();
        }
        return item;
    }

    public T removeLast(){
        int TempLast = nextlast-1;
        if(TempLast<0){
            TempLast = items.length;
        }
        T item = items[TempLast];
        items[TempLast]=null;
        nextlast=TempLast;
        size=size-1;
        if(size<=items.length/4 && items.length>=16){
            items=this.shrink();
        }
        return item;
    }

    public T get(int index){
        int TempFirst = (nextfirst+1)%items.length;
        int x = (index+TempFirst)%items.length;
        return items[x];
    }


    public static void main(String[] args){
        ArrayDeque<Integer> AD= new ArrayDeque<>();
        AD.addFirst(1);
        AD.addFirst(4);
        AD.addLast(4);
        AD.addLast(5);
        AD.addFirst(1);
        AD.addLast(6);
        AD.addLast(2);
        AD.addLast(8);
        AD.printDeque();
        AD.addLast(9);
        AD.printDeque();
        System.out.println(AD.get(5));
        AD.removeLast();
        AD.removeFirst();
        AD.removeFirst();
        AD.removeLast();
        AD.removeFirst();
        AD.removeFirst();
        AD.removeFirst();
        AD.printDeque();

    }
}

// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import synthesizer.AbstractBoundedQueue;

import java.util.Comparator;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.fillCount = 0;
        this.capacity = capacity;
        this.first = 0;
        this.last = 0;
        this.rb = (T[]) new Object[capacity];
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if(fillCount == capacity){
            throw new IndexOutOfBoundsException("Ring Buffer Overflow");
        }
        this.rb[last] = x;
        last = (last + 1) % capacity;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if(fillCount == 0){
            throw new IndexOutOfBoundsException("Ring Buffer Underflow");
        }
        T temp = this.rb[first];
        this.rb[first] = null;
        first = (first + 1) % capacity;
        fillCount -= 1;
        return temp;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return this.rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    public Iterator<T> iterator() {
        return new RingBufferIterator();
    }



    private class RingBufferIterator implements Iterator<T>{
        private int position;
        public RingBufferIterator(){
            position = first;
        }
        public boolean hasNext(){
            int temp = last - 1;
            if (temp < 0){
                temp = capacity - 1;
            }
            if (fillCount == 0){
                return false;
            }
            if (position == temp){
                return false;
            }
            else {
                return true;
            }

        }
        public T next(){
            T x = rb[position];
            position = (position + 1) % capacity;
            return x;
        }

    }
}

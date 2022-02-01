package Utils;

public class CustomArrayList<E> implements CustomInterfaceList<E> {

    // data fields
    private Object[] array;
    private int size;
    private int maxSize;

    /**
     * Default constructor, creates an empty underlying array with maxSize 2
     */
    public CustomArrayList() {
        maxSize = 2;
        size = 0;
        array = new Object[maxSize];
    }

    /**
     * Sized constructor, creates an empty object with maxSize size
     * @param size the initial size of the underlying array
     */
    public CustomArrayList(int size) {
        maxSize = size;
        size = 0;
        array = new Object[size];
    }

    /**
     * Element list constructor, takes in variable number of objects and creates an underlying
     * array large enough to fit them.
     * @param e
     */
    public CustomArrayList(E ...e) {
        maxSize = size = e.length;
        array = new Object[size];

        for (int i = 0; i < size; ++i) {
            array[i] = e[i];
        }
    }


    /**
     * Adds an object to the underlying array after all previously added objects.
     * If array needs to grow, it invokes grow method.
     * @param o object to be added
     */
    @Override
    public void add(E e) {
        //if (size= n) add o to n
        // NOTE: if size >= maxSize we need to grow array
        if (size >= maxSize) {
            growArray();
        }

        array[size] = e;
        size = size + 1;
    }

    /**
     * Adds object at specified index, advancing the size of the underlying array. This will
     * require us to shift all later elements further down the index order
     * @param index index location where object will be inserted
     * @param e element to be inserted
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void add(E e, int index) throws IndexOutOfBoundsException {
        if (index >= maxSize) {
            throw new IndexOutOfBoundsException();
        }

        //Implement this method
        if (size >= maxSize) {
            growArray();
        }

        if (index == 0)
        {
            for (int k = size - 2; k >= 0; k--) {
                array[k + 1] = array[k];
            }
        }
        else {
            for (int k = size - 1; k > (index - 1); k--) {
                array[k + 1] = array[k];
            }
        }

        array[index] = e;
        size = size + 1;
    }

    /**
     * gets the object located at supplied index
     * @param index index of object to get
     * @return object located at index
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException{
        //Implement this method
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E)array[index];
    }

    /**
     * Emptys the underlying array by setting it's private reference to null and allowing
     * the old array to be garbage collected.
     */
//    @Override
//    public void clear() {
//        //Implement this method
//    }
//
//    /**
//     * Check if object o is found within underlying array, using Object.equals() method
//     * @param o object to search for
//     * @return index location of first instance of matching object. -1 if not found.
//     */
//    @Override
//    public int contains(Object o) {
//        //Implement this method
//    }
//
//    /**
//     * Removes object at specified index from underlying array, we will then
//     * need to shift the remaining elements up in the index order to fill in the gap
//     * @param index index of object to remove from array
//     */
//    @Override
//    public void remove(int index) {
//        //Implement this method
//    }

    /**
     * returns size of array. This is the one greater than the index of the most advanced stored object,
     * not the maxSize which controls growth of the underlying array.
     * @return one greater than index of most advanced stored object
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Doubles the size of the underlying array by creating a new array and copying the
     * contents of the previous array into it.
     */
    private void growArray(){
        //System.out.println("Growing Array from " + maxSize + " to " + maxSize * 2);
        //set up new array
        maxSize = maxSize * 2;
        Object[] tempArray = array;
        array = new Object[maxSize];

        //copy to new array
        for (int i = 0; i < size; i++) {
            array[i] = tempArray[i];
        }
    }

}

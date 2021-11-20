package lesson2.arrayList;

public class ArrayListIp<E> implements ArrayList<E> {
    private Object[] array;
    private int size = 0;

    public ArrayListIp() {
        this.array = new Object[12];
    }

    public ArrayListIp(int size) {
        this.array = new Object[size];
    }

    @Override
    public void add(E item) {
        if (size == array.length)
            arrayBoost();
        array[size++] = item;
    }

    private void arrayBoost() {
        Object[] newArray = new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;

    }

    @Override
    public void addFirst(E item) {
        if (size == array.length)
            arrayBoost();
        Object[] buffArray = new Object[array.length];
        buffArray[0] = item;
        if (size + 1 >= 0)
            System.arraycopy(array, 0, buffArray, 1, size + 1);
        array = buffArray;
        size++;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.array = new Object[12];
    }

    @Override
    public ArrayList<E> clone() {
        ArrayListIp<E> clone = new ArrayListIp<>(array.length);
        for (int i = 0; i <= size; i++) {
            clone.add((E) array[i]);
        }
        return clone;
    }

    @Override
    public boolean contains(E item) {
        for (int i = 0; i <= size; i++)
            if (array[i] == item)
                return true;
        return false;
    }

    @Override
    public E get(int index) {
        if (index >= 0 && index <= size)
            return (E) array[index];
        return null;
    }

    @Override
    public int indexOf(E item) {
        for (int i = 0; i <= size; i++)
            if (array[i] == item)
                return i;
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(E item) {
        for (int i = 0; i <= size; i++) {
            if (array[i] == item) {
                Object[] newArray = new Object[array.length];
                for (int j = 0; j < size; j++) {
                    if (j < i)
                        newArray[j]=array[j];
                    if (j> i)
                        newArray[j-1] = array[j];
                }
                array = newArray;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void set(int index, E item) {

    }

    @Override
    public int size() {
        return size;
    }

    public void display() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            if (i == size - 1)
                System.out.print(array[i]);
            else
                System.out.print(array[i] + ", ");
        }
        System.out.println("]" + array.length);
    }
}

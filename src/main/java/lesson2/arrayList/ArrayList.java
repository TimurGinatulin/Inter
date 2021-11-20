package lesson2.arrayList;

public interface ArrayList<E> {
    void add(E item);

    void addFirst(E item);

    void clear();

    ArrayList<E> clone();

    boolean contains(E item);

    E get(int index);

    int indexOf(E item);

    boolean isEmpty();

    boolean remove(E item);

    void set(int index, E item);

    int size();

    void display();
}

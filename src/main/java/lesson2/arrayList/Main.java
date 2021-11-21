package lesson2.arrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayListIp<>(3);
        list.add(1);
        list.add(1);
        list.add(3);
        list.add(1);
        list.addFirst(5);
        list.display();
list.remove(3);
        list.display();
    }
}

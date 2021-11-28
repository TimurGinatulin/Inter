package lesson1;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        System.out.println(list);
        int i = 0;
        int j = list.size()-1;
        while (i < j) {
            Integer start = list.get(i);
            Integer end = list.get(j);
            list.set(i,end);
            list.set(j, start);
            i++;
            j--;
        }
        System.out.println(list);
    }
}

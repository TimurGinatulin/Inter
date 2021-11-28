package lesson3;

public class Lesson3Main {
    public static void main(String[] args) {
        PingPonger pingPonger = new PingPonger();
        new Thread(() -> TreadSafetyCounter.increment()).start();
        new Thread(() -> TreadSafetyCounter.increment()).start();
        new Thread(() -> TreadSafetyCounter.readCounter()).start();
    }
}

package lesson3;

public class PingPonger {
    private Object LockObject;

    public void say(String say) {
        synchronized (this) {
            while (true) {
                System.out.println(say);
                try {
                    this.notify();
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

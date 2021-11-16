package lesson1;

public class ReView {


    interface Moveable {
        void move();
    }

    interface Stopable {
        void stop();
    }
// Можно использовать аннтоцию Дата ломбок для доступа к полям класа, модификатор доступа у поля engine лучше использовать private
//либо так как для всех полей у неас есть геттеры и сеттеры то можно сразу у всех полей доступ паблик и не реализовывать геттера и сеттеры
    abstract class Car {
        public Engine engine;
        private String color;
        private String name;


        protected void start() {
            System.out.println("Car starting");
        }

        abstract void open();

        public Engine getEngine() {
            return engine;
        }

        public void setEngine(Engine engine) {
            this.engine = engine;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class LightWeightCar extends Car implements Moveable{

        @Override
        void open() {
            System.out.println("Car is open");
        }

        @Override
        public void move() {
            System.out.println("Car is moving");
        }

    }

   /* class Lorry extends Car, Moveable, Stopable{
*В Java запрещенно множественное наследование ,интерфейсы можно только имплементировать
* также контракт Car обязывает нас реализовать абстрактный метод
*/
    class Lorry extends Car implements Moveable, Stopable{
       @Override
       void open() {
           System.out.println("Car Lorry is open");
       }
       // метод move идентичный и всех классов реализующих его, предложил бы вынести его в абстрактный класс и сделать базовой реализацией
       // возожно использование интерфесо являются избыточным так как в теории все машины должны иметь возможность остановиться , но возможна такова бизнес логика
        public void move(){
            System.out.println("Car is moving");
        }

        public void stop(){
            System.out.println("Car is stop");
        }
    }
class Engine{

}

}

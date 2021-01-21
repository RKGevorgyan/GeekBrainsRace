import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cd1 = new CountDownLatch(4);
        CountDownLatch cd2 = new CountDownLatch(4);
        Semaphore semaphore = new Semaphore(2);
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(semaphore), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cd1,cd2);
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }


        cd1.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        cd2.await();
//        for (int i = 0; i < cars.length; i++) {
//            if (cars[i].getWinCount() == 3){
//                System.out.println(cars[i].getName() + " WIN!!!");
//                break;
//            }
//        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

    }
}
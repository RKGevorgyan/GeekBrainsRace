import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    CountDownLatch cd1;
    CountDownLatch cd2;
    private static volatile boolean winner;
    static {
        winner = true;
    }
    private int winCount;
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public static boolean isWinner() {
        return winner;
    }

    public static void setWinner(boolean winner) {
        Car.winner = winner;
    }

    public Car(Race race, int speed, CountDownLatch cd1, CountDownLatch cd2) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cd1 = cd1;
        this.cd2 = cd2;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            cd1.countDown();
            System.out.println(this.name + " готов");
            cd1.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            try {
                race.getStages().get(i).go(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cd2.countDown();
    }


}
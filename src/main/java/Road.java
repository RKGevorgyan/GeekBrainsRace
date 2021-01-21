public class Road extends Stage {



    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        c.setWinCount(c.getWinCount()+1);
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Car.isWinner() && c.getWinCount() == 3) {
            System.out.println(c.getName()+ " WIN!!!");
            Car.setWinner(false);
        }
    }
}
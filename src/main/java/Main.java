import Characters.Direction;
import Characters.PlayerActor;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        PlayerActor playerOne = new PlayerActor(4,5,100, Direction.NORTH, false);


        System.out.println(playerOne.getStartState()[0]);
        System.out.println(playerOne.getStartState()[1]);
        System.out.println(playerOne.getPosition()[0]);
        System.out.println(playerOne.getPosition()[1]);
        System.out.println(playerOne.getHealth());
        System.out.println(playerOne.getDirectionFacing());
        System.out.println(playerOne.getHasBossReward());
        System.out.println(playerOne);



    }
}

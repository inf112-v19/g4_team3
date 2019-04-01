package inf112.skeleton.app.gameObjects;

import inf112.skeleton.app.board.Direction;
import inf112.skeleton.app.card.ProgramCard;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Player extends Robot {
    private String name;
    private String path;


    public Player(Direction dir, int xPos, int yPos, String name, String path) {
        super(dir, xPos, yPos, name, path);
        this.name = name;
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player[] getSomePlayers() {
        Player[] arr = new Player[3];
        Player player1 = new Player(null, 0, 0, "Joachim", null);
        Player player2 = new Player(null, 0, 0, "Joach", null);
        Player player3 = new Player(null, 0, 0, "Joa", null);
        arr[0] = player1;
        arr[1] = player2;
        arr[2] = player3;
        return arr;
    }


}
package inf112.skeleton.app.gameObjects;
import inf112.skeleton.app.gameObjects.Items.Flag;
import inf112.skeleton.app.board.Direction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import inf112.skeleton.app.board.Position;
import inf112.skeleton.app.card.ProgramCard;

/**
 * The class that represents a robot. It's abstract because this makes it easier to make a robot
 * that is controlled by the computer.
 */
public abstract class Robot implements IRobot {
    private int id;
    private Direction dir;
    private Position pos;
    private int lifeTokens = 3;
    private Position backup;
    private ProgramCard[] cards;
    private int visitedFlags = 0;
    private int damageTokens = 0;
    private boolean destroyed;
    private boolean poweredDown;
    private String name;
    private String filePath;

    public Robot (int id, Direction dir, int xPos, int yPos, String name, String filePath){
        this.id = id;
        this.dir = dir;
        this.pos = new Position(xPos, yPos);
        this.backup = new Position(xPos, yPos);
        this.name = name;
        this.filePath = filePath;
    }

    public void chooseCards(ProgramCard[] pos_cards) {
        HashSet<ProgramCard> set = new HashSet<>(5);
        Random rn = new Random();
        int register = 9 - getDamageTokens();
        if(register > 5){
            register = 5;
        }
        this.cards = new ProgramCard[register];
        while(true) {
            ProgramCard s = pos_cards[rn.nextInt(pos_cards.length)];
            set.add(s);
            if(set.size() == register){
                break;
            }
        }
        ArrayList<ProgramCard> rr = new ArrayList<>(set);
        for(int x = 0; x < rr.size(); x++) {
            cards[x] = rr.get(x);

        }
    }
    @Override
    public void move(Direction dir) {
        switch(dir) {
            case NORTH: this.pos.moveNorth();
                break;
            case SOUTH: this.pos.moveSouth();
                break;
            case EAST: this.pos.moveEast();
                break;
            case WEST: this.pos.moveWest();
        }
    }
    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Direction getDirection(){
        return this.dir;
    }

    @Override
    public Position getPosition() {
        return pos;
    }

    @Override
    public void makeBackup(Position newBackup) {
        this.backup = newBackup;
    }

    @Override
    public void rotateLeft(){
        this.dir = dir.left();
    }

    @Override
    public void rotateRight() {
        this.dir = dir.right();
    }

    @Override
    public int getX(){
        return this.pos.getX();
    }

    @Override
    public int getY(){
        return this.pos.getY();
    }

    @Override
    public void takeDamage(){
        this.damageTokens++;
        if(this.damageTokens >= 10) {
            this.die();
            damageTokens = 0;
        }
    }

    @Override
    public void takeDamage(int damage){
        this.damageTokens = this.damageTokens + damage;
        if(damageTokens >= 10){
            this.die();
            damageTokens = 0;
        }
    }

    @Override
    public void repairDamage(){
        if (!(damageTokens == 0)){
            this.damageTokens--;
        }
    }

    @Override
    public boolean gameOver(){
        return !(this.lifeTokens > 0);
    }

    @Override
    public ProgramCard[] getCards() {
        return cards;
    }

    public void setCards(ProgramCard[] cardArray) {
        this.cards = cardArray;
    }

    @Override
    public void die() {
        this.destroyed = true;
        this.lifeTokens--;
    }
    @Override
    public void visitFlag(Flag flag) {
        if(visitedFlags == (flag.getFlagNum() - 1)){
            visitedFlags++;
            makeBackup(new Position(pos.getX(), pos.getY()));
        }
    }
    @Override
    public void respawn() {
        this.destroyed = false;
        this.pos = new Position(backup.getX(),backup.getY());
        this.damageTokens = 2;
    }
    @Override
    public int visitedFlags() {
        return this.visitedFlags;
    }
    @Override
    public void powerDown() {
        this.damageTokens = 0;
        this.poweredDown = true;
    }
    public void powerUp() {
        poweredDown = false;
    }

    @Override
    public boolean isPoweredDown(){
        return poweredDown;
    }

    @Override
    public boolean isDestroyed() {
        return this.destroyed;
    }

    @Override
    public int getDamageTokens(){
        return this.damageTokens;
    }

    public String getPath(){
        return this.filePath;
    }

    public int getLifeTokens(){
        return lifeTokens;
    }

    public String getName(){
        return name;
    }
}
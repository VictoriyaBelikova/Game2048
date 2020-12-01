package ru.sber.game;

public interface Game {

    void init();
    boolean canMove();
    void move(Direction direction) throws GameOverException;
    void addItem();
    Board getGameBoard();
    boolean hasWin();

}

package ru.sber.game;

import javax.swing.*;

public class PlayGame {
    public static void main(String[] args) {
        JFrame game = new JFrame();
        game.setTitle("2048 Game");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(340, 360);
        game.setResizable(false);

        game.add(new Game2048Panel());

        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}

package org.hyperskill.life.controller;

import org.hyperskill.life.model.Generation;
import org.hyperskill.life.model.Universe;
import org.hyperskill.life.view.GameOfLife;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller {

    private final GameOfLife game;
    private final Universe universe;
    private final Generation generation;
    private final JToggleButton playStopBtn;
    private final int mapSize = 30;
    private int nGeneration;

    public Controller() {

        game = new GameOfLife();
        universe = new Universe();
        generation = new Generation();

        playStopBtn = game.getPlayStopBtn();

        game.setMapSize(mapSize);
        createNewMapAndUpdateGUI();

        playStopBtn.addActionListener(e -> {

            if (playStopBtn.isSelected()) {
                timer.restart();
                playStopBtn.setIcon(FontIcon.of(
                        MaterialDesign.
                                MDI_PAUSE_CIRCLE_OUTLINE, 30));

            } else {
                timer.stop();
                playStopBtn.setIcon(FontIcon.of(
                        MaterialDesign.MDI_PLAY_CIRCLE_OUTLINE, 30));
            }

        });

        game.getReloadBtn().addActionListener(e -> {
            timer.stop();
            createNewMapAndUpdateGUI();
            playStopBtn.setIcon(FontIcon.of(
                    MaterialDesign.MDI_PLAY_CIRCLE_OUTLINE, 30));
            playStopBtn.setSelected(false);
            playStopBtn.setEnabled(true);
        });

    }


    Timer timer = new Timer(200, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            generation.generateMap();

            game.updateCounters(++nGeneration, countAlive(generation.getMap()));
            game.updateMap(generation.getMap());
        }
    });


    private void createNewMapAndUpdateGUI() {
        nGeneration = 1;
        universe.createMap(mapSize);
        generation.setPrevMap(universe.getMap());
        game.updateCounters(nGeneration, countAlive(universe.getMap()));
        game.updateMap(universe.getMap());
    }


    public int countAlive(boolean[][] map) {
        int nAlive = 0;
        for (boolean[] row : map) {
            for (boolean cell : row) {
                if (cell) {
                    nAlive++;
                }
            }
        }
        return nAlive;
    }

}

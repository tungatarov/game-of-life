package org.hyperskill.life.model;

import java.util.Random;

public class Universe {

    private boolean[][] map;


    public boolean[][] getMap() {
        return map;
    }


    public void createMap(int size) {
        Random random = new Random();
        map = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = random.nextBoolean();
            }
        }
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (boolean[] row : map) {
            for (boolean cell : row) {
                sb.append(cell ? 'O' : ' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}

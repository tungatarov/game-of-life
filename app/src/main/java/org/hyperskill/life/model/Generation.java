package org.hyperskill.life.model;

public class Generation {

    private boolean[][] prevMap;
    private boolean[][] nextMap;


    public boolean[][] getMap() {
        return nextMap;
    }


    public void setPrevMap(boolean[][] current) {
        this.prevMap = current;
    }


    public void generateMap() {
        int size = prevMap.length;
        this.nextMap = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                int neighbours = countNeighbours(i, j);

                if (prevMap[i][j]) {

                    nextMap[i][j] = neighbours <= 3 && neighbours >= 2;

                } else {
                    nextMap[i][j] = neighbours == 3;
                }

            }
        }
        this.prevMap = this.nextMap;
    }


    private int countNeighbours(int i, int j) {
        int numOfNeighbours = 0;

        for (int m = 1; m < 4; m++) {

            int x = getCoordinate(m, i);

            for (int n = 1; n < 4; n++) {

                if (m == 2 && n == 2) continue;

                int y = getCoordinate(n, j);

                if (prevMap[x][y]) {
                    numOfNeighbours++;
                }

            }

        }
        return numOfNeighbours;
    }


    private int getCoordinate(int iteration, int coordinate) {
        if (iteration == 1) {
            if (coordinate < 1) {
                coordinate = nextMap.length - 1;

            } else {
                coordinate = coordinate - 1;
            }
        }

        if (iteration == 3) {
            if (coordinate != nextMap.length - 1) {
                coordinate = coordinate + 1;

            } else {
                coordinate = 0;
            }
        }
        return coordinate;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (boolean[] row : nextMap) {
            for (boolean cell : row) {
                sb.append(cell ? 'O' : ' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}

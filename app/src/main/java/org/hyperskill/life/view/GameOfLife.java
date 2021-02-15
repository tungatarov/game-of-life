package org.hyperskill.life.view;

import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;


public class GameOfLife extends JFrame {

    private int mapSize;
    private JLabel gLabel;
    private JLabel aLabel;
    private JToggleButton playStopBtn;
    private JButton reloadBtn;
    private MapPainter mapPainter;


    public GameOfLife() {
        setTitle("Game of life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(createContentPane());
        setAlwaysOnTop(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public JToggleButton getPlayStopBtn() {
        return playStopBtn;
    }

    public JButton getReloadBtn() {
        return reloadBtn;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }


    private JPanel createContentPane() {
        JPanel interactivePane = new JPanel();
        interactivePane.setLayout(new BoxLayout(interactivePane,
                                BoxLayout.Y_AXIS));
        interactivePane.setBorder(BorderFactory
                .createEmptyBorder(10, 10, 10, 10));

        interactivePane.add(createButtonRow());
        interactivePane.add(Box.createRigidArea(
                new Dimension(0, 10)));
        interactivePane.add(createCounters());

        mapPainter = new MapPainter();

        JPanel basePane = new JPanel();
        basePane.setLayout(new BorderLayout());
        basePane.add(interactivePane, BorderLayout.WEST);
        basePane.add(mapPainter, BorderLayout.CENTER);
        return basePane;
    }


    private JPanel createButtonRow() {
        FontIcon playIcon = FontIcon.of(MaterialDesign.MDI_PLAY_CIRCLE_OUTLINE, 30);
        playStopBtn = new JToggleButton(playIcon);
        playStopBtn.setFocusPainted(false);
        playStopBtn.setName("PlayToggleButton");
        playStopBtn.setVerticalTextPosition(AbstractButton.BOTTOM);
        playStopBtn.setHorizontalTextPosition(AbstractButton.CENTER);

        FontIcon reloadIcon = FontIcon.of(MaterialDesign.MDI_RELOAD, 30);
        reloadBtn = new JButton(reloadIcon);
        reloadBtn.setFocusPainted(false);
        reloadBtn.setName("ResetButton");
        reloadBtn.setVerticalTextPosition(AbstractButton.BOTTOM);
        reloadBtn.setHorizontalTextPosition(AbstractButton.CENTER);

        JPanel pane = new JPanel();
        Dimension dimension = new Dimension(5, 0);
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.setAlignmentX(Component.LEFT_ALIGNMENT);
        pane.add(playStopBtn);
        pane.add(Box.createRigidArea(dimension));
        pane.add(reloadBtn);
        return pane;
    }


    private JPanel createCounters() {
        JPanel pane = new JPanel();
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 14);

        gLabel = new JLabel();
        gLabel.setFont(font);
        gLabel.setName("GenerationLabel");
        gLabel.setText("Generation #0");

        aLabel = new JLabel();
        aLabel.setFont(font);
        aLabel.setName("AliveLabel");
        aLabel.setText("Alive: 0" );

        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.setAlignmentX(Component.LEFT_ALIGNMENT);
        pane.add(gLabel);
        pane.add(aLabel);
        return pane;
    }


    /** Returns an ImageIcon, or null if the path was invalid. */
    public ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


    public void updateCounters(int nGeneration, int nAlive) {
        SwingUtilities.invokeLater(() -> {
            gLabel.setText("Generation #" + nGeneration);
            aLabel.setText("Alive: " + nAlive);
        });
    }


    public void updateMap(boolean[][] map) {
        SwingUtilities.invokeLater(() -> {
            mapPainter.setMap(map);
            mapPainter.repaint();
        });
    }


    private class MapPainter extends JPanel {

        private boolean[][] map;

        public void setMap(boolean[][] map) {
            this.map = map;
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(530, 530);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            int size = (Math.min(getWidth(), getHeight()) - 20) / mapSize;

            int y = 10;
            for (int horz = 0; horz < mapSize; horz++) {
                int x = 10;
                for (int vert = 0; vert < mapSize; vert++) {
                    if (map == null) {
                        g.drawRect(x, y, size, size);

                    } else {
                        if (map[horz][vert]) g.fillRect(x, y, size, size);
                        else g.drawRect(x, y, size, size);
                    }
                    x += size;
                }
                y += size;
            }
            g2d.dispose();
        }
    }
}

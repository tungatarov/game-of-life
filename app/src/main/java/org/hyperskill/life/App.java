package org.hyperskill.life;

import org.hyperskill.life.controller.Controller;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Controller());
    }
}

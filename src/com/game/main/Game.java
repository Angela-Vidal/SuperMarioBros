package com.game.main;

import com.game.gfx.Windows;

import java.awt.*;

public class Game extends Canvas implements Runnable {

    // Constantes
    private static final int MILLIS_PER_SEC = 1000;
    private static final int NANOS_PER_SEC = 1000000000;
    private static final double NUM_TICKS = 60.0;
    private static final String NAME = "Super Mario Bros";
    private static final int WINDOW_WIDTH = 960;
    private static final int WINDOW_HEIGTH = 720;

    // Variáveis
    private boolean running;

    // Componentes
    private Thread thread;

    public Game() {
        initialize();
    }

    public static void main(String[] args) {
        new Game();
    }

    private void initialize() {
        new Windows(WINDOW_WIDTH, WINDOW_HEIGTH, NAME, this);
        start();
    }

    private synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = NUM_TICKS;
        double ns = NANOS_PER_SEC / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }

            if (running) {
                render();
                frames++;
            }

            if (System.currentTimeMillis() - timer > MILLIS_PER_SEC) {
                timer += MILLIS_PER_SEC;
                System.out.println("FPS: " + frames + " | TICKS: " + updates);
                updates = 0;
                frames = 0;
            }
        }

        stop();
    }

    private void tick() {

    }

    private void render() {

    }
}

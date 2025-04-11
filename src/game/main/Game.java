
package game.main;

import static jdk.internal.misc.OSEnvironment.initialize;

public class Game implements Runnable {

    //constantes

    //variaveis
    private boolean running;

    //componentes

    public Game(){
        initialize();

    }
    public static void main(String[] args) {
        new Game();

    }

    private void initialize{
        start();
    }

    private synchronized void start(){
           running = true;
    }

    private synchronized void stop(){
          running = false;
    }

    @Override
    public void run() {

    }
}
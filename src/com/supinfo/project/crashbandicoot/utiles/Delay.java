package com.supinfo.project.crashbandicoot.utiles;

public class Delay {

    int delay;
    boolean init;

    Thread thread;

    public Delay (int delay, boolean init) {
        this.delay = delay;
        this.init = init;
    }

    public boolean talk () {

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(delay * 1000);

                    if (init == false) init = true;
                    else init = false;

                    thread.interrupt();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        return init;
    }

    public void start () {
        if (init == false) init = true;
        else init = false;

        thread.start();
    }

    public void stop () {
        thread.interrupt();
    }

}

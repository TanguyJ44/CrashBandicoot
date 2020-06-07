package com.supinfo.project.crashbandicoot.utiles;

public class Delay {

    // Cette classe est un utilitaire permettant de générer un délai sans interruption dans un thread séparé (très utile pour le multi tache)

    int delay;
    boolean init;

    Thread thread;

    // constructeur de la classe Delay
    public Delay (int delay, boolean init) {
        this.delay = delay;
        this.init = init;
    }

    // méthode d'appel pour le début d'un délai sur un temps donnée
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

    // méthode de lancement du délai (t0)
    public void start () {
        if (init == false) init = true;
        else init = false;

        thread.start();
    }

    // méthode pour stopper le délai en cours
    public void stop () {
        thread.interrupt();
    }

}

package view;

import controller.Transferencias;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaforo= new Semaphore(1);
        for (int i=0;i<21;i++){
            Thread t=new Transferencias(i,semaforo);
            t.start();
        }
    }
}

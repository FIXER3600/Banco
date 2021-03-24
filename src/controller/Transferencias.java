package controller;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

public class Transferencias extends Thread{
    private int cod;
    private double  saldo;
    private double valor;
    private double novoSaldo;
    private Semaphore semaforo;
    DecimalFormat df = new DecimalFormat("#.##");

    public Transferencias(int cod,Semaphore semaforo) {
        this.cod = cod;
        this.semaforo=semaforo;
    }

    private void saldo() {
        this.saldo =  ((Math.random()  * 901) + 99);
        this.valor =  ((Math.random()  * 801) + 99);

    }

    @Override
    public void run() {
       saldo();
        saque();
        deposito();


    }
    private void saque() {
        try {
            semaforo.acquire();
            if(valor>saldo){
                System.out.println("O valor R$"+df.format(this.valor)+" a ser sacado na conta "+this.cod+" é maior que o saldo R$" +df.format(this.saldo));
            }
            else {
                System.out.println("Saque de R$ " + df.format(this.valor) + " na conta " + this.cod + " de R$ " + df.format(this.saldo));
                //novoSaldo=this.saldo - this.valor;
                if (this.saldo == novoSaldo) {
                    novoSaldo = this.saldo - this.valor;
                    System.out.println("Saldo restante da conta " + this.cod + " é de R$ " + df.format(novoSaldo));

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaforo.release();
        }

    }

    private void deposito() {
        try {
            semaforo.acquire();
            System.out.println("Depósito de R$ "+df.format(this.valor)+" na conta "+this.cod);
            novoSaldo=this.saldo+this.valor;
            if(novoSaldo==this.valor){

                novoSaldo=this.saldo+this.valor;
                System.out.println("Saldo atual R$ "+df.format(novoSaldo)+" da conta "+this.cod);
            }else{
                System.out.println("Saldo atual R$ "+df.format(novoSaldo)+" da conta "+this.cod);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaforo.release();
        }
    }


}

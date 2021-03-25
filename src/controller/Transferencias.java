package controller;
import java.util.concurrent.Semaphore;

public class Transferencias extends Thread {
    private int operacao, conta;
    private Semaphore semaforoSaque, semaforoDeposito;
    private float saldo, valor;
    int wait = (int)((Math.random() * 1000) + 1);
    public Transferencias(int conta, int operacao, float saldo, float valor, Semaphore semaforoSaque,Semaphore semaforoDeposito){
        this.conta = conta;
        this.operacao = operacao;
        this.semaforoSaque = semaforoSaque;
        this.semaforoDeposito = semaforoDeposito;
        this.saldo = saldo;
        this.valor = valor;
    }

    public void run(){
        int opcao = operacao % 2;
        switch(opcao){
            case 0:
                try {
                    semaforoDeposito.acquire();
                    System.out.println("Depósitando na conta "+this.conta);
                    deposito();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaforoDeposito.release();
                }
                break;
            case 1:
                try {
                    semaforoSaque.acquire();
                    System.out.println("Sacando da conta "+this.conta);
                    saque();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaforoSaque.release();
                }
                break;
        }
    }

    public void saque(){
        try {
            Thread.sleep(this.wait);
            this.saldo += this.valor;
            System.out.println("Saldo da conta "+this.conta+", após saque "+this.saldo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void deposito(){
        try {
            Thread.sleep(this.wait);
            this.saldo += this.valor;
            System.out.println("Saldo da conta "+this.conta+", após depósito "+this.saldo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
package view;

import controller.Transferencias;

import java.util.concurrent.Semaphore;

public class Main {
    static int operacao,conta;
    static float saldo, valor;
    public static Semaphore semaforoSaque, semaforoDeposito;
    public static void main(String[] args) {
        semaforoSaque = new Semaphore(1);
        semaforoDeposito = new Semaphore(1);
        for (conta = 1 ; conta <= 10 ; conta++){
            operacao = (int)(Math.random()*10);
            saldo = (float)((Math.random()*5000)+5000);
            valor = (float)((Math.random()*900)+100);
            Thread tTransacao = new Transferencias(conta, operacao, saldo, valor, semaforoSaque, semaforoDeposito);
            tTransacao.start();

        }
    }
    }

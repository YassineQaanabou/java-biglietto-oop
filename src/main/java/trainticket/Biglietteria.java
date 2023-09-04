package trainticket;

import java.util.Scanner;

/*Creare una classe Biglietteria, che contiene il metodo main in cui:
        ● chiedere all’utente di inserire il numero di km e l’età del passeggero
        ● con quei dati provare a creare un nuovo Biglietto (gestire eventuali eccezioni con try-catch)
        ● stampare a video il prezzo del biglietto calcolato*/

public class Biglietteria {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Boolean isvalid = false;

        Biglietto biglietto = null;
        while (!isvalid) {
            System.out.println("Quanti Km devi percorrere? ");
            int km = Integer.parseInt(scan.nextLine());

            System.out.println("Qual è l'età del passeggero? ");
            int age = Integer.parseInt(scan.nextLine());

            try {
                biglietto = new Biglietto(km, age);
                isvalid = true;
                System.out.println("Il biglietto è valido");

            } catch (RuntimeException e) {
                System.out.println("Uno dei parametri non è valido ");
            }

        }
        System.out.println("Il prezzo del biglietto è " + biglietto.calcolaPrezzo() + "$");


        scan.close();
    }
}

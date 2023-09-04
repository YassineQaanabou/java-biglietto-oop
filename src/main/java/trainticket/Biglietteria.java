package trainticket;

import java.time.LocalDate;
import java.util.Scanner;

public class Biglietteria {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean isvalid = false;

        Biglietto biglietto = null;
        while (!isvalid) {
            LocalDate date=LocalDate.now();

            System.out.println("Quanti Km devi percorrere? ");
            int km = Integer.parseInt(scan.nextLine());

            System.out.println("Qual è l'età del passeggero? ");
            int age = Integer.parseInt(scan.nextLine());

            System.out.println("Vuoi il biglietto flessibile? y/n");
            String choice= scan.nextLine();

            boolean flexible=false;

            if(choice.equalsIgnoreCase("y")){
                flexible=true;
            }


            try {
                biglietto = new Biglietto(km, age, date, flexible);
                isvalid = true;
                System.out.println("Il biglietto è valido");

            } catch (RuntimeException e) {
                System.out.println("Uno dei parametri non è valido ");
            }

        }
        System.out.println("Il prezzo del biglietto è " + biglietto.calcolaPrezzo() + "$");
        System.out.println("la data di scadenza del biglietto è " + biglietto.calcolaDataScadenza());



        scan.close();
    }
}

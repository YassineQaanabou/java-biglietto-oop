package trainticket;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class BiglietteriaDB {
    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        String url="jdbc:mysql://localhost:3306/db_treni";
        String user="root";
        String password="Root6912";

        Biglietto bigliettoDB = null;
        LocalDate dateDB=LocalDate.now();

        try(Connection conn= DriverManager.getConnection(url, user,password)){
            System.out.println(conn.getCatalog());

            System.out.println("Inserisci città di partenza:");
            String partenzaDB= scan.nextLine();
            System.out.println("Inserisci città di arrivo:");
            String arrivoDB= scan.nextLine();
            System.out.println("Qual è l'età del passeggero? ");
            int ageDB = Integer.parseInt(scan.nextLine());

            boolean isvalidDB = false;


            System.out.println("Vuoi il biglietto flessibile? y/n");
            String choiceDB= scan.nextLine();

            boolean flexibleDB= choiceDB.equalsIgnoreCase("y");

            int kmDB = 0;

            String sql= "select * from tratte where partenza="+partenzaDB+" and arrivo="+arrivoDB;
            try(PreparedStatement ps= conn.prepareCall(sql)){
                try( ResultSet rs= ps.executeQuery()){
                    while(rs.next()){
                        kmDB=rs.getInt(4);
                    }
                }
            }

            while(!isvalidDB) {
                try {
                    bigliettoDB = new Biglietto(kmDB, ageDB, dateDB, flexibleDB);
                    isvalidDB = true;
                    System.out.println("Il biglietto è valido");

                } catch (RuntimeException e) {
                    System.out.println("Uno dei parametri non è valido ");
                }
            }

            System.out.println("Il prezzo del biglietto è " + bigliettoDB.calcolaPrezzo() + "$");
            System.out.println("la data di scadenza del biglietto è " + bigliettoDB.calcolaDataScadenza());



        } catch (SQLException e) {
            System.out.println("An error occured");

        }

        scan.close();
    }
}

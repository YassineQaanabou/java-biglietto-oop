package trainticket;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Biglietto {
    private int km;
    private int age;
    private final BigDecimal kmPrice= BigDecimal.valueOf(0.21);
    private final BigDecimal over65= BigDecimal.valueOf(0.4);
    private final BigDecimal under18= BigDecimal.valueOf(0.2);
    private LocalDate date;
    private Boolean flexible;
    private final int normalDuration=30;
    private final int flexibleDuration=90;

    public Biglietto(int km, int age, LocalDate date, Boolean flexible) throws RuntimeException {

        isValidAge(age);
        isValidKm(km);

        this.km = km;
        this.age = age;
        this.date=date;
        this.flexible=flexible;
    }

    public BigDecimal calcolaPrezzo(){

        BigDecimal price=kmPrice;
        price=price.multiply(BigDecimal.valueOf(km));

        if(flexible){
            price=price.add(price.multiply(BigDecimal.valueOf(0.1)));
        }

        return price.subtract(price.multiply(calcolaSconto()));
    }

    public BigDecimal calcolaSconto(){

        BigDecimal Discount= BigDecimal.valueOf(0);
        if(age>=65){
            Discount=over65;
        } else if (age<=18) {
            Discount=under18;

        }
        return Discount;

    }

    private void isValidKm(int km){
        if (km<0){
            throw new RuntimeException();
        }
    }

    private void isValidAge(int age){
        if (age<0 || age>100){
            throw new RuntimeException();
        }
    }

    public LocalDate calcolaDataScadenza(){
        LocalDate expirationDate = null;

        if(!flexible){
            expirationDate=date.plusDays(normalDuration);
        } else if (flexible) {
            expirationDate=date.plusDays(flexibleDuration);

        }

        return expirationDate;
    }
}

package trainticket;

import java.math.BigDecimal;

public class Biglietto {
    private int km;
    private int age;
    private final BigDecimal kmPrice= BigDecimal.valueOf(0.21);
    private final BigDecimal over65= BigDecimal.valueOf(0.4);
    private final BigDecimal under18= BigDecimal.valueOf(0.2);

    public Biglietto(int km, int age) throws RuntimeException {

        isValidAge();
        isValidKm();

        this.km = km;
        this.age = age;
    }

    public BigDecimal calcolaPrezzo(){

        BigDecimal price=kmPrice;
        price=price.multiply(BigDecimal.valueOf(km));

        return price.multiply(calcolaSconto());
    }

    public BigDecimal calcolaSconto(){

        BigDecimal Discount= BigDecimal.valueOf(1);
        if(age>65){
            Discount=over65;
        } else if (age<18) {
            Discount=under18;

        }
        return Discount;

    }

    private void isValidKm(){
        if (km<0){
            throw new RuntimeException();
        }
    }

    private void isValidAge(){
        if (km<0 || km>100){
            throw new RuntimeException();
        }
    }
}

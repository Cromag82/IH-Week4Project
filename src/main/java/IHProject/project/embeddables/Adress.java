package IHProject.project.embeddables;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
@NoArgsConstructor
public class Adress {

    private String street;
    private String num;
    private String zipCode;
    private String city;
    private String district;

    public Adress(String street, String num, String zipCode, String city, String district) {
        this.street = street;
        this.num = num;
        this.zipCode = zipCode;
        this.city = city;
        this.district = district;
    }
}

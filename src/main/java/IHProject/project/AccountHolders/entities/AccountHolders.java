package IHProject.project.AccountHolders.entities;

import IHProject.project.Accounts.entities.Checking;
import IHProject.project.Accounts.entities.CreditCard;
import IHProject.project.Security.entities.Role;
import IHProject.project.Security.entities.User;
import IHProject.project.embeddables.Adress;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountHolders extends User {


    private String name;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthDate;

    @Embedded
    private Adress primaryAddress;

    @Embedded
    @Basic(optional=true)
    @AttributeOverrides({
            @AttributeOverride(name="street", column=@Column(name="mAstreet")),
            @AttributeOverride(name="num", column = @Column(name="mAnum")),
            @AttributeOverride(name="zipCode", column = @Column(name="mAzipCode")),
            @AttributeOverride(name="city", column = @Column(name="mACity")),
            @AttributeOverride(name="district", column = @Column(name="mADistrict"))
    })
    private Adress mailingAdress;

    @OneToMany(mappedBy = "checkingPrimaryOwner", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Checking> accountsList;

    @OneToMany(mappedBy = "creditCardPrimaryOwner", cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CreditCard> creditCardList;


    public AccountHolders(String username, String password, String name, LocalDate birthDate, Adress primaryAddress, Adress mailingAdress) {
        super(username, password);
        this.name = name;
        this.birthDate = birthDate;
        this.primaryAddress = primaryAddress;
        this.mailingAdress = mailingAdress;
        this.getRoleList().add(new Role("USER"));

    }
}

package IHProject.project.AccountHolders.entities;

import IHProject.project.Accounts.entities.Checking;
import IHProject.project.Accounts.entities.CreditCard;
import IHProject.project.embeddable.Adress;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountHolders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

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

    @OneToMany(mappedBy = "checkingPrimaryOwner")
    @JsonIgnore
    private List<Checking> accountsList;

    @OneToMany(mappedBy = "creditCardPrimaryOwner")
    @JsonIgnore
    private List<CreditCard> creditCardList;

}

package IHProject.project.AccountHolders.controllers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferMoneyDTO {

    private long idOrigin;
    private long idDestiny;
    private double amount;
    private String name;

}

package africa.semicolon.BankingApplication.data.models;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Data
public class Customer {
    private String bvn;
    private List<Account> accounts = new ArrayList<>();
    private String firstName;
    private String lastName;
}

package africa.semicolon.BankingApplication.data.models;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Data
public class Bank {
    private List<Account> accounts = new ArrayList<>();
    private final String id;
    private String name;
}

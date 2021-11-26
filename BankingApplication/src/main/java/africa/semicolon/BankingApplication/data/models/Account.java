package africa.semicolon.BankingApplication.data.models;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Account {
    private String customerId;
    private String number;
    private AccountType type;
    private BigDecimal balance = BigDecimal.ZERO;
}

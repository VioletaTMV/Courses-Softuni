package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccount extends BillingDetail {

    @Column(name = "bank_name", length = 50)
    private String bankName;

    @Column(name = "SWIFT_code", length = 50)
    private String swiftCode;
}

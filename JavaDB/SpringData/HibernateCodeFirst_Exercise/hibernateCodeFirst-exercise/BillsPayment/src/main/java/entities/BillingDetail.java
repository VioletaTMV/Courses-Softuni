package entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetail {

    @Id
    @Column(unique = true, length = 30)
    private String number;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id",
            nullable = false)
    private User owner;

}

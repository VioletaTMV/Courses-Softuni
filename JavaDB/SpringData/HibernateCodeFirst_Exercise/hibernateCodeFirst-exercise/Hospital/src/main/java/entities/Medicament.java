package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medicaments")
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private int name;

   @ManyToMany(mappedBy = "medicamentsPrescribed", targetEntity = Prescription.class)
    private Set<Prescription> prescriptions;
}

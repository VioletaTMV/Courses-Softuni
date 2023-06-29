package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   @ManyToMany
   @JoinTable(name = "prescribed_medicaments",
   joinColumns = @JoinColumn(name = "prescription_id", referencedColumnName = "id"),
   inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
    private Set<Medicament> medicamentsPrescribed;

   @ManyToOne(optional = false)
   @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

}

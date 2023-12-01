package ar.utn.reportwizard.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "problem_specialty")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProblemSpecialty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @ManyToOne(cascade = CascadeType.ALL)
    private Technician technician;

    private Boolean isDeleted = Boolean.FALSE;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.problem);
        hash = 31 * hash + Objects.hashCode(this.specialty);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProblemSpecialty other = (ProblemSpecialty) obj;
        if (!Objects.equals(this.problem, other.problem)) {
            return false;
        }
        return Objects.equals(this.specialty, other.specialty);
    }

}

package ar.utn.reportwizard.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Specialty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalTime max_resolution_time;

    @OneToMany(mappedBy = "problem")
    Set<ProblemSpecialty> problems;

    private Boolean isDeleted = Boolean.FALSE;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Specialty {")
                .append("\n\tid=").append(id)
                .append(",\n\ttitle='").append(title).append('\'')
                .append(",\n\tmax_resolution_time=").append(max_resolution_time)
                .append(",\n\tisDeleted=").append(isDeleted)
                .append("\n}");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Specialty other = (Specialty) obj;
        return Objects.equals(this.id, other.id);
    }

}

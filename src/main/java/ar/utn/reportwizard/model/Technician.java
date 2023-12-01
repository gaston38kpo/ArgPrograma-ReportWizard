package ar.utn.reportwizard.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Technician implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String preferred_contact_method;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "technician_specialties",
            joinColumns = @JoinColumn(name = "technician_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties = new HashSet<>();

    private Boolean isDeleted = Boolean.FALSE;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Technician {")
                .append("\n\tid=").append(id)
                .append(",\n\tname='").append(name).append('\'')
                .append(",\n\tpreferred_contact_method='").append(preferred_contact_method).append('\'')
                .append(",\n\tisDeleted=").append(isDeleted)
                .append("\n}");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Technician other = (Technician) obj;
        return Objects.equals(this.id, other.id);
    }

}

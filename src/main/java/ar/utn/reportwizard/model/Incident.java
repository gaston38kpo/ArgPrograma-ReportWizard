package ar.utn.reportwizard.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Incident implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Boolean is_solved = Boolean.FALSE;
    private Boolean is_complex = Boolean.FALSE;
    private LocalDateTime register_date = LocalDateTime.now();
    private LocalTime hours_it_was_solved;
    private LocalDateTime solved_date;
    private String technician_report;
    private LocalTime extra_hours;
    private LocalTime estimated_hours;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Service service;

    private Boolean isDeleted = Boolean.FALSE;

    @Override
    public String toString() {
        return "\nIncident{" + "id=" + id + ", description=" + description + ", is_solved=" + is_solved + ", is_complex=" + is_complex + ", register_date=" + register_date + ", hours_it_was_solved=" + hours_it_was_solved + ", solved_date=" + solved_date + ", technician_report=" + technician_report + ", extra_hours=" + extra_hours + ", estimated_hours=" + estimated_hours + ", customer=" + customer + ", service=" + service + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Incident other = (Incident) obj;
        return Objects.equals(this.id, other.id);
    }

}

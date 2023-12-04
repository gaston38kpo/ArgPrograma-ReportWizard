package ar.utn.reportwizard.dao;

import ar.utn.reportwizard.model.ProblemSpecialty;
import ar.utn.reportwizard.model.Technician;
import java.util.List;

public interface ProblemSpecialtyDAO extends DAO<ProblemSpecialty> {

    /**
     * Obtiene un registro por su id de tecnico de la tabla problem_specialty
     *
     * @param id
     * @return List<ProblemSpecialty>
     */
    List<ProblemSpecialty> findByTechnicianId(Technician id);

}

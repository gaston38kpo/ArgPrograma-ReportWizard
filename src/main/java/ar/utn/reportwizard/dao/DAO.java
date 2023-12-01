package ar.utn.reportwizard.dao;

import ar.utn.reportwizard.dao.impl.exceptions.NonexistentEntityException;
import java.util.List;

public interface DAO<T> {

    /**
     * Obtiene todos los registros de la tabla T
     *
     * @return List<T>
     */
    List<T> findAll();

    /**
     * Obtiene un registro por su id de la tabla T
     *
     * @param id
     * @return T
     */
    T findById(Long id);

    /**
     * Crea un registro en la tabla T
     *
     * @param T
     * @return Boolean
     */
    Boolean create(T t);

    /**
     * Actualiza un registro de la tabla T
     *
     * @param t
     * @return Boolean
     */
    Boolean update(T t) throws NonexistentEntityException, Exception;

    /**
     * Elimina un registro de la tabla T de manera Fisica
     *
     * @param id
     * @return Boolean
     */
    Boolean deleteById(Long id) throws NonexistentEntityException;

    /**
     * Elimina un registro de la tabla T de manera Logica
     *
     * @param id
     * @return Boolean
     */
    Boolean logicalDeleteById(Long id);

    /**
     * Restaura un registro de la tabla T de manera Logica
     *
     * @param id
     * @return Boolean
     */
    Boolean logicalRestoreById(Long id);
    
}

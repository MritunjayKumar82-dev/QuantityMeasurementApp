package com.apps.quantitymeasurement.UC15;



import java.util.List;

/**
 * Repository interface defining contract for QuantityMeasurementEntity data access.
 * Follows Interface Segregation Principle for easy substitution of implementations.
 */
public interface IQuantityMeasurementRepository {

    /**
     * Saves a QuantityMeasurementEntity to the repository.
     */
    void save(QuantityMeasurementEntity entity);

    /**
     * Returns all saved measurement entities.
     */
    List<QuantityMeasurementEntity> getAllMeasurements();
}

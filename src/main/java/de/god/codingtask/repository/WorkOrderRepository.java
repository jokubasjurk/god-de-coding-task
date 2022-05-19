package de.god.codingtask.repository;

import de.god.codingtask.model.WorkOrder;
import org.springframework.data.repository.CrudRepository;

public interface WorkOrderRepository extends CrudRepository<WorkOrder, Long> {
}

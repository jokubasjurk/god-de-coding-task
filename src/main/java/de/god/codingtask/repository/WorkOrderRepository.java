package lt.jokubas.codingtask.repository;

import java.util.List;

import lt.jokubas.codingtask.model.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
  List<WorkOrder> findByDepartment(boolean published);
}

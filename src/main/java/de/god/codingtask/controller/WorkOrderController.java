package lt.jokubas.codingtask.controller;

import lt.jokubas.codingtask.model.WorkOrder;
import lt.jokubas.codingtask.payload.WorkOrderDTO;
import lt.jokubas.codingtask.service.workorder.factory.WorkOrderValidatorFactory;
import lt.jokubas.codingtask.service.workorder.validator.WorkOrderValidator;
import lt.jokubas.codingtask.repository.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/work-orders")
public class WorkOrderController {

    @Autowired
    WorkOrderRepository workOrderRepository;

    @PostMapping("/validate")
    public ResponseEntity<WorkOrder> validateWorkOrder(@RequestBody @Valid WorkOrderDTO workOrderDTO) {
        try {
            WorkOrderValidator workOrderValidator = getValidator(workOrderDTO);
            workOrderValidator.validate(workOrderDTO);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private WorkOrderValidator getValidator(WorkOrderDTO workOrderDTO) {
        WorkOrderValidatorFactory workOrderValidatorFactory = new WorkOrderValidatorFactory();
        return workOrderValidatorFactory.createWorkOrderValidator(workOrderDTO.getType());
    }

}

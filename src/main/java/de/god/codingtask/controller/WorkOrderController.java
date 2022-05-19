package de.god.codingtask.controller;

import de.god.codingtask.model.ValidationRequest;
import de.god.codingtask.model.WorkOrderValidationError;
import de.god.codingtask.payload.WorkOrderDTO;
import de.god.codingtask.repository.ValidationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class WorkOrderController {

    @Autowired
    ValidationRequestRepository validationRequestRepository;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/validate")
    @ResponseBody
    public ResponseEntity<List<String>> validateWorkOrder(@RequestBody @Valid WorkOrderDTO workOrderDTO) {
        try {
            List<String> validationErrors = WorkOrderValidationError.getValidationErrors(workOrderDTO);
            ValidationRequest validationRequest = ValidationRequest.builder()
                    .department(workOrderDTO.getDepartment())
                    .status(validationErrors.isEmpty() ? "Valid" : "Invalid")
                    .type(workOrderDTO.getType())
                    .timestamp(LocalDateTime.now())
                    .build();
            validationRequestRepository.save(validationRequest);
            return new ResponseEntity<>(validationErrors, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public String test() {
        return "<h1>Application is running</h1>";
    }

}

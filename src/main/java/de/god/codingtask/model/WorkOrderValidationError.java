package de.god.codingtask.model;

import de.god.codingtask.payload.WorkOrderDTO;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter
public enum WorkOrderValidationError {
    INVALID_DEPARTMENT(0, "Department field is required", WorkOrderType.DEFAULT) {
        @Override
        public Predicate<WorkOrderDTO> byApplicableWorkOrderRule() {
            return workOrderDTO -> workOrderDTO.getDepartment() == null;
        }
    },
    INVALID_START_DATE(1, "Start date field is required and must be before current date", WorkOrderType.DEFAULT) {
        @Override
        public Predicate<WorkOrderDTO> byApplicableWorkOrderRule() {
            return workOrderDTO -> workOrderDTO.getStartDate() == null || !workOrderDTO.getStartDate().isBefore(LocalDate.now());
        }
    },
    INVALID_END_DATE(2, "End date field is required and must be after start date", WorkOrderType.DEFAULT) {
        @Override
        public Predicate<WorkOrderDTO> byApplicableWorkOrderRule() {
            return workOrderDTO -> workOrderDTO.getEndDate() == null || !workOrderDTO.getEndDate().isAfter(workOrderDTO.getStartDate());
        }
    },
    INVALID_CURRENCY_CODE(3, "Currency must be a valid a ISO 4217 code", WorkOrderType.DEFAULT) {
        @Override
        public Predicate<WorkOrderDTO> byApplicableWorkOrderRule() {
            return workOrderDTO -> workOrderDTO.getCurrency() == null || workOrderDTO.getCurrency().length() != 3;
        }
    },
    INVALID_COST(4, "Cost must be a positive number", WorkOrderType.DEFAULT) {
        @Override
        public Predicate<WorkOrderDTO> byApplicableWorkOrderRule() {
            return workOrderDTO -> workOrderDTO.getCost() == null || workOrderDTO.getCost().compareTo(BigDecimal.ZERO) <= 0;
        }
    },
    INVALID_ANALYSIS_DATE(5, "Analysis date must be after start date and before end date", WorkOrderType.REPAIR) {
        @Override
        public Predicate<WorkOrderDTO> byApplicableWorkOrderRule() {
            return workOrderDTO -> workOrderDTO.getAnalysisDate() == null
                    || !workOrderDTO.getAnalysisDate().isAfter(workOrderDTO.getStartDate())
                    || !workOrderDTO.getAnalysisDate().isBefore(workOrderDTO.getEndDate());
        }
    },
    INVALID_RESPONSIBLE_PERSON(6, "Responsible person field is required", WorkOrderType.REPAIR) {
        @Override
        public Predicate<WorkOrderDTO> byApplicableWorkOrderRule() {
            return workOrderDTO -> workOrderDTO.getResponsiblePerson() == null;
        }
    },
    INVALID_TEST_DATE(7, "Test date must not be empty and must be after analysis date and before end date", WorkOrderType.REPAIR) {
        @Override
        public Predicate<WorkOrderDTO> byApplicableWorkOrderRule() {
            return workOrderDTO -> workOrderDTO.getTestDate() == null
                    || !workOrderDTO.getTestDate().isAfter(workOrderDTO.getAnalysisDate())
                    || !workOrderDTO.getTestDate().isBefore(workOrderDTO.getEndDate());
        }
    },
    INVALID_PARTS_LIST(8, "Parts list must not be empty", WorkOrderType.REPAIR) {
        @Override
        public Predicate<WorkOrderDTO> byApplicableWorkOrderRule() {
            return workOrderDTO -> workOrderDTO.getParts() == null || workOrderDTO.getParts().isEmpty();
        }
    },
    INVALID_FACTORY_NAME(10, "Factory name must not be empty", WorkOrderType.REPLACEMENT) {
        @Override
        public Predicate<WorkOrderDTO> byApplicableWorkOrderRule() {
            return workOrderDTO -> workOrderDTO.getFactoryName() == null;
        }
    },
    INVALID_FACTORY_ORDER_NUMBER(11, "Factory order number rules: the length is 10, first 2 characters - letters, others – numbers.", WorkOrderType.REPLACEMENT) {
        @Override
        public Predicate<WorkOrderDTO> byApplicableWorkOrderRule() {
            return workOrderDTO -> workOrderDTO.getFactoryOrderNumber() == null || !workOrderDTO.getFactoryOrderNumber().matches("^[a-zA-Z]{2}\\d{8}$");
        }
    },
    INVALID_PARTS_NUMBERS(12, "Inventory numbers must not be empty", WorkOrderType.REPLACEMENT) {
        @Override
        public Predicate<WorkOrderDTO> byApplicableWorkOrderRule() {
            return workOrderDTO -> workOrderDTO.getParts().stream().anyMatch(p -> p.getInventoryNumber() == null);
        }
    };

    private final int code;
    private final String message;

    private final WorkOrderType workOrderType;

    WorkOrderValidationError(int code, String message, WorkOrderType workOrderType) {
        this.code = code;
        this.message = message;
        this.workOrderType = workOrderType;
    }

    @Override
    public String toString() {
        return message;
    }

    public static List<String> getValidationErrors(WorkOrderDTO workOrderDTO) {
        return Arrays.stream(WorkOrderValidationError.values())
                .filter(error -> error.workOrderType == WorkOrderType.DEFAULT || error.workOrderType == workOrderDTO.getType())
                .filter(error -> error.byApplicableWorkOrderRule().test(workOrderDTO))
                .map(workOrderValidationError -> workOrderValidationError.toString())
                .collect(Collectors.toList());
    }

    public abstract Predicate<WorkOrderDTO> byApplicableWorkOrderRule();
}

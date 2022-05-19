package lt.jokubas.codingtask.payload;

import lt.jokubas.codingtask.model.Part;
import lt.jokubas.codingtask.model.WorkOrderType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class WorkOrderDTO {
    @NotBlank
    private String department;

    @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    private String currency;

    @Positive
    private BigDecimal cost;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private WorkOrderType type;

    private LocalDate analysisDate;

    private String responsiblePerson;

    private LocalDate testDate;

    private List<Part> parts;

    private String factoryName;

    private String factoryOrderNumber;
}

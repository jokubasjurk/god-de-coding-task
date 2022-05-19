package de.god.codingtask.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.god.codingtask.model.Part;
import de.god.codingtask.model.WorkOrderType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class WorkOrderDTO {
    private String department;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonProperty("start_date")
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonProperty("end_date")
    private LocalDate endDate;

    private String currency;

    private BigDecimal cost;

    @Enumerated(EnumType.STRING)
    private WorkOrderType type;

    @JsonProperty("analysis_date")
    private LocalDate analysisDate;

    @JsonProperty("responsible_person")
    private String responsiblePerson;

    @JsonProperty("test_date")
    private LocalDate testDate;

    private List<Part> parts;

    @JsonProperty("factory_name")
    private String factoryName;

    @JsonProperty("factory_order_number")
    private String factoryOrderNumber;
}

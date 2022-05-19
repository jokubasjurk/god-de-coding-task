package lt.jokubas.codingtask.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "work_orders")
@Getter
@Setter
public class WorkOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    public WorkOrder() {
    }

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

    private LocalDate analysisDate;

    private String responsiblePerson;

    private LocalDate testDate;

    @ElementCollection
    private List<Part> parts = new ArrayList<>();

    private String factoryName;

    private String factoryOrderNumber;


}

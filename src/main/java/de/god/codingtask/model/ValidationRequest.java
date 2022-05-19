package de.god.codingtask.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "validation_requests")
@Builder
@Getter
@AllArgsConstructor
public class ValidationRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public ValidationRequest() {
    }

    private LocalDateTime timestamp;

    private WorkOrderType type;

    private String department;

    @Enumerated(EnumType.STRING)
    private ValidationRequestStatus status;

}

package de.god.codingtask.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class Part implements Serializable {
    public Part () {

    }

    @JsonProperty("inventory_number")
    private String inventoryNumber;
    private String name;
    private long count;
}

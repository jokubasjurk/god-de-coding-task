package lt.jokubas.codingtask.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class Part implements Serializable {
    public Part () {

    }

    private String inventoryNumber;
    private String name;
    private long count;
}

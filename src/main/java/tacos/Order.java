package tacos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="Taco_Order")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taco_order_generator")
    @SequenceGenerator(name = "taco_order_generator", sequenceName = "TACO_ORDER_SEQ", allocationSize = 1)
    private Long id;

    private Date placedAt;

    @NotBlank(message="Name fehlt")
    private String deliveryName;
    @NotBlank(message="Straße fehlt")
    private String deliveryStreet;
    @NotBlank(message="Ort fehlt")
    private String deliveryCity;
    @NotBlank(message="Land fehlt")
    private String deliveryState;
    @NotBlank(message="Postleitzahl fehlt")
    private String deliveryZip;
    @CreditCardNumber(message="Kreditkartennummer ist fehlerhaft")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])(/)([1-9][0-9])$",
            message="Eingabe nach folgendem Muster: MM/YY")
    private String ccExpiration;
    @Digits(integer=3, fraction=0, message="Falscher CVV-Code")
    private String ccCVV;

    @ManyToMany(targetEntity=Taco.class)
    private List<Taco> tacos = new ArrayList<>();
    public void addDesign(Taco design) {
        this.tacos.add(design);
    }
    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }

}
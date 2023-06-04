package tacos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
public class Order {
    @NotBlank(message="Name fehlt")
    private String name;
    @NotBlank(message="Straße fehlt")
    private String street;
    @NotBlank(message="Ort fehlt")
    private String city;
    @NotBlank(message="Land fehlt")
    private String state;
    @NotBlank(message="Postleitzahl fehlt")
    private String zip;
    @CreditCardNumber(message="Kreditkartennummer ist fehlerhaft")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Eingabe nach folgendem Muster: MM/YY")
    private String ccExpiration;
    @Digits(integer=3, fraction=0, message="Falscher CVV-Code")
    private String ccCVV;
}
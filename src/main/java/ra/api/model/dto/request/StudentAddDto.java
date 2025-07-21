package ra.api.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class StudentAddDto {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotNull
    private LocalDate dob;
    @NotBlank
    private String address;
    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be between 10 and 15 digits and can start with '+'")
    private String phoneNumber;
}

package ra.api.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class StudentDto {
    private int id;
    private String name;
    private String email;
    private LocalDate dob;
    private String address;
    private String phoneNumber;
}

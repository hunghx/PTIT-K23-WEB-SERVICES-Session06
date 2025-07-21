package ra.api.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class StudentUpdateDto {
    private String name;
    private String email;
    private LocalDate dob;
    private String address;
    private String phoneNumber;
}

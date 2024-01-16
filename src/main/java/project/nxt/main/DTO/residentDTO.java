package project.nxt.main.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class residentDTO {
    private String citizen_id;
    private String date_of_birth;
    private String first_name;
    private String last_name;
    private boolean owner;
    private String phone_number;
}

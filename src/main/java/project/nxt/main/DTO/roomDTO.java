package project.nxt.main.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class roomDTO {
    private String type_id;
    private int number;
    private int number_of_bed;
    private int number_of_fridge;
    private int number_of_ac;
    private int number_of_desk;
}

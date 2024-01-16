package project.nxt.main.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class roomtypeDTO {
    private String name;
    private float area;
    private int default_rent_cost;
    private int default_number_of_bed;
    private int default_number_of_fridge;
    private int default_number_of_ac;
    private int default_number_of_desk;
}

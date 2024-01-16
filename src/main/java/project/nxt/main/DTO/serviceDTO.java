package project.nxt.main.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class serviceDTO {
    private String uuid;
    private String type;
    private String calculation_method;
    private int used_amount;
    private int total_service;

    private String name;
    private int price;
    private String unit;

}

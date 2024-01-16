package project.nxt.main.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class purchaseDTO {
    private String room_id;
    private int amount;
    private String note;
    private String time_record;
}

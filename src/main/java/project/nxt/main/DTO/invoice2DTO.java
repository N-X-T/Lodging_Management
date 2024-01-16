package project.nxt.main.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class invoice2DTO {
    private String uuid;
    private String name;
    private int month,year;
    private int rent_cost;
    private int purchase;
    private String status;
    private LocalDateTime create_time,from_date,to_date;
    private project.nxt.main.models.contract contract;
    private List<Map<String,Object>> detail;
    private int room;
}

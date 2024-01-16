package project.nxt.main.DTO;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class contractDTO {
    private int deposit;
    private String from_date;
    private Map<String, Integer> option_service = new HashMap<>();
    private int rent_cost_per_month;
    List<String> resident_list = new ArrayList<>();
    private String room_id;
    private String signer;
    private String to_date;
    private String type;
}

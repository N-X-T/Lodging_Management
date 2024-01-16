package project.nxt.main.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class invoiceDTO {
    private List<String> contracts;
    private String name;
    private LocalDateTime time;
}

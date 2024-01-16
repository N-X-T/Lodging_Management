package project.nxt.main.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @OneToOne
    @JoinColumn(name = "contract_id")
    private contract contract;

    private String status;

    private String name;
    @Column(columnDefinition = "DATE")
    private LocalDateTime time;
}

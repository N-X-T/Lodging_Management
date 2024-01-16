package project.nxt.main.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "residents")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class resident {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String first_name;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String last_name;

    @Column(columnDefinition = "DATE")
    private String date_of_birth;

    private String citizen_id;
    private String phone_number;
    private boolean owner;

    @OneToOne
    @JoinColumn(name = "contract_id")
    @JsonProperty("contract_id")
    private contract contract; //private String contract_id;

    private int room;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<feedback> feedbacks;

}

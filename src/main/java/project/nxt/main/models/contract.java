package project.nxt.main.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "contracts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class contract {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @OneToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private room room; //private String room_id;

    @JsonGetter("room_id")
    public String getRoomId() {
        return room.getUuid();
    }

    @JsonGetter("room")
    public int getRoomNumber() {
        return room.getNumber();
    }

    @OneToOne
    @JoinColumn(name = "signer")
    @JsonIgnore
    private resident resident; //private String signer;

    @JsonGetter("signer")
    public String getSigner() {
        return resident.getUuid();
    }

    @Column(columnDefinition = "DATE")
    private String from_date;

    @Column(columnDefinition = "DATE")
    private String to_date;

    private int rent_cost_per_month;
    private int deposit;
    private String type;
    private boolean status;
    //private int room;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<contract_service> contractServices;

    public void setSigner(resident signer){
        this.resident = signer;
    }
}

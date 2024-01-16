package project.nxt.main.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "rooms")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private int number;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonIgnore
    private roomtype roomtype; //private String type_id;

    @JsonGetter("type_id")
    public String getRoomtypeId() {
        return roomtype.getUuid();
    }

    private int number_of_bed;
    private int number_of_fridge;
    private int number_of_ac;
    private int number_of_desk;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @JsonIgnore
    List<service_index> serviceIndexList;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @JsonIgnore
    List<purchase> purchaseList;
}

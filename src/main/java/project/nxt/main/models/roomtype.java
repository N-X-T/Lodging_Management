package project.nxt.main.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import project.nxt.main.DTO.roomtypeDTO;

import java.util.List;

@Entity
@Table(name = "roomtypes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class roomtype {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @OneToMany(mappedBy = "roomtype", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<room> room;

    private String name;
    private float area;
    private int default_rent_cost;
    private int default_number_of_bed;
    private int default_number_of_fridge;
    private int default_number_of_ac;
    private int default_number_of_desk;

    public void update(roomtypeDTO roomtypeDTO){
        this.name=roomtypeDTO.getName();
        this.area = roomtypeDTO.getArea();
        this.default_rent_cost = roomtypeDTO.getDefault_rent_cost();
        this.default_number_of_bed = roomtypeDTO.getDefault_number_of_bed();
        this.default_number_of_fridge = roomtypeDTO.getDefault_number_of_fridge();
        this.default_number_of_ac = roomtypeDTO.getDefault_number_of_ac();
        this.default_number_of_desk = roomtypeDTO.getDefault_number_of_desk();
    }
}

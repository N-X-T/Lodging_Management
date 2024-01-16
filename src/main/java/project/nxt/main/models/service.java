package project.nxt.main.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import project.nxt.main.DTO.serviceDTO;

import java.util.List;


@Entity
@Table(name = "services")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class service {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String name;
    private String type;
    private int price;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String unit;
    private String calculation_method;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    @JsonIgnore
    List<service_index> serviceIndexList;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<contract_service> contractServices;

    public void update(serviceDTO serviceDTO){
        this.name = serviceDTO.getName();
        this.price = serviceDTO.getPrice();
        this.unit = serviceDTO.getUnit();
    }
}

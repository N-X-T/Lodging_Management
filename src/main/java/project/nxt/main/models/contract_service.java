package project.nxt.main.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contract_service")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class contract_service {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonIgnore
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    @JsonIgnore
    private contract contract;

    @JsonGetter("contract_id")
    public String contract_id(){
        return contract.getUuid();
    }

    @ManyToOne
    @JoinColumn(name = "service_id")
    @JsonIgnore
    private service service;

    @JsonGetter("service_id")
    public String service_id(){
        return service.getUuid();
    }

    private int register_amount;
    private String serivce_name;
}

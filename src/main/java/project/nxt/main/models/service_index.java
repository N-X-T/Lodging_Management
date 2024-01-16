package project.nxt.main.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "service_index")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class service_index {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private room room; //private String room_id;

    @JsonGetter("room_id")
    public String getRoomId() {
        return room.getUuid();
    }

    @Column(name = "index_price")
    private int index;

    @ManyToOne
    @JoinColumn(name = "service_id")
    @JsonIgnore
    private service service;

    @JsonGetter("service_id")
    public String getServiceId() {
        return service.getUuid();
    }

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime time_record;
}

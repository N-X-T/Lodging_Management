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
@Table(name = "purchases")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class purchase {
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

    private int amount;
    private String note;

//    @CreatedDate
//    @Column(nullable = false, updatable = false)
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime time_record;
}

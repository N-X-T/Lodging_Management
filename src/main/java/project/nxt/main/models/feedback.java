package project.nxt.main.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "feedbacks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    @JsonIgnore
    private resident resident;

    @JsonGetter("resident_id")
    String getResidenId(){
        return resident.getUuid();
    }

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date create_time;

    private String status;
    private int room;
}

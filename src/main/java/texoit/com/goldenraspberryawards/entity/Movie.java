package texoit.com.goldenraspberryawards.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producer_id")
    private Long producerId;

    @Column
    private Integer year;

    @Column
    private String title;

    @Column
    private String studio;

    @Column
    private Boolean winner;

    @ManyToOne
    @JoinColumn(referencedColumnName = "ID", name = "producer_id", insertable = false, updatable = false)
    private Producer producer;
}

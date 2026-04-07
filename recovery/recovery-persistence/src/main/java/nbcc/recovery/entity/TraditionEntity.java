package nbcc.recovery.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Table(name = "tradition", uniqueConstraints = @UniqueConstraint(columnNames = {"traditionNumber", "fellowshipType"}))
@EntityListeners(AuditingEntityListener.class)
public class TraditionEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer traditionNumber;

    @NotBlank
    private String title;

    @NotBlank @Column(length = 2000)
    private String description;

    @NotNull @Enumerated(EnumType.STRING)
    private nbcc.recovery.dto.FellowshipType fellowshipType;

    @Version private Long version;
    @CreatedDate @Column(updatable = false, nullable = false) private LocalDateTime createdAt;
    @LastModifiedDate @Column(nullable = false) private LocalDateTime updatedAt;

    public TraditionEntity() {}

    public Long getId() { return id; }
    public TraditionEntity setId(Long id) { this.id = id; return this; }
    public Integer getTraditionNumber() { return traditionNumber; }
    public TraditionEntity setTraditionNumber(Integer n) { this.traditionNumber = n; return this; }
    public String getTitle() { return title; }
    public TraditionEntity setTitle(String title) { this.title = title.trim(); return this; }
    public String getDescription() { return description; }
    public TraditionEntity setDescription(String d) { this.description = d.trim(); return this; }
    public nbcc.recovery.dto.FellowshipType getFellowshipType() { return fellowshipType; }
    public TraditionEntity setFellowshipType(nbcc.recovery.dto.FellowshipType f) { this.fellowshipType = f; return this; }
    public Long getVersion() { return version; }
    public TraditionEntity setVersion(Long v) { this.version = v; return this; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public TraditionEntity setCreatedAt(LocalDateTime c) { this.createdAt = c; return this; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public TraditionEntity setUpdatedAt(LocalDateTime u) { this.updatedAt = u; return this; }
}

package nbcc.recovery.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Table(name = "promise", uniqueConstraints = @UniqueConstraint(columnNames = {"promiseNumber", "fellowshipType"}))
@EntityListeners(AuditingEntityListener.class)
public class PromiseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer promiseNumber;

    @NotBlank @Column(length = 2000)
    private String text;

    @NotNull @Enumerated(EnumType.STRING)
    private nbcc.recovery.dto.FellowshipType fellowshipType;

    @Version private Long version;
    @CreatedDate @Column(updatable = false, nullable = false) private LocalDateTime createdAt;
    @LastModifiedDate @Column(nullable = false) private LocalDateTime updatedAt;

    public PromiseEntity() {}

    public Long getId() { return id; }
    public PromiseEntity setId(Long id) { this.id = id; return this; }
    public Integer getPromiseNumber() { return promiseNumber; }
    public PromiseEntity setPromiseNumber(Integer n) { this.promiseNumber = n; return this; }
    public String getText() { return text; }
    public PromiseEntity setText(String t) { this.text = t.trim(); return this; }
    public nbcc.recovery.dto.FellowshipType getFellowshipType() { return fellowshipType; }
    public PromiseEntity setFellowshipType(nbcc.recovery.dto.FellowshipType f) { this.fellowshipType = f; return this; }
    public Long getVersion() { return version; }
    public PromiseEntity setVersion(Long v) { this.version = v; return this; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public PromiseEntity setCreatedAt(LocalDateTime c) { this.createdAt = c; return this; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public PromiseEntity setUpdatedAt(LocalDateTime u) { this.updatedAt = u; return this; }
}

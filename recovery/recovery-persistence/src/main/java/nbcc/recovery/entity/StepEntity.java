package nbcc.recovery.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Table(name = "step", uniqueConstraints = @UniqueConstraint(columnNames = {"stepNumber", "fellowshipType"}))
@EntityListeners(AuditingEntityListener.class)
public class StepEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer stepNumber;

    @NotBlank
    private String title;

    @NotBlank @Column(length = 2000)
    private String description;

    @NotNull @Enumerated(EnumType.STRING)
    private nbcc.recovery.dto.FellowshipType fellowshipType;

    @Version
    private Long version;

    @CreatedDate @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate @Column(nullable = false)
    private LocalDateTime updatedAt;

    public StepEntity() {}

    public Long getId() { return id; }
    public StepEntity setId(Long id) { this.id = id; return this; }
    public Integer getStepNumber() { return stepNumber; }
    public StepEntity setStepNumber(Integer stepNumber) { this.stepNumber = stepNumber; return this; }
    public String getTitle() { return title; }
    public StepEntity setTitle(String title) { this.title = title.trim(); return this; }
    public String getDescription() { return description; }
    public StepEntity setDescription(String description) { this.description = description.trim(); return this; }
    public nbcc.recovery.dto.FellowshipType getFellowshipType() { return fellowshipType; }
    public StepEntity setFellowshipType(nbcc.recovery.dto.FellowshipType fellowshipType) { this.fellowshipType = fellowshipType; return this; }
    public Long getVersion() { return version; }
    public StepEntity setVersion(Long version) { this.version = version; return this; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public StepEntity setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public StepEntity setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
}

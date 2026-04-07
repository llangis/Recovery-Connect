package nbcc.recovery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Step {

    private Long id;

    @NotNull
    @Positive
    private Integer stepNumber;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private FellowshipType fellowshipType;

    private Long version;

    public Step() {}

    public Step(Step step) {
        this(step.getId(), step.getStepNumber(), step.getTitle(), step.getDescription(),
             step.getFellowshipType(), step.getVersion());
    }

    public Step(Long id, Integer stepNumber, String title, String description,
                FellowshipType fellowshipType, Long version) {
        this.id = id; this.stepNumber = stepNumber; this.title = title;
        this.description = description; this.fellowshipType = fellowshipType; this.version = version;
    }

    public Long getId() { return id; }
    public Step setId(Long id) { this.id = id; return this; }
    public Integer getStepNumber() { return stepNumber; }
    public Step setStepNumber(Integer stepNumber) { this.stepNumber = stepNumber; return this; }
    public String getTitle() { return title; }
    public Step setTitle(String title) { this.title = title; return this; }
    public String getDescription() { return description; }
    public Step setDescription(String description) { this.description = description; return this; }
    public FellowshipType getFellowshipType() { return fellowshipType; }
    public Step setFellowshipType(FellowshipType fellowshipType) { this.fellowshipType = fellowshipType; return this; }
    public Long getVersion() { return version; }
    public Step setVersion(Long version) { this.version = version; return this; }
}

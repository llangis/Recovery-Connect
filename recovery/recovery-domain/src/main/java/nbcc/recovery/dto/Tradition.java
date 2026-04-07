package nbcc.recovery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Tradition {

    private Long id;

    @NotNull @Positive
    private Integer traditionNumber;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private FellowshipType fellowshipType;

    private Long version;

    public Tradition() {}

    public Tradition(Tradition t) {
        this(t.getId(), t.getTraditionNumber(), t.getTitle(), t.getDescription(),
             t.getFellowshipType(), t.getVersion());
    }

    public Tradition(Long id, Integer traditionNumber, String title, String description,
                     FellowshipType fellowshipType, Long version) {
        this.id = id; this.traditionNumber = traditionNumber; this.title = title;
        this.description = description; this.fellowshipType = fellowshipType; this.version = version;
    }

    public Long getId() { return id; }
    public Tradition setId(Long id) { this.id = id; return this; }
    public Integer getTraditionNumber() { return traditionNumber; }
    public Tradition setTraditionNumber(Integer traditionNumber) { this.traditionNumber = traditionNumber; return this; }
    public String getTitle() { return title; }
    public Tradition setTitle(String title) { this.title = title; return this; }
    public String getDescription() { return description; }
    public Tradition setDescription(String description) { this.description = description; return this; }
    public FellowshipType getFellowshipType() { return fellowshipType; }
    public Tradition setFellowshipType(FellowshipType fellowshipType) { this.fellowshipType = fellowshipType; return this; }
    public Long getVersion() { return version; }
    public Tradition setVersion(Long version) { this.version = version; return this; }
}

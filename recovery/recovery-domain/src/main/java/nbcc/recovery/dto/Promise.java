package nbcc.recovery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Promise {

    private Long id;

    @NotNull @Positive
    private Integer promiseNumber;

    @NotBlank
    private String text;

    @NotNull
    private FellowshipType fellowshipType;

    private Long version;

    public Promise() {}

    public Promise(Promise p) {
        this(p.getId(), p.getPromiseNumber(), p.getText(), p.getFellowshipType(), p.getVersion());
    }

    public Promise(Long id, Integer promiseNumber, String text, FellowshipType fellowshipType, Long version) {
        this.id = id; this.promiseNumber = promiseNumber; this.text = text;
        this.fellowshipType = fellowshipType; this.version = version;
    }

    public Long getId() { return id; }
    public Promise setId(Long id) { this.id = id; return this; }
    public Integer getPromiseNumber() { return promiseNumber; }
    public Promise setPromiseNumber(Integer promiseNumber) { this.promiseNumber = promiseNumber; return this; }
    public String getText() { return text; }
    public Promise setText(String text) { this.text = text; return this; }
    public FellowshipType getFellowshipType() { return fellowshipType; }
    public Promise setFellowshipType(FellowshipType fellowshipType) { this.fellowshipType = fellowshipType; return this; }
    public Long getVersion() { return version; }
    public Promise setVersion(Long version) { this.version = version; return this; }
}

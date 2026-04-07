package nbcc.recovery.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nbcc.common.result.Result;
import nbcc.common.result.ValidatedResult;
import nbcc.recovery.dto.Step;
import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.service.StepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import static nbcc.common.result.ResultHandler.handleResult;

@Tag(name = "Steps API", description = "Step CRUD operations")
@RestController
@RequestMapping("/api/step")
public class StepApiController {

    private final StepService service;
    public StepApiController(StepService service) { this.service = service; }

    @Operation(summary = "Get all steps") @GetMapping
    public ResponseEntity<Result<Collection<Step>>> getAll() { return handleResult(service.getAll(), HttpStatus.OK); }

    @Operation(summary = "Get steps by fellowship") @GetMapping("/fellowship/{type}")
    public ResponseEntity<Result<Collection<Step>>> getByFellowship(@PathVariable FellowshipType type) {
        return handleResult(service.getByFellowship(type), HttpStatus.OK);
    }

    @Operation(summary = "Get step by id") @GetMapping("/{id}")
    public ResponseEntity<ValidatedResult<Step>> get(@PathVariable Long id) { return handleResult(service.get(id), HttpStatus.OK, HttpStatus.NOT_FOUND); }

    @Operation(summary = "Create new step") @PreAuthorize("isAuthenticated()") @PostMapping
    public ResponseEntity<ValidatedResult<Step>> create(@RequestBody Step item) { return handleResult(service.create(item), HttpStatus.CREATED); }

    @Operation(summary = "Update existing step") @PreAuthorize("isAuthenticated()") @PutMapping
    public ResponseEntity<ValidatedResult<Step>> update(@RequestBody Step item) { return handleResult(service.update(item), HttpStatus.OK); }

    @Operation(summary = "Delete step by id") @PreAuthorize("isAuthenticated()") @DeleteMapping("/{id}")
    public ResponseEntity<ValidatedResult<Void>> delete(@PathVariable Long id) { return handleResult(service.delete(id), HttpStatus.OK); }
}

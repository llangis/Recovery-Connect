package nbcc.recovery.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nbcc.common.result.Result;
import nbcc.common.result.ValidatedResult;
import nbcc.recovery.dto.Promise;
import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.service.PromiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import static nbcc.common.result.ResultHandler.handleResult;

@Tag(name = "Promises API", description = "Promise CRUD operations")
@RestController
@RequestMapping("/api/promise")
public class PromiseApiController {

    private final PromiseService service;
    public PromiseApiController(PromiseService service) { this.service = service; }

    @Operation(summary = "Get all promises") @GetMapping
    public ResponseEntity<Result<Collection<Promise>>> getAll() { return handleResult(service.getAll(), HttpStatus.OK); }

    @Operation(summary = "Get promises by fellowship") @GetMapping("/fellowship/{type}")
    public ResponseEntity<Result<Collection<Promise>>> getByFellowship(@PathVariable FellowshipType type) {
        return handleResult(service.getByFellowship(type), HttpStatus.OK);
    }

    @Operation(summary = "Get promise by id") @GetMapping("/{id}")
    public ResponseEntity<ValidatedResult<Promise>> get(@PathVariable Long id) { return handleResult(service.get(id), HttpStatus.OK, HttpStatus.NOT_FOUND); }

    @Operation(summary = "Create new promise") @PreAuthorize("isAuthenticated()") @PostMapping
    public ResponseEntity<ValidatedResult<Promise>> create(@RequestBody Promise item) { return handleResult(service.create(item), HttpStatus.CREATED); }

    @Operation(summary = "Update existing promise") @PreAuthorize("isAuthenticated()") @PutMapping
    public ResponseEntity<ValidatedResult<Promise>> update(@RequestBody Promise item) { return handleResult(service.update(item), HttpStatus.OK); }

    @Operation(summary = "Delete promise by id") @PreAuthorize("isAuthenticated()") @DeleteMapping("/{id}")
    public ResponseEntity<ValidatedResult<Void>> delete(@PathVariable Long id) { return handleResult(service.delete(id), HttpStatus.OK); }
}

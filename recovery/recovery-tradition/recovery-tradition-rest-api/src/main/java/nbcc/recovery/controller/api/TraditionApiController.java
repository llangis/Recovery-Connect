package nbcc.recovery.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nbcc.common.result.Result;
import nbcc.common.result.ValidatedResult;
import nbcc.recovery.dto.Tradition;
import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.service.TraditionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import static nbcc.common.result.ResultHandler.handleResult;

@Tag(name = "Traditions API", description = "Tradition CRUD operations")
@RestController
@RequestMapping("/api/tradition")
public class TraditionApiController {

    private final TraditionService service;
    public TraditionApiController(TraditionService service) { this.service = service; }

    @Operation(summary = "Get all traditions") @GetMapping
    public ResponseEntity<Result<Collection<Tradition>>> getAll() { return handleResult(service.getAll(), HttpStatus.OK); }

    @Operation(summary = "Get traditions by fellowship") @GetMapping("/fellowship/{type}")
    public ResponseEntity<Result<Collection<Tradition>>> getByFellowship(@PathVariable FellowshipType type) {
        return handleResult(service.getByFellowship(type), HttpStatus.OK);
    }

    @Operation(summary = "Get tradition by id") @GetMapping("/{id}")
    public ResponseEntity<ValidatedResult<Tradition>> get(@PathVariable Long id) { return handleResult(service.get(id), HttpStatus.OK, HttpStatus.NOT_FOUND); }

    @Operation(summary = "Create new tradition") @PreAuthorize("isAuthenticated()") @PostMapping
    public ResponseEntity<ValidatedResult<Tradition>> create(@RequestBody Tradition item) { return handleResult(service.create(item), HttpStatus.CREATED); }

    @Operation(summary = "Update existing tradition") @PreAuthorize("isAuthenticated()") @PutMapping
    public ResponseEntity<ValidatedResult<Tradition>> update(@RequestBody Tradition item) { return handleResult(service.update(item), HttpStatus.OK); }

    @Operation(summary = "Delete tradition by id") @PreAuthorize("isAuthenticated()") @DeleteMapping("/{id}")
    public ResponseEntity<ValidatedResult<Void>> delete(@PathVariable Long id) { return handleResult(service.delete(id), HttpStatus.OK); }
}

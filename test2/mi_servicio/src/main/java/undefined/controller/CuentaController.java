package pe.financieraoh.controller;

import pe.financieraoh.entity.Cuenta;
import pe.financieraoh.service.CuentaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuenta")
@Slf4j
public class CuentaController {

    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaService.findAllCuentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
        return cuentaService.findCuentaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuenta createCuenta(@RequestBody @Validated Cuenta cuenta) {
        return cuentaService.saveCuenta(cuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        return cuentaService.findCuentaById(id)
                .map(cuentaObj -> {
                    cuenta.setId(id);
                    return ResponseEntity.ok(cuentaService.saveCuenta(cuenta));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cuenta> deleteCuenta(@PathVariable Long id) {
        return cuentaService.findCuentaById(id)
                .map(cuenta -> {
                    cuentaService.deleteCuentaById(id);
                    return ResponseEntity.ok(cuenta);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

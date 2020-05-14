package pe.financieraoh.controller;

import pe.financieraoh.entity.Persona;
import pe.financieraoh.service.PersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persona")
@Slf4j
public class PersonaController {

    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.findAllPersonas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        return personaService.findPersonaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Persona createPersona(@RequestBody @Validated Persona persona) {
        return personaService.savePersona(persona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona persona) {
        return personaService.findPersonaById(id)
                .map(personaObj -> {
                    persona.setId(id);
                    return ResponseEntity.ok(personaService.savePersona(persona));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Persona> deletePersona(@PathVariable Long id) {
        return personaService.findPersonaById(id)
                .map(persona -> {
                    personaService.deletePersonaById(id);
                    return ResponseEntity.ok(persona);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

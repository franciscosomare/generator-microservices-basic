package pe.financieraoh.service;

import pe.financieraoh.entity.Persona;
import pe.financieraoh.repository.PersonaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaService {

    public List<Persona> findAllPersonas();

    public Optional<Persona> findPersonaById(Long id);

    public Persona savePersona(Persona persona);

    public void deletePersonaById(Long id);
}

package pe.financieraoh.service.impl;

import pe.financieraoh.entity.Persona;
import pe.financieraoh.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.financieraoh.service.PersonaService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> findAllPersonas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> findPersonaById(Long id) {
        return personaRepository.findById(id);
    }

    public Persona savePersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public void deletePersonaById(Long id) {
        personaRepository.deleteById(id);
    }
}
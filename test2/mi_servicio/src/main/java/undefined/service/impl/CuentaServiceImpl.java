package pe.financieraoh.service.impl;

import pe.financieraoh.entity.Cuenta;
import pe.financieraoh.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.financieraoh.service.CuentaService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;

    @Autowired
    public CuentaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public List<Cuenta> findAllCuentas() {
        return cuentaRepository.findAll();
    }

    public Optional<Cuenta> findCuentaById(Long id) {
        return cuentaRepository.findById(id);
    }

    public Cuenta saveCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public void deleteCuentaById(Long id) {
        cuentaRepository.deleteById(id);
    }
}
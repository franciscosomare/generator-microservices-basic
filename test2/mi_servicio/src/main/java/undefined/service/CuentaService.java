package pe.financieraoh.service;

import pe.financieraoh.entity.Cuenta;
import pe.financieraoh.repository.CuentaRepository;

import java.util.List;
import java.util.Optional;

public interface CuentaService {

    public List<Cuenta> findAllCuentas();

    public Optional<Cuenta> findCuentaById(Long id);

    public Cuenta saveCuenta(Cuenta cuenta);

    public void deleteCuentaById(Long id);
}

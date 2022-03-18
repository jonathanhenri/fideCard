package com.fidecard.application.services.empresa;

import com.fidecard.application.model.Empresa;
import com.fidecard.application.services.repository.CustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends CustomRepository<Empresa> {
}

package com.fidecard.application.services.cliente;

import com.fidecard.application.model.Cliente;
import com.fidecard.application.services.repository.CustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CustomRepository<Cliente> {
}

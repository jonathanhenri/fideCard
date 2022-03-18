package com.fidecard.application.services.cartaoFidelidade;

import com.fidecard.application.model.CartaoFidelidade;
import com.fidecard.application.services.repository.CustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoFidelidadeRepository extends CustomRepository<CartaoFidelidade> {
}

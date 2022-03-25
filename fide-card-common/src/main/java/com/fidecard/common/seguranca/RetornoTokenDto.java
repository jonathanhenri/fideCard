package com.fidecard.common.seguranca;

import com.fidecard.common.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe Wrapper para retornar token JWT.
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetornoTokenDto implements Dto {
    private String token;
}

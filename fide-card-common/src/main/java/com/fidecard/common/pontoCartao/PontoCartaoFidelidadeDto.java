package com.fidecard.common.pontoCartao;

import com.fidecard.common.AbstractDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "Ponto cartão Fidelidade")
public class PontoCartaoFidelidadeDto extends AbstractDto {
	
	@NotNull
	@Positive
	private Double valor;
	
	@NotBlank
	private String identificadorUnico;
	
	@NotNull
	private Date dataPontuacao;
	
	@NotNull
	@Positive
	private Long idCartaoFidelidade;
	
}

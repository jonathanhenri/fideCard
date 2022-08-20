package com.fidecard.common.request;

import com.fidecard.common.Dto;
import com.fidecard.common.cliente.ClienteDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "RequestDto")
public class RequestDto implements Dto {

	public ClienteDto clienteDto;
}

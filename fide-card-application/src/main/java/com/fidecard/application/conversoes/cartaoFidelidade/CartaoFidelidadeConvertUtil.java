package com.fidecard.application.conversoes.cartaoFidelidade;

import com.fidecard.application.conversoes.AbstractConvertUtil;
import com.fidecard.application.enuns.StatusCartao;
import com.fidecard.application.enuns.TipoRegraCartao;
import com.fidecard.application.model.CartaoFidelidade;
import com.fidecard.application.model.LayoutCartao;
import com.fidecard.application.model.regraCartao.RegraCartao;
import com.fidecard.application.services.cliente.ClienteService;
import com.fidecard.application.services.empresa.EmpresaService;
import com.fidecard.application.services.layoutCartao.LayoutCartaoService;
import com.fidecard.application.utils.DateUtils;
import com.fidecard.application.utils.Utils;
import com.fidecard.common.cartaoFidelidade.CartaoFidelidadeDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;

@Component
public class CartaoFidelidadeConvertUtil extends AbstractConvertUtil {
	
	
	@Autowired
	public CartaoFidelidadeConvertUtil(ClienteService clienteService, EmpresaService empresaService,
									   LayoutCartaoService layoutCartaoService) {
		super(clienteService, empresaService, layoutCartaoService);
	}
	
	public CartaoFidelidade convertCartaoDtoToCartao(String jsonCartaoFidelidade) {
		CartaoFidelidadeDto cartaoFidelidadeDto = new Gson().fromJson(jsonCartaoFidelidade,
				CartaoFidelidadeDto.class);
		
		CartaoFidelidade cartaoFidelidade = CartaoFidelidade.builder()
				.statusCartao(StatusCartao.fromValue(cartaoFidelidadeDto.getStatusCartao()))
				.cliente(clienteService.findById(cartaoFidelidadeDto.getClienteId()))
				.empresa(empresaService.findById(cartaoFidelidadeDto.getEmpresaId())).build();
		
		setLayoutCartao(cartaoFidelidadeDto, cartaoFidelidade);
		
		setRegrasCartao(cartaoFidelidadeDto, cartaoFidelidade);
		
		return cartaoFidelidade;
	}
	
	private void setRegrasCartao(CartaoFidelidadeDto cartaoFidelidadeDto, CartaoFidelidade cartaoFidelidade) {
//		if(!CollectionUtils.isEmpty(cartaoFidelidadeDto.getListRegrasCartaoDto())) {
//			cartaoFidelidade.setListaRegrasCartao(new ArrayList<>());
//
//			cartaoFidelidadeDto.getListRegrasCartaoDto().forEach(regraDto -> {
//
//				RegraCartao regraCartao = RegraCartao.builder().tipoRegraCartao(
//						TipoRegraCartao.fromValue(regraDto.getTipoRegraCartao())).valor(regraDto.getValor()).data(
//						DateUtils.convertStringToData(regraDto.getDate())).cartaoFidelidade(cartaoFidelidade).build();
//
//				cartaoFidelidade.getListaRegrasCartao().add(regraCartao);
//			});
//		}
	}
	
	private void setLayoutCartao(CartaoFidelidadeDto cartaoFidelidadeDto, CartaoFidelidade cartaoFidelidade) {
		if (cartaoFidelidadeDto.getLayoutCartaoDto() != null &&
				cartaoFidelidadeDto.getLayoutCartaoDto().getId() != null) {
			cartaoFidelidade.setLayoutCartao(
					layoutCartaoService.findById(cartaoFidelidadeDto.getLayoutCartaoDto().getId()));
		} else {
			cartaoFidelidade.setLayoutCartao(LayoutCartao.builder().codigoCor(cartaoFidelidadeDto.getLayoutCartaoDto().getCodigoCor()).build());
		}
	}
	
}

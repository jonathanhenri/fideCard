package com.fidecard.application.conversoes.cartaoFidelidade;

import com.fidecard.application.conversoes.AbstractConvertUtil;
import com.fidecard.application.conversoes.EmpresaConvertUtil;
import com.fidecard.application.enuns.StatusCartao;
import com.fidecard.application.model.CartaoFidelidade;
import com.fidecard.application.model.LayoutCartao;
import com.fidecard.application.services.cliente.ClienteService;
import com.fidecard.application.services.empresa.EmpresaService;
import com.fidecard.application.services.layoutCartao.LayoutCartaoService;
import com.fidecard.common.cartaoFidelidade.CartaoFidelidadeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartaoFidelidadeConvertUtil extends AbstractConvertUtil {
	
	
	@Autowired
	public CartaoFidelidadeConvertUtil(ClienteService clienteService, EmpresaService empresaService,
									   LayoutCartaoService layoutCartaoService) {
		super(clienteService, empresaService, layoutCartaoService);
	}
	
	public CartaoFidelidade convertCartaoDtoToCartao(CartaoFidelidadeDto cartaoFidelidadeDto) {
		
		CartaoFidelidade cartaoFidelidade = CartaoFidelidade.builder()
				.statusCartao(StatusCartao.fromValue(cartaoFidelidadeDto.getStatusCartao()))
				.cliente(clienteService.findById(cartaoFidelidadeDto.getCliente().getId()))
				.empresa(empresaService.findById(cartaoFidelidadeDto.getEmpresa().getId())).build();
		
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
	
	
	public CartaoFidelidadeDto convertEntityToDto(CartaoFidelidade cartaoFidelidade) {
		
		CartaoFidelidadeDto cartaoFidelidadeDto = new CartaoFidelidadeDto();
		cartaoFidelidadeDto.setEmpresa(EmpresaConvertUtil.convertEmpresaToDto(cartaoFidelidade.getEmpresa()));
		
		return cartaoFidelidadeDto;
	}
	
}

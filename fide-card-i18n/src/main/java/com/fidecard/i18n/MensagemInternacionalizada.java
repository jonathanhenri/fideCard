package com.fidecard.i18n;

/**
 * Interface que define os contratos de um objeto de mensagem internacionalizada
 * 
 * @author Ricardo Faria
 *
 */
public interface MensagemInternacionalizada {

	/**
	 * Obtem o identificador da mensagem internacionalizada para que seja
	 * procurada no provedor de recurso
	 * 
	 * @return o identificador da mensagem internacionalizada.
	 */
	String getIdentificadorMensagem();

}

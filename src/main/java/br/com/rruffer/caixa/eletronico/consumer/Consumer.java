package br.com.rruffer.caixa.eletronico.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.rruffer.caixa.eletronico.dto.ClienteDTO;
import br.com.rruffer.caixa.eletronico.util.JsonUtil;

@Component
public class Consumer {

	/*@JmsListener(destination =  "calculo.aposentadoria")
	public void consumer(String message) {
		System.out.println(message);
	}*/

	@JmsListener(destination =  "calculo.aposentadoria")
	public void consumer(String mensagem) {
		ClienteDTO cliente = JsonUtil.deserializarJSON(mensagem, ClienteDTO.class);
		System.out.println(cliente);
	}
	
}

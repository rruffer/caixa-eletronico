package br.com.rruffer.caixa.eletronico.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.rruffer.caixa.eletronico.CalculoService;
import br.com.rruffer.caixa.eletronico.dto.ClienteDTO;
import br.com.rruffer.caixa.eletronico.producer.Producer;
import br.com.rruffer.caixa.eletronico.util.JsonUtil;

@Component
public class Consumer {

	@Autowired
	private Producer producer;

	@Autowired
	private CalculoService calculoService;

	@RabbitListener(queues = { "${queue.cadastro-beneficiario}" })
	public void receive(@Payload String mensagem) {
		ClienteDTO cliente = JsonUtil.deserializarJSON(mensagem, ClienteDTO.class);
		calculoService.calculaAposentadoria(cliente);
		producer.send(cliente);
		
	}
	
}

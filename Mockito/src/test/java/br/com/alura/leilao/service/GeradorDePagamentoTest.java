package br.com.alura.leilao.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.alura.leilao.dao.PagamentoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Pagamento;
import br.com.alura.leilao.model.Usuario;

public class GeradorDePagamentoTest {
	
	private GeradorDePagamento gerador;
	
	@Mock
	private PagamentoDao pagamentoDao;
	
	@Mock
	private Clock clock;
	
	@Captor //captures the object created by a mocked object in this case "Pagamento" created during gerador.gerarPagamento(winner)
	private ArgumentCaptor<Pagamento> captorPayment;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		this.gerador = new GeradorDePagamento(pagamentoDao, clock);
	}
	
	@Test
	public void criarPagamantoParaVendedorDoLeilao() {
		Leilao auction = auctionMock();
		Lance winner = auction.getLanceVencedor();

		//fixing paymentDate to keep it on businessDays.
		LocalDate date = LocalDate.of(2022, 3, 21);//Monday
		Instant instant =  date.atStartOfDay(ZoneId.systemDefault()).toInstant();
		Mockito.when(clock.getZone()).thenReturn(ZoneId.systemDefault());
		Mockito.when(clock.instant()).thenReturn(instant);
		
		gerador.gerarPagamento(winner);
		
		Mockito.verify(pagamentoDao).salvar(captorPayment.capture());
		
		Pagamento payment = captorPayment.getValue(); //recovers the classe created
		
		assertEquals(LocalDate.now(clock).plusDays(1), payment.getVencimento());
		assertEquals(winner.getValor(), payment.getValor());
		assertEquals(winner.getUsuario(), payment.getUsuario());
		assertEquals(auction , payment.getLeilao());
	}
	
	private Leilao auctionMock(){
		Leilao auction = new Leilao("Celular", new BigDecimal("500"),
									new Usuario("Tester1"));

		Lance first = new Lance(new Usuario("Tester2"), new BigDecimal("600"));
		auction.propoe(first);
		auction.setLanceVencedor(first);
		return  auction;
	}

}

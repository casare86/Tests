package br.com.alura.leilao.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

class FinalizarLeilaoServiceTest {

	//@Mock could use this annotation to avoid the dependency of LeilaoDao
	private FinalizarLeilaoService service;
	
	@Mock
	private LeilaoDao leilaoDao;
	@Mock 
	private EnviadorDeEmails enviadorDeEmails;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this); //start all @Mock classes
		service = new FinalizarLeilaoService(leilaoDao, enviadorDeEmails);
	}
	
	@Test
	void finalizarLeilaoTest() {
		List<Leilao> auctions = auctionsMock();
		
		Mockito.when(leilaoDao.buscarLeiloesExpirados()).thenReturn(auctions);
		
		service.finalizarLeiloesExpirados();
		
		Leilao leilao = auctions.get(0);
		Lance lanceVendecor = leilao.getLanceVencedor();
		
		//verifica se lançou o método de enviar email ou não
		Mockito.verify(enviadorDeEmails).enviarEmailVencedorLeilao(lanceVendecor);
		
	}
	
	@Test
	void enviarEmailAoFinalizarLeilao() {
		List<Leilao> auctions = auctionsMock();
		
		Mockito.when(leilaoDao.buscarLeiloesExpirados()).thenReturn(auctions);
		
		service.finalizarLeiloesExpirados();
		
		Leilao leilao = auctions.get(0);
		
		assertTrue(leilao.isFechado());
		assertEquals(new BigDecimal("900"), leilao.getLanceVencedor().getValor());
		
		//return a boolean 
		Mockito.verify(leilaoDao).salvar(leilao);
		
	}
	
	@Test
	void falhaEnviarEmailAoFinalizarLeilao() {
		List<Leilao> auctions = auctionsMock();
		
		Mockito.when(leilaoDao.buscarLeiloesExpirados()).thenReturn(auctions);
		
		Mockito.when(leilaoDao.salvar(Mockito.any())).thenThrow(RuntimeException.class);
		
		
		try {
			service.finalizarLeiloesExpirados();
			//there should be no interactions since the save method is expecting a throw
			Mockito.verifyNoInteractions(enviadorDeEmails); 	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	private List<Leilao> auctionsMock(){
		List<Leilao> list = new ArrayList<Leilao>();
		Leilao auction = new Leilao("Celular", new BigDecimal("500"),
									new Usuario("Tester1"));

		Lance first = new Lance(new Usuario("Tester2"), new BigDecimal("600"));
		Lance second = new Lance(new Usuario("Winner1"), new BigDecimal("900"));
		
		auction.propoe(first);
		auction.propoe(second);
		list.add(auction);
		
		return  list;
	}
}

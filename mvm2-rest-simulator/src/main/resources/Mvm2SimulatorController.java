package br.com.mvm2.controllers;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mvm2.exceptions.UnsupportedMathOperationException;
import br.com.mvm2.returns.Mvm2ReturnSimulator;
import br.com.mvm2.validations.Mvm2ValidationDataEntry;
import mvm2services.services.Datas;
import mvm2services.services.ExpectativaDeRecebimento;
import mvm2services.services.ExpectativaRectoEspecial;
import mvm2services.services.ExpectativaRectoPRICE;
import mvm2services.services.ExpectativaRectoPRICEHP;
import mvm2services.services.ExpectativaRectoSAC;
import mvm2services.services.NumberConverter;
import mvm2services.services.ValorIOF;

@RestController
@RequestMapping("/simulator")
public class Mvm2SimulatorController {

	// Parâmetros de entrada 
	
	@GetMapping(value = "/{vlSolicitado}/"
				+ "{vl1aParcela}/"
				+ "{idTaxaFator}/"
				+ "{taxa}/"
				+ "{periodicidadeTaxa}/"
				+ "{baseTaxa}/"
				+ "{periodicidadeVcto}/"
				+ "{idDiaUtil}/"
				+ "{tipoJuros}/"
				+ "{qtdeParcelas}/"
				+ "{qtdeDiasCarencia}/"
				+ "{diaBaseVencimento}/"
				+ "{cdSistemaAmortizacao}/"
				+ "{tpNegociacaoIof}/"
				+ "{idIofEmbutido}/"
				+ "{pcAliquotaIOF}/"
				+ "{pcAliquotaAdicional}/"
				+ "{idCobrJuros}/"
				+ "{idCobrPrinc}/"
				+ "{vlParcelaIn}",
				+ "{idTipoParcelaIn}",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public Mvm2ReturnSimulator returnSimulator (
			  @PathVariable("vlSolicitado") String vlSolicitado,
			  @PathVariable("vl1aParcela") String vl1aParcela,
			  @PathVariable("idTaxaFator") String idTaxaFator,
			  @PathVariable("taxa") String taxa,
			  @PathVariable("periodicidadeTaxa") String periodicidadeTaxa,
			  @PathVariable("baseTaxa") String baseTaxa,
			  @PathVariable("periodicidadeVcto") String periodicidadeVcto,
			  @PathVariable("idDiaUtil") String idDiaUtil,
			  @PathVariable("tipoJuros") String tipoJuros,
			  @PathVariable("qtdeParcelas") String qtdeParcelas,
			  @PathVariable("qtdeDiasCarencia") String qtdeDiasCarencia,
			  @PathVariable("diaBaseVencimento") String diaBaseVencimento,
			  @PathVariable("cdSistemaAmortizacao") String cdSistemaAmortizacao,
			  @PathVariable("tpNegociacaoIof") String tpNegociacaoIof,
			  @PathVariable("idIofEmbutido") String idIofEmbutido,
			  @PathVariable("pcAliquotaIOF") String pcAliquotaIOF,
			  @PathVariable("pcAliquotaAdicional") String pcAliquotaAdicional,
			  @PathVariable("idCobrJuros") String[] idCobrJuros,
			  @PathVariable("idCobrPrinc") String[] idCobrPrinc,
			  @PathVariable("vlParcelaIn") String[] vlParcelaIn
			  @PathVariable("idTipoParcelaIn") String[] idTipoParcelaIn
		) throws Exception {
		
		Mvm2ValidationDataEntry validarDataEntry = new Mvm2ValidationDataEntry();
		validarDataEntry.setVlSolicitado(vlSolicitado);        
		validarDataEntry.setVl1aParcela(vl1aParcela);         
		validarDataEntry.setIdTaxaFator(idTaxaFator);         
		validarDataEntry.setTaxa(taxa);                
		validarDataEntry.setPeriodicidadeTaxa(periodicidadeTaxa);   
		validarDataEntry.setBaseTaxa(baseTaxa);            
		validarDataEntry.setPeriodicidadeVcto(periodicidadeVcto);   
		validarDataEntry.setIdDiaUtil(idDiaUtil);           
		validarDataEntry.setTipoJuros(tipoJuros);           
		validarDataEntry.setQtdeParcelas(qtdeParcelas);        
		validarDataEntry.setQtdeDiasCarencia(qtdeDiasCarencia);    
		validarDataEntry.setDiaBaseVencimento(diaBaseVencimento);   
		validarDataEntry.setCdSistemaAmortizacao(cdSistemaAmortizacao);
		validarDataEntry.setTpNegociacaoIof(tpNegociacaoIof);     
		validarDataEntry.setIdIofEmbutido(idIofEmbutido);       
		validarDataEntry.setPcAliquotaIOF(pcAliquotaIOF);       
		validarDataEntry.setPcAliquotaAdicional(pcAliquotaAdicional); 
		validarDataEntry.setIdCobrJuros(idCobrJuros);         
		validarDataEntry.setIdCobrPrinc(idCobrPrinc);         
		validarDataEntry.setVlParcelaIn(vlParcelaIn);
		validarDataEntry.setIdTipoParcelaIn(idTipoParcelaIn);

		boolean resultValidaEntrada =  validarDataEntry.ValidaEntrada();
		  
		if (!resultValidaEntrada) 
			throw new UnsupportedMathOperationException(validarDataEntry.getMsgRetorno());
		
		
		/* Se Sistema de Amortizaçãoo = 4 (Especial, obrigatório informar as parcelas e identificadores de 
	   	cobrança de juros e correçãoo monetária
		 */

		// 	Dados calculados

		double dbVlSolicitado = NumberConverter.convertToDouble(vlSolicitado);
		
		double vlPresente = dbVlSolicitado ; 						// calculado - equivalente ao valor que será financiado (com encargos e 
										   							// tarifas)                                         
		ArrayList<Double> fatorTaxa = new ArrayList<Double>(); 		// Fator da taxa no per�odo entre a data da 
    													   	   		// simulação e data de vencimento de cada parcela

		ArrayList<Double> fatorTaxaGAP = new ArrayList<Double>();   // Fator da taxa no per�odo entre a data do 
    														    	// vencimento anterior e a data de vencimento
    																// de cada parcela
		ArrayList<Integer> qtdeDiasGAP = new ArrayList<Integer>();  // qtde de dias entre a data do vencimento
																	// anterior e a cada de vencimento de cada
																	// parcela

		ArrayList<Double> dbVlParcelaIn = new ArrayList<Double>();  // auxiliar para converter o valor informado na entrada
		ArrayList<String> strIdTipoParcelaIn = new ArrayList<String>();  // auxiliar para converter identificador da parcela informada

		ArrayList<String> strIdCobrJuros = new ArrayList<String>();      // auxiliar para converter o valor informado na entrada
		ArrayList<String> strIdCobrPrincipal = new ArrayList<String>();  // auxiliar para converter o valor informado na entrada

		for (int i = 0; i < NumberConverter.convertToDouble(qtdeParcelas); i ++) {
			
			//strIdCobrJuros.add("S");
			//strIdCobrPrincipal.add("S");

			strIdCobrJuros.add(idCobrJuros[i]);
			strIdCobrPrincipal.add(idCobrPrinc[i]);
		}
		
		Datas datas = new Datas();      // classe para cálculo de datas, dias corridos e dias úteis

		LocalDate localDate = LocalDate.now();
		String dtCorrente = String.format("%04d",localDate.getYear()) + // data da simulação
    	String.format("%02d",localDate.getMonthValue()) +
    	String.format("%02d",localDate.getDayOfMonth());
		datas.setDtReferencia(dtCorrente);
    
		if (Integer.parseInt(diaBaseVencimento) == 0) {
			datas.setDiaBaseVcto(localDate.getDayOfMonth());
		} else {
			datas.setDiaBaseVcto(Integer.parseInt(diaBaseVencimento));
		}
		
		datas.setIdDiaUtil(idDiaUtil.charAt(0));
		datas.setPeriodicidadeVcto(periodicidadeVcto.charAt(0));
		datas.setQtdeDiasCarencia(Integer.parseInt(qtdeDiasCarencia));
		datas.setQtdeParcelas(Integer.parseInt(qtdeParcelas));

		boolean validaDatas = datas.calcularLinhaDoTempo();

		if (!validaDatas) {
			System.out.println(datas.getIdRetorno());
			System.out.println(datas.getMsgRetorno());
		}

		ArrayList<Integer> qtdeDiasVcto = datas.getQtdeDiasCorridos();

		ExpectativaDeRecebimento expRecebimento;


		if (Integer.parseInt(cdSistemaAmortizacao) == 1) {
			expRecebimento = new ExpectativaRectoPRICEHP();
		} else if (Integer.parseInt(cdSistemaAmortizacao) == 2) {
			expRecebimento = new ExpectativaRectoPRICE();
		} else if (Integer.parseInt(cdSistemaAmortizacao) == 3) {
			expRecebimento = new ExpectativaRectoSAC();
		}
		else {
			expRecebimento = new ExpectativaRectoEspecial();
		};

		expRecebimento.setBaseTaxa(Integer.parseInt(baseTaxa)); 
		expRecebimento.setCdSistemaAmortizacao(Integer.parseInt(cdSistemaAmortizacao));
		expRecebimento.setFatorTaxa(fatorTaxa);
		expRecebimento.setFatorTaxaGAP(fatorTaxaGAP);
		expRecebimento.setIdTaxaFator(idTaxaFator.charAt(0));
		expRecebimento.setPeriodicidadeTaxa(periodicidadeTaxa.charAt(0));
		expRecebimento.setPeriodicidadeVcto(periodicidadeVcto.charAt(0));
		expRecebimento.setQtdeDiasVcto(qtdeDiasVcto);
		expRecebimento.setQtdeDiasGAP(qtdeDiasGAP);
		expRecebimento.setQtdeParcelas(Integer.parseInt(qtdeParcelas));
		expRecebimento.setTaxa(NumberConverter.convertToDouble(taxa));
		expRecebimento.setTipoJuros(tipoJuros.charAt(0));
		expRecebimento.setVl1aParcela(NumberConverter.convertToDouble(vl1aParcela));
		expRecebimento.setVlParcelaIn(dbVlParcelaIn);
		expRecebimento.setIdTipoParcelaIn(strIdTipoParcelaIn);
		expRecebimento.setVlPresente(vlPresente);
		expRecebimento.setIdCobrancaJuros(strIdCobrJuros);
		expRecebimento.setIdCobrancaPrincipal(strIdCobrPrincipal);
		
		boolean calcularFluxo = expRecebimento.calcularFluxo();

		double vlIOF = 0.00;
		if (calcularFluxo) {
			ValorIOF valorIOF = new ValorIOF();

			valorIOF.setDiasCorridos(datas.getQtdeDiasCorridos());

			valorIOF.setIofEmbutido(false);
			if (idIofEmbutido.charAt(0) == 'S') {
				valorIOF.setIofEmbutido(true);
			}
			
			valorIOF.setTipoNegociacaoIOF(tpNegociacaoIof.charAt(0)); 
			valorIOF.setVlAliquota(NumberConverter.convertToDouble(pcAliquotaIOF)); // aliquota ao dia
			valorIOF.setVlAliquotaAdicional(NumberConverter.convertToDouble(pcAliquotaAdicional));
			valorIOF.setVlPresente(expRecebimento.getVlPresente());
			valorIOF.setVlPrincipal(expRecebimento.getVlPrincipal());

			vlIOF = valorIOF.calcularIOF();
			
			if (valorIOF.getIdRetorno() != 0)
				throw new UnsupportedMathOperationException(valorIOF.getMsgRetorno());

			if ((dbVlSolicitado == 0.00) || (valorIOF.getTipoNegociacaoIOF() != '1') ) {
				vlPresente = expRecebimento.getVlPresente();
				dbVlSolicitado = vlPresente - vlIOF;  
			}
			else {
				vlPresente = expRecebimento.getVlPresente() + vlIOF;
				expRecebimento.setVlPresente(vlPresente);
				calcularFluxo = expRecebimento.calcularFluxo();
			}
		}
		
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("0.00");
		
		Mvm2ReturnSimulator returnSimulator = new Mvm2ReturnSimulator();
		
		returnSimulator.setCdResult(expRecebimento.getIdRetorno());
		returnSimulator.setMsgResult(expRecebimento.getMsgRetorno());
		returnSimulator.setDtReferencia(datas.getDtReferencia());
		returnSimulator.setPcTaxaJuros(expRecebimento.getTaxa());
		returnSimulator.setPeriodicidadeTaxa(expRecebimento.getPeriodicidadeTaxa());
		returnSimulator.setBaseTaxaJuros(expRecebimento.getBaseTaxa());
		returnSimulator.setQtdeParcelas(expRecebimento.getQtdeParcelas());
		returnSimulator.setVlFinanciado(df.format(expRecebimento.getVlPresente()));
		returnSimulator.setVlLiberado(df.format(dbVlSolicitado));
		returnSimulator.setVlIof(df.format(vlIOF));
		returnSimulator.setSistemaAmortizacao(expRecebimento.getCdSistemaAmortizacao());
		returnSimulator.setPeriodicidadeVcto(expRecebimento.getPeriodicidadeVcto());
		returnSimulator.setTpJuros(expRecebimento.getTipoJuros());
		returnSimulator.setVl1aParela(df.format(expRecebimento.getVl1aParcela()));
		returnSimulator.setDtVencimento(datas.getDtVencimento());
		returnSimulator.setQtdeDiasCorr(datas.getQtdeDiasCorridos());
		returnSimulator.setQtdeDiasUteis(datas.getQtdeDiasUteis());
		returnSimulator.setNumParcela(expRecebimento.getNumParcela());
		returnSimulator.setIdCobraJuros(strIdCobrJuros);
		returnSimulator.setIdCobraPrincipal(strIdCobrPrincipal);
		returnSimulator.ConverterResultArray(expRecebimento.getVlParcela(), expRecebimento.getVlJuros(), expRecebimento.getVlPrincipal(), expRecebimento.getVlSdoRemanescente());
		
		return returnSimulator;
	}
}

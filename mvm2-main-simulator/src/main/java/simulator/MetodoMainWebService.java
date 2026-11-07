package simulator;

import java.text.DecimalFormat;

import java.time.LocalDate;
import java.util.ArrayList;

import mvm2services.services.Datas;
import mvm2services.services.ExpectativaDeRecebimento;
import mvm2services.services.ExpectativaRectoEspecial;
import mvm2services.services.ExpectativaRectoPRICE;
import mvm2services.services.ExpectativaRectoPRICEHP;
import mvm2services.services.ExpectativaRectoSAC;
import mvm2services.services.ValorIOF;

public class MetodoMainWebService {

	public static void main(String[] args) {

		// Dados de Entrada
		double vlSolicitado = 100000.00; // Valor a ser creditado ao cliente
		double vl1aParcela = 0.00; // obrigatório senão informado o valor solicitado
		char idTaxaFator = 'T'; // indica se recebeu taxa ou fator (T/F)
		double taxa = 12.00; // taxa de juros da operação
		char periodicidadeTaxa = 'A'; // periodicidade da taxa ('A' - anual, 'M' - mensal)
		int baseTaxa = 360; // Base da Taxa (360 - dias corridos ou 252 - dias úteis)
		char periodicidadeVcto = 'M'; // periodicidade de vencimento das parcelas (M - mensal, D - diária, A - anual)
		char idDiaUtil = 'N'; // informa se a parcela vence em dia útil (S/N)
		char tipoJuros = 'S'; // Tipo de Juros (S = Juros sobre Saldo Devedor, P = Juros sobre Parcela)
		int qtdeParcelas = 12; // quantidades de parcelas da operação
		int qtdeDiasCarencia = 0; // quantidade de dias de carencia;
		int diaBaseVencimento = 0; // dia do vencimento das parcelas;
		int cdSistemaAmortizacao = 4; // Sistema de Amortizaçãoo (1 = Price HP, 2 = Price, 3 = SAC, 4 - Especial)

		/* Se Sistema de Amortização = 4 (Especial, obrigatório informar as parcelas e identificadores de
		   cobrança de juros e correção monetária
		*/
		ArrayList<String> idCobrJuros = new ArrayList<>();
		ArrayList<String> idCobrPrincipal = new ArrayList<>();
		ArrayList<Double> vlParcelaIn = new ArrayList<>();
		ArrayList<String> idTipoParcelaIn = new ArrayList<>();

		for (int i = 0; i < qtdeParcelas; i++) {
			vlParcelaIn.add(0.0);
			idTipoParcelaIn.add("A");
		}

		for (int i = 0; i < qtdeParcelas; i++) {
			idCobrJuros.add("S");

			if (i <= 5)
				idCobrPrincipal.add("N");
			else
				idCobrPrincipal.add("S");

		}

		// Dados calculados

		double vlPresente = vlSolicitado ; // calculado - equivalente ao valor que será financiado (com encargos e 
		                                   // tarifas)                                         
		ArrayList<Double> fatorTaxa = new ArrayList<Double>(); // Fator da taxa no per�odo entre a data da 
		                                                       // simulação e data de vencimento de cada parcela
		
		ArrayList<Double> fatorTaxaGAP = new ArrayList<Double>();   // Fator da taxa no per�odo entre a data do 
		                                                            // vencimento anterior e a data de vencimento
		                                                            // de cada parcela
		ArrayList<Integer> qtdeDiasGAP = new ArrayList<Integer>();  // qtde de dias entre a data do vencimento
		                                                            // anterior e a cada de vencimento de cada
		                                                            // parcela

		Datas datas = new Datas();      // classe para cálculo de datas, dias corridos e dias úteis
		
		
		LocalDate localDate = LocalDate.now();
		String dtCorrente = String.format("%04d",localDate.getYear()) +         // data da simulação
							String.format("%02d",localDate.getMonthValue()) +
							String.format("%02d",localDate.getDayOfMonth());
		datas.setDtReferencia(dtCorrente);

		if (diaBaseVencimento == 0)
			datas.setDiaBaseVcto(localDate.getDayOfMonth());
		else
            datas.setDiaBaseVcto(diaBaseVencimento);

		datas.setIdDiaUtil(idDiaUtil);
		datas.setPeriodicidadeVcto(periodicidadeVcto);
		datas.setQtdeDiasCarencia(qtdeDiasCarencia);
		datas.setQtdeParcelas(qtdeParcelas);
		
		
		boolean validaDatas = datas.calcularLinhaDoTempo();
		if (!validaDatas) {
			exibirErro(datas.getIdRetorno(), datas.getMsgRetorno());
			return;
		}

		ArrayList<Integer> qtdeDiasVcto = datas.getQtdeDiasCorridos();
		ExpectativaDeRecebimento expRecebimento;

		switch (cdSistemaAmortizacao) {
			case 1:
				expRecebimento = new ExpectativaRectoPRICEHP();
				break;
			case 2:
				expRecebimento = new ExpectativaRectoPRICE();
				break;
			case 3:
				expRecebimento = new ExpectativaRectoSAC();
				break;
			default:
				expRecebimento = new ExpectativaRectoEspecial();
		}

		expRecebimento.setBaseTaxa(baseTaxa);
		expRecebimento.setCdSistemaAmortizacao(cdSistemaAmortizacao);
		expRecebimento.setFatorTaxa(fatorTaxa);
		expRecebimento.setFatorTaxaGAP(fatorTaxaGAP);
		expRecebimento.setIdTaxaFator(idTaxaFator);
		expRecebimento.setPeriodicidadeTaxa(periodicidadeTaxa);
		expRecebimento.setPeriodicidadeVcto(periodicidadeVcto);
		expRecebimento.setQtdeDiasVcto(qtdeDiasVcto);
		expRecebimento.setQtdeDiasGAP(qtdeDiasGAP);
		expRecebimento.setQtdeParcelas(qtdeParcelas);
		expRecebimento.setTaxa(taxa);
		expRecebimento.setTipoJuros(tipoJuros);
		expRecebimento.setVl1aParcela(vl1aParcela);
		expRecebimento.setVlParcelaIn(vlParcelaIn);
		expRecebimento.setIdTipoParcelaIn(idTipoParcelaIn);
		expRecebimento.setVlPresente(vlPresente);
		expRecebimento.setIdCobrancaJuros(idCobrJuros);
		expRecebimento.setIdCobrancaPrincipal(idCobrPrincipal);

		boolean calcularFluxo = expRecebimento.calcularFluxo();
		if (!calcularFluxo) {
			exibirErro(expRecebimento.getIdRetorno(), expRecebimento.getMsgRetorno());
			return;
		}

		ValorIOF valorIOF = new ValorIOF();
		valorIOF.setDiasCorridos(datas.getQtdeDiasCorridos());
		valorIOF.setIofEmbutido(false);
		valorIOF.setTipoNegociacaoIOF('2');
		valorIOF.setVlAliquota(0.0082); // aliquota ao dia
		valorIOF.setVlAliquotaAdicional(0.38); // aliquota adicional
		valorIOF.setVlPresente(expRecebimento.getVlPresente());
		valorIOF.setVlPrincipal(expRecebimento.getVlPrincipal());

		double vlIOF = valorIOF.calcularIOF();
		if ((vlSolicitado == 0.00) || (valorIOF.getTipoNegociacaoIOF() != '1')) {
			vlPresente = expRecebimento.getVlPresente();
			vlSolicitado = vlPresente - vlIOF;
		} else {
			vlPresente = expRecebimento.getVlPresente() + vlIOF;
			expRecebimento.setVlPresente(vlPresente);
			calcularFluxo = expRecebimento.calcularFluxo();
		}

		if (!calcularFluxo) {
			exibirErro(expRecebimento.getIdRetorno(), expRecebimento.getMsgRetorno());
			return;
		}

		DecimalFormat dcInteiro = new DecimalFormat("000000");
		DecimalFormat dcValor = new DecimalFormat("00000000.00");

		systemInfo(expRecebimento, datas, vlSolicitado, vlIOF, dcInteiro, dcValor, qtdeParcelas);
	}

	private static void exibirErro(int idRetorno, String mensagem) {
		System.out.println("Resultado execução = " + idRetorno);
		System.out.println("Mensagem  retorno  = " + mensagem);
	}

	private static void systemInfo(ExpectativaDeRecebimento expRecebimento, Datas datas, double vlSolicitado,
			double vlIOF, DecimalFormat dcInteiro, DecimalFormat dcValor, int qtdeParcelas) {
		System.out.println("Resultado execução = " + expRecebimento.getIdRetorno());
		System.out.println("Mensagem  retorno  = " + expRecebimento.getMsgRetorno());
		System.out.println();

		System.out.println("Data da simulação  = " + datas.getDtReferencia().substring(6, 8) + "/"
				+ datas.getDtReferencia().substring(4, 6) + "/" + datas.getDtReferencia().substring(0, 4));
		System.out.println("Taxa de Juros      = " + dcValor.format(expRecebimento.getTaxa()));
		System.out.println("Period. da Taxa    = " + expRecebimento.getPeriodicidadeTaxa());
		System.out.println("Base da Taxa       = " + expRecebimento.getBaseTaxa());
		System.out.println("Qtde de parcelas   = " + dcInteiro.format(expRecebimento.getQtdeParcelas()));
		System.out.println("Valor Liberado     = " + dcValor.format(vlSolicitado));
		System.out.println("Valor Financiado   = " + dcValor.format(expRecebimento.getVlPresente()));
		System.out.println("Valor do IOF       = " + dcValor.format(vlIOF));
		System.out.println("Sist. Amortização  = " + expRecebimento.getCdSistemaAmortizacao());
		System.out.println("Period. Vencimento = " + expRecebimento.getPeriodicidadeVcto());
		System.out.println("Tipo de Juros      = " + expRecebimento.getTipoJuros());
		System.out.println("Valor 1a parcela   = " + expRecebimento.getVl1aParcela());
		System.out.println();

		System.out.println("Parcela   Dt Vencimento  DD Corr   DD Úteis   "
				+ " Valor Parcela    Valor Juros   Cobra Juros   Valor Principal   "
				+ "Cobra Principal  Sdo Remanescente");

		for (int i = 0; i < qtdeParcelas; i++) {
			System.out.println(dcInteiro.format(expRecebimento.getNumParcela().get(i)) + "    "
				+ datas.getDtVencimento().get(i).substring(6, 8) + "/"
				+ datas.getDtVencimento().get(i).substring(4, 6) + "/"
				+ datas.getDtVencimento().get(i).substring(0, 4) + "     "
				+ dcInteiro.format(datas.getQtdeDiasCorridos().get(i)) + "    "
				+ dcInteiro.format(datas.getQtdeDiasUteis().get(i)) + "      "
				+ dcValor.format(expRecebimento.getVlParcela().get(i)) + "      "
				+ dcValor.format(expRecebimento.getVlJuros().get(i)) + "   "
				+ expRecebimento.getIdCobrancaJuros().get(i) + "             "
				+ dcValor.format(expRecebimento.getVlPrincipal().get(i)) + "       "
				+ expRecebimento.getIdCobrancaPrincipal().get(i) + "                "
				+ dcValor.format(expRecebimento.getVlSdoRemanescente().get(i)));
		}
	}
}

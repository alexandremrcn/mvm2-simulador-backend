package mvm2services.services;

import java.util.ArrayList;

public class ExpectativaDeRecebimento {
	
	// Dados de Entrada
	
	private double vlPresente;
	private char idTaxaFator;  // indica se recebeu taxa ou fator (T/F);
	private double taxa;
	private int baseTaxa;
	private char periodicidadeTaxa;
	private char periodicidadeVcto;
	private ArrayList<Double> fatorTaxa = new ArrayList<Double>();
	private ArrayList<Double> fatorTaxaGAP = new ArrayList<Double>();
	private char tipoJuros;
	private int qtdeParcelas;
	private int cdSistemaAmortizacao;
	private double vl1aParcela;
	private ArrayList<String> idCobrancaJuros = new ArrayList<String>();
	private ArrayList<String> idCobrancaPrincipal = new ArrayList<String>();
	private ArrayList<Integer> qtdeDiasVcto = new ArrayList<Integer>();
	private ArrayList<Integer> qtdeDiasGAP = new ArrayList<Integer>();
	private ArrayList<Double> vlParcelaIn = new ArrayList<Double>();
	private ArrayList<String> idTipoParcelaIn = new ArrayList<String>();

	// Dados de Sa�da
	
	protected ArrayList<Integer> numParcela = new ArrayList<Integer>();
	protected ArrayList<Double> vlParcela = new ArrayList<Double>();
	protected ArrayList<Double> vlJuros = new ArrayList<Double>();
	public ArrayList<Double> vlPrincipal = new ArrayList<Double>();
	protected ArrayList<Double> vlSdoRemanescente = new ArrayList<Double>();
	
	// Tratamento de erros
	
	boolean resultValue;
	public int idRetorno;
	public String msgRetorno = new String();

	public ExpectativaDeRecebimento(double vlPresente, char idTaxaFator, double taxa, int baseTaxa,
			char periodicidadeTaxa, char periodicidadeVcto, ArrayList<Double> fatorTaxa, ArrayList<Double> fatorTaxaGAP,
			char tipoJuros, int qtdeParcelas, int cdSistemaAmortizacao, double vl1aParcela,
			ArrayList<String> idCobrancaJuros, ArrayList<String> idCobrancaPrincipal, ArrayList<Integer> qtdeDiasVcto,
			ArrayList<Integer> qtdeDiasGAP, ArrayList<Double> vlParcelaIn, ArrayList<String> IdTipoParcelaIn, ArrayList<Integer> numParcela,
			ArrayList<Double> vlParcela, ArrayList<Double> vlJuros, ArrayList<Double> vlPrincipal,
			ArrayList<Double> vlSdoRemanescente, boolean resultValue, int idRetorno, String msgRetorno) {
		this.vlPresente = vlPresente;
		this.idTaxaFator = idTaxaFator;
		this.taxa = taxa;
		this.baseTaxa = baseTaxa;
		this.periodicidadeTaxa = periodicidadeTaxa;
		this.periodicidadeVcto = periodicidadeVcto;
		this.fatorTaxa = fatorTaxa;
		this.fatorTaxaGAP = fatorTaxaGAP;
		this.tipoJuros = tipoJuros;
		this.qtdeParcelas = qtdeParcelas;
		this.cdSistemaAmortizacao = cdSistemaAmortizacao;
		this.vl1aParcela = vl1aParcela;
		this.idCobrancaJuros = idCobrancaJuros;
		this.idCobrancaPrincipal = idCobrancaPrincipal;
		this.qtdeDiasVcto = qtdeDiasVcto;
		this.qtdeDiasGAP = qtdeDiasGAP;
		this.vlParcelaIn = vlParcelaIn;
		this.idTipoParcelaIn = idTipoParcelaIn;
		this.numParcela = numParcela;
		this.vlParcela = vlParcela;
		this.vlJuros = vlJuros;
		this.vlPrincipal = vlPrincipal;
		this.vlSdoRemanescente = vlSdoRemanescente;
		this.resultValue = resultValue;
		this.idRetorno = idRetorno;
		this.msgRetorno = msgRetorno;
	}

	public ExpectativaDeRecebimento()
	{
		
	}

	public double getVlPresente() {
		return vlPresente;
	}

	public void setVlPresente(double vlPresente) {
		this.vlPresente = vlPresente;
	}

	public double getTaxa() {
		return taxa;
	}


	public char getIdTaxaFator() {
		return idTaxaFator;
	}

	public void setIdTaxaFator(char idTaxaFator) {
		this.idTaxaFator = idTaxaFator;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public int getBaseTaxa() {
		return baseTaxa;
	}

	public void setBaseTaxa(int baseTaxa) {
		this.baseTaxa = baseTaxa;
	}

	public char getPeriodicidadeTaxa() {
		return periodicidadeTaxa;
	}

	public void setPeriodicidadeTaxa(char periodicidadeTaxa) {
		this.periodicidadeTaxa = periodicidadeTaxa;
	}

	public char getPeriodicidadeVcto() {
		return periodicidadeVcto;
	}

	public void setPeriodicidadeVcto(char periodicidadeVcto) {
		this.periodicidadeVcto = periodicidadeVcto;
	}

	public ArrayList<Double> getFatorTaxa() {
		return fatorTaxa;
	}

	public void setFatorTaxa(ArrayList<Double> fatorTaxa) {
		this.fatorTaxa = fatorTaxa;
	}

	public ArrayList<Double> getFatorTaxaGAP() {
		return fatorTaxaGAP;
	}

	public void setFatorTaxaGAP(ArrayList<Double> fatorTaxaGAP) {
		this.fatorTaxaGAP = fatorTaxaGAP;
	}

	public char getTipoJuros() {
		return tipoJuros;
	}

	public void setTipoJuros(char tipoJuros) {
		this.tipoJuros = tipoJuros;
	}

	public int getQtdeParcelas() {
		return qtdeParcelas;
	}

	public void setQtdeParcelas(int qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}

	public int getCdSistemaAmortizacao() {
		return cdSistemaAmortizacao;
	}

	public void setCdSistemaAmortizacao(int cdSistemaAmortizacao) {
		this.cdSistemaAmortizacao = cdSistemaAmortizacao;
	}
	

	public double getVl1aParcela() {
		return vl1aParcela;
	}

	public void setVl1aParcela(double vl1aParcela) {
		this.vl1aParcela = vl1aParcela;
	}

	public ArrayList<String> getIdCobrancaJuros() {
		return idCobrancaJuros;
	}

	public void setIdCobrancaJuros(ArrayList<String> idCobrancaJuros) {
		this.idCobrancaJuros = idCobrancaJuros;
	}

	public ArrayList<String> getIdCobrancaPrincipal() {
		return idCobrancaPrincipal;
	}

	public void setIdCobrancaPrincipal(ArrayList<String> idCobrancaPrincipal) {
		this.idCobrancaPrincipal = idCobrancaPrincipal;
	}

	public ArrayList<Integer> getQtdeDiasVcto() {
		return qtdeDiasVcto;
	}

	public void setQtdeDiasVcto(ArrayList<Integer> qtdeDiasVcto) {
		this.qtdeDiasVcto = qtdeDiasVcto;
	}

	public ArrayList<Integer> getQtdeDiasGAP() {
		return qtdeDiasGAP;
	}

	public void setQtdeDiasGAP(ArrayList<Integer> qtdeDiasGAP) {
		this.qtdeDiasGAP = qtdeDiasGAP;
	}

	public ArrayList<Double> getVlParcelaIn() {
		return vlParcelaIn;
	}

	public void setVlParcelaIn(ArrayList<Double> vlParcelaIn) {
		this.vlParcelaIn = vlParcelaIn;
	}

	public ArrayList<String> getIdTipoParcelaIn() {
		return idTipoParcelaIn;
	}

	public void setIdTipoParcelaIn(ArrayList<String> idTipoParcelaIn) {
		this.idTipoParcelaIn = idTipoParcelaIn;
	}


	public ArrayList<Integer> getNumParcela() {
		return numParcela;
	}

	public ArrayList<Double> getVlParcela() {
		return vlParcela;
	}


	public ArrayList<Double> getVlJuros() {
		return vlJuros;
	}

	public ArrayList<Double> getVlPrincipal() {
		return vlPrincipal;
	}

	public ArrayList<Double> getVlSdoRemanescente() {
		return vlSdoRemanescente;
	}

	public boolean isResultValue() {
		return resultValue;
	}


	public int getIdRetorno() {
		return idRetorno;
	}
	
	public String getMsgRetorno() {
		return msgRetorno;
	}

	boolean validarDadosComuns()
	{

		if (vlPresente < 0)
		{
			idRetorno = 1;
			msgRetorno = "Cálculo da Expectativa de Recebimento - Valor Presente inválido - "
			+ vlPresente;
			return false;
		}
		
		if (idTaxaFator != 'T' && idTaxaFator != 'F')
		{
			idRetorno = 2;
			msgRetorno = "Cálculo da Expectativa de Recebimento - Indicador da Taxa (T/F) inválida - "
			+ idTaxaFator;
			return false;
		}
		
		if (qtdeParcelas <= 0)
		{
			idRetorno = 3;
			msgRetorno = "Cálculo da Expectativa de Recebimento - Qtde de Parcelas inválida - "
			+ qtdeParcelas;
			return false;
		}
		
		if (idTaxaFator == 'T')
		{
			if (baseTaxa != 360 && baseTaxa != 252)
			{
				idRetorno = 4;
				msgRetorno = "Cálculo da Expectativa de Recebimento - Base da Taxa (360/252) inválida - "
				+ baseTaxa;
				return false;
			}

			if (periodicidadeTaxa != 'A' && periodicidadeTaxa != 'M' && periodicidadeTaxa != 'E')
			{
				idRetorno = 5;
				msgRetorno = "Cálculo da Expectativa de Recebimento - Periodicidade da Taxa (A/M/E) inválida - "
				+ periodicidadeTaxa;
				return false;
			}
		}
		else
		{
			for (int i = 1; i < qtdeParcelas; i++)
			{
				if (fatorTaxa.get(i) <= 0)
				{
					idRetorno = 6;
					msgRetorno = "Cálculo da Expectativa de Recebimento - Fator da Taxa inválida -> "
					+ fatorTaxa.get(i) + "ref. parcela " + i++;
					return false;
				}

				if (fatorTaxaGAP.get(i) <= 0)
				{
					idRetorno = 6;
					msgRetorno = "Cálculo da Expectativa de Recebimento - Fator GAP da Taxa inválida -> "
					+ fatorTaxaGAP.get(i) + "ref. parcela " + i++;
					return false;
				}

			}
		}
		
		if (tipoJuros != 'S' && tipoJuros != 'P')
		{
			idRetorno = 7;
			msgRetorno = "Cálculo da Expectativa de Recebimento - Tipo de Juros (S/P) inválido - "
			+ tipoJuros;
			return false;
		}
		
		if (cdSistemaAmortizacao != 1 && cdSistemaAmortizacao != 2 && cdSistemaAmortizacao != 3 &&
			cdSistemaAmortizacao != 4)
		{
			idRetorno = 8;
			msgRetorno = "Cálculo da Expectativa de Recebimento - Sistema de Amortização (1/2/3/4) inválido - "
			+ cdSistemaAmortizacao;
			return false;
		}
		
		int qtdeDiasVctoAnt = 0;
		
		for (int i = 0; i < qtdeParcelas; i++)
		{
			if (qtdeDiasVcto.get(i) <= 0)
			{
				idRetorno = 9;
				msgRetorno = "Cálculo da Expectativa de Recebimento - Dias até Vcto inválido - "
				+ qtdeDiasVcto.get(i) + " ref. parcela " + i++;
				return false;
			}
			if (qtdeDiasVcto.get(i) <= qtdeDiasVctoAnt )
			{
				idRetorno = 10;
				msgRetorno = "Cálculo da Expectativa de Recebimento - Dias até Vcto inválido - "
				+ qtdeDiasVcto.get(i) + " ref. parcela " + i++;
				return false;
			}
			qtdeDiasVctoAnt = qtdeDiasVcto.get(i);
			
		}

		return true;

	}
	
	public boolean calcularFluxo()
	{
		return resultValue;
	}
	
	public void calcularFatores()
	{
		
		double taxaAno = this.taxa;
		
		if (this.periodicidadeTaxa == 'M')
		{
			taxaAno = (this.taxa / 100) + 1;
			taxaAno = Math.pow(taxaAno, 12);
			taxaAno = (taxaAno - 1) * 100;
		}
			
		Fatores ftTaxa = new FatoresVencimento();
		ftTaxa.setBaseTaxa(this.baseTaxa);
		ftTaxa.setQtdeDias(this.qtdeDiasVcto);
		ftTaxa.setQtdeParcelas(this.qtdeParcelas);
		ftTaxa.setTaxaAno(taxaAno);
		this.setFatorTaxa(ftTaxa.calcularFatores());
		
		Fatores ftTaxaGAP = new FatoresGAP();
		ftTaxaGAP.setBaseTaxa(this.baseTaxa);
		ftTaxaGAP.setQtdeDias(this.qtdeDiasVcto);
		ftTaxaGAP.setQtdeParcelas(this.qtdeParcelas);
		ftTaxaGAP.setTaxaAno(taxaAno);
		this.setFatorTaxaGAP(ftTaxaGAP.calcularFatores());
		
	}

	public void calcularFatoresHP()
	{
		
		double taxaAno = this.taxa;
		
		if (this.periodicidadeTaxa == 'M')
		{
			taxaAno = (this.taxa / 100) + 1;
			taxaAno = Math.pow(taxaAno, 12);
			taxaAno = (taxaAno - 1) * 100;
		}
			
		FatoresHP ftTaxa = new FatoresHPVencimento();
		ftTaxa.setQtdeParcelas(this.qtdeParcelas);
		ftTaxa.setTaxaAno(taxaAno);
		ftTaxa.setPeriodicidade(this.getPeriodicidadeVcto());
		this.setFatorTaxa(ftTaxa.calcularFatoresHP());
		
		FatoresHP ftTaxaGAP = new FatoresHPGAP();
		ftTaxaGAP.setQtdeParcelas(this.qtdeParcelas);
		ftTaxaGAP.setTaxaAno(taxaAno);
		ftTaxaGAP.setPeriodicidade(this.getPeriodicidadeVcto());
		this.setFatorTaxaGAP(ftTaxaGAP.calcularFatoresHP());
		
	}
}

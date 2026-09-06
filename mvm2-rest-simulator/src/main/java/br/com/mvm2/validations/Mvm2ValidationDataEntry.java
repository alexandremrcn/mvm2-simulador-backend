package br.com.mvm2.validations;

import java.lang.reflect.Array;

import mvm2services.services.NumberConverter;

public class Mvm2ValidationDataEntry {
	
	// Dados de entrada
	private String vlSolicitado;
	private String vl1aParcela;
	private String idTaxaFator;
	private String taxa;
	private String periodicidadeTaxa;
	private String baseTaxa;
	private String periodicidadeVcto;
	private String idDiaUtil;
	private String tipoJuros;
	private String qtdeParcelas;
	private String qtdeDiasCarencia;
	private String diaBaseVencimento;
	private String cdSistemaAmortizacao;
	private String tpNegociacaoIof;
	private String idIofEmbutido;
	private String pcAliquotaIOF;
	private String pcAliquotaAdicional;
	private String[] idCobrJuros;
	private String[] idCobrPrinc;
	private String[] vlParcelaIn;
	private String[] idTipoParcelaIn;

	// retorno
	private String  msgRetorno;
	
	public Mvm2ValidationDataEntry()
	{
		
	}
	
	public Mvm2ValidationDataEntry(String vlSolicitado, String vl1aParcela, String idTaxaFator, String taxa,
			String periodicidadeTaxa, String baseTaxa, String periodicidadeVcto, String idDiaUtil, String tipoJuros,
			String qtdeParcelas, String qtdeDiasCarencia, String diaBaseVencimento, String cdSistemaAmortizacao,
			String tpNegociacaoIof, String idIofEmbutido, String pcAliquotaIOF, String pcAliquotaAdicional,
			String[] idCobrJuros, String[] idCobrPrinc, String[] vlParcelaIn,String[] idTipoParcelaIn,
			String msgRetorno) {
		super();
		this.vlSolicitado = vlSolicitado;
		this.vl1aParcela = vl1aParcela;
		this.idTaxaFator = idTaxaFator;
		this.taxa = taxa;
		this.periodicidadeTaxa = periodicidadeTaxa;
		this.baseTaxa = baseTaxa;
		this.periodicidadeVcto = periodicidadeVcto;
		this.idDiaUtil = idDiaUtil;
		this.tipoJuros = tipoJuros;
		this.qtdeParcelas = qtdeParcelas;
		this.qtdeDiasCarencia = qtdeDiasCarencia;
		this.diaBaseVencimento = diaBaseVencimento;
		this.cdSistemaAmortizacao = cdSistemaAmortizacao;
		this.tpNegociacaoIof = tpNegociacaoIof;
		this.idIofEmbutido = idIofEmbutido;
		this.pcAliquotaIOF = pcAliquotaIOF;
		this.pcAliquotaAdicional = pcAliquotaAdicional;
		this.idCobrJuros = idCobrJuros;
		this.idCobrPrinc = idCobrPrinc;
		this.vlParcelaIn = vlParcelaIn;
		this.idTipoParcelaIn = idTipoParcelaIn;
		this.msgRetorno = msgRetorno;
	}



	public String getVlSolicitado() {
		return vlSolicitado;
	}

	public void setVlSolicitado(String vlSolicitado) {
		this.vlSolicitado = vlSolicitado;
	}

	public String getVl1aParcela() {
		return vl1aParcela;
	}

	public void setVl1aParcela(String vl1aParcela) {
		this.vl1aParcela = vl1aParcela;
	}

	public String getIdTaxaFator() {
		return idTaxaFator;
	}

	public void setIdTaxaFator(String idTaxaFator) {
		this.idTaxaFator = idTaxaFator;
	}

	public String getTaxa() {
		return taxa;
	}

	public void setTaxa(String taxa) {
		this.taxa = taxa;
	}

	public String getPeriodicidadeTaxa() {
		return periodicidadeTaxa;
	}

	public void setPeriodicidadeTaxa(String periodicidadeTaxa) {
		this.periodicidadeTaxa = periodicidadeTaxa;
	}

	public String getBaseTaxa() {
		return baseTaxa;
	}

	public void setBaseTaxa(String baseTaxa) {
		this.baseTaxa = baseTaxa;
	}

	public String getPeriodicidadeVcto() {
		return periodicidadeVcto;
	}

	public void setPeriodicidadeVcto(String periodicidadeVcto) {
		this.periodicidadeVcto = periodicidadeVcto;
	}

	public String getIdDiaUtil() {
		return idDiaUtil;
	}

	public void setIdDiaUtil(String idDiaUtil) {
		this.idDiaUtil = idDiaUtil;
	}

	public String getTipoJuros() {
		return tipoJuros;
	}

	public void setTipoJuros(String tipoJuros) {
		this.tipoJuros = tipoJuros;
	}

	public String getQtdeParcelas() {
		return qtdeParcelas;
	}

	public void setQtdeParcelas(String qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}

	public String getQtdeDiasCarencia() {
		return qtdeDiasCarencia;
	}

	public void setQtdeDiasCarencia(String qtdeDiasCarencia) {
		this.qtdeDiasCarencia = qtdeDiasCarencia;
	}

	public String getDiaBaseVencimento() {
		return diaBaseVencimento;
	}

	public void setDiaBaseVencimento(String diaBaseVencimento) {
		this.diaBaseVencimento = diaBaseVencimento;
	}

	public String getCdSistemaAmortizacao() {
		return cdSistemaAmortizacao;
	}

	public void setCdSistemaAmortizacao(String cdSistemaAmortizacao) {
		this.cdSistemaAmortizacao = cdSistemaAmortizacao;
	}

	public String getTpNegociacaoIof() {
		return tpNegociacaoIof;
	}

	public void setTpNegociacaoIof(String tpNegociacaoIof) {
		this.tpNegociacaoIof = tpNegociacaoIof;
	}

	public String getIdIofEmbutido() {
		return idIofEmbutido;
	}

	public void setIdIofEmbutido(String idIofEmbutido) {
		this.idIofEmbutido = idIofEmbutido;
	}

	public String getPcAliquotaIOF() {
		return pcAliquotaIOF;
	}

	public void setPcAliquotaIOF(String pcAliquotaIOF) {
		this.pcAliquotaIOF = pcAliquotaIOF;
	}

	public String getPcAliquotaAdicional() {
		return pcAliquotaAdicional;
	}

	public void setPcAliquotaAdicional(String pcAliquotaAdicional) {
		this.pcAliquotaAdicional = pcAliquotaAdicional;
	}

	public String[] getIdCobrJuros() {
		return idCobrJuros;
	}

	public void setIdCobrJuros(String[] idCobrJuros) {
		this.idCobrJuros = idCobrJuros;
	}

	public String[] getIdCobrPrinc() {
		return idCobrPrinc;
	}

	public void setIdCobrPrinc(String[] idCobrPrinc) {
		this.idCobrPrinc = idCobrPrinc;
	}

	public String[] getVlParcelaIn() {
		return vlParcelaIn;
	}

	public void setVlParcelaIn(String[] vlParcelaIn) {
		this.vlParcelaIn = vlParcelaIn;
	}

	public String[] getIdTipoParcelaIn() {
		return idTipoParcelaIn;
	}

	public void setIdTipoParcelaIn(String[] idTipoParcelaIn) {
		this.idTipoParcelaIn = idTipoParcelaIn;
	}

	public String getMsgRetorno() {
		return msgRetorno;
	}

	public void setMsgRetorno(String msgRetorno) {
		this.msgRetorno = msgRetorno;
	}

	public boolean ValidaEntrada() {

		if (!NumberConverter.isNumeric(vlSolicitado) ) {
			msgRetorno = "Valor Solicitado não numeríco - " + vlSolicitado;
			return false;
		}
		
		if (!NumberConverter.isNumeric(vl1aParcela) ) {
			msgRetorno = "Valor da primeira parcela não numeríco - " + vl1aParcela;
			return false;
		}
		
		if (idTaxaFator.length() != 1) {
			msgRetorno = "Identificação de fator da taxa inválido (S/N)  " + idTaxaFator;
			return false;
		}

		if (!NumberConverter.isNumeric(taxa) ) {
			msgRetorno = "Taxa inválida  - " + taxa;
			return false;
		}
		
		if (periodicidadeTaxa.length() != 1) {
			msgRetorno = "Periodicidade da taxa inválida (M/A)  " + periodicidadeTaxa;
			return false;
		}
		
		if (!NumberConverter.isInteger(baseTaxa) ) {
			msgRetorno = "Base da Taxa inválida  - (252,360) " + baseTaxa;
			return false;
		}
		
		if (periodicidadeVcto.length() != 1) {
			msgRetorno = "Periodicidade de vencimento inválido (D/M/A)  " + periodicidadeVcto;
			return false;
		}

		if (idDiaUtil.length() != 1) {
			msgRetorno = "Indentificador de dia útil inválido (S/N)  " + idDiaUtil;
			return false;
		}

		if (tipoJuros.length() != 1) {
			msgRetorno = "Tipo de Juros inválido (S/P)  " + tipoJuros;
			return false;
		}

		if (!NumberConverter.isInteger(qtdeParcelas) ) {
			msgRetorno = "Quantidade de parcelas inválido " + qtdeParcelas;
			return false;
		}
		
		if (!NumberConverter.isInteger(qtdeDiasCarencia) ) {
			msgRetorno = "Quantidade de dias de carência inválido " + qtdeDiasCarencia;
			return false;
		}
		
		if (!NumberConverter.isInteger(diaBaseVencimento) ) {
			msgRetorno = "Dia Base de Vencimento inválido " + diaBaseVencimento;
			return false;
		}
		
		if (!NumberConverter.isInteger(cdSistemaAmortizacao) ) {
			msgRetorno = "Sistema de Amortização inválido " + cdSistemaAmortizacao;
			return false;
		}

		if (!NumberConverter.isInteger(tpNegociacaoIof) ) {
			msgRetorno = "Tipo de Negociação de IOF inválido (1/2/3) inválido " + tpNegociacaoIof;
			return false;
		}

		if (idIofEmbutido.length() != 1) {
			msgRetorno = "Indentificação de IOF embutido inválido (S/N)  " + idIofEmbutido;
			return false;
		}

		if (!NumberConverter.isNumeric(pcAliquotaIOF) ) {
			msgRetorno = "Aliquota de IOF inválida " + pcAliquotaIOF;
			return false;
		}

		if (!NumberConverter.isNumeric(pcAliquotaAdicional) ) {
			msgRetorno = "Aliquota adicional de IOF inválida " + pcAliquotaAdicional;
			return false;
		}
		
		if ( Array.getLength(idCobrJuros) != NumberConverter.convertToDouble(qtdeParcelas))
		{
			msgRetorno = "Número de ocorrências de cobrança de juros incompatível com a quantidade de parcelas ";
			return false;
			
		}

		if ( Array.getLength(idCobrPrinc) != NumberConverter.convertToDouble(qtdeParcelas))
		{
			msgRetorno = "Número de ocorrências de cobrança de principal incompatível com a quantidade de parcelas ";
			return false;
			
		}

		if ( Array.getLength(vlParcelaIn) > NumberConverter.convertToDouble(qtdeParcelas))
		{
			msgRetorno = "Quantidade de parcelas informadas maior do que a quantidade de parcelas da operação";
			return false;
		}

		for (int i = 0; i < NumberConverter.convertToDouble(qtdeParcelas); i++)
		{
			if ( (idCobrJuros[i].charAt(0) != 'S' && idCobrJuros[i].charAt(0) != 'N')
			   || idCobrJuros[i].length() != 1)
			{
				msgRetorno = "Identificador de cobrança de Juros inválido " + idCobrJuros[i];
				return false;
			}
				
			if ( (idCobrPrinc[i].charAt(0) != 'S' && idCobrPrinc[i].charAt(0) != 'N')
			      || idCobrPrinc[i].length() != 1)
			{
				msgRetorno = "Identificador de cobrança de Principal inválido " + idCobrPrinc[i];
				return false;
			}

			if ( (vlParcelaIn[i].charAt(0) > 0 && idTipoParcelaIn[i].charAt(0) != 'A'
					&& idTipoParcelaIn[i].charAt(0) != 'B')
			      || idCobrPrinc[i].length() != 1)
			{
				msgRetorno = "Identificaçao da parcela informada, inválido " + idTipoParcelaIn[i];
				return false;
			}
		}
		
		return true;
	}

}

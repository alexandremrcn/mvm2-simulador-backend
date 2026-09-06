package mvm2services.services;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ValorIOF {
	
	/* Atributos de Entrada */
	
	private double vlAliquota;
	private double vlAliquotaAdicional;
	private double vlPresente;
	private ArrayList<Double> vlPrincipal = new ArrayList<Double>();
	private ArrayList<Integer> diasCorridos = new ArrayList<Integer>();
	private char tipoNegociacaoIOF;
	private boolean iofEmbutido;
	
	/* Atributo de Sa�da */
	
	protected double vlIOF;

	// Tratamento de erros
	
	boolean resultValue;
	int idRetorno;
	String msgRetorno = new String();
	
	
	public ValorIOF(double vlAliquota, double vlAliquotaAdicional, double vlPresente, ArrayList<Double> vlPrincipal,
			ArrayList<Integer> diasCorridos, char tipoNegociacaoIOF, boolean iofEmbutido, double vlIOF,
			boolean resultValue, int idRetorno, String msgRetorno) {
		super();
		this.vlAliquota = vlAliquota;
		this.vlAliquotaAdicional = vlAliquotaAdicional;
		this.vlPresente = vlPresente;
		this.vlPrincipal = vlPrincipal;
		this.diasCorridos = diasCorridos;
		this.tipoNegociacaoIOF = tipoNegociacaoIOF;
		this.iofEmbutido = iofEmbutido;
		this.vlIOF = vlIOF;
		this.resultValue = resultValue;
		this.idRetorno = idRetorno;
		this.msgRetorno = msgRetorno;
	}
	
	public ValorIOF()
	{
		
	}
	
	
	public double getVlAliquota() {
		return vlAliquota;
	}
	public void setVlAliquota(double vlAliquota) {
		this.vlAliquota = vlAliquota;
	}
	public double getVlAliquotaAdicional() {
		return vlAliquotaAdicional;
	}
	public void setVlAliquotaAdicional(double vlAliquotaAdicional) {
		this.vlAliquotaAdicional = vlAliquotaAdicional;
	}
	public double getVlPresente() {
		return vlPresente;
	}
	public void setVlPresente(double vlPresente) {
		this.vlPresente = vlPresente;
	}
	public ArrayList<Double> getVlPrincipal() {
		return vlPrincipal;
	}
	public void setVlPrincipal(ArrayList<Double> vlPrincipal) {
		this.vlPrincipal = vlPrincipal;
	}
	public ArrayList<Integer> getDiasCorridos() {
		return diasCorridos;
	}
	public void setDiasCorridos(ArrayList<Integer> diasCorridos) {
		this.diasCorridos = diasCorridos;
	}
	public char getTipoNegociacaoIOF() {
		return tipoNegociacaoIOF;
	}
	public void setTipoNegociacaoIOF(char tipoNegociacaoIOF) {
		this.tipoNegociacaoIOF = tipoNegociacaoIOF;
	}
	public boolean isIofEmbutido() {
		return iofEmbutido;
	}
	public void setIofEmbutido(boolean iofEmbutido) {
		this.iofEmbutido = iofEmbutido;
	}
	public double getVlIOF() {
		return vlIOF;
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

	
	public double calcularIOF()
	{
		
		resultValue = validarDadosEntrada();
		
		if (resultValue == true)
		{
			double vlIOFPrincipal = 0.00;
			double vlIOFAdicional = 0.00;
			double vlIOFTotal = 0.00;

			DecimalFormat df = new DecimalFormat();
			df.applyPattern("0.00");

			
			//double ftAliquota = ((vlAliquota / 365) / 100.00);
			
			double ftAliquota = (vlAliquota  / 100.00);
			double ftAliquotaAdicional = (vlAliquotaAdicional / 100.00);
			
			for (int x = 0; x < vlPrincipal.size(); x++)
			{
				if (diasCorridos.get(x) <= 365)
					vlIOFPrincipal = vlIOFPrincipal + (ftAliquota * diasCorridos.get(x) * vlPrincipal.get(x));
				else
					vlIOFPrincipal = vlIOFPrincipal + (ftAliquota * 365 * vlPrincipal.get(x));
					
				vlIOFAdicional = vlIOFAdicional + (ftAliquotaAdicional * vlPrincipal.get(x));
			}
			
			vlIOFTotal = vlIOFPrincipal + vlIOFAdicional;

			double vlIOF = 0.00;

			if (tipoNegociacaoIOF == '1' && iofEmbutido == true ) // IOF Financiado e Embutido no Valor Presente
			{
				vlIOF = vlIOFTotal; 
			}
			else if (tipoNegociacaoIOF == '2' && iofEmbutido == true) // IOF a vista e Embutido no Valor Presente
			{
				double vlPresenteLiquido = (vlPresente - vlIOFTotal);
				vlIOF = (vlIOFTotal * (vlPresenteLiquido / vlPresente));
			}
			else if (tipoNegociacaoIOF == '1' && iofEmbutido == false) // IOF Financiado e n�o embutido no Valor Presente
			{
				vlIOF = vlPresente *  (vlIOFTotal / (vlPresente - vlIOFTotal));
			}
			else // IOF a vista e embutido no Valor Presente
			{
				vlIOF = vlIOFTotal; 
			}
			return NumberConverter.convertToDouble(df.format(vlIOF));
		}	
		else
			return 0.00;
	}
	
	boolean validarDadosEntrada()
	{
		
		if (vlAliquota < 0)
		{
			idRetorno = 1;
			msgRetorno = "Cálculo do IOF - Valor da Al�quota de IOF inv�lida";
			return false;
		}
		
		if (vlAliquotaAdicional < 0)
		{
			idRetorno = 2;
			msgRetorno = "Cálculo do IOF - Valor da Al�quota Adicional inv�lida";
			return false;
		}
		
		if (vlPrincipal.isEmpty())
		{
			idRetorno = 3;
			msgRetorno = "Cálculo do IOF - Valor do Principal n�o informado";
			return false;
		}
		
		if (diasCorridos.isEmpty())
		{
			idRetorno = 4;
			msgRetorno = "Cálculo do IOF - Dias Corridos n�o informado";
			return false;
		}
		
		if (vlPrincipal.size() != diasCorridos.size())
		{
			idRetorno = 5;
			msgRetorno = "Cálculo do IOF - qtde de v�rtices de principal diferente de dias corridos";
			return false;
		}
		
		if (vlPresente <= 0.00)
		{
			idRetorno = 5;
			msgRetorno = "Cálculo do IOF - Valor Presente inv�lido";
			return false;
		}
		
		for (int x = 0; x < vlPrincipal.size(); x++)
		{
/*			if (vlPrincipal.get(x) <= 0.00)
			{
				idRetorno = 3;
				msgRetorno = "Cálculo do IOF - Valor do Principal inválido";
				return false;
			}
*/			
			if (diasCorridos.get(x) <=0)
			{
				idRetorno = 3;
				msgRetorno = "Cálculo do IOF - Quantidade de dias corridos inv�lido";
				return false;
			}
		}
		
		if (tipoNegociacaoIOF != '1' && tipoNegociacaoIOF != '2')
		{
			idRetorno = 4;
			msgRetorno = "Cálculo do IOF - Tipo de Negocia��o do IOF inv�lido";
			return false;
		}
		
		if (iofEmbutido != true && iofEmbutido != false)
		{
			idRetorno = 5;
			msgRetorno = "Cálculo do IOF - Não informado se o iof está embutido no Principal";
			return false;
		}
			
		return true;
	}
}

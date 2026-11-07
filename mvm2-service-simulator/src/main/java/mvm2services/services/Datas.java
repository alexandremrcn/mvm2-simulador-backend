package mvm2services.services;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Datas {

	/* Atributos de Entrada  - classe Datas */
	
	private int diaBaseVcto;
	private char idDiaUtil;
	private String dtReferencia;
	private int qtdeDiasCarencia;
	private char periodicidadeVcto;
	private int qtdeParcelas;

	/*Atributos de Saída */
	
	private String dtVct1ap;

	private ArrayList<String> dtVencimento = new ArrayList<String>();
	private ArrayList<Integer> qtdeDiasCorridos = new ArrayList<Integer>();
	private ArrayList<Integer> qtdeDiasUteis = new ArrayList<Integer>();
	
	/* inicializar campos de saída */
	
	
	
	/* Tratamento de Erros */
	
	private boolean resultValue;
	private int idRetorno;
	private String msgRetorno;
	
	/* Variáveis para tratar o arquivo de dias  úteis do pacote data/smx_dias_uteis.txt */

	private static ArrayList<String> dtUteis = new ArrayList<String>();

	
	public Datas(int diaBaseVcto, char idDiaUtil, String dtReferencia, int qtdeDiasCarencia, char periodicidadeVcto,
			int qtdeParcelas, String dtVct1ap, ArrayList<String> dtVencimento, ArrayList<Integer> qtdeDiasCorridos,
			ArrayList<Integer> qtdeDiasUteis) {
		super();
		this.diaBaseVcto = diaBaseVcto;
		this.idDiaUtil = idDiaUtil;
		this.dtReferencia = dtReferencia;
		this.qtdeDiasCarencia = qtdeDiasCarencia;
		this.periodicidadeVcto = periodicidadeVcto;
		this.qtdeParcelas = qtdeParcelas;
		this.dtVct1ap = dtVct1ap;
		this.dtVencimento = dtVencimento;
		this.qtdeDiasCorridos = qtdeDiasCorridos;
		this.qtdeDiasUteis = qtdeDiasUteis;
	}

	public Datas() {
		
	}

	public int getDiaBaseVcto() {
		return diaBaseVcto;
	}

	public void setDiaBaseVcto(int diaBaseVcto) {
		this.diaBaseVcto = diaBaseVcto;
	}


	public char getIdDiaUtil() {
		return idDiaUtil;
	}


	public void setIdDiaUtil(char idDiaUtil) {
		this.idDiaUtil = idDiaUtil;
	}


	public String getDtReferencia() {
		return dtReferencia;
	}


	public void setDtReferencia(String dtReferencia) {
		this.dtReferencia = dtReferencia;
	}


	public int getQtdeDiasCarencia() {
		return qtdeDiasCarencia;
	}


	public void setQtdeDiasCarencia(int qtdeDiasCarencia) {
		this.qtdeDiasCarencia = qtdeDiasCarencia;
	}


	public char getPeriodicidadeVcto() {
		return periodicidadeVcto;
	}


	public void setPeriodicidadeVcto(char periodicidadeVcto) {
		this.periodicidadeVcto = periodicidadeVcto;
	}


	public int getQtdeParcelas() {
		return qtdeParcelas;
	}


	public void setQtdeParcelas(int qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}


	public String getDtVct1ap() {
		return dtVct1ap;
	}


	public void setDtVct1ap(String dtVct1ap) {
		this.dtVct1ap = dtVct1ap;
	}


	public ArrayList<String> getDtVencimento() {
		return dtVencimento;
	}


	public ArrayList<Integer> getQtdeDiasCorridos() {
		return qtdeDiasCorridos;
	}


	public ArrayList<Integer> getQtdeDiasUteis() {
		return qtdeDiasUteis;
	}

	public boolean isResultValue() {
		return resultValue;
	}

	private boolean dataFormatoValido(String data)
	{
		if (data == null || data.isBlank()) {
			return false;
		}
		try {
			LocalDate.parse(data, DateTimeFormatter.BASIC_ISO_DATE);
			return true;
		} catch (DateTimeParseException ex) {
			return false;
		}
	}

	public int getIdRetorno() {
		return idRetorno;
	}

	public String getMsgRetorno() {
		return msgRetorno;
	}

	public static ArrayList<String> getDtUteis() {
		return dtUteis;
	}


	private boolean validarDadosEntrada()
	{
		
		if (diaBaseVcto <=0 || diaBaseVcto > 31)
		{
			idRetorno = 12;
			msgRetorno = "Validação de Datas - Dia de Vencimento inválido -> "
			+ diaBaseVcto;
			return false;
		}
		
		if (idDiaUtil != 'S' && idDiaUtil != 'N')
		{
			idRetorno = 13;
			msgRetorno = "Validação de Datas - Indicador de dia útil inválido (S/N) - "
			+ idDiaUtil;
			return false;
		}
		
		
		try
		{
			SimpleDateFormat dataValida = new SimpleDateFormat("yyyyMMdd");
			dataValida.setLenient(false);
			dataValida.parse(dtReferencia);
		} catch (ParseException ex) {
			idRetorno = 14;
			msgRetorno = "Validação de Datas - Data de referência inválida - formato (AAAAMMDD)- "+dtReferencia;
			return false;
		}
		
		/* Recuperar a data corrente no formato AAAAMMDD  */
		
		LocalDate localDate = LocalDate.now();
		String dtCorrente = String.format("%04d",localDate.getYear()) + 
							String.format("%02d",localDate.getMonthValue()) +
							String.format("%02d",localDate.getDayOfMonth());
		
		if (Integer.parseInt(dtReferencia) < Integer.parseInt(dtCorrente))
		{
			idRetorno = 15;
			msgRetorno = "Validação de Datas - Data de referência menor do que data corrente - formato (AAAAMMDD)- "
						  +dtReferencia;
			return false;
		}
		
		if (qtdeDiasCarencia < 0)
		{
			idRetorno = 16;
			msgRetorno = "Validação de Datas - Quantidade de dias de car�ncia inválido -> "
			+ qtdeDiasCarencia;
			return false;
			
		}
		
		if (periodicidadeVcto != 'M' && periodicidadeVcto != 'B' && periodicidadeVcto != 'T'
				&& periodicidadeVcto != 'A' && periodicidadeVcto != 'E' && periodicidadeVcto != 'D')
		{
			idRetorno = 17;
			msgRetorno = "Validação de Datas - Periodicidade de Vencimento inválida (M/B/T/A/E/D) -> "
						+ periodicidadeVcto; 
			return false;
		}
		
		if (qtdeParcelas <= 0 )
		{
			idRetorno = 18;
			msgRetorno = "Validação de Datas - Quantidade de parcelas inválida -> "
			+ qtdeParcelas;
			return false;
		}

		if (qtdeParcelas > 10000 && periodicidadeVcto == 'D')
		{
			idRetorno = 19;
			msgRetorno = "Quantidade de parcelas maior do que o limite parametrizado ";
			return false;
		}

		if (qtdeParcelas > 1000 && (periodicidadeVcto == 'M' || periodicidadeVcto == 'B' || periodicidadeVcto == 'T'))
		{
			idRetorno = 19;
			msgRetorno = "Quantidade de parcelas maior do que o limite parametrizado ";
			return false;
		}
		
		if (qtdeParcelas > 60 && periodicidadeVcto == 'A')
		{
			idRetorno = 19;
			msgRetorno = "Quantidade de parcelas maior do que o limite parametrizado ";
			return false;
		}

		return true;
		
	}
	
/*
 * Validar o primeiro vencimento.
 * Nota - O primeiro vencimento deve ser maior do que a quantidade de dias de car�ncia.
 */
	
	public boolean validarDtVtc1ap(String dtVct1ap)
	{

		resultValue = true;
		
		if (qtdeDiasCarencia < 0)
		{
			idRetorno = 16;
			msgRetorno = "Validação de Datas - Quantidade de dias de car�ncia inválido -> "
			+ qtdeDiasCarencia;
			resultValue = false;
			return false;
		}

		try
		{
			SimpleDateFormat dataValida = new SimpleDateFormat("yyyyMMdd");
			dataValida.setLenient(false);
			dataValida.parse(dtVct1ap);
		} catch (ParseException ex) {
			idRetorno = 14;
			msgRetorno = "Validação de Datas - Data do primeiro vencimento inválida - formato (AAAAMMDD)- "
			+ dtVct1ap;
			resultValue = false;
			return false;
		}
		
		try
		{
			SimpleDateFormat dataValida = new SimpleDateFormat("yyyyMMdd");
			dataValida.setLenient(false);
			dataValida.parse(dtReferencia);
		} catch (ParseException ex) {
			idRetorno = 14;
			msgRetorno = "Validação de Datas - Data de referência inválida - formato (AAAAMMDD)- "
			+ dtReferencia;
			resultValue = false;
			return false;
		}
		
		
		LocalDate localDtCorrente = 	LocalDate.of(Integer.parseInt(this.getDtReferencia().substring(0,4)), 
										Integer.parseInt(this.getDtReferencia().substring(4, 6)),
										Integer.parseInt(this.getDtReferencia().substring(6,8)));
		
		LocalDate localDataLimite = localDtCorrente;
		localDataLimite = localDataLimite.plusDays(qtdeDiasCarencia);  // adiciona a quantidade de dias de carência
		
		LocalDate localdtVct1ap = 	LocalDate.of(Integer.parseInt(dtVct1ap.substring(0,4)), 
									Integer.parseInt(dtVct1ap.substring(4, 6)),
									Integer.parseInt(dtVct1ap.substring(6,8)));
		
		if (localdtVct1ap.isBefore(localDataLimite))
		{
			idRetorno = 20;
			msgRetorno = "Validação de Datas - Data do primeiro vencimento menor do que data limite parametrizada -> "
			+ "Primeiro Vencto = " + localdtVct1ap + " Data limite = " + localDataLimite;
			resultValue = false;
			return false;
		}

		return true;
	}
	
	public boolean validaFluxoVencimentos(String[] dtVencimento)
	{
		resultValue = true;
		
		boolean resultado = true;
		
		for (int x = 0; x < qtdeParcelas; x++)
		{
			
			if (x == 0)
			{
				resultado = this.validarDtVtc1ap(dtVencimento[x]);
				if (!resultado) {
					resultValue = false;
					return false;
				}
			}
			else
			{
				try
				{
					SimpleDateFormat dataValida = new SimpleDateFormat("yyyyMMdd");
					dataValida.setLenient(false);
					dataValida.parse(dtVencimento[x]);
				} catch (ParseException ex) {
					idRetorno = 21;
					msgRetorno = "Validação de Datas - Data de vencimento inválida - formato (AAAAMMDD)- "
					+ dtVencimento[x] + " Parcela "+ x;
					resultValue = false;
					return false;
				}
				
				if (Integer.parseInt(dtVencimento[x]) <= Integer.parseInt(dtVencimento[x-1]))
				{
					idRetorno = 22;
					msgRetorno = "Validação de Datas - Fluxo de Vencimentos n�o est� na ordem crescente -"
					+ " Parc ant = "+ (x) + " " + dtVencimento[x-1] + " Part atual = "+ (x+1) + " " + dtVencimento[x]; 
					resultValue = false;
					return false;
				}
			}
			
		}
		
		return true;
	}
	
	/* Cálculo do Primeiro Vencimento - 2  métodos - um  método para validar caso o campo estiver preenchido, 
	 *  o outro para calcular, caso não informado
	 *  - Dados utilizados
	 * 		- Data Corrente;
	 * 		- Quantidade de dias de car�ncia (o primeiro vencimento deve ser após a quantidade de dias de car�ncia)
	 * 		- Dia Base de Vencimento ( se preenchido - considerar o dia Base de Vencimento para cálculo)
	 * 		- Indicar de Dia útil (S/N) - Verificar se vence em dia  útil ou  não;
	 * 		- Data do Primeiro Vencimento (se preenchido, realizar somente a validação do primeiro vencimento);
	 */
	
	
	public String calcularDtVtc1ap()
	{
		if (diaBaseVcto == 0 && dtReferencia != null && dtReferencia.length() >= 8) {
			LocalDate localDtReferencia = LocalDate.of(
					Integer.parseInt(dtReferencia.substring(0, 4)),
					Integer.parseInt(dtReferencia.substring(4, 6)),
					Integer.parseInt(dtReferencia.substring(6, 8)));
			diaBaseVcto = localDtReferencia.getDayOfMonth();
		}
		
		resultValue = validarDadosEntrada();
		
		if (resultValue)
		{
			
			LocalDate 	localDtReferencia = LocalDate.of(Integer.parseInt(dtReferencia.substring(0,4)), 
						Integer.parseInt(dtReferencia.substring(4, 6)),
						Integer.parseInt(dtReferencia.substring(6,8)));
			
			LocalDate localDataLimite = localDtReferencia;
			
			localDataLimite = localDataLimite.plusDays(qtdeDiasCarencia);  // adiciona a quantidade de dias de carência

			LocalDate localDateVct1ap = localDataLimite;
			
			String dtVct1Ap = 	String.format("%04d",localDateVct1ap.getYear()) + 
								String.format("%02d",localDateVct1ap.getMonthValue()) +
								String.format("%02d",localDateVct1ap.getDayOfMonth());
			
			boolean resultdataValida;
			int diaBaseVctoValido;
			
			diaBaseVctoValido = diaBaseVcto;
			resultdataValida = false;
			
			while (!resultdataValida)
			{
				try
				{
					resultdataValida = true;
					dtVct1Ap = 	String.format("%04d",localDateVct1ap.getYear()) + 
								String.format("%02d",localDateVct1ap.getMonthValue()) +
								String.format("%02d",diaBaseVctoValido);
					SimpleDateFormat dataValida = new SimpleDateFormat("yyyyMMdd");
					dataValida.setLenient(false);
					dataValida.parse(dtVct1Ap);
				} catch (ParseException ex) {
					diaBaseVctoValido --;
					resultdataValida = false;
				}
			}
			
			localDateVct1ap = 	LocalDate.of(Integer.parseInt(dtVct1Ap.substring(0,4)), 
								Integer.parseInt(dtVct1Ap.substring(4, 6)),
								Integer.parseInt(dtVct1Ap.substring(6,8)));
			
			   
			if (periodicidadeVcto == 'M' || periodicidadeVcto == 'B' || periodicidadeVcto == 'T')
			{
				localDateVct1ap = localDateVct1ap.plusMonths(mesesPorPeriodicidade());

				// Verifica se o dia base de vencimento é menor do que o dia da data limite

				if (diaBaseVcto <     localDateVct1ap.getDayOfMonth())
					localDateVct1ap = localDateVct1ap.plusMonths(1);
						
				diaBaseVctoValido = diaBaseVcto;
				resultdataValida = false;
				while (!resultdataValida)
				{
					try
					{
						resultdataValida = true;
						dtVct1Ap = 	String.format("%04d",localDateVct1ap.getYear()) + 
									String.format("%02d",localDateVct1ap.getMonthValue()) +
									String.format("%02d",diaBaseVctoValido);
						SimpleDateFormat dataValida = new SimpleDateFormat("yyyyMMdd");
						dataValida.setLenient(false);
						dataValida.parse(dtVct1Ap);
					} catch (ParseException ex) {
						diaBaseVctoValido --;
						resultdataValida = false;
					}
				}
			} 
			else if (periodicidadeVcto == 'A')  // Periodicidade Anual
			{

				localDateVct1ap = localDateVct1ap.plusYears(1);
					
				if (localDataLimite.getMonthValue() <= localDateVct1ap.getMonthValue() &&
					localDateVct1ap.getDayOfMonth() <  diaBaseVctoValido)
					localDateVct1ap = localDateVct1ap.plusMonths(1);
					
				diaBaseVctoValido = diaBaseVcto;
				resultdataValida = false;
				while (!resultdataValida)
				{
					try
					{
						resultdataValida = true;
						dtVct1Ap = 	String.format("%04d",localDateVct1ap.getYear()) + 
									String.format("%02d",localDateVct1ap.getMonthValue()) +
									String.format("%02d",diaBaseVctoValido);
						SimpleDateFormat dataValida = new SimpleDateFormat("yyyyMMdd");
						dataValida.setLenient(false);
						dataValida.parse(dtVct1Ap);
					} catch (ParseException ex) {
						diaBaseVctoValido --;
						resultdataValida = false;
					}
				}
			} 
			else if (periodicidadeVcto == 'D')  // Periodicidade Diária
			{
				// Verificar se o primeiro vencimento � menor do que a data limite, caso positivo, adicionar 1 dia
				// na data de primeiro vencimento.
				
				localDateVct1ap = localDataLimite;

				resultdataValida = false;
				while (!resultdataValida)
				{
					try
					{
						resultdataValida = true;
						dtVct1Ap = 	String.format("%04d",localDateVct1ap.getYear()) + 
								String.format("%02d",localDateVct1ap.getMonthValue()) +
								String.format("%02d",localDateVct1ap.getDayOfMonth());
						SimpleDateFormat dataValida = new SimpleDateFormat("yyyyMMdd");
						dataValida.setLenient(false);
						dataValida.parse(dtVct1Ap);
					} catch (ParseException ex) {
						diaBaseVctoValido --;
						resultdataValida = false;
					}
				} 

			}
			/*
			 * Verifica se a data de vencimento  é dia útil
			 * 
			 */
			
			if (idDiaUtil == 'S')
			{
				dtVct1Ap = ajustarParaDiaUtil(localDateVct1ap);
			}

			resultValue = true;
			return dtVct1Ap;
			
		} else

			resultValue = false;
			return "";
	}
	
	
	/*
	 * Avança para o próximo dia útil; se ultrapassar o último dia do mês,
	 * retorna o último dia útil do mês da data original.
	 */
	private String ajustarParaDiaUtil(LocalDate data)
	{
		LocalDate candidata = data;
		while (!verificaDiaUtil(candidata.format(DateTimeFormatter.BASIC_ISO_DATE)))
		{
			candidata = candidata.plusDays(1);
			if (candidata.getMonthValue() != data.getMonthValue() && periodicidadeVcto != 'D')
			{
				candidata = data.withDayOfMonth(data.lengthOfMonth());
				while (candidata.getDayOfMonth() > 1
						&& !verificaDiaUtil(candidata.format(DateTimeFormatter.BASIC_ISO_DATE)))
				{
					candidata = candidata.minusDays(1);
				}
				break;
			}
		}
		return candidata.format(DateTimeFormatter.BASIC_ISO_DATE);
	}

	public boolean verificaDiaUtil(String dtUtil)
	{
	
		// Caso atributo dtUteis estiver vazio, carregar a tabela de dias �teis 
		
		if (dtUteis.isEmpty())
		{
			dtUteis = carregarDiasUteis();
		}

        return dtUteis.contains(dtUtil);
	}

	private ArrayList<String> carregarDiasUteis() {

		resultValue = true;
		ArrayList<String> dtRegistro = new ArrayList<>();

		try (InputStream is = getClass().getResourceAsStream("/data/smx_dias_uteis.txt")) {

			if (is == null) {
				idRetorno = 23;
				msgRetorno = "Arquivo de dias úteis não encontrado no resources -> /data/smx_dias_uteis.txt";
				resultValue = false;
				return dtRegistro;
			}

			try (BufferedReader buffRead = new BufferedReader(new InputStreamReader(is))) {

				String linha;
				while ((linha = buffRead.readLine()) != null) {
					dtRegistro.add(linha);
				}

			}

		} catch (IOException e) {
			idRetorno = 24;
			msgRetorno = "Erro na leitura do arquivo de dias úteis";
			resultValue = false;
		}

		return dtRegistro;
	}

	
	/* Calcular quantidade de dias corridos
	 *  - Dados utilizados
	 * 		- Data de    - formato AAAAMMDD 
	 * 		- Data At�   - formato AAAAMMDD
	 */
	
	public int calcularDiasCorridos(String dtInicial, String dtFinal )
	{
		resultValue = true;
		String dataDe = dtInicial;
		String dataAte = dtFinal;

		try
		{
			SimpleDateFormat dataValida = new SimpleDateFormat("yyyyMMdd");
			dataValida.setLenient(false);
			dataValida.parse(dataDe);
		} catch (ParseException ex) {
			resultValue = false;
			idRetorno = 23;
			msgRetorno = "Cálculo de Dias Corridos - Data Inicial inválida - formato (AAAAMMDD) - "
			+ dataDe; 
			resultValue = false;
			return 0;
		}
		
		try
		{
			SimpleDateFormat dataValida = new SimpleDateFormat("yyyyMMdd");
			dataValida.setLenient(false);
			dataValida.parse(dataAte);
		} catch (ParseException ex) {
			idRetorno = 24;
			msgRetorno = "Cálculo de Dias Corridos - Data Final inválida - formato (AAAAMMDD) - "
			+ dataDe; 
			resultValue = false;
			return 0;
		}
		
		LocalDate 	localdataDe = LocalDate.of(Integer.parseInt(dataDe.substring(0,4)), 
				Integer.parseInt(dataDe.substring(4, 6)),
				Integer.parseInt(dataDe.substring(6,8)));

		LocalDate 	localdataAte = LocalDate.of(Integer.parseInt(dataAte.substring(0,4)), 
				Integer.parseInt(dataAte.substring(4, 6)),
				Integer.parseInt(dataAte.substring(6,8)));
		
		if (localdataAte.isBefore(localdataDe))
		{
			idRetorno = 25;
			msgRetorno = "Data Inicial menor do que data final - Data Inicial = " + dataDe + " "
			+ "Data Final = " + dataAte; 
			resultValue = false;
			return 0;
		}
				
		if (localdataDe.isEqual(localdataAte))
			return 0;
		
		return (int) ChronoUnit.DAYS.between(localdataDe, localdataAte);
		
	}
	
	public int calcularDiasUteis(String dtInicial, String dtFinal)
	{
		resultValue = true;
		String dataDe = dtInicial;
		String dataAte = dtFinal;

		try
		{
			SimpleDateFormat dataValida = new SimpleDateFormat("yyyyMMdd");
			dataValida.setLenient(false);
			dataValida.parse(dataDe);
		} catch (ParseException ex) {
			idRetorno = 26;
			msgRetorno = "Cálculo de Dias �teis - Data Inicial inválida - formato (AAAAMMDD) - "
			+ dataDe; 
			resultValue = false;
			return 0;
		}
		
		try
		{
			SimpleDateFormat dataValida = new SimpleDateFormat("yyyyMMdd");
			dataValida.setLenient(false);
			dataValida.parse(dataAte);
		} catch (ParseException ex) {
			idRetorno = 27;
			msgRetorno = "Cálculo de Dias �teis - Data Final inválida - formato (AAAAMMDD) - "
			+ dataDe; 
			resultValue = false;
			return 0;
		}

		if (dtUteis.isEmpty())
		{
			dtUteis = carregarDiasUteis();
		}

		String dtref = dataDe;
		
		int i = dtUteis.indexOf(dtref);
		
		while (i < 0)
		{
			LocalDate 	localdtref = LocalDate.of(Integer.parseInt(dtref.substring(0,4)), 
					Integer.parseInt(dtref.substring(4, 6)),
					Integer.parseInt(dtref.substring(6,8)));
			localdtref = localdtref.plusDays(1);

			dtref = 	String.format("%04d",localdtref.getYear()) + 
					String.format("%02d",localdtref.getMonthValue()) +
					String.format("%02d",localdtref.getDayOfMonth());

			i = dtUteis.indexOf(dtref);
		}
		
		int qtdediasuteis = 0;
		while (Integer.parseInt(dtUteis.get(i)) < Integer.parseInt(dataAte))
		{
			if (Integer.parseInt(dtUteis.get(i)) >= Integer.parseInt(dataDe))
					qtdediasuteis++;
			i++;
		}
		
		return qtdediasuteis;
		
	}
	
	/* Calcular próximo vencimento a partir da data informada */
	
	public String calcularPrxVcto(String dtBase)
	{

		resultValue = true;
		String dtBaseCalc = dtBase;
		
		try
		{
			SimpleDateFormat dataValida = new SimpleDateFormat("yyyyMMdd");
			dataValida.setLenient(false);
			dataValida.parse(dtBaseCalc);
		} catch (ParseException ex) {
			idRetorno = 28;
			msgRetorno = "Cálculo do Próximo Vencimento - Data Base Inválida - formato (AAAAMMDD) - "
			+ dtBaseCalc;
			resultValue = false;
			return "";
			
		}

		String dtPrxVcto = "";
		resultValue = validarDadosEntrada();
		
		if (resultValue)
		{

			dtPrxVcto = dtBaseCalc;
			
			LocalDate localdtPrxVcto = LocalDate.of(Integer.parseInt(dtPrxVcto.substring(0,4)),
					Integer.parseInt(dtPrxVcto.substring(4,6)),
					Integer.parseInt(dtPrxVcto.substring(6,8)));
			

			if (this.getPeriodicidadeVcto() == 'M' || this.getPeriodicidadeVcto() == 'B'
					|| this.getPeriodicidadeVcto() == 'T')
			{
				localdtPrxVcto = localdtPrxVcto.plusMonths(mesesPorPeriodicidade());
			}
			else if (this.getPeriodicidadeVcto() == 'A')
			{
				localdtPrxVcto = localdtPrxVcto.plusYears(1);
			}
			else if (this.getPeriodicidadeVcto() == 'D')
			{
				localdtPrxVcto = localdtPrxVcto.plusDays(1);
				
				dtPrxVcto =	String.format("%04d",localdtPrxVcto.getYear()) +
					 		String.format("%02d", localdtPrxVcto.getMonthValue()) +
					 		String.format("%02d", localdtPrxVcto.getDayOfMonth());
			}

			if (this.getPeriodicidadeVcto() != 'D')
			{
				int diaBaseVctoValido = diaBaseVcto;
				boolean resultdataValida = false;
				while (!resultdataValida)
				{
					try
					{
						resultdataValida = true;
						dtPrxVcto =	String.format("%04d",localdtPrxVcto.getYear()) +
									 	String.format("%02d", localdtPrxVcto.getMonthValue()) +
									 	String.format("%02d", diaBaseVctoValido);
						SimpleDateFormat dataValida = new SimpleDateFormat("yyyyMMdd");
						dataValida.setLenient(false);
						dataValida.parse(dtPrxVcto);
					} catch (ParseException ex) {
						diaBaseVctoValido --;
						resultdataValida = false;
					}
				}
				
				localdtPrxVcto = LocalDate.of(Integer.parseInt(dtPrxVcto.substring(0,4)),
						Integer.parseInt(dtPrxVcto.substring(4,6)),
						Integer.parseInt(dtPrxVcto.substring(6,8)));
				
			}

			if (idDiaUtil == 'S')
			{
				dtPrxVcto = ajustarParaDiaUtil(localdtPrxVcto);
			}
		}	

		return dtPrxVcto;
	
	}
	
	/* Calcular a linha do Tempo
	 *	dtVencimento[];
	/*  qtdeDiasCorridos[];
	/*  qtdeDiasUteis[];
	 */
	
	public boolean calcularLinhaDoTempo()
	{
		
		resultValue = true;

		if (this.getDtVct1ap() == null)
		{
			dtVct1ap = this.calcularDtVtc1ap();
		} else if (idDiaUtil == 'S' && dataFormatoValido(dtVct1ap)) {
			dtVct1ap = ajustarParaDiaUtil(LocalDate.parse(dtVct1ap, DateTimeFormatter.BASIC_ISO_DATE));
		}
		
		resultValue = this.validarDadosEntrada();
			
		if (resultValue)
			this.validarDtVtc1ap(dtVct1ap);
		
		if (resultValue)
		{
			
			for (int i = 0; i < this.getQtdeParcelas(); i++)
			{
				if (i == 0)
				{
					dtVencimento.add(this.getDtVct1ap());
					qtdeDiasCorridos.add(this.calcularDiasCorridos(dtReferencia,dtVct1ap));
					qtdeDiasUteis.add(this.calcularDiasUteis(dtReferencia,dtVct1ap));
					
				}
				else
				{
					dtVencimento.add(this.calcularPrxVcto(dtVencimento.get(i-1)));
					qtdeDiasCorridos.add(this.calcularDiasCorridos(dtReferencia, dtVencimento.get(i)));
					qtdeDiasUteis.add(this.calcularDiasUteis(dtReferencia, dtVencimento.get(i)));
				}
			}
		}
		
		return resultValue;
	}

	public boolean calcularLinhaDoTempo(List<String> vencimentosInformados)
	{
		if (vencimentosInformados == null || vencimentosInformados.isEmpty()) {
			return calcularLinhaDoTempo();
		}

		dtVencimento.clear();
		qtdeDiasCorridos.clear();
		qtdeDiasUteis.clear();

		if (vencimentosInformados.size() != qtdeParcelas) {
			idRetorno = 21;
			msgRetorno = "Quantidade de datas de vencimento diferente da quantidade de parcelas";
			resultValue = false;
			return false;
		}
		if (vencimentosInformados.get(0) == null) {
			idRetorno = 21;
			msgRetorno = "Data do primeiro vencimento não informada";
			resultValue = false;
			return false;
		}

		resultValue = validarDadosEntrada();
		if (!resultValue || !validarDtVtc1ap(vencimentosInformados.get(0))) {
			return false;
		}

		LocalDate dataAnterior = null;
		for (int i = 0; i < vencimentosInformados.size(); i++) {
			String vencimento = vencimentosInformados.get(i);
			if (vencimento == null) {
				idRetorno = 21;
				msgRetorno = "Data de vencimento não informada - parcela " + (i + 1);
				resultValue = false;
				return false;
			}
			LocalDate dataVencimento;
			try {
				dataVencimento = LocalDate.parse(vencimento, DateTimeFormatter.BASIC_ISO_DATE);
			} catch (DateTimeParseException ex) {
				idRetorno = 21;
				msgRetorno = "Data de vencimento inválida - formato (AAAAMMDD) - " + vencimento;
				resultValue = false;
				return false;
			}

			if (dataAnterior != null && !dataVencimento.isAfter(dataAnterior)) {
				idRetorno = 22;
				msgRetorno = "Fluxo de vencimentos não está em ordem crescente - parcela " + (i + 1);
				resultValue = false;
				return false;
			}

			if (idDiaUtil == 'S' && !verificaDiaUtil(vencimento)) {
				idRetorno = 22;
				msgRetorno = "Vencimento informado não é dia útil - parcela " + (i + 1);
				resultValue = false;
				return false;
			}

			dtVencimento.add(vencimento);
			qtdeDiasCorridos.add(calcularDiasCorridos(dtReferencia, vencimento));
			qtdeDiasUteis.add(calcularDiasUteis(dtReferencia, vencimento));
			dataAnterior = dataVencimento;
		}

		dtVct1ap = vencimentosInformados.get(0);
		resultValue = true;
		return true;
	}

	private int mesesPorPeriodicidade()
	{
		if (periodicidadeVcto == 'B') {
			return 2;
		}
		if (periodicidadeVcto == 'T') {
			return 3;
		}
		return 1;
	}
	
}

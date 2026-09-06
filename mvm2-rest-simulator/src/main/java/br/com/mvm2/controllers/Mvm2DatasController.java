package br.com.mvm2.controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mvm2.exceptions.UnsupportedMathOperationException;
import mvm2services.services.Datas;
import mvm2services.services.NumberConverter;

@RestController
@RequestMapping("/datas")
public class Mvm2DatasController {
	
	// Parâmetros de entrada 
	
	@GetMapping(value = "/{diaBaseVcto}/"
								+ "{idDiaUtil}/"
								+ "{dtReferencia}/"
								+ "{qtdeDiasCarencia}/"
								+ "{periodicidadeVcto}/"
								+ "{qtdeParcelas}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Datas datas(
			@PathVariable(value = "diaBaseVcto") String diaBaseVcto,
			@PathVariable(value = "idDiaUtil") String idDiaUtil,
			@PathVariable(value = "dtReferencia") String dtReferencia,
			@PathVariable(value = "qtdeDiasCarencia") String qtdeDiasCarencia,
			@PathVariable(value = "periodicidadeVcto") String periodicidadeVcto,
			@PathVariable(value = "qtdeParcelas") String qtdeParcelas
		) throws Exception { 
		
		
		if (!NumberConverter.isNumeric(diaBaseVcto))
			throw new UnsupportedMathOperationException("Dia Base de Vencimento não numérico - " + diaBaseVcto);

		if (!NumberConverter.isNumeric(qtdeDiasCarencia))
			throw new UnsupportedMathOperationException("Qtde de dias de carência não numérico - " + qtdeDiasCarencia);

		if (!NumberConverter.isNumeric(qtdeParcelas))
			throw new UnsupportedMathOperationException("Qtde de parcelas não numérico - " + qtdeParcelas);
		
		Datas datas = new Datas();
		datas.setDiaBaseVcto(Integer.parseInt(diaBaseVcto));
		datas.setIdDiaUtil(idDiaUtil.charAt(0));
		datas.setDtReferencia(dtReferencia);
		datas.setQtdeDiasCarencia(Integer.parseInt(qtdeDiasCarencia));
		datas.setPeriodicidadeVcto(periodicidadeVcto.charAt(0));
		datas.setQtdeParcelas(Integer.parseInt(qtdeParcelas));
		
		if (!datas.calcularLinhaDoTempo()) {
			throw new UnsupportedMathOperationException(datas.getMsgRetorno());
		}
	
		return datas;
	}
}

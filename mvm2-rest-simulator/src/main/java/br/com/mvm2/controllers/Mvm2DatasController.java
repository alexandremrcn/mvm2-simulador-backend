package br.com.mvm2.controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mvm2.dto.Mvm2DatasRequestDTO;
import br.com.mvm2.exceptions.UnsupportedMathOperationException;
import mvm2services.services.Datas;
import mvm2services.services.NumberConverter;

@RestController
@RequestMapping("/datas")
public class Mvm2DatasController {

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Datas gerarFluxo(@RequestBody Mvm2DatasRequestDTO request) throws Exception {
		if (request == null
				|| !NumberConverter.isNumeric(request.getDiaBaseVcto())
				|| !NumberConverter.isNumeric(request.getQtdeDiasCarencia())
				|| !NumberConverter.isNumeric(request.getQtdeParcelas())) {
			throw new UnsupportedMathOperationException("Os campos diaBaseVcto, qtdeDiasCarencia e qtdeParcelas devem ser numéricos.");
		}
		if (request.getIdDiaUtil() == null || request.getIdDiaUtil().length() != 1
				|| request.getPeriodicidadeVcto() == null || request.getPeriodicidadeVcto().length() != 1
				|| request.getDtReferencia() == null) {
			throw new UnsupportedMathOperationException("Os campos idDiaUtil, periodicidadeVcto e dtReferencia são obrigatórios.");
		}

		Datas datas = new Datas();
		datas.setDiaBaseVcto(Integer.parseInt(request.getDiaBaseVcto()));
		datas.setIdDiaUtil(request.getIdDiaUtil().charAt(0));
		datas.setDtReferencia(request.getDtReferencia());
		datas.setDtVct1ap(request.getDtVct1ap());
		datas.setQtdeDiasCarencia(Integer.parseInt(request.getQtdeDiasCarencia()));
		datas.setPeriodicidadeVcto(request.getPeriodicidadeVcto().charAt(0));
		datas.setQtdeParcelas(Integer.parseInt(request.getQtdeParcelas()));

		if (!datas.calcularLinhaDoTempo()) {
			throw new UnsupportedMathOperationException(datas.getMsgRetorno());
		}
		return datas;
	}
	
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
			throw new UnsupportedMathOperationException("Dia Base de Vencimento deve ser numérico: " + diaBaseVcto);

		if (!NumberConverter.isNumeric(qtdeDiasCarencia))
			throw new UnsupportedMathOperationException("Qtde de dias de carência deve ser numérica: " + qtdeDiasCarencia);

		if (!NumberConverter.isNumeric(qtdeParcelas))
			throw new UnsupportedMathOperationException("Qtde de parcelas deve ser numérica: " + qtdeParcelas);
		
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

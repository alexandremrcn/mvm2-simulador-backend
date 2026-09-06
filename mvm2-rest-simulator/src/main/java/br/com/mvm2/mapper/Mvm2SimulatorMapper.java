package br.com.mvm2.mapper;

import br.com.mvm2.dto.Mvm2SimulatorRequestDTO;
import br.com.mvm2.dto.Mvm2SimulatorResponseDTO;
import mvm2services.models.SimulatorInput;
import mvm2services.models.SimulatorResult;

public class Mvm2SimulatorMapper {

    public static SimulatorInput toInput(Mvm2SimulatorRequestDTO dto) {
        SimulatorInput input = new SimulatorInput();

        input.vlSolicitado = dto.getVlSolicitado();
        input.vl1aParcela = dto.getVl1aParcela();
        input.idTaxaFator = dto.getIdTaxaFator();
        input.taxa = dto.getTaxa();
        input.periodicidadeTaxa = dto.getPeriodicidadeTaxa();
        input.baseTaxa = dto.getBaseTaxa();
        input.periodicidadeVcto = dto.getPeriodicidadeVcto();
        input.idDiaUtil = dto.getIdDiaUtil();
        input.tipoJuros = dto.getTipoJuros();
        input.qtdeParcelas = dto.getQtdeParcelas();
        input.qtdeDiasCarencia = dto.getQtdeDiasCarencia();
        input.diaBaseVencimento = dto.getDiaBaseVencimento();
        input.cdSistemaAmortizacao = dto.getCdSistemaAmortizacao();
        input.tpNegociacaoIof = dto.getTpNegociacaoIof();
        input.idIofEmbutido = dto.getIdIofEmbutido();
        input.pcAliquotaIOF = dto.getPcAliquotaIOF();
        input.pcAliquotaAdicional = dto.getPcAliquotaAdicional();

        input.idCobrJuros = dto.getIdCobrJuros();
        input.idCobrPrinc = dto.getIdCobrPrinc();
        input.vlParcelaIn = dto.getVlParcelaIn();

        return input;
    }

    public static Mvm2SimulatorResponseDTO toDTO(SimulatorResult result) {
        Mvm2SimulatorResponseDTO dto = new Mvm2SimulatorResponseDTO();

        dto.setCdResult(result.cdResult);
        dto.setMsgResult(result.msgResult);
        dto.setDtReferencia(result.dtReferencia);
        dto.setPcTaxaJuros(result.pcTaxaJuros);
        dto.setPeriodicidadeTaxa(result.periodicidadeTaxa);
        dto.setBaseTaxaJuros(result.baseTaxaJuros);
        dto.setQtdeParcelas(result.qtdeParcelas);
        dto.setVlLiberado(result.vlLiberado);
        dto.setVlFinanciado(result.vlFinanciado);
        dto.setVlIof(result.vlIof);
        dto.setSistemaAmortizacao(result.sistemaAmortizacao);
        dto.setPeriodicidadeVcto(result.periodicidadeVcto);
        dto.setTpJuros(result.tpJuros);
        dto.setVl1aParela(result.vl1aParela);

        dto.setNumParcela(result.numParcela);
        dto.setDtVencimento(result.dtVencimento);
        dto.setQtdeDiasCorr(result.qtdeDiasCorr);
        dto.setQtdeDiasUteis(result.qtdeDiasUteis);
        dto.setIdCobraJuros(result.idCobraJuros);
        dto.setIdCobraPrincipal(result.idCobraPrincipal);

        dto.setVlParcela(result.vlParcela);
        dto.setVlJuros(result.vlJuros);
        dto.setVlPrincipal(result.vlPrincipal);
        dto.setVlSdoRemanescente(result.vlSdoRemanescente);

        return dto;
    }
}

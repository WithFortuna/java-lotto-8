package lotto.controller;

import lotto.controller.dto.LottoResultRequest;
import lotto.controller.dto.LottoResultResponse;
import lotto.service.LottoResultService;

public class LottoResultController {
	private final LottoResultService lottoResultService;

	public LottoResultController(LottoResultService lottoResultService) {
		this.lottoResultService = lottoResultService;
	}

	public LottoResultResponse calculateResult(LottoResultRequest request) {
		return lottoResultService.calculateResult(request.lottoDTOList(), request.winningNumbersDTO(), request.lottoCost());
	}
}

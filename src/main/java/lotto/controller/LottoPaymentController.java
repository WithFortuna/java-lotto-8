package lotto.controller;

import lotto.controller.dto.LottoPaymentRequest;
import lotto.controller.dto.LottoPaymentResponse;
import lotto.service.LottoPaymentService;

public class LottoPaymentController {
	private final LottoPaymentService lottoPaymentService;

	public LottoPaymentController(LottoPaymentService lottoPaymentService) {
		this.lottoPaymentService = lottoPaymentService;
	}

	public LottoPaymentResponse payForLotto(LottoPaymentRequest request) {
		return lottoPaymentService.payForLotto(request);
	}

}

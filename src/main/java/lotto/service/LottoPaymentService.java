package lotto.service;

import java.util.List;

import lotto.domain.Lotto;
import lotto.controller.dto.LottoPaymentRequest;
import lotto.controller.dto.LottoPaymentResponse;

public class LottoPaymentService {
	public LottoPaymentResponse payForLotto(LottoPaymentRequest request) {
		List<Lotto> paidLottos = Lotto.createFromCost(request.lottoCost());

		return LottoPaymentResponse.from(paidLottos, request.lottoCost());
	}
}

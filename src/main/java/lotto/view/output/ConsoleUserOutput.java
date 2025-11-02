package lotto.view.output;

import java.util.List;

import lotto.controller.dto.LottoDTO;
import lotto.controller.dto.LottoPaymentResponse;

public class ConsoleUserOutput {
	public void printPaymentInfo(LottoPaymentResponse paymentResponse) {
		printTheNumberOfLotto(paymentResponse);
		printLottoNumbers(paymentResponse);
	}

	private static void printLottoNumbers(LottoPaymentResponse paymentResponse) {
		List<LottoDTO> lottoDTOS = paymentResponse.lottoList();
		for (LottoDTO lotto : lottoDTOS) {
			System.out.println(lotto.numbers());
		}
	}

	private static void printTheNumberOfLotto(LottoPaymentResponse paymentResponse) {
		System.out.println(paymentResponse.theNumberOfLotto() + "개를 구매했습니다.");
	}
}

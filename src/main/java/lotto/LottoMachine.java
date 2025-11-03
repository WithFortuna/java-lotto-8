package lotto;

import lotto.controller.LottoPaymentController;
import lotto.controller.LottoResultController;
import lotto.controller.dto.LottoPaymentRequest;
import lotto.controller.dto.LottoPaymentResponse;
import lotto.controller.dto.LottoResultRequest;
import lotto.controller.dto.LottoResultResponse;
import lotto.controller.dto.WinningNumbersDTO;
import lotto.view.input.ConsoleUserInput;
import lotto.view.output.ConsoleUserOutput;

public class LottoMachine {
	private final LottoPaymentController lottoPaymentController;
	private final LottoResultController lottoResultController;
	private final ConsoleUserInput consoleUserInput;
	private final ConsoleUserOutput consoleUserOutput;

	public LottoMachine(LottoPaymentController lottoPaymentController, LottoResultController lottoResultController,
		ConsoleUserInput consoleUserInput, ConsoleUserOutput consoleUserOutput) {
		this.lottoPaymentController = lottoPaymentController;
		this.lottoResultController = lottoResultController;
		this.consoleUserInput = consoleUserInput;
		this.consoleUserOutput = consoleUserOutput;
	}

	public void turnOn() {
		LottoPaymentResponse paymentResponse = processPayment();
		processResult(paymentResponse);
	}

	private LottoPaymentResponse processPayment() {
		while (true) {
			try {
				String inputLottoCost = consoleUserInput.inputLottoCost();
				LottoPaymentRequest paymentRequest = LottoPaymentRequest.from(inputLottoCost);
				LottoPaymentResponse paymentResponse = lottoPaymentController.payForLotto(paymentRequest);
				consoleUserOutput.printPaymentInfo(paymentResponse);
				return paymentResponse;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void processResult(LottoPaymentResponse paymentResponse) {
		WinningNumbersDTO winningNumbers = getWinningNumbersRequest();
		LottoResultRequest request = new LottoResultRequest(winningNumbers, paymentResponse.lottoDTOList(), paymentResponse.lottoCost());

		LottoResultResponse resultResponse = lottoResultController.calculateResult(request);

		consoleUserOutput.printLottoResult(resultResponse);
	}

	private WinningNumbersDTO getWinningNumbersRequest() {
		while (true) {
			try {
				String winningNumbersInput = consoleUserInput.inputWinningNumbers();
				String bonusNumberInput = consoleUserInput.inputBonusNumber();
				return WinningNumbersDTO.from(winningNumbersInput, bonusNumberInput);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}

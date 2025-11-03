package lotto;

import lotto.controller.LottoPaymentController;
import lotto.controller.LottoResultController;
import lotto.service.LottoPaymentService;
import lotto.service.LottoResultService;
import lotto.view.input.ConsoleUserInput;
import lotto.view.output.ConsoleUserOutput;

public class Application {
    public static void main(String[] args) {
        LottoPaymentService lottoPaymentService = new LottoPaymentService();
        LottoPaymentController lottoPaymentController = new LottoPaymentController(lottoPaymentService);

        LottoResultService lottoResultService = new LottoResultService();
        LottoResultController lottoResultController = new LottoResultController(lottoResultService);

        ConsoleUserInput consoleUserInput = new ConsoleUserInput();
        ConsoleUserOutput consoleUserOutput = new ConsoleUserOutput();

        LottoMachine lottoMachine = new LottoMachine(lottoPaymentController, lottoResultController,
            consoleUserInput, consoleUserOutput);
        lottoMachine.turnOn();
    }
}

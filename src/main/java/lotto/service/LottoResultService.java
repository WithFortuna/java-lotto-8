package lotto.service;

import java.util.List;

import lotto.controller.dto.LottoDTO;
import lotto.controller.dto.LottoResultResponse;
import lotto.controller.dto.WinningNumbersDTO;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.WinningNumbers;

public class LottoResultService {
	public LottoResultResponse calculateResult(List<LottoDTO> lottoDTOList, WinningNumbersDTO winningNumbersDTO, int purchaseCost) {
		WinningNumbers winningNumbers = new WinningNumbers(winningNumbersDTO.numbers(), winningNumbersDTO.bonusNumber());
		LottoResult lottoResult = new LottoResult(lottoDTOList, winningNumbers);
		return LottoResultResponse.from(lottoResult, purchaseCost);
	}
}

package lotto.controller.dto;

import java.util.List;

public record LottoResultRequest(
	WinningNumbersDTO winningNumbersDTO,
	List<LottoDTO> lottoDTOList,
	int lottoCost
) {
}

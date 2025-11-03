package lotto.controller.dto;

import java.util.List;

import lotto.domain.Lotto;

public record LottoPaymentResponse(
	String lottoSize,
	List<LottoDTO> lottoDTOList,
	int lottoCost
) {
	public static LottoPaymentResponse from(List<Lotto> lottoList, int lottoCost) {
		return new LottoPaymentResponse(
			String.valueOf(lottoList.size()),
			lottoList.stream()
				.map(lotto -> new LottoDTO(lotto.getNumbers()))
				.toList(),
			lottoCost
		);
	}
}

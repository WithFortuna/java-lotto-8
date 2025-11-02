package lotto.controller.dto;

import java.util.List;

import lotto.domain.Lotto;

public record LottoPaymentResponse(
	String theNumberOfLotto,
	List<LottoDTO> lottoList
) {
	public static LottoPaymentResponse from(List<Lotto> lottoList) {
		return new LottoPaymentResponse(
			String.valueOf(lottoList.size()),
			lottoList.stream()
				.map(lotto -> new LottoDTO(lotto.getNumbers()))
				.toList()
		);
	}
}

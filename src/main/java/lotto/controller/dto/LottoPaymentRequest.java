package lotto.controller.dto;

public record LottoPaymentRequest(
	Integer cost
) {
	public LottoPaymentRequest {
		if (cost % 1000 != 0) {
			throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 1000원 단위여야합니다");
		}
	}

	public static LottoPaymentRequest from(String cost) {
		return new LottoPaymentRequest(Integer.parseInt(cost));
	}
}

package lotto.controller.dto;

public record LottoPaymentRequest(
	Integer lottoCost
) {
	public static LottoPaymentRequest from(String cost) {
		validateNotEmpty(cost);
		try {
			return new LottoPaymentRequest(Integer.parseInt(cost));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
		}
	}

	private static void validateNotEmpty(String cost) {
		if (cost == null || cost.isEmpty()) {
			throw new IllegalArgumentException("[ERROR] 구입 금액을 반드시 입력해주세요");
		}
	}
}

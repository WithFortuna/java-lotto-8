package lotto.controller.dto;

import java.util.Map;

import lotto.domain.LottoResult;
import lotto.domain.Rank;

public record LottoResultResponse(
	Map<Rank, Integer> rankCounts,
	double profitRate
) {
	public static LottoResultResponse from(LottoResult lottoResult, int purchaseCost) {
		Map<Rank, Integer> rankCounts = lottoResult.getRankCounts();
		double profitRate = lottoResult.calculateProfitRate(purchaseCost);
		return new LottoResultResponse(rankCounts, profitRate);
	}

	public int getRankCount(Rank rank) {
		return rankCounts.getOrDefault(rank, 0);
	}
}

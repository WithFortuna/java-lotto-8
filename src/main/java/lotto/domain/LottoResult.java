package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import lotto.controller.dto.LottoDTO;

public class LottoResult {
	private final Map<Rank, Integer> rankCounts;

	public LottoResult(List<LottoDTO> lottos, WinningNumbers winningNumbers) {
		this.rankCounts = new EnumMap<>(Rank.class);
		initializeRankCounts();
		calculateResult(lottos, winningNumbers);
	}

	private void initializeRankCounts() {
		for (Rank rank : Rank.values()) {
			if (rank.isWinning()) {
				rankCounts.put(rank, 0);
			}
		}
	}

	private void calculateResult(List<LottoDTO> lottos, WinningNumbers winningNumbers) {
		for (LottoDTO lotto : lottos) {
			Rank rank = winningNumbers.match(lotto.numbers());
			if (rank.isWinning()) {
				rankCounts.put(rank, rankCounts.get(rank) + 1);
			}
		}
	}

	public double calculateProfitRate(int purchaseCost) {
		long totalPrize = calculateTotalPrize();
		return (double) totalPrize / purchaseCost * 100;
	}

	private long calculateTotalPrize() {
		return rankCounts.entrySet().stream()
			.mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
			.sum();
	}

	public Map<Rank, Integer> getRankCounts() {
		return new EnumMap<>(rankCounts);
	}
}

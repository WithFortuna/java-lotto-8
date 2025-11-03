package lotto.view.output;

import java.util.List;

import lotto.controller.dto.LottoDTO;
import lotto.controller.dto.LottoPaymentResponse;
import lotto.controller.dto.LottoResultResponse;
import lotto.domain.Rank;

public class ConsoleUserOutput {
	public void printPaymentInfo(LottoPaymentResponse paymentResponse) {
		printTheNumberOfLotto(paymentResponse);
		printLottoNumbers(paymentResponse);
	}

	private static void printLottoNumbers(LottoPaymentResponse paymentResponse) {
		List<LottoDTO> lottoDTOS = paymentResponse.lottoDTOList();
		for (LottoDTO lotto : lottoDTOS) {
			System.out.println(lotto.numbers());
		}
	}

	private static void printTheNumberOfLotto(LottoPaymentResponse paymentResponse) {
		System.out.println(paymentResponse.lottoSize() + "개를 구매했습니다.");
	}

	public void printLottoResult(LottoResultResponse resultResponse) {
		System.out.println();
		System.out.println("당첨 통계");
		System.out.println("---");
		printRankStatistics(resultResponse);
		printProfitRate(resultResponse);
	}

	private void printRankStatistics(LottoResultResponse resultResponse) {
		printRank(Rank.FIFTH, resultResponse);
		printRank(Rank.FOURTH, resultResponse);
		printRank(Rank.THIRD, resultResponse);
		printRank(Rank.SECOND, resultResponse);
		printRank(Rank.FIRST, resultResponse);
	}

	private void printRank(Rank rank, LottoResultResponse resultResponse) {
		int count = resultResponse.getRankCount(rank);
		String message = formatRankMessage(rank, count);
		System.out.println(message);
	}

	private String formatRankMessage(Rank rank, int count) {
		if (rank.isRequireBonus()) {
			return String.format("%d개 일치, 보너스 볼 일치 (%s원) - %d개",
				rank.getMatchCount(), formatPrize(rank.getPrize()), count);
		}
		return String.format("%d개 일치 (%s원) - %d개",
			rank.getMatchCount(), formatPrize(rank.getPrize()), count);
	}

	private String formatPrize(int prize) {
		return String.format("%,d", prize);
	}

	private void printProfitRate(LottoResultResponse resultResponse) {
		double profitRate = resultResponse.profitRate();
		System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
	}
}

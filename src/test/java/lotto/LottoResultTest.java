package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import lotto.controller.dto.LottoDTO;
import lotto.domain.LottoResult;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @DisplayName("당첨 내역을 정확하게 계산한다")
    @Test
    void 당첨_내역을_정확하게_계산한다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        List<LottoDTO> lottos = List.of(
                new LottoDTO(List.of(1, 2, 3, 4, 5, 6)),
                new LottoDTO(List.of(1, 2, 3, 4, 5, 7)),
                new LottoDTO(List.of(1, 2, 3, 4, 5, 8)),
                new LottoDTO(List.of(1, 2, 3, 4, 8, 9)),
                new LottoDTO(List.of(1, 2, 3, 8, 9, 10)),
                new LottoDTO(List.of(1, 2, 8, 9, 10, 11))
        );

        LottoResult result = new LottoResult(lottos, winningNumbers);
        Map<Rank, Integer> rankCounts = result.getRankCounts();

        assertThat(rankCounts.get(Rank.FIRST)).isEqualTo(1);
        assertThat(rankCounts.get(Rank.SECOND)).isEqualTo(1);
        assertThat(rankCounts.get(Rank.THIRD)).isEqualTo(1);
        assertThat(rankCounts.get(Rank.FOURTH)).isEqualTo(1);
        assertThat(rankCounts.get(Rank.FIFTH)).isEqualTo(1);
    }

    @DisplayName("수익률을 정확하게 계산한다")
    @Test
    void 수익률을_정확하게_계산한다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        List<LottoDTO> lottos = List.of(
                new LottoDTO(List.of(1, 2, 3, 8, 9, 10))
        );

        LottoResult result = new LottoResult(lottos, winningNumbers);
        double profitRate = result.calculateProfitRate(1000);

        assertThat(profitRate).isEqualTo(500.0);
    }

    @DisplayName("당첨이 없으면 수익률은 0이다")
    @Test
    void 당첨이_없으면_수익률은_0이다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        List<LottoDTO> lottos = List.of(
                new LottoDTO(List.of(8, 9, 10, 11, 12, 13))
        );

        LottoResult result = new LottoResult(lottos, winningNumbers);
        double profitRate = result.calculateProfitRate(1000);

        assertThat(profitRate).isEqualTo(0.0);
    }

    @DisplayName("1등 당첨시 수익률을 정확하게 계산한다")
    @Test
    void 일등_당첨시_수익률을_정확하게_계산한다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        List<LottoDTO> lottos = List.of(
                new LottoDTO(List.of(1, 2, 3, 4, 5, 6))
        );

        LottoResult result = new LottoResult(lottos, winningNumbers);
        double profitRate = result.calculateProfitRate(1000);
        assertThat(profitRate).isEqualTo(200_000_000.0);
    }

    @DisplayName("여러 등수가 섞인 경우 수익률을 정확하게 계산한다")
    @Test
    void 여러_등수가_섞인_경우_수익률을_정확하게_계산한다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        List<LottoDTO> lottos = List.of(
                new LottoDTO(List.of(1, 2, 3, 8, 9, 10)),
                new LottoDTO(List.of(8, 9, 10, 11, 12, 13))
        );

        LottoResult result = new LottoResult(lottos, winningNumbers);
        double profitRate = result.calculateProfitRate(2000);

        assertThat(profitRate).isEqualTo(250.0);
    }

    @DisplayName("당첨 내역에서 NONE은 포함되지 않는다")
    @Test
    void 당첨_내역에서_NONE은_포함되지_않는다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        List<LottoDTO> lottos = List.of(
                new LottoDTO(List.of(8, 9, 10, 11, 12, 13))
        );

        LottoResult result = new LottoResult(lottos, winningNumbers);
        Map<Rank, Integer> rankCounts = result.getRankCounts();

        assertThat(rankCounts).doesNotContainKey(Rank.NONE);
    }
}

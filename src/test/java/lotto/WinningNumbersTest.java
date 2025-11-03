package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import lotto.domain.Rank;
import lotto.domain.WinningNumbers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumbersTest {

    @DisplayName("당첨 번호는 6개여야 한다")
    @Test
    void 당첨_번호는_6개여야_한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5), 7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("당첨 번호가 7개면 예외가 발생한다")
    @Test
    void 당첨_번호가_7개면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6, 7), 8))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("당첨 번호는 1부터 45 사이여야 한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 46, 50, -1})
    void 당첨_번호_범위를_벗어나면_예외가_발생한다(int invalidNumber) {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, invalidNumber), 7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("보너스 번호는 1부터 45 사이여야 한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 46, 50, -1})
    void 보너스_번호_범위를_벗어나면_예외가_발생한다(int invalidBonusNumber) {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), invalidBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("보너스 번호는 당첨 번호와 중복될 수 없다")
    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("6개 모두 일치하면 1등이다")
    @Test
    void 여섯개_모두_일치하면_일등이다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);

        Rank rank = winningNumbers.match(lottoNumbers);

        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @DisplayName("5개 일치하고 보너스 번호가 일치하면 2등이다")
    @Test
    void 다섯개_일치하고_보너스_번호가_일치하면_이등이다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 7);

        Rank rank = winningNumbers.match(lottoNumbers);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("5개 일치하고 보너스 번호가 불일치하면 3등이다")
    @Test
    void 다섯개_일치하고_보너스_번호가_불일치하면_삼등이다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 8);

        Rank rank = winningNumbers.match(lottoNumbers);

        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @DisplayName("4개 일치하면 4등이다")
    @Test
    void 네개_일치하면_사등이다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 8, 9);

        Rank rank = winningNumbers.match(lottoNumbers);

        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("3개 일치하면 5등이다")
    @Test
    void 세개_일치하면_오등이다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        List<Integer> lottoNumbers = List.of(1, 2, 3, 8, 9, 10);

        Rank rank = winningNumbers.match(lottoNumbers);

        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("2개 이하 일치하면 당첨되지 않는다")
    @Test
    void 두개_이하_일치하면_당첨되지_않는다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        List<Integer> lottoNumbers = List.of(1, 2, 8, 9, 10, 11);

        Rank rank = winningNumbers.match(lottoNumbers);

        assertThat(rank).isEqualTo(Rank.NONE);
    }
}

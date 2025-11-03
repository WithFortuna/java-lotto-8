package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.Lotto;

class LottoTest {

    @DisplayName("로또 번호는 6개여야 한다")
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("로또 번호의 개수가 6개 미만이면 예외가 발생한다")
    @Test
    void 로또_번호의_개수가_6개_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("로또 번호는 1부터 45 사이의 숫자여야 한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 46, 50, -1})
    void 로또_번호_범위를_벗어나면_예외가_발생한다(int invalidNumber) {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, invalidNumber)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("로또 구입 금액은 1000원 단위여야 한다")
    @ParameterizedTest
    @ValueSource(ints = {1500, 2200, 999, 100})
    void 로또_구입_금액이_1000원_단위가_아니면_예외가_발생한다(int invalidCost) {
        assertThatThrownBy(() -> Lotto.createFromCost(invalidCost))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("로또 구입 금액에 따라 올바른 개수의 로또를 생성한다")
    @Test
    void 로또_구입_금액에_따라_올바른_개수의_로또를_생성한다() {
        int cost = 5000;
        List<Lotto> lottos = Lotto.createFromCost(cost);

        assertThat(lottos).hasSize(5);
    }

    @DisplayName("생성된 로또는 6개의 번호를 가진다")
    @Test
    void 생성된_로또는_6개의_번호를_가진다() {
        List<Lotto> lottos = Lotto.createFromCost(1000);
        Lotto lotto = lottos.get(0);

        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @DisplayName("로또 번호를 정상적으로 생성한다")
    @Test
    void 로또_번호를_정상적으로_생성한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }
}

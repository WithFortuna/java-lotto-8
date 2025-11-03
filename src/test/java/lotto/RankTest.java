package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.Rank;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("6개 일치시 1등이다")
    @Test
    void 여섯개_일치시_일등이다() {
        Rank rank = Rank.findRank(6, false);

        assertThat(rank).isEqualTo(Rank.FIRST);
        assertThat(rank.getPrize()).isEqualTo(2_000_000_000);
    }

    @DisplayName("5개 일치 + 보너스 일치시 2등이다")
    @Test
    void 다섯개_일치_보너스_일치시_이등이다() {
        Rank rank = Rank.findRank(5, true);

        assertThat(rank).isEqualTo(Rank.SECOND);
        assertThat(rank.getPrize()).isEqualTo(30_000_000);
    }

    @DisplayName("5개 일치시 3등이다")
    @Test
    void 다섯개_일치시_삼등이다() {
        Rank rank = Rank.findRank(5, false);

        assertThat(rank).isEqualTo(Rank.THIRD);
        assertThat(rank.getPrize()).isEqualTo(1_500_000);
    }

    @DisplayName("4개 일치시 4등이다")
    @Test
    void 네개_일치시_사등이다() {
        Rank rank = Rank.findRank(4, false);

        assertThat(rank).isEqualTo(Rank.FOURTH);
        assertThat(rank.getPrize()).isEqualTo(50_000);
    }

    @DisplayName("3개 일치시 5등이다")
    @Test
    void 세개_일치시_오등이다() {
        Rank rank = Rank.findRank(3, false);

        assertThat(rank).isEqualTo(Rank.FIFTH);
        assertThat(rank.getPrize()).isEqualTo(5_000);
    }

    @DisplayName("2개 이하 일치시 당첨되지 않는다")
    @Test
    void 두개_이하_일치시_당첨되지_않는다() {
        Rank rank = Rank.findRank(2, false);

        assertThat(rank).isEqualTo(Rank.NONE);
        assertThat(rank.getPrize()).isEqualTo(0);
    }

    @DisplayName("1등은 당첨이다")
    @Test
    void 일등은_당첨이다() {
        assertThat(Rank.FIRST.isWinning()).isTrue();
    }

    @DisplayName("NONE은 당첨이 아니다")
    @Test
    void NONE은_당첨이_아니다() {
        assertThat(Rank.NONE.isWinning()).isFalse();
    }

    @DisplayName("2등은 보너스 번호가 필요하다")
    @Test
    void 이등은_보너스_번호가_필요하다() {
        assertThat(Rank.SECOND.isRequireBonus()).isTrue();
    }

    @DisplayName("1등은 보너스 번호가 필요하지 않다")
    @Test
    void 일등은_보너스_번호가_필요하지_않다() {
        assertThat(Rank.FIRST.isRequireBonus()).isFalse();
    }
}

package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import camp.nextstep.edu.missionutils.Randoms;

public class Lotto {
	private final List<Integer> numbers;

	public Lotto(List<Integer> numbers) {
		validate(numbers);
		this.numbers = numbers;
	}

	public static List<Lotto> createFromCost(int cost) {
		int theNumberOfLotto = cost / 1000;

		List<Lotto> lottos = new ArrayList<>();

		for (int i = 0; i < theNumberOfLotto; i++) {
			TreeSet<Integer> numbers = new TreeSet<>();
			while (numbers.size() < 6) {
				numbers.add(Randoms.pickNumberInRange(1, 45));
			}
			lottos.add(new Lotto(List.copyOf(numbers)));
		}

		return lottos;
	}

	private void validate(List<Integer> numbers) {
		checkLottoSize(numbers);
		checkLottoElements(numbers);
	}

	private static void checkLottoElements(List<Integer> numbers) {
		for (int number : numbers) {
			checkNumberRange(number);
		}
	}

	private static void checkNumberRange(int number) {
		if (number < 1 || number > 45) {
			throw new IllegalArgumentException("[ERROR] 로또의 숫자 범위는 1~45 입니다");
		}
	}

	private static void checkLottoSize(List<Integer> numbers) {
		if (numbers.size() != 6) {
			throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
		}
	}

	public List<Integer> getNumbers() {
		return List.copyOf(numbers);
	}
}

package lotto.domain;

import java.util.List;

public class WinningNumbers {
	private final List<Integer> numbers;
	private final int bonusNumber;

	public WinningNumbers(List<Integer> numbers, int bonusNumber) {
		validate(numbers, bonusNumber);
		this.numbers = numbers;
		this.bonusNumber = bonusNumber;
	}

	private void validate(List<Integer> numbers, int bonusNumber) {
		checkWinningNumbersSize(numbers);
		checkNumberRange(numbers);
		checkBonusNumberRange(bonusNumber);
		checkDuplication(numbers, bonusNumber);
	}

	private void checkWinningNumbersSize(List<Integer> numbers) {
		if (numbers.size() != 6) {
			throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
		}
	}

	private void checkNumberRange(List<Integer> numbers) {
		for (int number : numbers) {
			if (number < 1 || number > 45) {
				throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
			}
		}
	}

	private void checkBonusNumberRange(int bonusNumber) {
		if (bonusNumber < 1 || bonusNumber > 45) {
			throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
		}
	}

	private void checkDuplication(List<Integer> numbers, int bonusNumber) {
		if (numbers.contains(bonusNumber)) {
			throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
		}
	}

	public Rank match(List<Integer> numbers) {
		int matchCount = countMatchingNumbers(numbers);
		boolean bonusMatch = isBonusMatch(numbers);
		return Rank.findRank(matchCount, bonusMatch);
	}

	private int countMatchingNumbers(List<Integer> lottoNumbers) {
		return (int) lottoNumbers.stream()
			.filter(numbers::contains)
			.count();
	}

	private boolean isBonusMatch(List<Integer> lottoNumbers) {
		return lottoNumbers.contains(bonusNumber);
	}
}

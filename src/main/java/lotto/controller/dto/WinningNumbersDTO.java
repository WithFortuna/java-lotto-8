package lotto.controller.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record WinningNumbersDTO(
	List<Integer> numbers,
	int bonusNumber
) {
	public static WinningNumbersDTO from(String numbersInput, String bonusInput) {
		List<Integer> numbers = parseWinningNumbers(numbersInput);
		int bonusNumber = parseBonusNumber(bonusInput);
		return new WinningNumbersDTO(numbers, bonusNumber);
	}

	private static List<Integer> parseWinningNumbers(String input) {
		validateNotEmpty(input);
		String[] numbers = input.split(",");
		validateNumbers(numbers);

		return Arrays.stream(numbers)
			.map(String::trim)
			.map(WinningNumbersDTO::parseNumber)
			.collect(Collectors.toList());
	}

	private static String[] validateNumbers(String... numbers) {
		Arrays.asList(numbers)
			.forEach(WinningNumbersDTO::validateNotEmpty);
		return numbers;
	}

	private static int parseBonusNumber(String input) {
		validateNotEmpty(input);
		return parseNumber(input.trim());
	}

	private static void validateNotEmpty(String input) {
		if (input == null || input.trim().isEmpty()) {
			throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
		}
	}

	private static int parseNumber(String number) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("[ERROR] 숫자 형식이 올바르지 않습니다.");
		}
	}
}

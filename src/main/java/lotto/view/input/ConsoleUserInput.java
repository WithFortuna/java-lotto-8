package lotto.view.input;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleUserInput{
	public String payForLotto() {
		System.out.println("구입금액을 입력해 주세요.");
		String cost = Console.readLine();

		return cost;
	}
}

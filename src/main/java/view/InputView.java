package view;

import domains.Lotto;
import domains.LottoNumber;
import domains.Money;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Money inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");

        int value = Integer.parseInt(scanner.nextLine());
        return new Money(value);
    }

    public static Lotto inputWinningNumbers() throws IllegalArgumentException {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        List<LottoNumber> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        return new Lotto(numbers);
    }

    public static LottoNumber inputBonusNumber(Lotto winningLotto) throws IllegalArgumentException {
        System.out.println("보너스 볼을 입력해 주세요.");
        int number = Integer.parseInt(scanner.nextLine());
        LottoNumber bonusNumber = new LottoNumber(number);

        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }

        return bonusNumber;
    }
}

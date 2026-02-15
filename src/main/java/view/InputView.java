package view;

import domains.Lotto;
import domains.LottoCount;
import domains.LottoNumber;
import domains.Money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Money inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return new Money(inputInteger());
    }

    public static LottoCount inputManualCount(Money money) {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return money.toLottoCount(inputInteger());
    }

    public static Lotto inputManualLotto() {
        return parseLottoNumbers(inputString());
    }

    public static Lotto inputWinningNumbers() throws IllegalArgumentException {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return parseLottoNumbers(inputString());
    }

    private static Lotto parseLottoNumbers(String input) {
        List<LottoNumber> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::from)
                .collect(Collectors.toList());

        return new Lotto(numbers);
    }

    public static LottoNumber inputBonusNumber(Lotto winningLotto) throws IllegalArgumentException {
        System.out.println("보너스 볼을 입력해 주세요.");
        LottoNumber bonusNumber = new LottoNumber(inputInteger());

        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }

        return bonusNumber;
    }

    public static Integer inputInteger() {
        return Integer.parseInt(scanner.nextLine());
    }

    public static String inputString() {
        return scanner.nextLine();
    }
}

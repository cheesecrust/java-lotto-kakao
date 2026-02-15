package domains;

import controller.WinningLotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final Integer LOTTO_SIZE = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);

        this.numbers = new ArrayList<>(numbers.stream()
                .sorted()
                .toList());
    }

    public Lotto(int... numbers) {
        this(toLottoNumberList(numbers));
    }

    private static List<LottoNumber> toLottoNumberList(int[] numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList());
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        long distinctCount = numbers.stream()
                .distinct()
                .count();

        if (distinctCount != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public Boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lotto that)) {
            return false;
        }
        return this.numbers.equals(that.numbers);
    }

    @Override
    public int hashCode() {
        return numbers.hashCode();
    }

    public Rank match(WinningLotto winningLotto) {
        int matchCount = countMatches(winningLotto.getWinningNumbers());
        Boolean matchBonus = contains(winningLotto.getBonusNumber());

        Rank rank = Rank.mainMatch(matchCount);
        return Rank.bonusMatch(rank, matchBonus);
    }

    public int countMatches(Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::contains)
                .count();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
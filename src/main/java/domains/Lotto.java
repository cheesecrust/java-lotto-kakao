package domains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final ArrayList<LottoNumber> numbers;
    private static final int LOTTO_SIZE = 6;

    public Lotto(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);

        this.numbers = (ArrayList<LottoNumber>) numbers.stream()
                .sorted()
                .toList();
    }

    public Lotto(int... numbers) {
        this(toLottoNumberList(numbers));
    }

    private static List<LottoNumber> toLottoNumberList(int[] numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
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

    public boolean contains(LottoNumber number) {
        return Arrays.asList(numbers).contains(number);
    }

    public ArrayList<LottoNumber> getNumbers() {
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lotto)) return false;
        Lotto that = (Lotto) o;
        return this.numbers.equals(that.numbers);
    }

    @Override
    public int hashCode() {
        return numbers.hashCode();
    }

    public Rank match(Lotto winningLotto, LottoNumber bonusNumber) {
        int matchCount = countMatches(winningLotto);
        boolean matchBonus = contains(bonusNumber);

        return Rank.valueOf(matchCount, matchBonus);
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
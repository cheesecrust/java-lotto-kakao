package domains;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private LottoNumber[] numbers;
    private static final int LOTTO_SIZE = 6;

    public Lotto(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);

        this.numbers = numbers.stream()
                .sorted()
                .toArray(LottoNumber[]::new);
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

    public LottoNumber[] getNumbers() {
        return Arrays.copyOf(numbers, numbers.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lotto)) return false;
        Lotto that = (Lotto) o;
        return Arrays.equals(this.numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(numbers);
    }

    public Rank match(Lotto winningLotto, LottoNumber bonusNumber) {
        int matchCount = countMatches(winningLotto);
        boolean matchBonus = contains(bonusNumber);

        return Rank.valueOf(matchCount, matchBonus);
    }

    // 배열을 스트림으로 변환하여 매칭 개수 계산
    public int countMatches(Lotto winningLotto) {
        return (int) Arrays.stream(numbers)
                .filter(winningLotto::contains)
                .count();
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers);
    }
}
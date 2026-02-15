package domains;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final List<LottoNumber> ALL_NUMBERS = IntStream.rangeClosed(MIN, MAX)
            .mapToObj(LottoNumber::new)
            .toList();
    private final Integer number;

    public LottoNumber(int number) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException("로또 번호는 %d~%d사이여야 합니다.".formatted(MIN, MAX));
        }
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber)) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }

    @Override
    public String toString() {
        return number.toString();
    }

    public static LottoNumber from(int number) {
        int idx = number - 1;
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException("로또 번호는 1~45사이여야 합니다.");
        }
        return ALL_NUMBERS.get(idx);
    }

    public static List<LottoNumber> getNumbers() {
        return new ArrayList<>(ALL_NUMBERS);
    }
}

package domains;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    static final List<LottoNumber> ALL_NUMBERS = IntStream.rangeClosed(1, 45)
            .mapToObj(LottoNumber::new)
            .toList();
    private final Integer number;

    public LottoNumber(int number) {
        if (number < ALL_NUMBERS.getFirst().getNumber() || number > ALL_NUMBERS.getLast().getNumber()) {
            throw new IllegalArgumentException("로또 번호는 1~45사이여야 합니다.");
        }
        this.number = number;
    }

    public Integer getNumber() {
        return number;
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
}

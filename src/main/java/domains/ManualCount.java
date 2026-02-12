package domains;

public class ManualCount {
    private final Integer count;

    public ManualCount(Integer availableLottoCount, Integer count) {
        if (availableLottoCount < count) throw new IllegalArgumentException("원래의 금액을 넘길 수 없습니다.");
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }
}

package domains;

import java.util.List;

public class Money {
    private final int amount;
    private static final int LOTTO_COST = 1000;

    public Money(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("돈은 음수일 수 없습니다.");
        }
        this.amount = amount;
    }

    public int availableLottoCount() {
        return amount / LOTTO_COST;
    }

    public Float calculateRate(List<Rank> rankList) {
        if (amount == 0) return (float) 0;

        long totalWinningMoney = rankList.stream()
                .mapToLong(Rank::getWinningMoney)
                .sum();

        return (float) totalWinningMoney / amount;
    }
}

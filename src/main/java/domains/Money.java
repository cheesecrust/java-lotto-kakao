package domains;

import java.util.List;

public class Money {
    private final int amount;
    private static final int LOTTO_COST = 1000;

    public Money(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("돈은 음수일 수 없습니다.");
        }
        if (amount < LOTTO_COST) {
            throw new IllegalArgumentException("돈은 " + LOTTO_COST + "이상이여야 합니다.");
        }
        this.amount = amount;
    }

    public int availableLottoCount() {
        return amount / LOTTO_COST;
    }

    public float calculateRate(List<Rank> rankList) {

        long totalWinningMoney = rankList.stream()
                .mapToLong(Rank::getWinningMoney)
                .sum();

        return (float) totalWinningMoney / amount;
    }
}

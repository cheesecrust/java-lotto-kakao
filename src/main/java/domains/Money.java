package domains;

import controller.LottoCount;

import java.util.List;

public class Money {
    private static final Integer LOTTO_COST = 1000;
    private final Integer amount;

    public Money(Integer amount) {
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

    public LottoCount toLottoCount(int manualCount) {
        return new LottoCount(manualCount, availableLottoCount());
    }

    public Double calculateRate(List<Rank> rankList) {

        Long totalWinningMoney = rankList.stream()
                .mapToLong(Rank::getWinningMoney)
                .sum();

        return totalWinningMoney.doubleValue() / amount.doubleValue();
    }
}

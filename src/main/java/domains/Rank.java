package domains;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private final Integer countOfMatch;
    private final Integer winningMoney;

    Rank(Integer countOfMatch, Integer winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public static Rank valueOf(Integer countOfMatch, Boolean matchBonus) {
        if (countOfMatch < 3) {
            return MISS;
        }

        if (countOfMatch.equals(SECOND.countOfMatch) && matchBonus) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount(countOfMatch) && rank != SECOND)
                .findFirst()
                .orElse(MISS);
    }

    private boolean matchCount(Integer countOfMatch) {
        return this.countOfMatch.equals(countOfMatch);
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }
}

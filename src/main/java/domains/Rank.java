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

    public static Rank mainMatch(Integer countOfMatch) {
        if (countOfMatch < 3) {
            return MISS;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount(countOfMatch) && rank != SECOND)
                .findFirst()
                .orElse(MISS);
    }

    public static Rank bonusMatch(Rank rank, Boolean matchBonus) {
        if (rank.equals(THIRD) && matchBonus) {
            return SECOND;
        }
        return rank;
    }

    private Boolean matchCount(Integer countOfMatch) {
        return this.countOfMatch.equals(countOfMatch);
    }

    public Integer getCountOfMatch() {
        return countOfMatch;
    }

    public Integer getWinningMoney() {
        return winningMoney;
    }
}

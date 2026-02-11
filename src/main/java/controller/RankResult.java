package controller;

import domains.Rank;

import java.util.List;

public class RankResult {
    private final List<Rank> ranks;
    private final Double rate;

    public RankResult(List<Rank> ranks, Double rate) {
        this.ranks = ranks;
        this.rate = rate;
    }

    public List<Rank> getRanks() {
        return ranks;
    }

    public Double getRate() {
        return rate;
    }
}

package view;

import domains.Lotto;
import domains.Rank;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OutputView {

    public static void printLottos(List<Lotto> lottos) {
        System.out.println(lottos.size()+"개를 구매했습니다.");
        lottos.forEach(System.out::println);
    }

    public static void printWinning(List<Rank> ranks) {
        System.out.println("당첨 통계\n---------");
        Map<Rank, Long> counts = countRanks(ranks);

        List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)
                .forEach(rank -> printEachRank(rank, counts));
    }

    private static Map<Rank, Long> countRanks(List<Rank> ranks) {
        return ranks.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static void printEachRank(Rank rank, Map<Rank, Long> counts) {
        long count = counts.getOrDefault(rank, 0L);

        if (rank == Rank.SECOND) {
            printSecondRank(rank, count);
            return;
        }

        printGeneralRank(rank, count);
    }

    private static void printSecondRank(Rank rank, long count) {
        System.out.printf("5개 일치, 보너스 볼 일치(%d원) - %d개%n",
                rank.getWinningMoney(), count);
    }

    private static void printGeneralRank(Rank rank, long count) {
        System.out.printf("%d개 일치 (%d원)- %d개%n",
                rank.getCountOfMatch(), rank.getWinningMoney(), count);
    }

    public static void printRate(float rate) {
        System.out.printf("총 수익률은 %f입니다.%n", rate);
    }
}

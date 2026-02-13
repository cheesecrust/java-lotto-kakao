package view;

import domains.Lotto;
import domains.Rank;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OutputView {

    public static void printLottos(int manualCount, List<Lotto> lottos) {
        int autoCount = lottos.size() - manualCount;
        System.out.println("\n수동으로 " + manualCount + "장, 자동으로 " + autoCount + "장을 구매했습니다.");
        lottos.forEach(System.out::println);
    }

    public static void printManualComment() {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
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
        Long count = counts.getOrDefault(rank, 0L);

        if (rank == Rank.SECOND) {
            printSecondRank(rank, count);
            return;
        }

        printGeneralRank(rank, count);
    }

    private static void printSecondRank(Rank rank, Long count) {
        System.out.printf("5개 일치, 보너스 볼 일치(%d원) - %d개%n",
                rank.getWinningMoney(), count);
    }

    private static void printGeneralRank(Rank rank, Long count) {
        System.out.printf("%d개 일치 (%d원)- %d개%n",
                rank.getCountOfMatch(), rank.getWinningMoney(), count);
    }

    public static void printRate(Double rate) {
        System.out.printf("총 수익률은 %.2f입니다.%n", Math.floor(rate * 100) / 100);
    }
}

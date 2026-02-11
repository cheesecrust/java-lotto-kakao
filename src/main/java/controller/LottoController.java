package controller;

import domains.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class LottoController {
    private static final Integer RETRY_ATTEMPT = 10;

    public static void run() {
        try {
            Money userMoney = retry(InputView::inputMoney);
            LottoTickets lottoTickets = new LottoTickets(userMoney);
            OutputView.printLottos(lottoTickets.getLottos());

            Lotto winningLotto = retry(InputView::inputWinningNumbers);
            LottoNumber bonusNumber = retry(() -> InputView.inputBonusNumber(winningLotto));

            RankResult result = execute(lottoTickets, winningLotto, bonusNumber, userMoney);

            OutputView.printWinning(result.getRanks());
            OutputView.printRate(result.getRate());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            System.out.println("로또가 종료되었습니다.");
        }
    }

    public static RankResult execute(LottoTickets lottoTickets, Lotto winningLotto, LottoNumber bonusNumber, Money userMoney) {
        List<Rank> ranks = lottoTickets.match(winningLotto, bonusNumber);
        Double rate = userMoney.calculateRate(ranks);
        return new RankResult(ranks, rate);
    }

    private static <T> T retry(Supplier<T> supplier) {
        int attempt = 0;
        while (attempt++ < RETRY_ATTEMPT) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
        throw new IllegalStateException("최대 재시도 횟수를 초과했습니다.");
    }
}

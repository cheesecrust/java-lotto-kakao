package controller;

import domains.Lotto;
import domains.LottoNumber;
import domains.LottoTickets;
import domains.Money;
import domains.Rank;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class LottoController {
    public static void run() {
        Money userMoney = retry(InputView::inputMoney);
        LottoTickets lottoTickets = new LottoTickets(userMoney);

        OutputView.printLottos(lottoTickets.getLottos());
        Lotto winningLotto = retry(InputView::inputWinningNumbers);
        LottoNumber bonusNumber = retry(() -> InputView.inputBonusNumber(winningLotto));

        List<Rank> ranks = lottoTickets.match(winningLotto, bonusNumber);
        OutputView.printWinning(ranks);
        OutputView.printRate(userMoney.calculateRate(ranks));
    }

    private static <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }
}

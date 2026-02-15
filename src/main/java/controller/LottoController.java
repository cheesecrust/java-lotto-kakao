package controller;

import domains.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class LottoController {
    private static final Integer RETRY_ATTEMPT = 10;

    private final Generator generator;

    public LottoController(Generator generator) {
        this.generator = generator;
    }

    public void run() {
        try {
            Money userMoney = retry(InputView::inputMoney);

            LottoCount lottoCount = retry(() -> InputView.inputManualCount(userMoney));

            OutputView.printManualComment();
            List<Lotto> manualLottos = new ArrayList<>();
            for (int i = 0; i < lottoCount.getManualCount(); i++) {
                manualLottos.add(retry(InputView::inputManualLotto));
            }

            LottoTickets lottoTickets = new LottoTickets(manualLottos, lottoCount.getAutoCount(), generator);
            OutputView.printLottos(lottoCount.getManualCount(), lottoTickets.getLottos());

            Lotto winningLotto = retry(InputView::inputWinningNumbers);
            LottoNumber bonusNumber = retry(InputView::inputBonusNumber);
            WinningLotto winning = retry(() -> new WinningLotto(winningLotto, bonusNumber));

            RankResult result = execute(userMoney, lottoTickets, winning);

            OutputView.printWinning(result.getRanks());
            OutputView.printRate(result.getRate());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            System.out.println("로또가 종료되었습니다.");
        }
    }

    public RankResult execute(Money userMoney, LottoTickets lottoTickets, WinningLotto winningLotto) {
        List<Rank> ranks = lottoTickets.match(winningLotto);
        Double rate = userMoney.calculateRate(ranks);
        return new RankResult(ranks, rate);
    }

    private <T> T retry(Supplier<T> supplier) {
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

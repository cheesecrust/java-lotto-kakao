package domains;

import controller.WinningLotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.stream.Collectors;

public class LottoTickets {
    private final List<Lotto> lottos;

    public LottoTickets(List<Lotto> manualLottos, int autoCount, Generator lottoGenerator) {
        lottos = new ArrayList<>(manualLottos);
        for (int i = 0; i < autoCount; i++) {
            lottos.add(lottoGenerator.generate());
        }
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public List<Rank> match(WinningLotto winningLotto) {
        return lottos.stream()
                .map(lotto -> lotto.match(winningLotto))
                .collect(Collectors.toList());
    }
}

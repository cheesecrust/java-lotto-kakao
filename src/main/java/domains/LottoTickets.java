package domains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.stream.Collectors;

public class LottoTickets {
    private final List<Lotto> lottos;

    public LottoTickets(Money money) {
        int count = money.availableLottoCount();

        lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(LottoGenerator.randomGenerate());
        }
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public List<Rank> match(Lotto winningLotto, LottoNumber bonusNumber) {
        return lottos.stream()
                .map(lotto -> lotto.match(winningLotto, bonusNumber))
                .collect(Collectors.toList());
    }
}

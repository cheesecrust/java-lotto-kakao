package domains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTickets {
    private final List<Lotto> lottos;
    private static final List<LottoNumber> ALL_NUMBERS = IntStream.rangeClosed(1, 45)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toList());

    public LottoTickets(Money money) {
        int count = money.availableLottoCount();

        lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generateLotto());
        }
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    private Lotto generateLotto() {
        List<LottoNumber> numbers = new ArrayList<>(ALL_NUMBERS);

        Collections.shuffle(numbers);

        List<LottoNumber> result = new ArrayList<>(numbers.subList(0, 6));
        return new Lotto(result);
    }

    public List<Rank> match(Lotto winningLotto, LottoNumber bonusNumber) {
        return lottos.stream()
                .map(lotto -> lotto.match(winningLotto, bonusNumber))
                .collect(Collectors.toList());
    }
}

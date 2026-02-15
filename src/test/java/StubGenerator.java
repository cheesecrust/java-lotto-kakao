import domains.Generator;
import domains.Lotto;

import java.util.List;

public class StubGenerator implements Generator {
    private final List<Lotto> lottos;
    private int index = 0;

    public StubGenerator(Lotto... lottos) {
        this.lottos = List.of(lottos);
    }

    @Override
    public Lotto generate() {
        return lottos.get(index++);
    }
}

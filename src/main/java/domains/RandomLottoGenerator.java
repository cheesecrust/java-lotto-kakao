package domains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoGenerator implements LottoGenerator {
    private final static int FROM_INDEX = 0;
    private final static int TO_INDEX = 6;

    @Override
    public Lotto generate() {
        List<LottoNumber> numbers = new ArrayList<>(LottoNumber.getAllNumbers());
        Collections.shuffle(numbers);
        return new Lotto(numbers.subList(FROM_INDEX, TO_INDEX));
    }
}

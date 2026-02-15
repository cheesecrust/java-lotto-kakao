package domains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator implements Generator {
    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 6;

    @Override
    public Lotto generate() {
        List<LottoNumber> numbers = LottoNumber.getNumbers();
        Collections.shuffle(numbers);
        return new Lotto(numbers.subList(FROM_INDEX, TO_INDEX));
    }
}

package domains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator implements Generator {
    private static final Integer FROM_INDEX = 0;
    private static final Integer TO_INDEX = 6;

    @Override
    public Lotto randomGenerate() {
        List<LottoNumber> numbers = new ArrayList<>(LottoNumber.getNumber());
        Collections.shuffle(numbers);
        return new Lotto(numbers.subList(FROM_INDEX, TO_INDEX));
    }
}

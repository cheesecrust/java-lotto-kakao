package domains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static domains.LottoNumber.ALL_NUMBERS;

public class LottoGenerator {
    public static Lotto randomGenerate() {
        List<LottoNumber> numbers = new ArrayList<>(ALL_NUMBERS);
        Collections.shuffle(numbers);
        return new Lotto(numbers.subList(0, 6));
    }
}

import domains.LottoNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LottoNumberTest {
    @Test
    public void 숫자가_1에서_45까지만_가능한지_검증한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber number = new LottoNumber(-1);
        });
    }

    @Test
    public void 로또_번호가_같은지_검증한다() {
        LottoNumber lottoNumber1 = new LottoNumber(1);
        LottoNumber lottoNumber2 = new LottoNumber(1);
        assertEquals(lottoNumber2, lottoNumber1);
    }

    @Test
    public void 로또_번호가_다른지_검증한다() {
        LottoNumber lottoNumber1 = new LottoNumber(1);
        LottoNumber lottoNumber2 = new LottoNumber(2);
        assertNotEquals(lottoNumber2, lottoNumber1);
    }
}

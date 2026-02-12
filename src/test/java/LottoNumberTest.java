import domains.Lotto;
import domains.LottoNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LottoNumberTest {
    @Test
    public void 숫자가_1에서_45까지만_가능한지_검증한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoNumber(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoNumber(46);
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

    @Test
    public void 범위를_넘은_번호는_객체변환_실패한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber.from(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber.from(46);
        });
    }

    @Test
    public void 비교_객체타입_다를_경우_실패() {
        LottoNumber lottoNumber = new LottoNumber(1);
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
        assertNotEquals(lottoNumber, lotto);
    }

    @Test
    public void of_변환_테스트() {
        LottoNumber lottoNumber = new LottoNumber(1);
        assertEquals(lottoNumber, LottoNumber.from(1));
    }

    @Test
    public void to_String_test() {
        LottoNumber lottoNumber = new LottoNumber(1);
        assertEquals("1", lottoNumber.toString());
    }
}

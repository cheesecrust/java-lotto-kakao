import domains.Lotto;
import domains.LottoNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class LottoTest {
    @Test
    public void 번호가_6개가_아닌경우_에러를_반환한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(1, 2, 3, 4, 5);
        });
    }

    @Test
    public void 번호가_중복되지_않는지_검증한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(1, 2, 2, 4, 5, 6);
        });
    }

    @Test
    public void 번호가_포함되어_있는지_확인할_수_있다() {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
        LottoNumber number = new LottoNumber(2);

        assertTrue(lotto.contains(number));
    }

    @ParameterizedTest
    @CsvSource({
            "6,5,4,3,2,1",
            "3,1,2,6,5,4",
            "2,1,4,3,6,5"
    })
    public void 로또는_오름차순이어야_한다(int n1, int n2, int n3, int n4, int n5, int n6) {
        Lotto lotto1 = new Lotto(n1, n2, n3, n4, n5, n6);
        Lotto lotto2 = new Lotto(1, 2, 3, 4, 5, 6);

        assertEquals(lotto1, lotto2);
    }

    @Test
    public void 비교_객체타입_다를_경우_실패() {
        LottoNumber lottoNumber = new LottoNumber(1);
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
        assertNotEquals(lotto, lottoNumber);
    }

    @Test
    void 같은_객체_참조이면_equals는_true를_반환한다() {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
        boolean result = lotto.equals(lotto);
        assertTrue(result);
    }

    @Test
    public void to_String_test() {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
        assertEquals("[1, 2, 3, 4, 5, 6]", lotto.toString());
    }
}

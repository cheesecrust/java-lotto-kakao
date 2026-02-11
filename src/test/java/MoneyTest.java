import domains.Money;
import domains.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domains.Rank.FIRST;
import static domains.Rank.SECOND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Test
    public void 돈이_들어오면_로또를_몇장_살_수_있는지_반환한다() {
        Money money = new Money(14243);
        assertEquals(14, money.availableLottoCount());
    }

    @Test
    public void 총당첨금을_받아_수익률을_계산합니다() {
        int value = 14000;
        Money money = new Money(value);
        List<Rank> rankList = List.of(FIRST, SECOND);
        int sum = FIRST.getWinningMoney() + SECOND.getWinningMoney();
        assertEquals((float) sum / value, money.calculateRate(rankList));
    }

    @Test
    public void 초기자금이_1000원_미만이면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> new Money(999));
    }
}

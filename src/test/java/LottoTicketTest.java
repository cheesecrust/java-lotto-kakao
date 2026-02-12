import domains.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static domains.Rank.FIFTH;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoTicketTest {
    private Money money;
    private LottoTickets lottoTickets;

    @BeforeEach
    public void setUP() {
        money = new Money(14000);
        LottoGenerator lottoGenerator = new RandomLottoGenerator();
        lottoTickets = new LottoTickets(money, lottoGenerator);
    }

    @Test
    public void 로또_번호들이_중복되지_않는지_검증한다() {
        List<Lotto> lottos = lottoTickets.getLottos();
        assertEquals(lottos.size(), new HashSet<>(lottos).size());
    }

    @Test
    public void 로또_번호가_3개_동일했을_때_5등이다() {
        Lotto winningLotto = new Lotto(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(1, 2, 3, 9, 10, 11);
        assertEquals(3, FIFTH.getCountOfMatch());
        assertEquals(Rank.FIFTH, lotto.match(winningLotto, new LottoNumber(44)));
    }

    @Test
    public void 로또_번호가_5개_동일하고_보너스가_맞았을때_2등이다() {
        Lotto winningLotto = new Lotto(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 11);
        assertEquals(Rank.SECOND, lotto.match(winningLotto, new LottoNumber(11)));
    }

    @Test
    public void 로또_번호가_5개_동일하고_보너스가_틀리면_3등이다() {
        Lotto winningLotto = new Lotto(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 11);
        assertEquals(Rank.THIRD, lotto.match(winningLotto, new LottoNumber(10)));
    }

    @Test
    public void 로또_리스트가_주어졌을때_결과리스트_반환(){
        Lotto winningLotto = new Lotto(1, 2, 3, 4, 5, 6);
        LottoNumber bonusNumber = LottoNumber.from(7);

        List<Rank> result = lottoTickets.match(winningLotto, bonusNumber);
        assertEquals(money.availableLottoCount(), result.size());
    }
}

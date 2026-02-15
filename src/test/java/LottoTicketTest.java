import controller.WinningLotto;
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
        Generator generator = new LottoGenerator();
        lottoTickets = new LottoTickets(List.of(), money.availableLottoCount(), generator);
    }

    @Test
    public void 로또_번호들이_중복되지_않는지_검증한다() {
        List<Lotto> lottos = lottoTickets.getLottos();
        assertEquals(lottos.size(), new HashSet<>(lottos).size());
    }

    @Test
    public void 로또_번호가_3개_동일했을_때_5등이다() {
        WinningLotto winningLotto = new WinningLotto(new Lotto(1, 2, 3, 4, 5, 6), new LottoNumber(44));
        Lotto lotto = new Lotto(1, 2, 3, 9, 10, 11);
        assertEquals(3, FIFTH.getCountOfMatch());
        assertEquals(Rank.FIFTH, lotto.match(winningLotto));
    }

    @Test
    public void 로또_번호가_5개_동일하고_보너스가_맞았을때_2등이다() {
        WinningLotto winningLotto = new WinningLotto(new Lotto(1, 2, 3, 4, 5, 6), new LottoNumber(11));
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 11);
        assertEquals(Rank.SECOND, lotto.match(winningLotto));
    }

    @Test
    public void 로또_번호가_5개_동일하고_보너스가_틀리면_3등이다() {
        WinningLotto winningLotto = new WinningLotto(new Lotto(1, 2, 3, 4, 5, 6), new LottoNumber(10));
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 11);
        assertEquals(Rank.THIRD, lotto.match(winningLotto));
    }

    @Test
    public void 로또_리스트가_주어졌을때_결과리스트_반환(){
        WinningLotto winningLotto = new WinningLotto(new Lotto(1, 2, 3, 4, 5, 6), LottoNumber.from(7));

        List<Rank> result = lottoTickets.match(winningLotto);
        assertEquals(money.availableLottoCount(), result.size());
    }

    @Test
    public void 수동_로또와_자동_로또가_합산된다() {
        Lotto manualLotto = new Lotto(1, 2, 3, 4, 5, 6);
        Generator generator = new LottoGenerator();
        LottoTickets tickets = new LottoTickets(List.of(manualLotto), 2, generator);

        assertEquals(3, tickets.getLottos().size());
        assertEquals(manualLotto, tickets.getLottos().get(0));
    }
}

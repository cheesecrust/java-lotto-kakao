import controller.WinningLotto;
import domains.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domains.Rank.FIFTH;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoTicketTest {

    @Test
    public void 로또_번호들이_중복되지_않는지_검증한다() {
        Generator generator = new StubGenerator(
                new Lotto(1, 2, 3, 4, 5, 6),
                new Lotto(7, 8, 9, 10, 11, 12)
        );
        LottoTickets lottoTickets = new LottoTickets(List.of(), 2, generator);

        List<Lotto> lottos = lottoTickets.getLottos();
        assertEquals(2, lottos.size());
        assertEquals(new Lotto(1, 2, 3, 4, 5, 6), lottos.get(0));
        assertEquals(new Lotto(7, 8, 9, 10, 11, 12), lottos.get(1));
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
    public void 로또_리스트가_주어졌을때_결과리스트_반환() {
        Generator generator = new StubGenerator(
                new Lotto(10, 11, 12, 13, 14, 15),
                new Lotto(20, 21, 22, 23, 24, 25)
        );
        LottoTickets lottoTickets = new LottoTickets(List.of(), 2, generator);
        WinningLotto winningLotto = new WinningLotto(new Lotto(1, 2, 3, 4, 5, 6), LottoNumber.from(7));

        List<Rank> result = lottoTickets.match(winningLotto);
        assertEquals(2, result.size());
    }

    @Test
    public void 수동_로또와_자동_로또가_합산된다() {
        Lotto manualLotto = new Lotto(1, 2, 3, 4, 5, 6);
        Generator generator = new StubGenerator(
                new Lotto(7, 8, 9, 10, 11, 12),
                new Lotto(13, 14, 15, 16, 17, 18)
        );
        LottoTickets tickets = new LottoTickets(List.of(manualLotto), 2, generator);

        assertEquals(3, tickets.getLottos().size());
        assertEquals(manualLotto, tickets.getLottos().get(0));
    }
}

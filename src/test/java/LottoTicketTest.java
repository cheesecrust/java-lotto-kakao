import domains.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoTicketTest {
    private Money money;
    private LottoTickets lottoTickets;
    @BeforeEach
    public void setUP() {
        money = new Money(14000);
        lottoTickets = new LottoTickets(money);
    }


    @Test
    public void 로또_번호들이_중복되지_않는지_검증한다() {
        List<Lotto> lottos = lottoTickets.getLottos();
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
        assertEquals(lottos.size(), new HashSet<>(lottos).size());
    }

    @Test
    public void 로또_번호가_3개_동일했을_때_5등이다() {
        Lotto winningLotto = new Lotto(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(1, 2, 3, 9, 10, 11);

        assertEquals(Rank.FIFTH, lotto.match(winningLotto, new LottoNumber(44)));
    }
}

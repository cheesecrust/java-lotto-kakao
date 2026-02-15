import controller.LottoController;
import domains.LottoGenerator;

public class Main {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new LottoGenerator());
        lottoController.run();
    }
}

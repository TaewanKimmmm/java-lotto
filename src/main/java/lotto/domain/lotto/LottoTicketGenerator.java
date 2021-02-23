package lotto.domain.lotto;

import java.util.ArrayList;

public class LottoTicketGenerator {
    private static final String ERROR_LOTTO_MONEY_NOT_ENOUGH = "[ERROR] 돈이 부족합니다.";
    protected static final int PRICE_EACH_LOTTO = 1000;

    private static LottoTicketGenerator instance;
    private final RandomLottoGenerator randomLottoGenerator;

    private LottoTicketGenerator() {
        randomLottoGenerator = new RandomLottoGenerator();
    }

    public static LottoTicketGenerator getInstance() {
        if (instance == null) {
            instance = new LottoTicketGenerator();
        }

        return instance;
    }


    public LottoTicket createLottoTicket(int money) {
        int numberOfLottoLine = getNumberOfLottoLine(money);

        ArrayList<LottoLine> lottoLineList = new ArrayList<LottoLine>();
        for (int i = 0; i < numberOfLottoLine; i++) {
            lottoLineList.add(new LottoLine(randomLottoGenerator.createLottoLine()));
        }
        return new LottoTicket(lottoLineList);
    }

    private int getNumberOfLottoLine(int money) {
        int numberOfLottoLine = money / PRICE_EACH_LOTTO;
        if (numberOfLottoLine <= 0) {
            throw new IllegalArgumentException(ERROR_LOTTO_MONEY_NOT_ENOUGH);
        }
        return numberOfLottoLine;
    }
}

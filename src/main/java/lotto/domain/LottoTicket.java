package lotto.domain;

import java.util.*;

public class LottoTicket {
    public static final int SIZE = 6;

    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateNotDuplicate(lottoNumbers);
        lottoNumbers.sort(LottoNumber::compareTo);
        this.numbers = new ArrayList<>(lottoNumbers);
    }

    public List<LottoNumber> getLottoTicketNumbers() {
        return Collections.unmodifiableList(this.numbers);
    }

    public int compare(LottoTicket lottoTicket) {
        return (int) this.numbers.stream().filter(lottoTicket::hasNumber).count();
    }

    public boolean hasNumber(LottoNumber number) {
        return this.numbers.contains(number);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != SIZE) {
            throw new IllegalArgumentException("로또 번호 개수는 6개여야 합니다.");
        }
    }

    private void validateNotDuplicate(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> lottoNumbersWithoutDuplication = new HashSet<>(lottoNumbers);
        if (lottoNumbersWithoutDuplication.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }
}

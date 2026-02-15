package controller;

public class LottoCount {
    private final int manualCount;
    private final int autoCount;

    public LottoCount(int manualCount, int totalCount) {
        validate(manualCount, totalCount);
        this.manualCount = manualCount;
        this.autoCount = totalCount - manualCount;
    }

    private void validate(int manualCount, int totalCount) {
        if (manualCount < 0) {
            throw new IllegalArgumentException("수동 구매 수량은 음수일 수 없습니다.");
        }
        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동 구매 수량이 구매 가능 수량을 초과합니다.");
        }
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }
}

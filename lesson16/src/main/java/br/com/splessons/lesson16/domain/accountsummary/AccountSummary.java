package br.com.splessons.lesson16.domain.accountsummary;

import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class AccountSummary {

    @NonNull private String id;
    @NonNull private double initialBalance;
    @NonNull private double remainingBalance;

    private void changeBalance(double value) {
        this.remainingBalance += value;
    }

    public void increaseValueBalance(double value) {
        this.changeBalance(value);
    }

    public void deductValueBalance(double value) {
        this.changeBalance(-value);
    }

}

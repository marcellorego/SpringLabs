package br.com.splessons.lesson16.domain.accountsummary;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FetchAccountSummariesQuery {

    @NonNull private Integer size;
    @NonNull private Integer offset;
}

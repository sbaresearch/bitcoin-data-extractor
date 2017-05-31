package thesis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * THis class is used to ignore the transaction list in blocks, if a second request is required
 */
public abstract class BlockDtoTxIgnore{

    @JsonIgnore
    @JsonProperty("tx")
    abstract void setTransactions(List<NMCTransactionDto> transactions);

    @JsonIgnore
    @JsonProperty("tx")
    abstract List<NMCTransactionDto> getTransactions();
}

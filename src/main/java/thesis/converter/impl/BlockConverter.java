package thesis.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import thesis.converter.Converter;
import thesis.dto.BlockDto;
import thesis.dto.NMCTransactionDto;
import thesis.model.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlockConverter extends Converter<BlockDto, Block> {

    @Autowired
    private AuxpowConverter auxpowConverter;

    @Autowired
    private TransactionConverter transactionConverter;

    @Value("${chain.lightRun}")
    private boolean lightRun;

    @Override
    protected BlockDto newDto() {
        return new BlockDto();
    }

    @Override
    protected Block newEntity() {
        return new Block();
    }

    @Override
    public Block toEntity(BlockDto dto){
        Block entity = newEntity();

        this.copyProperties(dto, entity);

        List<Transaction> transactions = new ArrayList<>();
        if(dto.getTransactions() != null){
            for(NMCTransactionDto nmcTransactionDto : dto.getTransactions()){

                if(lightRun && !isCoinbase(nmcTransactionDto)) continue;

                Transaction transaction = transactionConverter.toEntity(nmcTransactionDto);

                transaction.setBitcoinBlockHash(entity.getHash());
                transaction.setBlocktime(entity.getTime());
                transaction.setTime(entity.getTime());
                transaction.setConfirmations(entity.getConfirmations());
                transaction.setBlock(entity);
                transactions.add(transaction);
                setBlockheight(transaction, entity.getHeight());
            }

            entity.setTransactions(transactions);
        }

        if(dto.getAuxpow() != null){
            Auxpow auxpow = auxpowConverter.toEntity(dto.getAuxpow());
            auxpow.setBlock(entity);
            entity.setAuxpow(auxpow);

            for(AuxpowVout auxpowVout : auxpow.getOutputs()){
                auxpowVout.setBlockheight(entity.getHeight());
            }
        }

        return entity;
    }


    private boolean isCoinbase(NMCTransactionDto nmcTransactionDto){
        return nmcTransactionDto.getInputs().size() == 1 && nmcTransactionDto.getInputs().get(0).getCoinbase() != null;
    }


    private void setBlockheight(Transaction transaction, int blockheight){

        transaction.setBlockheight(blockheight);
        for(Vin vin : transaction.getInputs()){
            vin.setBlockheight(blockheight);
        }

        for(Vout vout : transaction.getOutputs()) {
            vout.setBlockheight(blockheight);
        }
    }
}

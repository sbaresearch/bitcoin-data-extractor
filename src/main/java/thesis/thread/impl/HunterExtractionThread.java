package thesis.thread.impl;

import com.codahale.metrics.Timer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import thesis.dto.BlockDto;
import thesis.dto.NMCTransactionDto;
import thesis.exception.ServiceException;
import thesis.model.Block;
import thesis.model.Transaction;

import java.util.Arrays;
import java.util.List;

@Profile("hunter")
@Component
public class HunterExtractionThread extends AbstractExtractionThread {

}

package thesis.http.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import thesis.converter.impl.TransactionConverter;
import thesis.dto.NMCTransactionDto;
import thesis.exception.ServiceException;
import thesis.http.TransactionRequestService;
import thesis.model.Transaction;

@Service
public class TransactionRequestServiceImpl implements TransactionRequestService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RestTemplate restTemplate;

    @Autowired
    private TransactionConverter converter;

    @Value("${rest.url}")
    private String url;

    public TransactionRequestServiceImpl() {
        this.restTemplate = new RestTemplate();
    }


    @Override
    public Transaction getTransaction(String txid) throws ServiceException {

        String restUrl = url + "/tx/" + txid + ".json";

        NMCTransactionDto nmcTransactionDto = restTemplate.getForObject(restUrl, NMCTransactionDto.class);

        return converter.toEntity(nmcTransactionDto);
    }
}

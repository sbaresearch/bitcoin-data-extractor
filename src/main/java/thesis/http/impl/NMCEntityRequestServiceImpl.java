package thesis.http.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import thesis.converter.impl.NMCEntityConverter;
import thesis.dto.NMCEntityDto;
import thesis.exception.NotFoundException;
import thesis.exception.ServiceException;
import thesis.http.NMCEntityRequestService;
import thesis.model.NMCEntity;

@Profile({"nmc", "ncsq"})
@Service
public class NMCEntityRequestServiceImpl implements NMCEntityRequestService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RestTemplate restTemplate;

    @Autowired
    private NMCEntityConverter converter;

    @Value("${rest.url}")
    private String url;

    public NMCEntityRequestServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public NMCEntity getName(String name) throws ServiceException {

        String restUrl = url + "/name/" + name + ".json";

        NMCEntityDto nmcEntityDto;
        try {
            nmcEntityDto = restTemplate.getForObject(restUrl, NMCEntityDto.class);
        }catch(HttpClientErrorException e){
            if(e.getStatusCode().value() == 404){
                throw new NotFoundException("Could not retrieve " + name + " from client");
            }else{
                throw new ServiceException(e);
            }
        }

        logger.debug(restUrl);

        return converter.toEntity(nmcEntityDto);
    }

}

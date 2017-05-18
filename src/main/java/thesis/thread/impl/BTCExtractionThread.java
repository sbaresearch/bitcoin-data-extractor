package thesis.thread.impl;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import thesis.dto.BlockDto;
import thesis.exception.NotFoundException;
import thesis.exception.ServiceException;
import thesis.http.BlockRequestService;
import thesis.http.NMCEntityRequestService;
import thesis.http.TransactionRequestService;
import thesis.model.*;
import thesis.service.BlockService;
import thesis.service.MetainfoService;
import thesis.service.SendMailService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Profile("btc")
@Component
public class BTCExtractionThread extends AbstractExtractionThread {
}

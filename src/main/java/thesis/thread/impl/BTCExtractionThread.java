package thesis.thread.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("btc")
@Component
public class BTCExtractionThread extends AbstractExtractionThread {
}

package thesis.thread.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import thesis.thread.impl.AbstractExtractionThread;

@Profile("myriad")
@Component
public class MyriadExtractionThread extends AbstractExtractionThread {
}

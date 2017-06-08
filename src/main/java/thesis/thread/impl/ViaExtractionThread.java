package thesis.thread.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("via")
@Component
public class ViaExtractionThread extends AbstractExtractionThread {
}

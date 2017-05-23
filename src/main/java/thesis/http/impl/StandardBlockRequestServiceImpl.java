package thesis.http.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"btc", "nmc", "ncsq"})
@Service
public class StandardBlockRequestServiceImpl extends AbstractBlockRequestServiceImpl {
}

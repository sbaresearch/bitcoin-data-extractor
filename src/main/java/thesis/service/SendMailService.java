package thesis.service;

import thesis.exception.ServiceException;

public interface SendMailService {

    /**
     * Sends an email to the address specified in the application properties
     * @param message
     */
    void sendMail(String message) throws ServiceException;
}

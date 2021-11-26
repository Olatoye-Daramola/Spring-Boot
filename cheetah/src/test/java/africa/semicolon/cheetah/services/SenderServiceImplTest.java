package africa.semicolon.cheetah.services;

import africa.semicolon.cheetah.dtos.requests.RegisterSenderRequest;
import africa.semicolon.cheetah.dtos.responses.RegisterSenderResponse;
import africa.semicolon.cheetah.exceptions.DuplicateUserException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SenderServiceImplTest {
    SenderService senderService;

    @BeforeEach
    void setUp() {
        senderService = new SenderServiceImpl();
    }

    @AfterEach
    void tearDown() {
        senderService.deleteAllSenders();
    }

    public RegisterSenderResponse registerSenderResponse() {
        RegisterSenderRequest registerSenderRequest = new RegisterSenderRequest();
        registerSenderRequest.setSenderName("Jerry");
        registerSenderRequest.setSenderEmail("jerry@email.com");
        registerSenderRequest.setSenderPhone("123456");

        return senderService.registerSenderResponse(registerSenderRequest);
    }

    @Test
    void registerSender() {
        RegisterSenderResponse response = registerSenderResponse();
//        assertEquals(response.getSenderEmail(), registerSenderRequest.getSenderEmail());
        assertEquals(1, senderService.getSenders().size());
    }

    @Test
    void duplicateEmail_throwsException() {
        registerSenderResponse();
        assertThrows(DuplicateUserException.class, this::registerSenderResponse);
    }
}
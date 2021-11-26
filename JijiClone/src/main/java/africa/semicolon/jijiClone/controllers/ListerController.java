package africa.semicolon.jijiClone.controllers;

import africa.semicolon.jijiClone.dtos.requests.RegisterListerRequest;
import africa.semicolon.jijiClone.exceptions.JijiCloneAppException;
import africa.semicolon.jijiClone.services.ListerService;
import africa.semicolon.jijiClone.services.ListerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lister")
public class ListerController {

    @Autowired
    private ListerService listerService = new ListerServiceImpl();

    @PostMapping("/")
    public ResponseEntity<?> register(@RequestBody RegisterListerRequest request) {
        try {
            return new ResponseEntity<>(listerService.registerLister(request), HttpStatus.CREATED);
        }
        catch (JijiCloneAppException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

package africa.semicolon.jijiClone.services;

import africa.semicolon.jijiClone.data.models.Lister;
import africa.semicolon.jijiClone.data.repositories.ListerRepository;
import africa.semicolon.jijiClone.dtos.requests.RegisterListerRequest;
import africa.semicolon.jijiClone.dtos.responses.RegisterListerResponse;
import africa.semicolon.jijiClone.exceptions.DuplicateEmailException;
import africa.semicolon.jijiClone.utils.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Component
@Service
public class ListerServiceImpl implements ListerService {

    @Autowired
    private ListerRepository listerRepository;

    @Override
    public RegisterListerResponse registerLister(RegisterListerRequest request) {
        Optional<Lister> optionalLister = listerRepository.findListerByEmail(request.getEmail());
        if (optionalLister.isPresent()) throw new DuplicateEmailException(request.getEmail() + " already exists");

        Lister lister = ModelMapper.map(request);

        Lister savedLister = listerRepository.save(lister);
        //validate user does not exist before
                //find lister by email, if null
                        //create lister from request
                        //save lister
                        //create response from lister
                        //return response
                //else throw exception
        return ModelMapper.map(savedLister);
    }
}

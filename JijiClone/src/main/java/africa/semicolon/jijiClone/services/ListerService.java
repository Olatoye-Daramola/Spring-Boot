package africa.semicolon.jijiClone.services;

import africa.semicolon.jijiClone.dtos.requests.RegisterListerRequest;
import africa.semicolon.jijiClone.dtos.responses.RegisterListerResponse;

public interface ListerService {
    RegisterListerResponse registerLister(RegisterListerRequest request);
}

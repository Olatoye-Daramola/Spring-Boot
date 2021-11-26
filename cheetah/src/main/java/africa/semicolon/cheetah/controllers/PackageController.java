package africa.semicolon.cheetah.controllers;

import africa.semicolon.cheetah.data.models.Package;
import africa.semicolon.cheetah.dtos.requests.AddPackageRequest;
import africa.semicolon.cheetah.dtos.responses.AddPackageResponse;
import africa.semicolon.cheetah.exceptions.UserNotFoundException;
import africa.semicolon.cheetah.services.PackageService;
import africa.semicolon.cheetah.services.PackageServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PackageController {
    private final PackageService packageService = new PackageServiceImpl();

    @PostMapping("/api/addPackage")
    public ResponseEntity<?> addPackage(@RequestBody AddPackageRequest addPackageRequest) {
        try {
            AddPackageResponse response = packageService.addPackage(addPackageRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (UserNotFoundException es) {
            return new ResponseEntity<>(es.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/package/{trackingNumber}")
    public Package findPackageByTrackingNumber(@PathVariable("trackingNumber") Integer trackingNumber) {
        return packageService.findMyPackageWithMy(trackingNumber);
    }
}
